/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import scheduler.MainApp;
import scheduler.model.Course;
import scheduler.model.Group;
import scheduler.model.Lesson;
import scheduler.model.Professor;
import scheduler.model.Room;

public class SchedulerController implements Initializable {
    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, Integer> courseIdColumn;
    @FXML
    private TableColumn<Course, String> courseNameColumn;
    
    @FXML
    private TableView<Group> groupTable;
    @FXML
    private TableColumn<Group, Integer> groupIdColumn;
    @FXML 
    private TableColumn<Group, Integer> groupSizeColumn;
    @FXML
    private TableColumn<Group, Integer> groupSemesterColumn;
    
    @FXML
    private TableView<Lesson> lessonTable;
    @FXML
    private TableColumn<Lesson, Integer> lessonCourseIdColumn;
    @FXML
    private TableColumn<Lesson, Integer> lessonGroupIdColumn;
    @FXML
    private TableColumn<Lesson, Integer> lessonProfessorIdColumn;
    @FXML
    private TableColumn<Lesson, Integer> lessonRoomIdColumn;
    
    @FXML
    private TableView<Professor> professorTable;
    @FXML
    private TableColumn<Professor, Integer> professorIdColumn;
    @FXML
    private TableColumn<Professor, String> professorNameColumn;
    @FXML
    private TableColumn<Professor, String> professorSurnameColumn;
    
    @FXML 
    private TableView<Room> roomTable;
    @FXML 
    private TableColumn<Room, Integer> roomIdColumn;
    @FXML
    private TableColumn<Room, Integer> roomSizeColumn;
    
    private MainApp mainApp;
    
    public SchedulerController(){
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        courseIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        
        groupIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        groupSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getSize().asObject());
        groupSemesterColumn.setCellValueFactory(cellData -> cellData.getValue().getSemester().asObject());
        
        lessonCourseIdColumn.setCellValueFactory(cellData -> cellData.getValue().getCourse().getId().asObject());
        lessonGroupIdColumn.setCellValueFactory(cellData -> cellData.getValue().getGroup().getId().asObject());
        lessonProfessorIdColumn.setCellValueFactory(cellData -> cellData.getValue().getProfessor().getId().asObject());
        lessonRoomIdColumn.setCellValueFactory(cellData -> cellData.getValue().getRoom().getId().asObject());
        
        professorIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        professorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        professorSurnameColumn.setCellValueFactory(cellData -> cellData.getValue().getSurname());
        
        roomIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        roomSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getSize().asObject());
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        courseTable.setItems(mainApp.getCourses());
        groupTable.setItems(mainApp.getGroups());
        lessonTable.setItems(mainApp.getLessons());
        professorTable.setItems(mainApp.getProfessors());
        roomTable.setItems(mainApp.getRooms());
    }
    
}