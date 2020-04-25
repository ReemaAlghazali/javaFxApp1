/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

/**
 *
 * @author ريما
 */
public class Registration {
    private Integer studentid;
    private Integer courseid;
    private String semester;

    public Registration() {
    }

    public Registration(Integer studentid, Integer courseid, String semester) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.semester = semester;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getCourseid() {
        return courseid;
    }

    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
}
