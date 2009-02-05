/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jus.aoo.turtle;

import java.util.ArrayList;


/**
 *
 * @author jc
 */
public class Environnement {

    public static int nbMaxObstacle=2;
    private ArrayList<Obstacle> obstacles;

    public Environnement(){
        this.obstacles= new ArrayList<Obstacle>(10);
    }

    /*
     * @require NbMaxObstaclesNonAteint : this.obstacles.size() < Environnement.nbMaxObstacle
     */
    public void addObstacle(Obstacle ob) throws Exception{

        if(this.obstacles.size()<nbMaxObstacle){
            this.obstacles.add(ob);
        }else{
            throw new Exception("Nombre max d'obstacle atteint ("+nbMaxObstacle+")");
        }
    }

    public int getNbObstacles(){
        return this.obstacles.size();
    }

    public ArrayList getObstacles(){
        return this.obstacles;
    }
}
