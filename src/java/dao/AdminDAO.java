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
import java.util.List;
import model.Account;
import model.Role;

/**
 *
 * @author Trungnh
 */
public class AdminDAO extends DBContext {

    public List<Account> getAccountToManage(String[] role, String[] active) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT a.*, r.role_name FROM Account a\n"
                + "JOIN Role r ON a.role_id = r.role_id\n"
                + "WHERE a.role_id > 1\n";
        
        if (role == null || role.length <= 0) {
            sql += "";
        } else {
            sql += "\nAND a.role_id IN (0";
            for (String r : role) {
                sql += ("," + r);
            }
            sql += ")";
        }
        
        if (active == null || active.length <= 0) {
            sql += "";
        } else {
            sql += "\nAND a.active IN (0";
            for (String r : active) {
                sql += ("," + r);
            }
            sql += ")";
        }
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                byte[] imageData = null;
                String base64Avatar = "";
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setDob(rs.getString("dob"));
                acc.setPhone(rs.getString("phone"));
                if (imageData != null) {
                    acc.setAvatar(base64Avatar);
                }
                acc.setOtp(rs.getString("otp"));
                acc.setActive(rs.getInt("active"));
                acc.setRole_id(rs.getInt("role_id"));
                acc.setRoleName(rs.getString("role_name"));
                list.add(acc);
            }
        } catch (SQLException e) {
        }

        return list;
    }
    
    public List<Role> getAllRole(){
        List<Role> list = new ArrayList<>();
        String sql = "SELECT * FROM ROLE WHERE role_id > 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Role r = new Role();
                r.setRole_id(rs.getInt(1));
                r.setRole_name(rs.getString(2));
                list.add(r);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public void blockAccount(String id){
        String sql = "UPDATE Account SET active = 2 WHERE account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void activeAccount(String id){
        String sql = "UPDATE Account SET active = 1 WHERE account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void editRole(String id, String role){
        String sql = "UPDATE Account SET role_id = ? WHERE account_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, role);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public static void main(String[] args) {
    }
}
