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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ريما
 */
public class StudentAppController implements Initializable {
    
    
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TextField txtFieldID;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldMajor;
    @FXML
    private TextField txtFieldGrade;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnShow;
    
     
    Statement statement;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnAddCourse;
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
        tableView.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedStudent() );

    }    

    @FXML
    private void btnAddhandle(ActionEvent event) throws Exception {
        Integer id = Integer.parseInt(txtFieldID.getText());
        String name = txtFieldName.getText();
        String major = txtFieldMajor.getText();
        Double grade = Double.parseDouble(txtFieldGrade.getText());
        String sql = "insert into Student values ("+ id +",'"+name+"','"+major
                +"'," + grade +")";  
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void btnEdithandle(ActionEvent event) throws Exception{
        Integer id = Integer.parseInt(txtFieldID.getText());
        String name = txtFieldName.getText();
        String major = txtFieldMajor.getText();
        Double grade = Double.parseDouble(txtFieldGrade.getText());
        String sql = "Update Student Set name = '" + name + "', major='" + 
                major + "', grade=" + grade + " Where id=" +id;
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void btnDeletehandle(ActionEvent event) throws Exception{
        String sql = "Delete  From Student Where id = " + txtFieldID.getText() ;
         this.statement.executeUpdate(sql);
    }

    @FXML
    private void btnShowhandle(ActionEvent event) throws Exception {
        ResultSet rs = this.statement.executeQuery("Select * From Student");
        tableView.getItems().clear();
        while(rs.next()){
            Student student = new Student();
           student.setId(rs.getInt("id"));
           student.setName(rs.getString("name"));
           student.setMajor(rs.getString("major"));
           student.setGrade(rs.getDouble("grade"));
           tableView.getItems().add(student);
        
    }
    }

    private void showSelectedStudent() {
       Student s = tableView.getSelectionModel().getSelectedItem();
        if(s != null){
        txtFieldID.setText(String.valueOf(s.getId()));
        txtFieldName.setText(s.getName());
        txtFieldMajor.setText(s.getMajor());
        txtFieldGrade.setText(String.valueOf(s.getGrade()));
        }

    }

    @FXML
    private void btnExithandle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnAddCoursehandle(ActionEvent event) throws Exception{
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCourse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
    }


