/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import java.util.Random;

/**
 *
 * @author adrian
 */
public class Schedule {
    private Lesson[][][] schedule;
    private boolean[][][] isUsed;
    private double fitness;
    
    public Schedule(){
        this.schedule = new Lesson[Data.getDays()][Data.getHours()][Data.getGroupCount()];
        this.isUsed = new boolean[Data.getDays()][Data.getHours()][Data.getGroupCount()];
    }
    
    public void generateSchedule(){
        int day;
        int hour;
        Random rand = new Random();
        for(Lesson lesson : Data.getLessons()){
            do{
                day = rand.nextInt(Data.getDays());
                hour = rand.nextInt(Data.getHours());
            }while(isUsed[day][hour][lesson.getGroup().getId().get()]);
            schedule[day][hour][lesson.getGroup().getId().get()-1] = lesson;
            isUsed[day][hour][lesson.getGroup().getId().get()-1] = true;
        }
    }
    
    private void calcFitness(){
        int fitness = 0;
        for(int i = 0; i < Data.getDays(); i++)
            for(int j = 0; j < Data.getHours(); j++){
                fitness += checkProfessorAvailability(i, j);
                fitness += checkRoomAvailability(i, j);
                fitness += checkRoomSize(i, j);
            }
        this.fitness = fitness / (Data.getDays() * Data.getHours() * 3);
    }
    
    private int checkProfessorAvailability(int day, int hour){
        int fitness = Data.getGroupCount();
        int check = 0;
        for(Professor professor : Data.getProfessors()){
            for(int group = 0; group < Data.getGroupCount(); group++){
                if(professor == schedule[day][hour][group].getProfessor())
                    check++;
            }
            if(check > 1)
                fitness -= check;
            check = 0;
        }
        return fitness;
    }
    
    private int checkRoomAvailability(int day, int hour){
        int fitness = Data.getGroupCount();
        int check = 0;
        for(Room room : Data.getRooms()){
            for(int group = 0; group < Data.getGroupCount(); group++){
                if(room == schedule[day][hour][group].getRoom())
                    check++;
            }
            if(check > 1)
                fitness -= check;
            check = 0;
        }
        return fitness;
    }
    
    private int checkRoomSize(int day, int hour){
        int fitness = Data.getGroupCount();
        int check = 0;
        for(int group = 0; group < Data.getGroupCount(); group++){
            if(schedule[day][hour][group].getGroup().getSize().get() < schedule[day][hour][group].getRoom().getSize().get())
                fitness--;
            }
        return fitness;
    }

    public double getFitness(){
        return fitness;
    }
    
    public void crossover(){
        
    }
    
    public void mutation(){
        
    }
    
    public Lesson[][][] getSchedule(){
        return schedule;
    }
    
    public boolean[][][] getIsUsed(){
        return isUsed;
    }
}
