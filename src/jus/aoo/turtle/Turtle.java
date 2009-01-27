package jus.aoo.turtle;

import java.awt.geom.AffineTransform;

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
            position = new Point(Point.CARTESIEN, 0, 0);
        } catch (Require e) {
        }
        cap = new Vecteur(Vecteur.UNITE);
        estLeve = true;
        image = new Image(position, imageFile, feuille);
        /* On r�alise des transformations sur le support de dessin pour avoir le zero au centre de l'espace
         * l'orientation positive des ordonn�es vers le haut et le cap zero  � droite.
         * On maintient cependant la rotation dans le sens anti-horaire */
        feuille.addAffineTransformation(AffineTransform.getTranslateInstance(feuille.getWidth() / 2, feuille.getHeight() / 2));
        feuille.addAffineTransformation(AffineTransform.getScaleInstance(1, -1));
        feuille.addPermanent(image);
    }

    /**
     * Fait avancer la tortue de d pas
     * @param d la distance � parcourir
     * @require argumentValide : d>=0
     * @ensure CapInchange : _cap().equals(cap())
     * @ensure DeplacementOk : new Vecteur(_position(),position()).module()-d < Vecteur.EPSILON
     * @ensure capOk : new Vecteur(_position(),position()).colineaire(cap())
     */
    public void avancer(int d) {
        Point _position = new Point(position);
        Vecteur v = new Vecteur(cap);
        v.homothetie(d);
        position.translation(v);
        image.translation(v);
        if (!estLeve) {
            feuille.add(new Segment(_position, position));
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
    public void reculer(int d) {
        Point _position = new Point(position);
        Vecteur v = cap.oppose();
        v.homothetie(d);
        position.translation(v);
        image.translation(v);
        if (!estLeve) {
            feuille.add(new Segment(_position, position));
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
        cap.rotation((double) -a);
        image.rotation(-a);
        feuille.repaint();
    }

    /**
     * Fait tourner la tortue � gauche d'un angle a
     * @param a l'angle de rotation en degr�
     * @require argumentValide : a>=0
     * @ensure PositionInchange : new Vecteur(_position(),position()).module() < Vecteur.EPSILON
     */
    public void gauche(int a) {
        cap.rotation((double) a);
        image.rotation(a);
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