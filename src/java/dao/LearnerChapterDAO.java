/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Learner_Chapter;

/**
 *
 * @author Admin
 */
public class LearnerChapterDAO extends DBContext {

     public int CheckStatusLearnerChapter(String account_id, String chapter_id) {
        int check = 0;
        String sql = "SELECT lc.status_id FROM learner_chapter lc WHERE lc.learner_id = ? AND lc.chapter_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(chapter_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    check = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return check;
    }

    public void updateStatusChapter(String account_id, String chapter_id) {
        String sql = "UPDATE learner_chapter SET status_id = 2 WHERE learner_id = ? AND chapter_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(chapter_id));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int  CountAllLearnerChapterComplex(String account_id, String course_id) {
        int data = 0;
        String sql = "SELECT Count(lc.chapter_id) "
                   + "FROM learner_chapter lc "
                   + "JOIN chapter c ON c.chapter_id = lc.chapter_id "
                   + "JOIN course co ON co.course_id = c.course_id "
                   + "WHERE lc.learner_id = ? AND co.course_id = ? AND lc.status_id = 2";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(course_id));
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    data = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return data;
    }
}
