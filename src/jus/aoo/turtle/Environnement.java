/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jus.aoo.turtle;

import java.util.ArrayList;
import jus.util.assertion.Require;

/**
 *
 * @author jc
 */
public class Environnement {

    public static int nbMaxObstacle=10;
    private ArrayList<Obstacle> obstacles;

    public Environnement(){
        this.obstacles= new ArrayList<Obstacle>(10);
    }

    /*
     * @require NbMaxObstaclesNonAteint : this.obstacles.size() < Environnement.nbMaxObstacle
     */
    public void addObstacle(Obstacle ob){

        if(this.obstacles.size()<nbMaxObstacle){
            this.obstacles.add(ob);
        }else{
            throw new Require("Nombre max d'obstacle atteint ("+nbMaxObstacle+")");
        }
    }

    public int getNbObstacles(){
        return this.obstacles.size();
    }

    public ArrayList getObstacles(){
        return this.obstacles;
    }
}
