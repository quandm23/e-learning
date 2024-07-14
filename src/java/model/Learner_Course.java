/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.*;

/**
 *
 * @author admin
 */
public class Learner_Course {
    private String id;
    private String learner_id;
    private String course_id;
    private String status_id;
    private String rate;

    public Learner_Course() {
    }

    public Learner_Course(String id, String learner_id, String course_id, String status_id, String rate) {
        this.id = id;
        this.learner_id = learner_id;
        this.course_id = course_id;
        this.status_id = status_id;
        this.rate = rate;
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

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getStatus() {
        return status_id;
    }

    public void setStatus(String status_id) {
        this.status_id = status_id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    
}
