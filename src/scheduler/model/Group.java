/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author adrian
 */
public class Group {
    private static int idCounter = 0;
    private IntegerProperty id;
    private IntegerProperty size;
    private IntegerProperty semester;
    
    public Group(){
        this(0,0);
    }
    
    public Group(int size, int semester){
        this.id = new SimpleIntegerProperty(++idCounter);
        this.size = new SimpleIntegerProperty(size);
        this.semester = new SimpleIntegerProperty(semester);
    }
    
    public Group(int id){
        this.id = new SimpleIntegerProperty(id);
        this.size = new SimpleIntegerProperty(0);
        this.semester = new SimpleIntegerProperty(0);
    }
    
    public IntegerProperty getId(){
        return this.id;
    }
    
    public IntegerProperty getSize(){
        return this.size;
    }
    
    public IntegerProperty getSemester(){
        return this.semester;
    }
    
    public void setSize(IntegerProperty size){
        this.size = size;
    }
    
    public void setSemester(IntegerProperty semester){
        this.semester = semester;
    }
}
