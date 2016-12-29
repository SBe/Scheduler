/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author adrian
 */
public class Algorithm {
    private static ObservableList<Schedule> Schedules = FXCollections.observableArrayList();
    private static Schedule bestSchedule;
    
    //testowalem czy dobrze generuje randomowy plan zajec i czy dobrze wyswietla, wiec TODO
    public static void start(){
        Schedules.add(new Schedule());
        Schedules.get(0).generateSchedule();
        bestSchedule = Schedules.get(0);
        Data.setSchedule(bestSchedule);
    }
    
    public static ObservableList<Schedule> getSchedules(){
        return Schedules;
    }
    
    public static Schedule getBestSchedule(){
        return bestSchedule;
    }
}

