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
import model.Learner_Lesson;

/**
 *
 * @author Admin
 */
public class LearnerLessonDAO extends DBContext {
     public LearnerLessonDAO() {
        super();
    }


    public boolean updateStatusLesson(String account_id, String lesson_id) {
        boolean check = false;
        String sql = "UPDATE learner_lesson SET status_id = 2 WHERE learner_id = ? AND lesson_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(lesson_id));
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

    public int CountAllLearnerLessonComplex(String account_id, String chapter_id) {
        int data =  0;
        String sql = "SELECT Count(ll.lesson_id) "
                + "FROM learner_lesson ll "
                + "JOIN lesson l ON l.lesson_id = ll.lesson_id "
                + "JOIN chapter c ON c.chapter_id = l.chapter_id "
                + "WHERE ll.learner_id = ? AND c.chapter_id = ? AND ll.status_id = 2";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));
            stm.setInt(2, Integer.parseInt(chapter_id));
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
