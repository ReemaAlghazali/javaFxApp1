/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackageQ2;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ريما
 */
public class ShowResultController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private Button btnShowResult;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    
    Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conniction = DriverManager.getConnection("jdbc:mysql://localhost:3306/university?serverTimezone=UTC",
                "root", "");
            this.statement = conniction.createStatement();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
     
   
    }    

    @FXML
    private void btnShowResulthandle(ActionEvent event) throws Exception {
       if(textArea.getText().equals("Select all students studying \"Software Engineering\" major")){
            ResultSet rs = this.statement.executeQuery("SELECT * FROM Student WHERE major = \"Software Engineering\""  );
        tableView.getItems().clear();
        while(rs.next()){
            Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getDouble("grade"));
           tableView.getItems().addAll(student);
        
        }
    }
       else{
           if(textArea.getText().equals("Select students who have excellent grades")){
               ResultSet rs = this.statement.executeQuery("SELECT * FROM Student WHERE grade >" + 85  );
            tableView.getItems().clear();
           while(rs.next()){
            Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getDouble("grade"));
           tableView.getItems().addAll(student);
           }
       }
       
    else {
        if(textArea.getText().equals("Select all pass students in ascending order based on their names")){
        ResultSet rs = this.statement.executeQuery("SELECT * FROM Student WHERE grade >" + 60 + " ORDER BY name" );
            tableView.getItems().clear();
           while(rs.next()){
            Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getDouble("grade"));
           tableView.getItems().addAll(student);
           }
        }
        else{
           if(textArea.getText().equals("For all students studying \"Computer Science\" major and have grade less than 70%, increase their grades by 3")){
          this.statement.executeUpdate("Update Student set grade = grade + " +  3 + " WHERE major = \"Computer Science\" and grade < " + 70 );
               ResultSet rs = this.statement.executeQuery("Select * From Student");
            tableView.getItems().clear();
           while(rs.next()){
            Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getDouble("grade"));
           tableView.getItems().addAll(student);
          
           }
       } 
           } 
           }}}}
        
    
