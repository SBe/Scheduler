/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import scheduler.model.Course;
import scheduler.model.Group;
import scheduler.model.Lesson;
import scheduler.model.Professor;
import scheduler.model.Room;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import scheduler.util.filereadUtil;
import scheduler.view.SchedulerController;

/**
 *
 * @author adrian
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final ObservableList<Lesson> lessons  = FXCollections.observableArrayList();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();
    private final ObservableList<Group> groups = FXCollections.observableArrayList();
    private final ObservableList<Professor> professors = FXCollections.observableArrayList();
    private final ObservableList<Room> rooms = FXCollections.observableArrayList();
    
    public MainApp(){
        professors.addAll(filereadUtil.readProfessors());
        courses.addAll(filereadUtil.readCourses());
        groups.addAll(filereadUtil.readGroups());
        rooms.addAll(filereadUtil.readRooms());
        lessons.addAll(filereadUtil.readLessons());
       
        filereadUtil.loadLessons(lessons, courses, groups, professors, rooms);

        for(Lesson lesson : lessons){
            System.out.println(lesson.getCourse().getName());
            System.out.println(lesson.getGroup().getSemester());
            System.out.println(lesson.getGroup().getSize());
            System.out.println(lesson.getProfessor().getName());
            System.out.println(lesson.getProfessor().getSurname());
            System.out.println(lesson.getRoom().getSize());
        }
    }
    
    public ObservableList<Lesson> getLessons(){
        return lessons;
    }
    
    public ObservableList<Course> getCourses(){
        return courses;
    }
    
    public ObservableList<Group> getGroups(){
        return groups;
    }
    
    public ObservableList<Professor> getProfessors(){
        return professors;
    }
    
    public ObservableList<Room> getRooms(){
        return rooms;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Evolutionary Scheduler");

        initRootLayout();

        showScheduler();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showScheduler() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Scheduler.fxml"));
            AnchorPane scheduler = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(scheduler);
            
            SchedulerController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
