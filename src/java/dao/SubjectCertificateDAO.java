/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CourseCertificate;
import model.SubjectCertificate;

/**
 *
 * @author Admin
 */
public class SubjectCertificateDAO extends DBContext {

    public void addSubjectCertificateD(String learner_subject_id, String issue_date) {
        String sql = "INSERT INTO subject_certificate (learner_subject_id, issue_date) \n"
                + "VALUES (?,?);";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(learner_subject_id));
            stm.setDate(2, java.sql.Date.valueOf(issue_date));
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }

    public SubjectCertificate selectSubjectCertificateBySubject_id(String subject_id, String account_id) {
        SubjectCertificate data = new SubjectCertificate();
        String sql = "SELECT sc.subject_certificate_id,sc.learner_subject_id, sc.issue_date  FROM subject_certificate sc\n"
                + "join learner_subject ls on ls.id = sc.learner_subject_id\n"
                + "join subject s on s.subject_id = ls.subject_id\n"
                + "where s.subject_id = ? and ls.learner_id = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(subject_id));
            stm.setInt(2, Integer.parseInt(account_id));
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String subject_certificate_id = rs.getInt(1) + "";
                    String learner_subject_id = rs.getInt(2) + "";
                    Date issue_date = rs.getDate(3);
                    data = new SubjectCertificate(subject_certificate_id, learner_subject_id, issue_date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
        }
        return data;
    }

    public ArrayList<SubjectCertificate> selectAllSubjectCertificate(int accountId) {
        ArrayList<SubjectCertificate> data = new ArrayList<>();
        String sql = "SELECT sc.subject_certificate_id, sc.learner_subject_id, sc.issue_date, s.subject_name, a.fullname, ls.subject_id "
                   + "FROM subject_certificate sc "
                   + "JOIN learner_subject ls ON ls.id = sc.learner_subject_id "
                   + "JOIN subject s ON s.subject_id = ls.subject_id "
                   + "JOIN account a ON a.account_id = s.lecturer_id "
                   + "WHERE ls.learner_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String subjectCertificateId = String.valueOf(rs.getInt(1));
                    String learnerSubjectId = String.valueOf(rs.getInt(2));
                    Date issueDate = rs.getDate(3);
                    String subjectName = rs.getString(4);
                    String fullname = rs.getString(5);
                    String subjectId = String.valueOf(rs.getInt(6));
                    SubjectCertificate sc = new SubjectCertificate(subjectCertificateId, learnerSubjectId, issueDate, subjectName, fullname, subjectId);
                    data.add(sc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
