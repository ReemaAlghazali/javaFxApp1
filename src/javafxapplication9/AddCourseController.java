/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ريما
 */
public class AddCourseController implements Initializable {

    @FXML
    private TextField txtFieldStudent;
    @FXML
    private TextField txtFieldCourse;
    @FXML
    private TextField txtFieldSemester;
    @FXML
    private TableView<Registration> tableView;
    @FXML
    private TableColumn<Registration, Integer> tcStudent;
    @FXML
    private TableColumn<Registration, Integer> tcCourse;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnShow;
    
    Statement statement;
    @FXML
    private Button btnExit;

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
        tcStudent.setCellValueFactory(new PropertyValueFactory("studentid"));
        tcCourse.setCellValueFactory(new PropertyValueFactory("courseid"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
        
    }    

    @FXML
    private void btnAddhandle(ActionEvent event) throws Exception {
        Integer studentid = Integer.parseInt(txtFieldStudent.getText());
        Integer courseid = Integer.parseInt(txtFieldCourse.getText());
        String semester = txtFieldSemester.getText();
        
        String sql = "insert into Registration values ("+ studentid +","+ courseid +",'"+semester + "')"; 
               
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void btnShowhandle(ActionEvent event) throws Exception{
        ResultSet rs = this.statement.executeQuery("Select * From Registration");
        tableView.getItems().clear();
        while(rs.next()){
            Registration course = new Registration();
            course.setStudentid(rs.getInt("studentid"));
            course.setCourseid(rs.getInt("courseid"));
            course.setSemester(rs.getString("semester"));
            tableView.getItems().add(course);

    }
    
}

    @FXML
    private void btnExithandle(ActionEvent event) {
        System.exit(0);
    }
}
