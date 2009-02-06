package jus.aoo.turtle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import jus.aoo.geometrie.Figure;
import jus.aoo.geometrie.Segment;
import jus.util.geometrie.*;

/** 
 * Mod�lise la notion d'obstacle pour la tortue.
 * Un obstacle est un rectangle(cadre) qui peut �tre plac� dans l'espace de dessin.
 * @author P.Morat ou http://imag.fr/Philippe.Morat
 * @version 1.0 date 1/1/06
 * @see <a href="Obstacle.java">Obstacle</a>
 */
public class Obstacle extends Figure {

    protected Point p1; // le sommet le plus petit du cadre
    protected int width,  height; // largeur  & hauteur du cadre

    /**
     * Construction d'un Obstacle
     * @param p1 l'extr�mit� d'une diagonale
     * @param p2 l'autre extr�mit� de la diagonale
     */
    public Obstacle(Point p1, Point p2) {
        System.out.println("Obstacle builded");
        this.p1 = new Point(Point.CARTESIEN, Math.min((int) p1.abscisse(), (int) p2.abscisse()), Math.min((int) p1.ordonnee(), (int) p2.ordonnee()));
        
        width = Math.max((int) p1.abscisse(), (int) p2.abscisse()) - Math.min((int) p1.abscisse(), (int) p2.abscisse());
        height = Math.max((int) p1.ordonnee(), (int) p2.ordonnee()) - Math.min((int) p1.ordonnee(), (int) p2.ordonnee());
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#barycentre()
     */

    @Override
    public Point barycentre() {
        Vecteur v = new Vecteur(Vecteur.CARTESIEN, width, height);
        v.homothetie(0.5);
        Point p = new Point(p1);
        p.translation(v);
        return p;
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#nbSommet()
     */

    @Override
    public int nbSommet() {
        return 4;
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#translation(jus.util.geometrie.Vecteur)
     */

    @Override
    public void translation(Vecteur v) {
        p1.translation(v);
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#rotation(double)
     */

    @Override
    public void rotation(double a) {
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#perimetre()
     */

    @Override
    public double perimetre() {
        return (width + height) * 2;
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#sommet(int)
     */

    @Override
    public Point sommet(int num) {
        switch (num) {
            case 0: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse(), (int) p1.ordonnee());
            }
            case 1: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse() + width, (int) p1.ordonnee());
            }
            case 2: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse() + width, (int) p1.ordonnee() + height);
            }
            default: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse(), (int) p1.ordonnee() + height);
            }
        }
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie.Figure#segments()
     */

    @Override
    public Iterator<Segment> segments() {
        return new Iterator<Segment>() {

            int r = 0;
            boolean b = true;

            public boolean hasNext() {
                return b;
            }

            public Segment next() {
                Point p1, p2;
                p1 = sommet(r);
                r = (r + 1) % 4;
                b = (r != 0);
                p2 = sommet(r);
                return new Segment(p1, p2);
            }

            public void remove() {
            }
        };
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie._Figure#draw(java.awt.Graphics)
     */

    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(Color.RED);
        java.awt.Rectangle cadre = cadre();
        g.fillRect(cadre.x, cadre.y, cadre.width, cadre.height);
        g.setColor(oldColor);
    }
    /* (non-Javadoc)
     * @see jus.aoo.geometrie._Figure#cadre()
     */

    public java.awt.Rectangle cadre() {
        return new java.awt.Rectangle((int) p1.abscisse(), (int) p1.ordonnee(), width, height);
    }

    public java.awt.Rectangle cadreElargit() {
        return new java.awt.Rectangle((int) p1.abscisse() -16, (int) p1.ordonnee()-16, width+16, height+16);
    }

    public Point sommetElargit(int num) {
        switch (num) {
            case 0: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse()-16, (int) p1.ordonnee()-16);
            }
            case 1: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse() + width+16, (int) p1.ordonnee()-16);
            }
            case 2: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse() + width +16, (int) p1.ordonnee() + height+16);
            }
            default: {
                return new Point(Point.CARTESIEN, (int) p1.abscisse()-16, (int) p1.ordonnee() + height+16);
            }
        }
    }
    
}