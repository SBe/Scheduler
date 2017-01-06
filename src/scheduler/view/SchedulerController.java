/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import scheduler.MainApp;
import scheduler.model.Algorithm;
import scheduler.model.Course;
import scheduler.model.Data;
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
    
    @FXML
    private ChoiceBox<String> groupChoiceBox;
    
    @FXML
    private TableView<Lesson[]> lessonScheduleTable;
    @FXML
    private TableColumn<Lesson[], String> mondayColumn;
    @FXML
    private TableColumn<Lesson[], String> tuesdayColumn;
    @FXML
    private TableColumn<Lesson[], String> wenesdayColumn;
    @FXML
    private TableColumn<Lesson[], String> thursdayColumn;
    @FXML
    private TableColumn<Lesson[], String> fridayColumn;
    
    @FXML
    private Label mondayLabel1;
    @FXML
    private Label mondayLabel2;
    @FXML
    private Label mondayLabel3;
    @FXML
    private Label mondayLabel4;
    @FXML
    private Label mondayLabel5;
    @FXML
    private Label mondayLabel6;
    @FXML
    private Label tuesdayLabel1;
    @FXML
    private Label tuesdayLabel2;
    @FXML
    private Label tuesdayLabel3;
    @FXML
    private Label tuesdayLabel4;
    @FXML
    private Label tuesdayLabel5;
    @FXML
    private Label tuesdayLabel6;
    @FXML
    private Label wenesdayLabel1;
    @FXML
    private Label wenesdayLabel2;
    @FXML
    private Label wenesdayLabel3;
    @FXML
    private Label wenesdayLabel4;
    @FXML
    private Label wenesdayLabel5;
    @FXML
    private Label wenesdayLabel6;
    @FXML
    private Label thursdayLabel1;
    @FXML
    private Label thursdayLabel2;
    @FXML
    private Label thursdayLabel3;
    @FXML
    private Label thursdayLabel4;
    @FXML
    private Label thursdayLabel5;
    @FXML
    private Label thursdayLabel6;
    @FXML
    private Label fridayLabel1;
    @FXML
    private Label fridayLabel2;
    @FXML
    private Label fridayLabel3;
    @FXML
    private Label fridayLabel4;
    @FXML
    private Label fridayLabel5;
    @FXML
    private Label fridayLabel6;
    @FXML
    private ChoiceBox<String> groupChoiceBox2;
            
    @FXML
    private void handleButton(){
    	
    	Algorithm.getSchedules().get(0).mutation();
   // 	Algorithm.crossover(Algorithm.getSchedules().get(0), Algorithm.getSchedules().get(0));
    	setLabels(Integer.parseInt(groupChoiceBox2.getValue()) - 1);
    }
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
        
        groupChoiceBox.getItems().addAll(Data.getGroupList());
        
        groupChoiceBox2.getItems().addAll(Data.getGroupList());
        groupChoiceBox2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue ov, Number value, Number newValue){
                setLabels(newValue.intValue());
            }
        });
    }    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        courseTable.setItems(Data.getCourses());
        groupTable.setItems(Data.getGroups());
        lessonTable.setItems(Data.getLessons());
        professorTable.setItems(Data.getProfessors());
        roomTable.setItems(Data.getRooms());
    }
    
    //nie pytajcie
    public void setLabels(int group){
        if(Data.getSchedule().getIsUsed()[0][0][group])
            mondayLabel1.setText(Algorithm.getSchedules().get(0).getSchedule()[0][0][group].getLesson());
        else
            mondayLabel1.setText("none");
        
        if(Data.getSchedule().getIsUsed()[0][1][group])
            mondayLabel2.setText(Algorithm.getSchedules().get(0).getSchedule()[0][1][group].getLesson());
        else
            mondayLabel2.setText("none");
        
        if(Data.getSchedule().getIsUsed()[0][2][group])
            mondayLabel3.setText(Algorithm.getSchedules().get(0).getSchedule()[0][2][group].getLesson());
        else
            mondayLabel3.setText("none");
        
        if(Data.getSchedule().getIsUsed()[0][3][group])
            mondayLabel4.setText(Algorithm.getSchedules().get(0).getSchedule()[0][3][group].getLesson());
        else
            mondayLabel4.setText("none");
        
        if(Data.getSchedule().getIsUsed()[0][4][group])
            mondayLabel5.setText(Algorithm.getSchedules().get(0).getSchedule()[0][4][group].getLesson());
        else
            mondayLabel5.setText("none");
        
        if(Data.getSchedule().getIsUsed()[0][5][group])
            mondayLabel6.setText(Algorithm.getSchedules().get(0).getSchedule()[0][5][group].getLesson());
        else
            mondayLabel6.setText("none");
        
        
        
        if(Data.getSchedule().getIsUsed()[1][0][group])
            tuesdayLabel1.setText(Algorithm.getSchedules().get(0).getSchedule()[1][0][group].getLesson());
        else
           tuesdayLabel1.setText("none");
        
        if(Data.getSchedule().getIsUsed()[1][1][group])
        tuesdayLabel2.setText(Algorithm.getSchedules().get(0).getSchedule()[1][1][group].getLesson());
        else
           tuesdayLabel2.setText("none");
        
        if(Data.getSchedule().getIsUsed()[1][2][group])
        tuesdayLabel3.setText(Algorithm.getSchedules().get(0).getSchedule()[1][2][group].getLesson());
        else
           tuesdayLabel3.setText("none");
        
        if(Data.getSchedule().getIsUsed()[1][3][group])
        tuesdayLabel4.setText(Algorithm.getSchedules().get(0).getSchedule()[1][3][group].getLesson());
        else
           tuesdayLabel4.setText("none");
        
        if(Data.getSchedule().getIsUsed()[1][4][group])
        tuesdayLabel5.setText(Algorithm.getSchedules().get(0).getSchedule()[1][4][group].getLesson());
        else
           tuesdayLabel5.setText("none");
        
        if(Data.getSchedule().getIsUsed()[1][5][group])
        tuesdayLabel6.setText(Algorithm.getSchedules().get(0).getSchedule()[1][5][group].getLesson());
        else
           tuesdayLabel6.setText("none");
        
        
        
        if(Data.getSchedule().getIsUsed()[2][0][group])
            wenesdayLabel1.setText(Algorithm.getSchedules().get(0).getSchedule()[2][0][group].getLesson());
        else
            wenesdayLabel1.setText("none");
        
        if(Data.getSchedule().getIsUsed()[2][1][group])
            wenesdayLabel2.setText(Algorithm.getSchedules().get(0).getSchedule()[2][1][group].getLesson());
        else
            wenesdayLabel2.setText("none");
        
        if(Data.getSchedule().getIsUsed()[2][2][group])
            wenesdayLabel3.setText(Algorithm.getSchedules().get(0).getSchedule()[2][2][group].getLesson());
        else
            wenesdayLabel3.setText("none");
        
        if(Data.getSchedule().getIsUsed()[2][3][group])
            wenesdayLabel4.setText(Algorithm.getSchedules().get(0).getSchedule()[2][3][group].getLesson());
        else
            wenesdayLabel4.setText("none");
        
        if(Data.getSchedule().getIsUsed()[2][4][group])
            wenesdayLabel5.setText(Algorithm.getSchedules().get(0).getSchedule()[2][4][group].getLesson());
        else
            wenesdayLabel5.setText("none");
        
        if(Data.getSchedule().getIsUsed()[2][5][group])
            wenesdayLabel6.setText(Algorithm.getSchedules().get(0).getSchedule()[2][5][group].getLesson());
        else
            wenesdayLabel6.setText("none");
        
        
        
        if(Data.getSchedule().getIsUsed()[3][0][group])
            thursdayLabel1.setText(Algorithm.getSchedules().get(0).getSchedule()[3][0][group].getLesson());
        else
            thursdayLabel1.setText("none");
        
        if(Data.getSchedule().getIsUsed()[3][1][group])
            thursdayLabel2.setText(Algorithm.getSchedules().get(0).getSchedule()[3][1][group].getLesson());
        else
            thursdayLabel2.setText("none");
        
        if(Data.getSchedule().getIsUsed()[3][2][group])
            thursdayLabel3.setText(Algorithm.getSchedules().get(0).getSchedule()[3][2][group].getLesson());
        else
            thursdayLabel3.setText("none");
        
        if(Data.getSchedule().getIsUsed()[3][3][group])
            thursdayLabel4.setText(Algorithm.getSchedules().get(0).getSchedule()[3][3][group].getLesson());
        else
            thursdayLabel4.setText("none");
        
        if(Data.getSchedule().getIsUsed()[3][4][group])
            thursdayLabel5.setText(Algorithm.getSchedules().get(0).getSchedule()[3][4][group].getLesson());
        else
            thursdayLabel5.setText("none");
        
        if(Data.getSchedule().getIsUsed()[3][5][group])
            thursdayLabel6.setText(Algorithm.getSchedules().get(0).getSchedule()[3][5][group].getLesson());
        else
            thursdayLabel6.setText("none");
        
        
        
        if(Data.getSchedule().getIsUsed()[4][0][group])
            fridayLabel1.setText(Algorithm.getSchedules().get(0).getSchedule()[4][0][group].getLesson());
        else
            fridayLabel1.setText("none");
        
        if(Data.getSchedule().getIsUsed()[4][1][group])
            fridayLabel2.setText(Algorithm.getSchedules().get(0).getSchedule()[4][1][group].getLesson());
        else
            fridayLabel2.setText("none");
        
        if(Data.getSchedule().getIsUsed()[4][2][group])
            fridayLabel3.setText(Algorithm.getSchedules().get(0).getSchedule()[4][2][group].getLesson());
        else
            fridayLabel3.setText("none");
        
        if(Data.getSchedule().getIsUsed()[4][3][group])
            fridayLabel4.setText(Algorithm.getSchedules().get(0).getSchedule()[4][3][group].getLesson());
        else
            fridayLabel4.setText("none");
        
        if(Data.getSchedule().getIsUsed()[4][4][group])
            fridayLabel5.setText(Algorithm.getSchedules().get(0).getSchedule()[4][4][group].getLesson());
        else
            fridayLabel5.setText("none");
        
        if(Data.getSchedule().getIsUsed()[4][5][group])
            fridayLabel6.setText(Algorithm.getSchedules().get(0).getSchedule()[4][5][group].getLesson());
        else
            fridayLabel6.setText("none");
        
    }
}
