/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import model.Learner_Subject;

/**
 *
 * @author Admin
 */
public class LearnerSubjectDAO extends DBContext {

  public ArrayList<Learner_Subject> selectAllMySubjectByAcc_id(String learner_id) {
    ArrayList<Learner_Subject> data = new ArrayList<>();
    String sql = "SELECT ls.id, ls.learner_id, ls.subject_id, ls.enrolled_date, s.status_name, sb.subject_name, sb.description, a.fullname "
            + "FROM learner_subject ls "
            + "JOIN status s ON ls.status_id = s.status_id "
            + "JOIN subject sb ON sb.subject_id = ls.subject_id "
            + "JOIN account a ON a.account_id = sb.lecturer_id "
            + "WHERE learner_id = ?";

    try (PreparedStatement stm = connection.prepareStatement(sql)) {
        stm.setString(1, learner_id);
        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String id = String.valueOf(rs.getInt(1));
                String subject_id = String.valueOf(rs.getInt(3));
                Date enrolled_date = rs.getDate(4);
                String status = rs.getString(5);
                String subject_name = rs.getString(6);
                String description = rs.getString(7);
                String fullname = rs.getString(8);
                int numberlessoninsubject = CountAllLessonInSubject(subject_id);
                int numberlessoninlearnersubject = CountAllLessonInLearnerSubject(subject_id);
                float percentageCompleted = (numberlessoninlearnersubject / (float) numberlessoninsubject) * 100;
                String formattedPercentage = String.format("%.2f", percentageCompleted);
                Learner_Subject ls = new Learner_Subject(id, learner_id, subject_id, enrolled_date, status, subject_name, description, fullname, formattedPercentage);
                data.add(ls);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception for debugging purposes
    }

    return data;
}

    public int CheckStatusLearnerSubject(String account_id, String subject_id) {
        int check = 0;
        String sql = "SELECT ls.status_id FROM learner_subject ls "
                + "JOIN subject s ON ls.subject_id = s.subject_id "
                + "WHERE ls.learner_id = ? AND ls.subject_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    check = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return check;
    }

    public void updateStatusSubject(String account_id, String subject_id) {
        String sql = "UPDATE learner_subject SET status_id = 2 WHERE learner_id = ? AND subject_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(subject_id));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    public boolean checkLearnerSubject(String subject_id) {
        boolean check = false;
        String sql = "SELECT ls.status_id FROM learner_subject ls "
                + "JOIN subject s ON ls.subject_id = s.subject_id "
                + "WHERE ls.subject_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return check;
    }

    public int selectIdByLearner_idAndSubject_id(String account_id, String subject_id) {
        int id = 0;
        String sql = "Select ls.id\n"
                + "                from learner_subject ls\n"
                + "                where ls.learner_id = ? and ls.subject_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return id;
    }

    public int CountAllLessonInSubject(String subject_id) {
        int check = 0;
        String sql = "Select Count(l.lesson_id)\n"
                + "from lesson l\n"
                + "join chapter ch on ch.chapter_id = l.chapter_id\n"
                + "join course c on c.course_id = ch.course_id\n"
                + "join subject s on s.subject_id = c.subject_id\n"
                + "where s.subject_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    check = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return check;
    }

    public int CountAllLessonInLearnerSubject(String subject_id) {
        int check = 0;
        String sql = "Select Count(ll.id)\n"
                + "from learner_lesson ll\n"
                + "join lesson l on l.lesson_id = ll.lesson_id\n"
                + "join chapter ch on ch.chapter_id = l.chapter_id\n"
                + "join course c on c.course_id = ch.course_id\n"
                + "join subject s on s.subject_id = c.subject_id\n"
                + "where s.subject_id = ? and ll.status_id = 2";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    check = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return check;
    }
}
