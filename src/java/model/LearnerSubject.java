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
public class LearnerSubject {
    private String id, learner_id , subject_id;
    private Date enrolled_date;
    private String status_id,subject_name,description,fullname;

    public LearnerSubject() {
    }

    public LearnerSubject(String id, String learner_id, String subject_id, Date enrolled_date, String status_id) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.status_id = status_id;
    }
    
       public LearnerSubject(String id, String learner_id, String subject_id, Date enrolled_date, String status_id, String subject_name, String description, String fullname) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.status_id = status_id;
        this.subject_name = subject_name;
        this.description = description;
        this.fullname = fullname;
    }
    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(String learner_id) {
        this.learner_id = learner_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public Date getEnrolled_date() {
        return enrolled_date;
    }

    public void setEnrolled_date(Date enrolled_date) {
        this.enrolled_date = enrolled_date;
    }

    public String getStatus() {
        return status_id;
    }

    public void setStatus(String status_id) {
        this.status_id = status_id;
    }
    
}
