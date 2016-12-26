/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author adrian
 */
public class Course {
    private static int idCounter;
    private IntegerProperty id;
    private StringProperty name;
    
    public Course(){
        this(null);
    }
    
    public Course(String name){
        this.id = new SimpleIntegerProperty(++idCounter);
        this.name = new SimpleStringProperty(name);
    }
    
    public Course(int id){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(null);
    }
    
    public StringProperty getName(){
        return name;
    }
    
    public IntegerProperty getId(){
        return id;
    }
    
    public void setName(StringProperty name){
        this.name = name;
    }
}
