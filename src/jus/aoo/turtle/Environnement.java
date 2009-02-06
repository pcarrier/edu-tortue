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

    public static int nbMaxObstacle = 2;
    private ArrayList<Obstacle> obstacles;

    public Environnement() {
        this.obstacles = new ArrayList<Obstacle>(10);
    }

    /*
     * @require NbMaxObstaclesNonAteint : this.obstacles.size() < Environnement.nbMaxObstacle
     */
    public void addObstacle(Obstacle ob) throws Exception {

        if (this.obstacles.size() < nbMaxObstacle) {
            this.obstacles.add(ob);
        } else {
            throw new Exception("Nombre max d'obstacle atteint (" + nbMaxObstacle + ")");
        }
    }

    public int getNbObstacles() {
        return this.obstacles.size();
    }

    public ArrayList getObstacles() {
        return this.obstacles;
    }

    public boolean hasCollision(Obstacle obs,Point p1, Point p2) {
    int i;
    boolean ret=false;
    
        if(this.mayCollapse(p1, p2, obs.sommetElargit(0),obs.sommetElargit(1))){
            if (this._hasCollision(obs, p2)){
                ret=true;
            }
    }

    return ret;
    }

    void getCoordonneeMax(Object obs, int d) {
       
    }

    // Can the line from p1 to p2 cross p3 to p4 ?
    private boolean mayCollapse(Point p1, Point p2, Point p3,Point p4) {
        double uaNominator = (p4.abscisse() - p3.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p4.ordonnee() - p3.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double ubNominator = (p2.abscisse() - p1.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p2.ordonnee() - p1.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double denominator = ((p4.ordonnee() - p3.ordonnee()) * (p2.abscisse() - p1.abscisse())) - ((p4.abscisse() - p3.abscisse()) * (p2.ordonnee() - p1.ordonnee()));
        double ua = uaNominator / denominator;
        double ub = uaNominator / denominator;

        if (denominator != 0) //not parallels
        {
            System.out.println("NextPos x :" + p2.abscisse());
            System.out.println("NextPos y :" + p2.ordonnee());

            System.out.println("uaN :" + uaNominator);
            System.out.println("ubN :" + ubNominator);

            System.out.println("ua :" + uaNominator / denominator);
            System.out.println("ub :" + ubNominator / denominator);
            System.out.println("denominator :" + denominator);

            return (checkUxRange(ub) && checkUxRange(ua));
        }
        return false;
    }

    //Is the point in the large cadre ?
    private boolean _hasCollision(Obstacle obs, Point p){
        return obs.cadreElargit().contains(p.abscisse(), p.ordonnee());
    }
    
    private boolean checkUxRange(double ux) {

        if ((ux >= 0) && (ux <= 1)) {
            return true;
        }
        return false;
    }
}
