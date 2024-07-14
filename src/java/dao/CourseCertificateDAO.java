/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CourseCertificate;

/**
 *
 * @author Admin
 */
public class CourseCertificateDAO extends DBContext {

    public void addCourseCertificateD(String learner_course_id, String issue_date) {
        String sql = "INSERT INTO course_certificate (learner_course_id, issue_date) \n"
                + "VALUES (?,?);";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(learner_course_id));
            stm.setDate(2, java.sql.Date.valueOf(issue_date));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    public CourseCertificate selectCertificateByCourse_id(String course_id, String account_id) {
        CourseCertificate data = new CourseCertificate();
        String sql = "SELECT cc.course_certificate_id,cc.learner_course_id, cc.issue_date  FROM course_certificate cc\n"
                + "join learner_course lc on lc.id = cc.learner_course_id\n"
                + "join course c on c.course_id = lc.course_id\n"
                + "where c.course_id = ? and lc.learner_id =?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(course_id));
            stm.setInt(2, Integer.parseInt(account_id));
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String course_certificate_id = rs.getInt(1) + "";
                    String learner_course_id = rs.getInt(2) + "";
                    Date issue_date = rs.getDate(3);
                    data = new CourseCertificate(course_certificate_id, learner_course_id, issue_date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
        return data;
    }

    public ArrayList<CourseCertificate> selectAllCourseCertificate(int accountId) {
        ArrayList<CourseCertificate> data = new ArrayList<>();
        String sql = "SELECT cc.course_certificate_id, cc.learner_course_id, cc.issue_date, c.course_name, a.fullname, c.subject_id, c.course_id "
                   + "FROM course_certificate cc "
                   + "JOIN learner_course lc ON cc.learner_course_id = lc.id "
                   + "JOIN course c ON c.course_id = lc.course_id "
                   + "JOIN subject s ON s.subject_id = c.subject_id "
                   + "JOIN account a ON a.account_id = s.lecturer_id "
                   + "WHERE lc.learner_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String courseCertificateId = String.valueOf(rs.getInt(1));
                    String learnerCourseId = String.valueOf(rs.getInt(2));
                    Date issueDate = rs.getDate(3);
                    String courseName = rs.getString(4);
                    String fullname = rs.getString(5);
                    String subjectId = String.valueOf(rs.getInt(6));
                    String courseId = String.valueOf(rs.getInt(7));
                    CourseCertificate courseCertificate = new CourseCertificate(courseCertificateId, learnerCourseId, issueDate, courseName, fullname, subjectId, courseId);
                    data.add(courseCertificate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
