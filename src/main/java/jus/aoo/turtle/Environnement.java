/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aoo.turtle;

import java.util.ArrayList;
import jus.util.geometrie.Point;

/**
 *
 * @author jc
 */
public class Environnement {

    public static int nbMaxObstacle = 10;
    private ArrayList<Obstacle> obstacles;

    public Environnement() {
        obstacles = new ArrayList<Obstacle>(10);
    }

    /*
     * @require NbMaxObstaclesNonAteint : obstacles.size() < Environnement.nbMaxObstacle
     */
    public void addObstacle(Obstacle ob) throws Exception {

        if (obstacles.size() < nbMaxObstacle) {
            obstacles.add(ob);
        } else {
            throw new Exception("Nombre max d'obstacles atteint (" + nbMaxObstacle + ")");
        }
    }

    public int getNbObstacles() {
        return obstacles.size();
    }

    public ArrayList getObstacles() {
        return obstacles;
    }

    public boolean hasCollision(Obstacle obs, Point p1, Point p2) {
        int i;
        boolean ret = false;

        if (mayCollapse(p1, p2, obs.sommetElargi(0), obs.sommetElargi(1)) ||
                mayCollapse(p1, p2, obs.sommetElargi(1), obs.sommetElargi(2)) ||
                mayCollapse(p1, p2, obs.sommetElargi(2), obs.sommetElargi(3)) ||
                mayCollapse(p1, p2, obs.sommetElargi(3), obs.sommetElargi(0))) {
            if (_hasCollision(obs, p2)) {
                ret = true;
            }
        }

        return ret;
    }

    public Point[] getSegCollapse(Obstacle obs, Point p1, Point p2) {

        Point[] ret = new Point[2];

        if (mayCollapse(p1, p2, obs.sommetElargi(0), obs.sommetElargi(1))) {
            ret[0] = obs.sommetElargi(0);
            ret[1] = obs.sommetElargi(1);
        } else if (mayCollapse(p1, p2, obs.sommetElargi(1), obs.sommetElargi(2))) {
            ret[0] = obs.sommetElargi(1);
            ret[1] = obs.sommetElargi(2);
        } else if (mayCollapse(p1, p2, obs.sommetElargi(2), obs.sommetElargi(3))) {
            ret[0] = obs.sommetElargi(2);
            ret[1] = obs.sommetElargi(3);
        } else if (mayCollapse(p1, p2, obs.sommetElargi(0), obs.sommetElargi(3))) {
            ret[0] = obs.sommetElargi(0);
            ret[1] = obs.sommetElargi(3);
        }
        return ret;
    }

    public Point getCollapsePoint(Point p1, Point p2, Point p3, Point p4) {
        double uaNominator = (p4.abscisse() - p3.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p4.ordonnee() - p3.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double ubNominator = (p2.abscisse() - p1.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p2.ordonnee() - p1.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double denominator = ((p4.ordonnee() - p3.ordonnee()) * (p2.abscisse() - p1.abscisse())) - ((p4.abscisse() - p3.abscisse()) * (p2.ordonnee() - p1.ordonnee()));
        double ua = uaNominator / denominator;
        double ub = uaNominator / denominator;

        double x = p1.abscisse() + ua * (p2.abscisse() - p1.abscisse());
        double y = p1.ordonnee() + ua * (p2.ordonnee() - p1.ordonnee());

        return new Point(Point.CARTESIEN, (int) x, (int) y);
    }

// Can the line from p1 to p2 cross p3 to p4 ?
    private boolean mayCollapse(Point p1, Point p2, Point p3, Point p4) {
        double uaNominator = (p4.abscisse() - p3.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p4.ordonnee() - p3.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double ubNominator = (p2.abscisse() - p1.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p2.ordonnee() - p1.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double denominator = ((p4.ordonnee() - p3.ordonnee()) * (p2.abscisse() - p1.abscisse())) - ((p4.abscisse() - p3.abscisse()) * (p2.ordonnee() - p1.ordonnee()));
        double ua = uaNominator / denominator;
        double ub = uaNominator / denominator;

        if (denominator != 0) //not parallels
        {
            /*
            System.out.println("NextPos x :" + p2.abscisse());
            System.out.println("NextPos y :" + p2.ordonnee());

            System.out.println("uaN :" + uaNominator);
            System.out.println("ubN :" + ubNominator);

            System.out.println("ua :" + uaNominator / denominator);
            System.out.println("ub :" + ubNominator / denominator);
            System.out.println("denominator :" + denominator);
             */
            return (checkUxRange(ub) && checkUxRange(ua));
        }

        return false;
    }

//Is the point in the large cadre ?
    private boolean _hasCollision(Obstacle obs, Point p) {
        return obs.cadreElargi().contains(p.abscisse(), p.ordonnee());
    }

    private boolean checkUxRange(double ux) {

        if ((ux >= 0) && (ux <= 1)) {
            return true;
        }

        return false;
    }
}
