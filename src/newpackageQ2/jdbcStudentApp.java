/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackageQ2;

import javafxapplication9.*;
import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author ريما
 */
public class jdbcStudentApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
       Pane pane = FXMLLoader.load(getClass().getResource("ShowResult.fxml"));
       Map<String,Pane> map = new TreeMap<>();
       map.put("ShowResult", pane);
       Scene s = new Scene(map.get("ShowResult"));
        primaryStage.setTitle("ShowResult");
        primaryStage.setScene(s);
        primaryStage.show();
    }
//    public class AddCourse extends Application{
//
//        @Override
//        public void start(Stage primaryStage) throws Exception {
//            Pane pane = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
//       Map<String,Pane> map = new TreeMap<>();
//       map.put("StudentApp", pane);
//       Scene s = new Scene(map.get("StudentApp"));
//        primaryStage.setTitle("Student App");
//        primaryStage.setScene(s);
//        primaryStage.show();
//        }
//        
 //   } 
    
    
     public static void main(String[] args) {
        launch(args);
    }
     
}
