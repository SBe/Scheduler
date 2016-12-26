/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import scheduler.util.filereadUtil;

/**
 *
 * @author adrian
 */
public class Schedule {
    private Class schedule[][][];
    private double fitness;
    
    Schedule(){
        this.schedule = new Class[5][6][filereadUtil.getGroupCount()];
    }
    
    private void calcFitness(){
        fitness = 0;
    }
    
    public double getFitness(){
        return fitness;
    }
    
    public void crossover(){
        
    }
    
    public void mutation(){
        
    }
}
