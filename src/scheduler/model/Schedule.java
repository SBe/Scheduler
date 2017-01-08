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
    private final Lesson[][][] schedule;
    private final boolean[][][] isUsed;
    private double fitness;
    private int softRequirementsValue;
    private final int mutationSize;
    private final int crossoverSize;
    private final int mutationProbability;
    private final int crossoverProbability;
    
    public Schedule(){
        this.schedule = new Lesson[Data.getDays()][Data.getHours()][Data.getGroupCount()];
        this.isUsed = new boolean[Data.getDays()][Data.getHours()][Data.getGroupCount()];
        this.mutationSize = 5000;
        this.crossoverSize = 3;
        this.mutationProbability = 99;
        this.crossoverProbability = 20;
        this.fitness = 0;
        this.softRequirementsValue = 0;
    }
    
    public void generateSchedule(){
        int day;
        int hour;
        Random rand = new Random();
        for(Lesson lesson : Data.getLessons()){
            do{
                day = rand.nextInt(Data.getDays());
                hour = rand.nextInt(Data.getHours());
            }while(isUsed[day][hour][lesson.getGroup().getId().get() - 1]);
            schedule[day][hour][lesson.getGroup().getId().get()-1] = lesson;
            isUsed[day][hour][lesson.getGroup().getId().get()-1] = true;
        }
    }
    
    public void calcFitness(){
        int fitness = 0;
        for(int i = 0; i < Data.getDays(); i++)
            for(int j = 0; j < Data.getHours(); j++){
                fitness += checkProfessorAvailability(i, j);
                fitness += checkRoomAvailability(i, j);
                fitness += checkRoomSize(i, j);
            }
        this.fitness = fitness / (Data.getDays() * Data.getHours() * 3);
    }
    
    public void calcSoftReqValue(){
        int value = 0;
        value += calcSoftStudentsReq();
        value+= calcSoftEveningReq();
        this.softRequirementsValue = value;     
    }
    
    //Grupy studenckie chcialyby konczyc w piatki  najpozniej o 14
    private int calcSoftStudentsReq(){
        int value = 0;
        for(int i = 0; i < Data.getGroupCount(); i++)
            if(schedule[4][3][i] == null)
                if(schedule[4][4][i] == null)
                    if(schedule[4][5][i] == null)
                        value++;
        return value;
    }
    
    //Zajecia najpozniej do godziny 18
    private int calcSoftEveningReq(){
        int value = 0;
        for(int i = 0; i < Data.getGroupCount(); i++)
            for(int j = 0; j < Data.getDays(); j++)
                if(schedule[j][5][i] == null)
                    value++;
        return value;
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
    
    public int getSoftReqValue(){
        return softRequirementsValue;
    }
        
    public void mutation(){
    	Random rand = new Random();   
    	if(rand.nextInt(100) < this.mutationProbability){
	        for(int i = 0; i < this.mutationSize; i++){
	        	int a = rand.nextInt(Data.getDays());
	        	int b = rand.nextInt(Data.getHours());
	        	int c = rand.nextInt(Data.getDays());
	        	int d = rand.nextInt(Data.getHours());
	        	int k = rand.nextInt(Data.getGroupCount());
	        	if(isUsed[a][b][k] == true || isUsed[c][d][k] == true){
		        	Lesson tmp = schedule[a][b][k];
		        	schedule[a][b][k] = schedule[c][d][k];
		        	schedule[c][d][k] = tmp;
		        	if(schedule[c][d][k] != null){
		        		mutation(schedule[c][d][k], rand);
		        		isUsed[c][d][k] = true;
		        	}
		        	else
		        		isUsed[c][d][k] = false;
		        	if(schedule[a][b][k] != null){
		        		mutation(schedule[a][b][k], rand);
		        		isUsed[a][b][k] = true;
		        	}
		        	else
		        		isUsed[a][b][k] = false;
	        	}   
	        }
    	}
    }
    
    public void mutation(Lesson l, Random rand){
    	do{
			Room myRoom = Data.getRooms().get(rand.nextInt(Data.getRooms().size()));
			if(myRoom.getSize().get() > l.getGroup().getSize().get()){
				l.setRoom(myRoom);        		
				break;
			}
		} while(true);
}
    
    public Lesson[][][] getSchedule(){
        return schedule;
    }
    public int getCrossoverSize(){
    	return this.crossoverSize;
    }
    public void setSchedule(int day, int hour, int group, Lesson l){
    	this.schedule[day][hour][group] = l;
    }
    public boolean[][][] getIsUsed(){
        return isUsed;
    }
    public boolean getUsedAt(int day, int hour, int group){
    	return isUsed[day][hour][group];
    }
    public void setUsed(int day, int hour, int group, boolean value){
    	isUsed[day][hour][group] = value;
    }
}
