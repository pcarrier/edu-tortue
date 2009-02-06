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
    public Turtle(DrawingSpace feuille) {
        this.feuille = feuille;
        try {
            position = new Point(Point.CARTESIEN, feuille.getWidth() / 2, feuille.getHeight() / 2);
        } catch (Require e) {
        }
        cap = new Vecteur(Vecteur.UNITE);
        estLeve = true;
        image = new Image(position, imageFile, feuille);
        feuille.addPermanent(image);
    }

    public Turtle(DrawingSpace feuille, Environnement env) {
        this(feuille);
        this.env = env;
    }

    public Environnement getEnvironnement() {
        return this.env;
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
        Point _position = new Point(position);
        Vecteur v = new Vecteur(cap);
        v.homothetie(d);
        position.translation(v);
        if (position.abscisse() < 0 ||
                position.ordonnee() < 0 ||
                position.abscisse() > feuille.getWidth() ||
                position.ordonnee() > feuille.getHeight()) {
            position = _position;
            throw new Exception("Out of screen move!");
        } else {
            int i;
            boolean collapse = false;
            Obstacle obs;
            Point segp1, segp2;

            segp1=new Point();
            segp2=new Point();
            
            for (i = 0; i < this.env.getObstacles().size(); i++) {
                if (this.env.hasCollision((Obstacle) this.env.getObstacles().get(i), _position, position)) {
                    collapse = true;
                    obs=(Obstacle) this.env.getObstacles().get(i);
                    segp1=this.env.getSegCollapse(obs, position, _position)[0];
                    segp2=this.env.getSegCollapse(obs, position, _position)[1];
                    break;
                }
            }

            if (!collapse) {//On avance
                image.translation(v);
                if (!estLeve) {
                    feuille.add(new Segment(_position, position));
                } else {
                    feuille.repaint();
                }
            } else {//On trouve le point le plus proche qu'on peut atteindre
                //position = _position;

                position=this.env.getCollapsePoint(_position, position, segp1,segp2 );
                Vecteur nv = new Vecteur(_position, position);
                throw new Exception("Un obstacle est sur votre route.");
            }
        }

        System.out.println("Nombre d'obstacles : " + this.env.getNbObstacles());
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
        for (int it = 0; it < env.getObstacles().size(); ++it) {
            Obstacle obs = (Obstacle) env.getObstacles().get(it);
            if (obs.cadreElargit().contains(nx,ny)) {
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