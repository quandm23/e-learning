/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Chapter {

    String chapter_id, chapter_no, chapter_name, course_id;
    private String status_id;

    public Chapter() {
    }

    public Chapter(String chapter_id, String chapter_no, String chapter_name, String course_id, String status_id) {
        this.chapter_id = chapter_id;
        this.chapter_no = chapter_no;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
        this.status_id = status_id;
    }

    
    public Chapter(String chapter_id, String chapter_no, String chapter_name, String course_id) {
        this.chapter_id = chapter_id;
        this.chapter_no = chapter_no;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_no() {
        return chapter_no;
    }

    public void setChapter_no(String chapter_no) {
        this.chapter_no = chapter_no;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    
    
}
