/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jus.aoo.turtle;

import java.util.ArrayList;
import javax.swing.text.Segment;
import jus.aoo.geometrie.Figure;
import jus.util.geometrie.Point;
import jus.util.geometrie.Vecteur;

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

    public boolean hasCollision(Point p1, Point p2) {
    int i;
    boolean ret=false;
    for (i=0; i<this.obstacles.size();i++){
        if(this._hasCollision(p1, p2, this.obstacles.get(i))){
            ret=true;
        }
        System.out.println("Collision sobject "+i+" : "+ret);
        
    }

    return ret;
    }

    private boolean _hasCollision(Point p1, Point p2, Obstacle obs) {
        Point p3 = obs.sommet(0);
        Point p4 = obs.sommet(1);


        double uaNominator = (p4.abscisse() - p3.abscisse()) * (p1.ordonnee() - p3.ordonnee()) - (p4.ordonnee() - p3.ordonnee()) * (p1.abscisse() - p3.abscisse());
        double ubNominator = (p2.abscisse() - p1.abscisse()) *
                (p1.ordonnee() - p3.ordonnee()) - (p2.ordonnee() - p1.ordonnee()) * (p1.abscisse() - p3.abscisse());


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

            if (checkUxRange(ub) && checkUxRange(ua)) {

                double x=p1.abscisse()+ua*(p2.abscisse()-p1.abscisse());
                double y=p1.ordonnee()+ua*(p2.ordonnee()-p1.ordonnee());
                if(obs.cadre().contains(x,y)){
                    System.out.println("Coolision!");
                    return true;
                }

            }
        }
        return false;
    }
    
    private boolean checkUxRange(double ux) {

        if ((ux >= 0) && (ux <= 1)) {
            return true;
        }
        return false;
    }
}
