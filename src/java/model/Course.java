/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Course {
    private String course_id,course_name,image,subject_id,course_no,description;

    public Course() {
    }

    public Course(String course_id, String course_name, String image, String subject_id, String course_no, String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.image = image;
        this.subject_id = subject_id;
        this.course_no = course_no;
        this.description = description;
    }

    public Course(String course_id, String course_name, String subject_id, String course_no, String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.subject_id = subject_id;
        this.course_no = course_no;
        this.description = description;
    }
    

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getCourse_no() {
        return course_no;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
