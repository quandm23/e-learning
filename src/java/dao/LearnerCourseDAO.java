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
import model.LearnerCourse;

/**
 *
 * @author Admin
 */
public class LearnerCourseDAO extends DBContext {

    public int CheckStatusLearnerCourse(String account_id, String course_id) {
        int check = 0;
        String sql = "SELECT lc.status_id FROM learner_course lc WHERE lc.learner_id = ? AND lc.course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(course_id));
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

    
     
     
    public void updateStatusCourse(String account_id, String course_id) {
        String sql = "UPDATE learner_course SET status_id = 2 WHERE learner_id = ? AND course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(course_id));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    public int CountAllCourseBySubject(String account_id, String subject_id) {
        int count = 0;
        String sql = "SELECT COUNT(lc.id) FROM learner_course lc "
                + "JOIN course c ON c.course_id = lc.course_id "
                + "JOIN subject s ON s.subject_id = c.subject_id "
                + "WHERE lc.learner_id = ? AND s.subject_id = ? AND lc.status_id = 2";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(subject_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return count;
    }
    
     public int selectIdByLerner_idAndCourse_id(String account_id, String course_id) {
        int id = 0;
        String sql = "Select lc.id\n"
                + "from learner_course lc\n"
                + "where lc.learner_id = ? and lc.course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(course_id));
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
     
      public LearnerCourse selectStatus_idAndRateCourse(String account_id, String course_id) {
        LearnerCourse learnerCourse = new LearnerCourse();
        String sql = "SELECT lc.status_id,lc.rate FROM learner_course lc WHERE lc.learner_id = ? AND lc.course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(course_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String status_id = rs.getInt(1)+"";
                    String rate = rs.getInt(2)+"";
                    learnerCourse = new LearnerCourse("", account_id, course_id, status_id, rate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }

        return learnerCourse;
    }

    public boolean updateRate(String rating, String account_id, String course_id) {
        boolean check = false;
        String sql = "UPDATE learner_course SET rate = ? WHERE learner_id = ? AND course_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(rating));
            stm.setInt(2, Integer.parseInt(account_id));
            stm.setInt(3, Integer.parseInt(course_id));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }
}
