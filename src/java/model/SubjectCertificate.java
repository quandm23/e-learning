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
public class SubjectCertificate {
    private String subject_certificate_id,learner_subject_id;
    private Date issue_date;
    private String subject_name, lecturer_name,subject_id;

    public SubjectCertificate(String subject_certificate_id, String learner_subject_id, Date issue_date, String subject_name, String lecturer_name, String subject_id) {
        this.subject_certificate_id = subject_certificate_id;
        this.learner_subject_id = learner_subject_id;
        this.issue_date = issue_date;
        this.subject_name = subject_name;
        this.lecturer_name = lecturer_name;
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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


    public SubjectCertificate() {
    }

    public SubjectCertificate(String subject_certificate_id, String learner_subject_id, Date issue_date) {
        this.subject_certificate_id = subject_certificate_id;
        this.learner_subject_id = learner_subject_id;
        this.issue_date = issue_date;
    }

    public String getSubject_certificate_id() {
        return subject_certificate_id;
    }

    public void setSubject_certificate_id(String subject_certificate_id) {
        this.subject_certificate_id = subject_certificate_id;
    }

    public String getLearner_subject_id() {
        return learner_subject_id;
    }

    public void setLearner_subject_id(String learner_subject_id) {
        this.learner_subject_id = learner_subject_id;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }
    
}
