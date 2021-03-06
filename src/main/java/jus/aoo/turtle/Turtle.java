package jus.aoo.turtle;

import com.sun.corba.se.spi.oa.OADefault;
import java.util.Iterator;
import jus.aoo.geometrie.DrawingSpace;
import jus.aoo.geometrie.Image;
import jus.aoo.geometrie.Segment;
import jus.util.geometrie.*;
import jus.util.assertion.*;

/**
 * R�alise les fonctions d'une tortue du mod�le Logo
 * @author	P.Morat ou http://imag.fr/Philippe.Morat ...
 * @version	1.0
 * date : 1/9/99
 * @invariant Coh�rent : Math.abs(Cap().module()-1) < Vecteur.EPSILON
 * @motcle tortue, Logo, trac�, dessin
 * @see <a href="Turtle.java">Turtle</a>
 */
public class Turtle {

    protected Environnement env = new Environnement(); //Environnement des instances de tortues (obstacles, ...)
    protected DrawingSpace feuille; // l'espace de d�placement de la tortue
    protected static final String imageFile = "/jus/aoo/turtle/Turtle.gif"; //le nom de l'image mat�rialisant la tortue.
    protected Image image;			//l'image de la tortue
    protected boolean estLeve;  // �tat de la plume
    protected Point position;   // la position courante de la tortue
    protected Vecteur cap;      // direction courante de la tortue

    /**
     * le constructeur de tortue
     * @param feuille la surface explor�e
     * @require fmtOk : feuille!=null
     */
    public Turtle(DrawingSpace feuille, int num, Environnement env) {
        this.feuille = feuille;
        try {
            position = new Point(Point.CARTESIEN, feuille.getWidth() / 2, feuille.getHeight() / 2);
        } catch (Require e) {
        }
        cap = new Vecteur(Vecteur.UNITE);
        estLeve = true;
        num = 1 + ((num - 1) % 15);
        image = new Image(position, "/jus/aoo/turtle/data/tortue" + num + ".png", feuille);
        feuille.addPermanent(image);
        this.env = env;
    }

    public Environnement getEnvironnement() {
        return env;
    }

    /**
     * Fait avancer la tortue de d pas
     * @param d la distance � parcourir
     * @require argumentValide : d>=0
     * @ensure CapInchange : _cap().equals(cap())
     * @ensure DeplacementOk : new Vecteur(_position(),position()).module()-d < Vecteur.EPSILON
     * @ensure capOk : new Vecteur(_position(),position()).colineaire(cap())
     */
    public void avancer(int d) throws Exception {
        Point ancienne_position = new Point(position);
        Point nouvelle_position = new Point(position);
        Vecteur v = new Vecteur(cap);
        v.homothetie(d);
        nouvelle_position.translation(v);
        if (nouvelle_position.abscisse() < 0 ||
                nouvelle_position.ordonnee() < 0 ||
                nouvelle_position.abscisse() > feuille.getWidth() ||
                nouvelle_position.ordonnee() > feuille.getHeight()) {
            throw new Exception("Out of screen move!");
        }

        for (int it = 0; it < env.getObstacles().size(); ++it) {
            if (env.hasCollision((Obstacle) env.getObstacles().get(it),
                    ancienne_position, nouvelle_position)) {
                Obstacle obs = (Obstacle) env.getObstacles().get(it);
                Point position_intermediaire = env.getCollapsePoint(
                        ancienne_position, nouvelle_position,
                        env.getSegCollapse(obs, nouvelle_position, ancienne_position)[0],
                        env.getSegCollapse(obs, nouvelle_position, ancienne_position)[1]);
                Vecteur nv = new Vecteur(ancienne_position, position_intermediaire);
                avancer((d > 0 ? 1 : -1) * ((int) nv.module() - 1));
                return;
            }
        }

        position = nouvelle_position;
        image.translation(v);
        if (!estLeve) {
            feuille.add(new Segment(ancienne_position, nouvelle_position));
        } else {
            feuille.repaint();
        }
    }

    /**
     * Fait reculer la tortue de d pas
     * @param d la distance � parcourir
     * @require argumentValide : d>=0
     * @ensure CapInchange : _cap().equals(cap())
     * @ensure DeplacementOk : new Vecteur(_position(),position()).module()-d < Vecteur.EPSILON
     * @ensure capOk : new Vecteur(_position(),position()).colineaire(cap())
     */
    public void reculer(int d) throws Exception {
        avancer(-d);
    }

    public void allerA(int x, int y) throws Exception {
        Point ancienne_position = new Point(position);
        int nx = (int) (x * feuille.getWidth() / 100.0);
        int ny = (int) (y * feuille.getHeight() / 100.0);
        Point future_position = new Point(Point.CARTESIEN, nx, ny);
        for (int it = 0; it <
                env.getObstacles().size(); ++it) {
            Obstacle obs = (Obstacle) env.getObstacles().get(it);
            if (obs.cadreElargi().contains(nx, ny)) {
                throw new Exception("Obstacle à cette position !");
            }

        }
        Vecteur v = new Vecteur(ancienne_position, future_position);
        position.translation(v);
        image.translation(v);
        if (!estLeve) {
            feuille.add(new Segment(ancienne_position, future_position));
        } else {
            feuille.repaint();
        }

    }

    /**
     * Fait tourner la � droite d'un angle a
     * @param a l'angle de rotation en degr�
     * @require argumentValide : a>=0
     * @ensure PositionInchange : new Vecteur(_position(),position()).module() < Vecteur.EPSILON
     */
    public void droite(int a) {
        cap.rotation((double) a);
        image.rotation(a);
        feuille.repaint();
    }

    /**
     * Fait tourner la tortue � gauche d'un angle a
     * @param a l'angle de rotation en degr�
     * @require argumentValide : a>=0
     * @ensure PositionInchange : new Vecteur(_position(),position()).module() < Vecteur.EPSILON
     */
    public void gauche(int a) {
        droite(-a);
    }

    public void tournerVers(int a) {
        System.out.println(cap.argument());
        double r = (double) a - Math.toDegrees(cap.argument());
        cap.rotation(r);
        image.rotation(r);
        feuille.repaint();
    }

    /**
     * L�ve la plume
     * @ensure PlumeLev�e : estLeve()
     */
    public void lever() {
        estLeve = true;
    }

    /**
     * Baisse la plume
     * @ensure PlumeLev�e : !estLeve()
     */
    public void baisser() {
        estLeve = false;
    }

    /**
     * Restitue la repr�sentation textuelle de la tortue
     * @return	la chaine
     */
    @Override
    public String toString() {
        return "Tortue[" + position + "," + cap + "]";
    }

    /**
     * Restitue la position de la tortue
     * @return	le Point de r�f�rence de la tortue
     */
    public Point position() {
        return new Point(position);
    }

    /**
     * Restitue le cap de la tortue
     * @return	le Vecteur de r�f�rence de la tortue
     */
    public Vecteur cap() {
        return new Vecteur(cap);
    }

    /**
     * Restitue l'�tat de la plume
     * @return	true si la plume est lev�e
     */
    public boolean estLeve() {
        return estLeve;
    }
}
