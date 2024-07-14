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
import java.util.List;
import model.RoleChangeRequest;

public class RoleChangeRequestDAO extends DBContext {

    // Lưu yêu cầu thay đổi role
    public void saveRoleChangeRequest(int accountId, int requestedRoleId, String content) {
        String sql = "INSERT INTO RoleChangeRequest (account_id, requested_role_id, content) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, requestedRoleId);
            stmt.setString(3, content);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách các yêu cầu thay đổi role
    public List<RoleChangeRequest> getRoleChangeRequests() {
        List<RoleChangeRequest> requests = new ArrayList<>();
        String sql = "SELECT rcr.id, rcr.account_id, a.username, a.fullname, a.email, a.dob, a.phone, a.role_id AS current_role_id, "
                + "cr.role_name AS current_role_name, rr.role_name AS requested_role_name, rcr.requested_role_id, rcr.content "
                + "FROM RoleChangeRequest rcr "
                + "JOIN Account a ON rcr.account_id = a.account_id "
                + "JOIN Role cr ON a.role_id = cr.role_id "
                + "JOIN Role rr ON rcr.requested_role_id = rr.role_id";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                RoleChangeRequest request = new RoleChangeRequest();
                request.setAccountId(rs.getInt("account_id"));
                request.setUsername(rs.getString("username"));
                request.setFullname(rs.getString("fullname"));
                request.setEmail(rs.getString("email"));
                request.setDob(rs.getString("dob"));
                request.setPhone(rs.getString("phone"));
                request.setCurrentRoleId(rs.getInt("current_role_id"));
                request.setCurrentRoleName(rs.getString("current_role_name"));
                request.setRequestedRoleId(rs.getInt("requested_role_id"));
                request.setRequestedRoleName(rs.getString("requested_role_name"));
                request.setContent(rs.getString("content"));
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // Xóa yêu cầu sau khi đã được xử lý
    public void deleteRoleChangeRequest(int accountId) {
        String sql = "DELETE FROM RoleChangeRequest WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
