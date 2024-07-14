/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import model.CommentLesson;

/**
 *
 * @author Admin
 */
public class CommentLessonDAO extends DBContext {

    public ArrayList<CommentLesson> selectAllCommentLesson(String lesson_id, int page, int limit) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname \n"
                + "                FROM comment_lesson cl \n"
                + "                JOIN account a ON a.account_id = cl.account_id \n"
                + "                WHERE cl.lesson_id = ? AND cl.status = 1 And cl.parent_comment_id IS NULL \n"
                + "                ORDER BY cl.comment_date DESC\n"
                + "                LIMIT ?, ?";

//        1 là ch reply and report 
//                2 là bij report 
//                        3 đã 
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(lesson_id));
            stm.setInt(2, page);
            stm.setInt(3, limit);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String comment_id = rs.getInt(1) + "";
                    String account_id = rs.getInt(2) + "";
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    CommentLesson commentLesson = new CommentLesson(comment_id, account_id, lesson_id, comment, comment_date, fullname);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<CommentLesson> selectAllCommentLessonBySubject_id(String subject_id, int page, int limit) {
        ArrayList<CommentLesson> data = new ArrayList<>();
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname "
                + "FROM comment_lesson cl "
                + "JOIN account a ON a.account_id = cl.account_id "
                + "JOIN lesson l ON l.lesson_id = cl.lesson_id "
                + "JOIN chapter ch ON ch.chapter_id = l.chapter_id "
                + "JOIN course c ON c.course_id = ch.course_id "
                + "JOIN subject s ON s.subject_id = c.subject_id "
                + "WHERE s.subject_id = ? AND cl.status = 1 And cl.parent_comment_id IS NULL "
                + "ORDER BY cl.comment_date DESC "
                + "LIMIT ?, ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            stm.setInt(2, page);
            stm.setInt(3, limit);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String comment_id = String.valueOf(rs.getInt(1));
                    String account_id = String.valueOf(rs.getInt(2));
                    String lesson_id = String.valueOf(rs.getInt(3));
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    CommentLesson commentLesson = new CommentLesson(comment_id, account_id, lesson_id, comment, comment_date, fullname);
                    data.add(commentLesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public int getNoOfCommentsByLesson(String lesson_id) {
        int count = 0;
        String sql = "SELECT Count(*) FROM comment_lesson WHERE lesson_id = ? and parent_comment_id IS NULL";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(lesson_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getNoOfCommentsBySubject(String subject_id) {
        int count = 0;
        String sql = "SELECT Count(cl.comment_id) FROM comment_lesson cl\n"
                + "			Join lesson l on l.lesson_id = cl.lesson_id\n"
                + "                Join Chapter ch on ch.chapter_id = l.chapter_id\n"
                + "                join Course c on c.course_id = ch.course_id\n"
                + "                join subject s on s.subject_id = c.subject_id \n"
                + "                WHERE s.subject_id = ? And cl.parent_comment_id IS NULL";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean insertCommentLesson(CommentLesson commentLesson) {
        boolean check = false;
        String sql = "INSERT INTO comment_lesson (account_id, lesson_id, comment, comment_date, status,parent_comment_id) "
                + "VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, Integer.parseInt(commentLesson.getAccount_id()));
            stm.setInt(2, Integer.parseInt(commentLesson.getLesson_id()));
            stm.setString(3, commentLesson.getComment());
            stm.setDate(4, new java.sql.Date(commentLesson.getComment_date().getTime()));
            stm.setString(5, "1");
            if (commentLesson.getParent_comment_id() == null || commentLesson.getParent_comment_id() .isEmpty()) {
                stm.setNull(6, java.sql.Types.INTEGER); 
            } else {
                stm.setInt(6, Integer.parseInt(commentLesson.getParent_comment_id())); 
            }
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void removeCommentByCommentID(String comment_id) {
        String sql = "DELETE FROM comment_lesson WHERE comment_id = ?";

        try (Connection cnn = connection; PreparedStatement stm = cnn.prepareStatement(sql)) {

            stm.setInt(1, Integer.parseInt(comment_id));
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateCommentLesson(CommentLesson commentLesson) {
        boolean check = false;
        String sql = "UPDATE comment_lesson SET comment = ?, comment_date = ? WHERE comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, commentLesson.getComment());
            stm.setDate(2, new java.sql.Date(commentLesson.getComment_date().getTime()));
            stm.setInt(3, Integer.parseInt(commentLesson.getComment_id()));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void reportCommentLesson(String comment_id) {
        String sql = "UPDATE comment_lesson SET status = 2 WHERE comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, Integer.parseInt(comment_id));
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommentLesson selectCommentByComment_id(String comment_id) {
        CommentLesson commentLesson = null;
        String sql = "SELECT cl.comment_id, cl.account_id, cl.lesson_id, cl.comment, cl.comment_date, a.fullname "
                + "FROM comment_lesson cl "
                + "JOIN account a ON a.account_id = cl.account_id "
                + "WHERE cl.comment_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(comment_id));

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String account_id = rs.getInt(2) + "";
                    String lesson_id = rs.getInt(3) + "";
                    String comment = rs.getString(4);
                    Date comment_date = rs.getDate(5);
                    String fullname = rs.getString(6);
                    commentLesson = new CommentLesson(comment_id, account_id, lesson_id, comment, comment_date, fullname);
                } else {
                    System.out.println("No comment found with ID: " + comment_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid comment ID format: " + comment_id);
        }
        return commentLesson;
    }

}
