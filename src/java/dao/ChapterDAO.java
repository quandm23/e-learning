/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Chapter;
import model.Course;

/**
 *
 * @author Admin
 */
public class ChapterDAO extends DBContext {

    public ChapterDAO() {
        connectDB();
    }
    Connection cnn;// Kết nối DB
    PreparedStatement stm; // Thực hiện các câu lệnh sql
    ResultSet rs;

    private void connectDB() {
        cnn = connection;
        if (cnn != null) {
            System.out.println("Connect success");
        } else {
            System.out.println("Not success");
        }
    }

    public ArrayList<Chapter> selectAllChapterByCourse_id(String course_id) {
        ArrayList<Chapter> data = new ArrayList();
        try {
            String sql = "Select chapter_id,chapter_no,chapter_name,course_id\n"
                    + "  from Chapter\n"
                    + "  where course_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt(1) + "";
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id);
                data.add(chapter);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public ArrayList<Chapter> selectAllChapterAndStatusByCourse_id(String course_id, String account_id) {
        ArrayList<Chapter> data = new ArrayList();
        try {
            String sql = "Select c.chapter_id,c.chapter_no,c.chapter_name,c.course_id,lc.status_id\n"
                    + "  from Chapter c\n"
                    + "join learner_chapter lc on lc.chapter_id = c.chapter_id"
                    + "  where course_id = ? ";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_id);
//            stm.setString(2, account_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_id = rs.getInt(1) + "";
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                String status_id = rs.getInt(5) + "";
                Chapter chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id, status_id);
                data.add(chapter);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public int CountAllChapterByCourse(String course_id) {
        int count = 0;
        try {
            String sql = "Select Count(ch.chapter_id)\n"
                    + "from chapter ch\n"
                    + "join Course c on ch.course_id = c.course_id\n"
                    + "where c.course_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(course_id));
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public int SelectMaxChapterNoByCourse_id(String course_id) {
        int max = 0;
        try {
            String sql = "Select Max(c.chapter_no)\n"
                    + "from chapter c\n"
                    + "where c.course_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setString(1, course_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                max = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return max;

    }

    public boolean CheckNameExits(String course_id, String chapter_name) {
        boolean check = false;
        try {
            String sql = "Select c.chapter_name\n"
                    + "                    from chapter c \n"
                    + "                    where c.course_id = ? and c.chapter_name = ?";

            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(course_id));
            stm.setString(2, chapter_name);
            rs = stm.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }

    public void insertChapter(String chapter_no, String chapter_name, String course_id) {
        try {
            String sql = "INSERT INTO chapter (chapter_no, chapter_name,course_id) "
                    + "VALUES (?, ?, ?)";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(chapter_no));
            stm.setInt(3, Integer.parseInt(course_id));
            stm.setString(2, chapter_name);
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Chapter selectChapterByChapter_id(String chapter_id) {
        Chapter chapter = new Chapter();
        try {
            String sql = "Select chapter_id,chapter_no,chapter_name,course_id\n"
                    + "  from Chapter\n"
                    + "  where chapter_id = ?";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(chapter_id));
            rs = stm.executeQuery();
            while (rs.next()) {
                String chapter_no = rs.getInt(2) + "";
                String chapter_name = rs.getString(3);
                String course_id = rs.getInt(4) + "";
                chapter = new Chapter(chapter_id, chapter_no, chapter_name, course_id);
            }
        } catch (Exception e) {
        }
        return chapter;
    }

    public void updateChapter(String chapter_name, String chapter_id) {
        try {
            String sql = "Update chapter  \n"
                    + "                Set chapter_name = ?"
                    + "                 where chapter_id = ?";

            stm = cnn.prepareStatement(sql);
            stm.setString(1, chapter_name);
            stm.setInt(2, Integer.parseInt(chapter_id));
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void DeleteChapter(String chapter_id) {
        try {
            String sql = "DELETE FROM chapter\n"
                    + "WHERE chapter_id = ?;";
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(chapter_id));
            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean CheckNameExitsUpdate(String course_id, String chapter_name, String chapter_id) {
        boolean check = false;
        try {
            String sql = "Select c.chapter_name\n"
                    + "                    from chapter c \n"
                    + "                    where c.course_id = ? and c.chapter_name = ? and c.chapter_id != ?";

            stm = cnn.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(course_id));
            stm.setString(2, chapter_name);
            stm.setInt(3, Integer.parseInt(chapter_id));
            rs = stm.executeQuery();
            while (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }
}
