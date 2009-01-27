package jus.aoo.turtle;

import jus.util.geometrie.*;
import jus.aoo.geometrie.*;

public class NewObstacle extends NewFigure {

    /** les points caract�risant l'obstacle */
    protected Point p1,  p2;
    /** la figure correspondant � la situation courante */
    protected Figure p;
    /** l'�tat de construction de la figure finale */
    protected int etat = 0;
    /** la construction est-elle finalis�e */
    protected boolean complete = false;

    /** Ajout d'un point non d�finitif
     * @param p le point
     */
    public void changePoint(Point p) {
        if (etat == 1) {
            point2(p);
        }
    }

    /** Ajout d'un point d�finitif
     * @param p le point
     */
    public void newPoint(Point p) {
        if (etat == 0) {
            point1(p);
        } else if (etat == 1) {
            complete = true;
            this.p = new Obstacle(p1, p2);
        }
        etat++;
    }

    /** la figure courante
     * @return la figure courante compatible � la situation
     */
    public Figure newFigure() {
        return p;
    }

    /** la construction est-elle finalis�e
     * @return vrai si fini faux sinon
     */
    public boolean isComplete() {
        return complete;
    }

    /** acquistion du premier point
     * @param p le point
     */
    protected void point1(Point p) {
        p1 = new Point(p);
        this.p = new Segment(p1, p1);
    }

    /** acquistion du second point
     * @param p le point
     */
    protected void point2(Point p) {
        p2 = new Point(p);
        this.p = new Segment(p1, p2);
    }
}
