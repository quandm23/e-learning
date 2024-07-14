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
public class AccountReport {

    private int reportId;
    private int accountId;
    private int reported_accountId;
    private String reportType;
    private String reportDescription;
    private Date reportDate;
    private int status;

    public AccountReport() {
    }

    public AccountReport( int accountId, int reported_accountId, String reportType, String reportDescription, Date reportDate, int status) {
        this.accountId = accountId;
        this.reported_accountId = reported_accountId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.reportDate = reportDate;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getReported_accountId() {
        return reported_accountId;
    }

    public void setReported_accountId(int reported_accountId) {
        this.reported_accountId = reported_accountId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
