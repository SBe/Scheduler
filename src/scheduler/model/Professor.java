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
public class Professor {
    private static int idCounter = 0;
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty surname;
    
    public Professor(){
        this(null, null);
    }
    
    public Professor(String name, String surname){
        this.id = new SimpleIntegerProperty(++idCounter);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }
    
    public Professor(int id){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(null);
        this.surname = new SimpleStringProperty(null);
    }
    
    public IntegerProperty getId(){
        return id;
    }
    
    public StringProperty getName(){
        return this.name;
    }
    
    public StringProperty getSurname(){
        return this.surname;
    }
    
    public void setName(StringProperty name){
        this.name = name;
    }
    
    public void setSurname(StringProperty surname){
        this.surname = surname;
    }
   
}
