/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;

/**
 *
 * @author ThaiVD
 */
public class BlogDAO extends DBContext {

    // Khai bao logger
    private static final Logger logger = Logger.getLogger(BlogDAO.class.getName());

    public ArrayList<Blog> selectAllBlogByMarketer_id(String marketer_id) {
        ArrayList<Blog> data = new ArrayList();
        String sql = "Select *\n"
                + "  from Blog \n"
                + "  where marketer_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, marketer_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String BLog_id = rs.getInt(1) + "";
                String title = rs.getString(2);
                String content = rs.getString(3);
                String description = rs.getString(4);
                Date create_date = rs.getDate(5);
                String status = (rs.getInt(6) == 1) ? "Active" : "";
                String tag = rs.getString(8);
                String image = rs.getString(9);
                Blog blog = new Blog(BLog_id, title, content, description, create_date, status, marketer_id, tag, image);
                data.add(blog);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
        return data;
    }

    public ArrayList<Blog> searchBlogByTittle(String title_search, String marketer_id) {
        String title_like = "%" + title_search + "%";
        ArrayList<Blog> data = new ArrayList();
        String sql = "Select *\n"
                + "  from Blog \n"
                + "  where title like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title_like);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String BLog_id = rs.getInt(1) + "";
                String title = rs.getString(2);
                String content = rs.getString(3);
                String description = rs.getString(4);
                Date create_date = rs.getDate(5);
                String status = (rs.getInt(6) == 1) ? "Active" : "";
                String tag = rs.getString(8);
                String image = rs.getString(9);
                Blog blog = new Blog(BLog_id, title, content, description, create_date, status, marketer_id, tag, image);

                data.add(blog);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
        return data;
    }

    public List<Blog> getAllBlog() {
        List<Blog> listB = new ArrayList<>();

        String sql = "select * from Blog order by id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listB.add(new Blog(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
        return listB;
    }

    public Blog getBlogByID(String id) {
        String sql = "select * from Blog where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Blog(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
        return null;
    }

    public List<Blog> searchBlogByTitle(String title) {
        String sql = "select * from Blog where title like '%" + title + "%' order by id desc";
        List<Blog> listBlogSearched = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listBlogSearched.add(new Blog(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
        return listBlogSearched;
    }

    // Add Blog ( add file image )
    public void addBlog(String title, String content, String description, String created_date, String status, String marketer_id, String tag, String image) {

        String sql = "insert into Blog(title, content, description, created_date, status, marketer_id, tag, image) values (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, description);
            ps.setString(4, created_date);
            ps.setString(5, status);
            ps.setString(6, marketer_id);
            ps.setString(7, tag);
            ps.setString(8, image);

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }
    }

    public List<Blog> getBlogPaging(int page, int record_per_page, String txtSearch) {
        List<Blog> list = new ArrayList<>();
        int start = (page - 1) * record_per_page;

        String sql = "SELECT * FROM Blog ";

        if (txtSearch != null) {
            sql += " where title like '%" + txtSearch + "%'"; // tim kiem theo title neu co
        }

        sql += " ORDER BY id DESC LIMIT ? OFFSET ?;"; // Limit ?1 la so bai moi trang ?2 bat dau tu vi tri bao nhieu

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, record_per_page);
            ps.setInt(2, start);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());

        }

        return list;
    }


    public void deleteBlog(String id) {
        String sql = "delete from blog where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    public void editBlog(String blogid, String title, String content, String description, String image) { //edit
        String sql = "update Blog set \n"
                + "title = ? ,\n"
                + "content = ?,\n"
                + "description = ?";
        if (!image.equals("")) {
            sql += ",\nimage = '" + image + "'";
        }
        sql += "\nwhere id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, description);
            ps.setString(4, blogid);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
