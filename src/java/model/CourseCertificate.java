/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class CourseCertificate {
    private String course_certificate_id,learner_course_id;
    private Date issue_date;
    private String course_name , lecturer_name;
    private String subject_id, course_id;

    public CourseCertificate(String course_certificate_id, String learner_course_id, Date issue_date, String course_name, String lecturer_name, String subject_id, String course_id) {
        this.course_certificate_id = course_certificate_id;
        this.learner_course_id = learner_course_id;
        this.issue_date = issue_date;
        this.course_name = course_name;
        this.lecturer_name = lecturer_name;
        this.subject_id = subject_id;
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    

    public CourseCertificate() {
    }

    public CourseCertificate(String course_certificate_id, String learner_course_id, Date issue_date) {
        this.course_certificate_id = course_certificate_id;
        this.learner_course_id = learner_course_id;
        this.issue_date = issue_date;
    }

    
    
    public String getCourse_certificate_id() {
        return course_certificate_id;
    }

    public void setCourse_certificate_id(String course_certificate_id) {
        this.course_certificate_id = course_certificate_id;
    }

    public String getLearner_course_id() {
        return learner_course_id;
    }

    public void setLearner_course_id(String learner_course_id) {
        this.learner_course_id = learner_course_id;
    }

    

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }
    
    
}
