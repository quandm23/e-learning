package dao;

import com.sun.jdi.connect.spi.Connection;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.Lesson;

/**
 *
 * @author lam12
 */
public class LessonDAO extends DBContext {

    // Lấy tất cả các lesson của 1 chapter kết hợp tìm kiếm và phân trang
    public List<Lesson> getLessonByChapterId(String chapterId, int page, int records_per_page, String txtSearch) {
        List<Lesson> list = new ArrayList<>();
        int start = (page - 1) * records_per_page;
        String sql = "select * from lesson where chapter_id = ?  and lesson_name like '%" + txtSearch + "%' \n"
                + "limit ? offset ?;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapterId);
            ps.setInt(2, records_per_page);
            ps.setInt(3, start);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Lesson(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(7),
                        rs.getString(6)));
            }

        } catch (SQLException e) {
        }
        return list;
    }

    // Lấy tổng số lượng lesson lấy được
    public int getNumberOfLessonByChapterId(String chapterId, String txtSearch) {
        int count = 0;

        String sql = "select * from lesson where chapter_id = ? and lesson_name like '%" + txtSearch + "%'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapterId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
        }
        return count;
    }

    // Khi thêm lesson, thì lesson NO phải là mới nhất (lớn nhất) -> method để lấy lesson_no lớn nhất có trong DB và + 1
    public int getMaxLessonNo(String chapterId) {
        String sql = "select lesson_no from lesson where chapter_id = ? order by lesson_no desc limit 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapterId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
        }
        // Nếu chapter đấy chưa có lesson nào thì lesson mặc định đầu tiên là 1
        return 1;
    }

    public int getCurrentMaxLessonNo(String chapterId) {
        String sql = "select lesson_no from lesson where chapter_id = ? order by lesson_no desc limit 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, chapterId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        // Nếu chapter đấy chưa có lesson nào thì lesson mặc định đầu tiên là 1
        return 1;
    }

    // Add lesson
    public void addLesson(String lesson_no, String lesson_name, String video, String document, String chapter_id, String description) {
        String sql = "insert into lesson(lesson_no, lesson_name, video, document, chapter_id, description) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lesson_no);
            ps.setString(2, lesson_name);
            ps.setString(3, video);
            ps.setString(4, document);
            ps.setString(5, chapter_id);
            ps.setString(6, description);

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Lấy lesson_no của lesson sắp bị xóa
    public int getLessonNoByLessonId(String lessonid) {
        String sql = "select lesson_no from lesson where lesson_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lessonid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }

        return -1;
    }

    public void deleteLesson(String lessonid, int lessonNo, String chapterid, boolean isAutoDecrease) {
        String sql = "delete from lesson where lesson_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lessonid);
            ps.executeUpdate();
            // nếu lesson_no của lesson bị xóa không phải là lessonNO lớn nhất thì giảm các lessonNo ở phía sau xuống 1 đơn vị
            if (isAutoDecrease == true) {
                String sqlUpd = "update lesson set lesson_no = lesson_no - 1 where chapter_id = " + chapterid + " and lesson_no > " + lessonNo + ";";
                PreparedStatement ps1 = connection.prepareStatement(sqlUpd);
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
    }

    // Lấy chi tiết Lesson by lessonid
    public Lesson getLessonByLessonId(String lessonid) {
        String sql = "select * from lesson where lesson_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, lessonid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Lesson(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(7),
                        rs.getString(6));
            }
        } catch (SQLException e) {
        }

        return null;
    }

    // edit lesson
    public void editLesson(String lesson_id, String name, String video, String document, String description) {
        String sql = """
                     update lesson set
                     lesson_name = ?,
                     video = ?,
                     document = ?,
                     description = ?
                     where lesson_id = ?
                     """;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, video);
            ps.setString(3, document);
            ps.setString(4, description);
            ps.setString(5, lesson_id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public LessonDAO() {
        super();
    }

    public ArrayList<Lesson> selectAllLessonByCourse_id(String course_id) {
        ArrayList<Lesson> data = new ArrayList<>();
        String sql = "SELECT l.lesson_id, l.lesson_no, l.lesson_name, l.video, l.document, l.chapter_id, l.description "
                + "FROM chapter c "
                + "JOIN lesson l ON c.chapter_id = l.chapter_id "
                + "WHERE c.course_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, course_id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String lesson_id = String.valueOf(rs.getInt("lesson_id"));
                    String lesson_no = rs.getString("lesson_no");
                    String lesson_name = rs.getString("lesson_name");
                    String video = rs.getString("video");
                    String document = rs.getString("document");
                    String chapter_id = rs.getString("chapter_id");
                    String description = rs.getString("description");
                    Lesson lesson = new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description);
                    data.add(lesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Lesson> selectAllLessonAndStatusByCourse_id(String course_id, String account_id) {
        ArrayList<Lesson> data = new ArrayList<>();
        String sql = "SELECT l.lesson_id, l.lesson_no, l.lesson_name, l.video, l.document, l.chapter_id, l.description, ll.status_id "
                + "FROM chapter c "
                + "JOIN lesson l ON c.chapter_id = l.chapter_id "
                + "JOIN learner_lesson ll ON ll.lesson_id = l.lesson_id "
                + "WHERE c.course_id = ? ";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(course_id));
//            stm.setString(2, account_id);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String lesson_id = String.valueOf(rs.getInt("lesson_id"));
                    String lesson_no = rs.getString("lesson_no");
                    String lesson_name = rs.getString("lesson_name");
                    String video = rs.getString("video");
                    String document = rs.getString("document");
                    String chapter_id = rs.getString("chapter_id");
                    String description = rs.getString("description");
                    String status_id = String.valueOf(rs.getInt("status_id"));
                    Lesson lesson = new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description, status_id);
                    data.add(lesson);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public int countAllLessonByChapter(String chapter_id) {
        int count = 0;
        String sql = "SELECT COUNT(l.lesson_id) "
                + "FROM lesson l "
                + "JOIN chapter c ON c.chapter_id = l.chapter_id "
                + "WHERE c.chapter_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(chapter_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Lesson selectLessonByLesson_id(String lesson_id) {
        Lesson lesson = null;
        String sql = "SELECT l.lesson_id, l.lesson_no, l.lesson_name, l.video, l.document, l.chapter_id, l.description "
                + "FROM lesson l "
                + "WHERE l.lesson_id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, Integer.parseInt(lesson_id));
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String lesson_no = String.valueOf(rs.getInt("lesson_no"));
                    String lesson_name = rs.getString("lesson_name");
                    String video = rs.getString("video");
                    String document = rs.getString("document");
                    String chapter_id = String.valueOf(rs.getInt("chapter_id"));
                    String description = rs.getString("description");
                    lesson = new Lesson(lesson_id, lesson_no, lesson_name, video, document, chapter_id, description);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    public static void main(String[] args) {
        LessonDAO ld = new LessonDAO();
//        List<Lesson> list = ld.getLessonByChapterId("1", 2, 3, "");
//        System.out.println(list.size());
//        System.out.println(ld.getNumberOfLessonByChapterId("1", ""));
        System.out.println(ld.getMaxLessonNo("111"));
        ld.deleteLesson("79", 4, "13", true);
    }
}
