/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Cart;
import model.Item;
import model.Learner;

/**
 *
 * @author trungnh
 */
public class OrderDAO extends DBContext {

    public void addOrder(Learner l, Cart cart) {
        PreparedStatement ps;
        ResultSet rs;
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into Order values (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, l.getAccount_id());
            ps.setString(2, date);
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();
            String sql1 = "Select top 1 id from Order order by id desc";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            rs = ps1.executeQuery();
            if (rs.next()) {
                int order_id = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into Order_Detail values (?,?,?)";
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, order_id);
                    ps2.setInt(2, i.getOrderSubject().getSubject_id());
                    ps2.setDouble(3, i.getPrice());
                    ps2.executeUpdate();

                }
            }
        } catch (SQLException e) {

        }
    }

}
