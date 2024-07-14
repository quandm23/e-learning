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
import java.util.Date;
import model.Favorite;

/**
 *
 * @author Admin
 */
public class FavoriteDAO extends DBContext {

    public ArrayList<Favorite> selectAllFavorite(String account_id) {
        ArrayList<Favorite> data = new ArrayList<>();
        String sql = "Select  favorite_id,account_id,subject_id,added_date\n"
                + "from favorite_subjects"
                + "where account_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(account_id));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String favorite_id = rs.getInt(1) + "";
                    String subject_id = rs.getInt(3) + "";
                    Date added_date = rs.getDate(4);
                    Favorite favorite = new Favorite(favorite_id, account_id, subject_id, added_date);
                    data.add(favorite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
