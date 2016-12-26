/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import scheduler.model.Course;
import scheduler.model.Group;
import scheduler.model.Lesson;
import scheduler.model.Professor;
import scheduler.model.Room;

/**
 *
 * @author adrian
 */
public class filereadUtil {
    private static int groupCount;
    
    public static ObservableList<Course> readCourses(){
        ObservableList<Course> courses = FXCollections.observableArrayList();
        String csvFile = "data/courses.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] course = line.split(csvSplitBy);
                courses.add(new Course(course[0]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }
    
    public static ObservableList<Group> readGroups(){
        ObservableList<Group> groups= FXCollections.observableArrayList();
        String csvFile = "data/groups.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                groupCount++;
                String[] group = line.split(csvSplitBy);
                groups.add(new Group(Integer.parseInt(group[0]), Integer.parseInt(group[1])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return groups;
    }
    
    public static ObservableList<Professor> readProfessors(){
        ObservableList<Professor> professors = FXCollections.observableArrayList();
        String csvFile = "data/professors.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] professor = line.split(csvSplitBy);
                professors.add(new Professor(professor[0], professor[1]));
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return professors;
    }
    
    public static ObservableList<Room> readRooms(){
        ObservableList<Room> rooms = FXCollections.observableArrayList();
        String csvFile = "data/rooms.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ";";

        try {
            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] room = line.split(csvSplitBy);
                rooms.add(new Room(Integer.parseInt(room[0])));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rooms;
    }
    
    public static ObservableList<Lesson> readLessons(){
        ObservableList<Lesson> lessons = FXCollections.observableArrayList();
        String csvFile = "data/lessons.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String csvSplitBy = ";";

        try {

            bufferedReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferedReader.readLine()) != null) {
                String[] lesson = line.split(csvSplitBy);
                lessons.add(new Lesson(Integer.parseInt(lesson[0]), Integer.parseInt(lesson[1]), Integer.parseInt(lesson[2]), lesson[3]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lessons;
    }
    
    private static void loadLessonsCourses(ObservableList<Lesson> lessons, ObservableList<Course> courses){
        for(Lesson lesson : lessons){
            lesson.loadCourse(courses.get(lesson.getCourse().getId().get()-1));
        }
    }
    
    private static void loadLessonsGroups(ObservableList<Lesson> lessons, ObservableList<Group> groups){
        for(Lesson lesson : lessons){
            lesson.loadGroup(groups.get(lesson.getGroup().getId().get()-1));
        }
    }
    
    private static void loadLessonsProfessors(ObservableList<Lesson> lessons, ObservableList<Professor> professors){
        for(Lesson lesson : lessons){
            lesson.loadProfessor(professors.get(lesson.getProfessor().getId().get()-1));
        }
    }
    
    private static void loadLessonsRooms(ObservableList<Lesson> lessons, ObservableList<Room> rooms){
        for(Lesson lesson : lessons){
            lesson.loadRoom(rooms.get(lesson.getRoom().getId().get()-1));
        }
    }
    
    public static void loadLessons(ObservableList<Lesson> lessons, ObservableList<Course> courses, ObservableList<Group> groups, ObservableList<Professor> professors, ObservableList<Room> rooms){
        loadLessonsCourses(lessons, courses);
        loadLessonsGroups(lessons, groups);
        loadLessonsProfessors(lessons, professors);
        loadLessonsRooms(lessons, rooms);
    }
    
    public static int getGroupCount(){
        return groupCount;
    }
}
