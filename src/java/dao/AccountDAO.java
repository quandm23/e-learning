/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utils.MD5Encryption;
//import java.sql.Date;

public class AccountDAO extends DBContext {

    //login
    public Account login(String username, String password) {
        String sql = "SELECT * FROM Account\n"
                + "WHERE username = ? AND password = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                byte[] imageData = null;
                String base64Avatar = "";
                if (rs.getString("avatar") != null) {
                    imageData = rs.getBytes("avatar");
                    base64Avatar = new String(Base64.getEncoder().encode(imageData));
                }
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
                return acc;
            }
        } catch (SQLException e) {
        }
        return null;
    }

//Check Email Exist
    public boolean checkEmail(String email) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM Account where email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//Find Account Exist

    public Account findByAccount(String username) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM Account where username = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] imageData = null;
                String base64Avatar = "";
                if (rs.getString("avatar") != null) {
                    imageData = rs.getBytes("avatar");
                    base64Avatar = new String(Base64.getEncoder().encode(imageData));
                }
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
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//Find Email Exist

    public Account findByEmail(String email) {

        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM Account where email = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] imageData = null;
                String base64Avatar = "";
                if (rs.getString("avatar") != null) {
                    imageData = rs.getBytes("avatar");
                    base64Avatar = new String(Base64.getEncoder().encode(imageData));
                }
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
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //Verify OTP

    public void verify(String username) {
        PreparedStatement ps;
        try {
            String sql = "update Account set active = 1 where username=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
            sql = "update Account set otp = null where username=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //register
    public void register(String username, String password, String fullname,
            String dob, String email) {
        try {
            password = new MD5Encryption().convertPassword(password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into Account(username, password, fullname, email, dob, active, role_id)\n"
                + "values(?,?,?,?,?,'1','2')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, dob);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // find account by user name
    public boolean checkExistedUsername(String username) {
        String sql = "select * from Account\n"
                + "where username = ? and active in(1,2)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    //check email is existed
    public boolean checkExistedEmail(String email) {
        String sql = "select * from Account\n"
                + "where email = ? and active in (1,2)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    // find account by email
    public Account findAccountByEmail(String email) {
        String sql = "select * from Account\n"
                + "where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] imageData = null;
                String base64Avatar = "";
                if (rs.getString("avatar") != null) {
                    imageData = rs.getBytes("avatar");
                    base64Avatar = new String(Base64.getEncoder().encode(imageData));
                }
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
                return acc;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateProfile(String fullname, String dob, String phone, int account_id) {
        String query = "UPDATE Account\n"
                + "                  SET fullname = ?,\n"
                + "                     dob = ?,\n"
                + "                      phone = ?\n"
                + "                 WHERE account_id = ?";

        try {
            ResultSet rs;
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setDate(2, java.sql.Date.valueOf(dob));
            ps.setString(3, phone);
            ps.setInt(4, account_id);
            ps.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void updateOTP(String email, String otp) {
        PreparedStatement ps;
        try {
            String sql = "update Account set otp = ? where email=?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, otp);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Reset password
    public void resetPassword(String username, String password) {
        String sql = "update Account\n"
                + "set password = ?\n"
                + "where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Change password
    public void changePassword(String username, String newPass) {
        String sql = "update Account\n"
                + "set password = ?\n"
                + "where username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newPass);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Login with google
    // add account
    public void addAccGoogle(String fullname, String email) {
        String sql = "INSERT INTO Account(username, password, fullname, email, dob, active, role_id) "
                + "VALUES ('', '', ?, ?, '', '1', '2')";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }
    }

    // check existed account
    public boolean checkExistAccGoogle(List<Account> list, String email) {
        for (Account account : list) {
            if (account.getUsername().equals(email) && account.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public Account getAccountById(int account_id) {
        String query = "SELECT account_id\n"
                + "      ,username\n"
                + "      ,password\n"
                + "      ,fullname\n"
                + "      ,email\n"
                + "      ,dob\n"
                + "      ,phone\n"
                + "      ,avatar\n"
                + "      ,otp\n"
                + "      ,active\n"
                + "      ,role_id\n"
                + "  FROM Account\n"
                + "  WHERE account_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, account_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                byte[] imageData = rs.getBytes("avatar");
                String base64Avatar = new String(Base64.getEncoder().encode(imageData));
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setDob(rs.getString("dob"));
                acc.setPhone(rs.getString("phone"));
                acc.setAvatar(base64Avatar);
                acc.setOtp(rs.getString("otp"));
                acc.setActive(rs.getInt("active"));
                acc.setRole_id(rs.getInt("role_id"));
                return acc;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Account getAccountProfile(Integer account_id) {
        String query = "select a.account_id, a.username, a.password, a.fullname,"
                + "a.email, a.dob, a.phone, a.avatar, "
                + "r.role_name from Account a \n"
                + "JOIN Role r on a.role_id = r.role_id\n"
                + "where a.account_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, account_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                byte[] imageData = rs.getBytes("avatar");
                String base64Avatar = new String(Base64.getEncoder().encode(imageData));
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setDob(rs.getString("dob"));
                acc.setPhone(rs.getString("phone"));
                acc.setAvatar(base64Avatar);
//                acc.setOtp(rs.getString("otp"));
//                acc.setActive(rs.getInt("active"));
//                acc.setRole_id(rs.getInt("role_id"));
                // acc.setRoleName(rs.getString("role_name"));

                return acc;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Account> getTop5Lecturers() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT TOP 5 * FROM Account WHERE role_id = 3";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                byte[] imageData = rs.getBytes("avatar");
                String base64Avatar = new String(Base64.getEncoder().encode(imageData));
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setDob(rs.getString("dob"));
                acc.setPhone(rs.getString("phone"));
                acc.setAvatar(base64Avatar);
                acc.setOtp(rs.getString("otp"));
                acc.setActive(rs.getInt("active"));
                acc.setRole_id(rs.getInt("role_id"));
                list.add(acc);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public void deleteOTPByUsername() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String sql = "UPDATE Account\n"
                    + "SET otp = NULL\n"
                    + "WHERE otp IS NOT NULL;";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account selectTTAcountLecturer(String subject_id) {
        Account account = new Account();
        String sql = "Select a.fullname,a.phone,a.email\n"
                + "from Account a \n"
                + "Join Subject s on a.account_id = s.lecturer_id\n"
                + "where s.subject_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString(1);
                String phone = rs.getString(2);
                String email = rs.getString(3);
                account.setFullname(fullname);
                account.setEmail(email);
                account.setPhone(phone);
            }
        } catch (Exception e) {
        }
        return account;
    }

    public void updateRole(int accountId, int newRoleId) {
        String sql = "UPDATE Account SET role_id = ? WHERE account_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newRoleId);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    public static void main(String[] args) {
        AccountDAO ad = new AccountDAO();
        List<Account> a = ad.getTop5Lecturers();
        System.out.println(a);
    }
}
