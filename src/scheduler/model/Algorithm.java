/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import java.util.ArrayList;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author adrian
 */
public class Algorithm {
    private static ObservableList<Schedule> Schedules = FXCollections.observableArrayList();
    private static ObservableList<Schedule> Elite = FXCollections.observableArrayList();
    private static Schedule bestSchedule;
    private static final int POPULATION = 1000;
    
    //testowalem czy dobrze generuje randomowy plan zajec i czy dobrze wyswietla, wiec TODO
    public static void start(){
        /*int iterations = 0;
    //inicjacja populacji poczatkowej
        for(int i = 0; i < POPULATION; i++){
            Schedules.add(new Schedule());
            Schedules.get(0).generateSchedule();
        }
    //ocena populacji poczatkowej
        calcFitness();
        if(selectBest().getFitness() == 1)
            return;
        
        while(getBestFitness() < 0.99) //dalem tyle, bo nie wiem czy te double sa wystarczajaco dokladne, zeby dac 1.0
        {
            
        }
        */
        Schedules.add(new Schedule());
        Schedules.get(0).generateSchedule();
        Schedules.add(new Schedule());
        Schedules.get(1).generateSchedule();
        bestSchedule = Schedules.get(0);
        Data.setSchedule(Schedules.get(0));
    }
    
    public static Schedule crossover(Schedule sch1, Schedule sch2){
    	Schedule child = new Schedule();
    	Random rand = new Random();
    	int crossoverBegin =  rand.nextInt(Data.getHours() - sch1.getCrossoverSize());
    	int crossoverDay = rand.nextInt(Data.getDays() + 1);
    	int day = 0, hour = 0, group = 0;
    	ArrayList<Pair> lessonList = new ArrayList<Pair>();
    	ArrayList<Pair> sch1List = new ArrayList<Pair>();
    	ArrayList<Pair> overWritten = new ArrayList<Pair>();
    	for(int i = 0; i < (Data.getDays() ) * (Data.getHours()) * Data.getGroupCount() ; i++){		
    		if(day == crossoverDay - 1  && (hour >= crossoverBegin && hour < (crossoverBegin + child.getCrossoverSize()))){	
    			child.setUsed(day, hour, group, sch2.getIsUsed()[day][hour][group]);
    			child.setSchedule(day, hour, group, sch2.getSchedule()[day][hour][group]);
    			if(child.getSchedule()[day][hour][group] != null){
    				lessonList.add(new Pair(child.getSchedule()[day][hour][group], group));
    				sch1List.add(new Pair(sch1.getSchedule()[day][hour][group], group, sch1.getUsedAt(day, hour, group)));
    			}
    			if(sch1.getUsedAt(day, hour, group) == true && sch2.getUsedAt(day, hour, group) == false){
    				Pair p = new Pair(sch1.getSchedule()[day][hour][group], group);
    				overWritten.add(p);
    				for(int j = 0; j < lessonList.size();j++)
    					if(lessonList.get(j).getLesson() == p.getLesson()){
    						overWritten.remove(p);
    						break;
    					}
    			}	
    		} 	
    		else{
	    		child.setSchedule(day, hour, group, sch1.getSchedule()[day][hour][group]);
	    		child.setUsed(day, hour, group, sch1.getIsUsed()[day][hour][group]);
    		}
    		++hour;
    		if(day == Data.getDays() - 1 && hour == Data.getHours()){
    			day = 0;
    			hour = 0;
    			++group;
    		}    			
    		if(hour == Data.getHours()){
    			hour = 0;
    			++day;
    		}
    	}
    	hour = day = group = 0;
    	for(int j = 0; j < lessonList.size(); j++){
    		for(int i = 0; i < Data.getDays() * Data.getHours(); i++){
    			if(day == crossoverDay - 1  && (hour >= crossoverBegin && hour < (crossoverBegin + child.getCrossoverSize()))){
    				
    			}
    			else{
    				if(lessonList.get(j) != null || child.getSchedule()[day][hour][lessonList.get(j).getGroup()] != null){
    					if(lessonList.get(j).getLesson() == child.getSchedule()[day][hour][lessonList.get(j).getGroup()]){
	    					child.setUsed(day, hour, lessonList.get(j).getGroup(), sch1List.get(j).isUsed());
	    					child.setSchedule(day, hour, lessonList.get(j).getGroup(), sch1List.get(j).getLesson());
	    				}
    				}   				
    			}
    			++hour;
    			if(day == Data.getDays() - 1 && hour == Data.getHours()){
        			day = 0;
        			hour = 0;
        		}    			
        		if(hour == Data.getHours()){
        			hour = 0;
        			++day;
        		}
    		}
    		day = hour = 0;
    	}
    	for(int j = 0; j < overWritten.size(); j++){
    		while(true){
    			day = rand.nextInt(Data.getDays());
    			hour = rand.nextInt(Data.getHours());
    			if(child.getUsedAt(day, hour, overWritten.get(j).getGroup()) == false){
    				child.setUsed(day, hour, overWritten.get(j).getGroup(), true);
    				child.setSchedule(day, hour, overWritten.get(j).getGroup(), overWritten.get(j).getLesson());
    				break;
    			}
    		}	
    	}
    	Schedules.set(0, child);
    	Data.setSchedule(child);
	    return child;
    }
    
    private static void selection(){
        calcFitness();
        selectElite();
    }
    
    private static void calcFitness(){
        for(Schedule s : Schedules){
            s.calcFitness();
            s.calcSoftReqValue();
        }
    }
    
    private static void selectElite(){
        Elite.clear();
        for(int i = 0; i < Data.getSelectionParam(); i++){
            Elite.add(selectBest());
        }
    }
    
    private static Schedule selectBest(){
        Schedule best = new Schedule();
        for(Schedule s : Schedules)
        {
            if(best.getFitness() < s.getFitness())
                best = s;
            if(best.getFitness() == s.getFitness())
                if(best.getSoftReqValue() < s.getSoftReqValue())
                    best = s;
        }
        return best;
    }
    
    private static double getBestFitness(){
        return selectBest().getFitness();
    }
    
    public static ObservableList<Schedule> getSchedules(){
        return Schedules;
    }
    
    public static Schedule getBestSchedule(){
        return bestSchedule;
    }
    
}
