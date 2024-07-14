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
public class CommentLesson{
    private String comment_id,account_id,lesson_id,comment;
    private Date comment_date;
    private String fullname;
    private String parent_comment_id;

    public CommentLesson(String comment_id, String account_id, String lesson_id, String comment, Date comment_date, String fullname, String parent_comment_id) {
        this.comment_id = comment_id;
        this.account_id = account_id;
        this.lesson_id = lesson_id;
        this.comment = comment;
        this.comment_date = comment_date;
        this.fullname = fullname;
        this.parent_comment_id = parent_comment_id;
    }
    

    public CommentLesson() {
    }

    public CommentLesson(String comment_id, String account_id, String lesson_id, String comment, Date comment_date, String fullname) {
        this.comment_id = comment_id;
        this.account_id = account_id;
        this.lesson_id = lesson_id;
        this.comment = comment;
        this.comment_date = comment_date;
        this.fullname = fullname;
    }

    public String getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(String parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    
    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    

    
}
