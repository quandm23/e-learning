/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.Date;
import model.AccountReport;

/**
 *
 * @author Admin
 */
public class AccountReportDAO extends DBContext {

    public boolean insertCommentLesson(AccountReport accountReport) {
        boolean check = false;
        String sql = "INSERT INTO accountreport (account_id, reported_account_id, report_type, report_description, report_date, status) "
                + "VALUES (?, ?, ?, ?, ?, 0)";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setInt(1, accountReport.getAccountId());
            stm.setInt(2, accountReport.getReported_accountId());
            stm.setString(3, accountReport.getReportType());
            stm.setString(4, accountReport.getReportDescription());
            Date sqlDate = new Date(accountReport.getReportDate().getTime());
            stm.setDate(5, sqlDate);
            int number = stm.executeUpdate();
            check = number > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
