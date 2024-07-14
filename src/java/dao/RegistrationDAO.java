package dao;

import dal.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Learner_Subject;
import model.Order;
import model.OrderDetail;
import model.OrderSubject;

/**
 *
 * @author trungnh
 */
public class RegistrationDAO extends DBContext {

    public List<OrderSubject> getAll() {
        PreparedStatement ps;
        ResultSet rs;
        List<OrderSubject> list = new ArrayList<>();
        String sql = "select * from subject ";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                byte[] imageData = null;
                String base64Image = "";
                if (rs.getString("image") != null) {
                    imageData = rs.getBytes("image");
                    base64Image = new String(Base64.getEncoder().encode(imageData));
                }
                OrderSubject sub = new OrderSubject(rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        base64Image,
                        rs.getDouble("price"));
                list.add(sub);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public OrderSubject getSubjectbyId(int subject_id) {
        String sql = "select * from Subject where subject_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, subject_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                OrderSubject sub = new OrderSubject(rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        rs.getString("image"),
                        rs.getDouble("price"));
                sub.setDiscount(rs.getDouble("discount"));
                return sub;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderSubject addorderSubject(Account a, Cart cart, double subtotal) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        String sql = "insert into Order values(?,?,?)";
        PreparedStatement ps;
        int orderId = -1;
        try {
            Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getAccount_id());
            ps.setDate(2, currentDate);
            ps.setDouble(3, subtotal);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            String sql2 = "insert into Order_Detail values(?,?,?)";
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            for (Item i : cart.getItems()) {
                ps2.setInt(1, orderId);
                ps2.setInt(2, i.getOrderSubject().getSubject_id());
                ps2.setDouble(3, i.getPrice());
                ps2.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
            String sql3 = "SELECT od.subject_id FROM Order o INNER JOIN Order_Detail od ON o.order_id = od.order_id WHERE o.order_id = ?";
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.setInt(1, orderId);
            ResultSet rs = ps3.executeQuery();

            String sql4 = "INSERT INTO Learner_Subject (learner_id, subject_id, enrolled_date, active, status_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps4 = connection.prepareStatement(sql4);

            while (rs.next()) {
                int learner_id = a.getAccount_id();
                int subject_id = rs.getInt("subject_id");
                Date enroll_date = currentDate;
//                Date end_date = calculateOneYearFromDate(enroll_date);

                ps4.setInt(1, learner_id);
                ps4.setInt(2, subject_id);
                ps4.setDate(3, enroll_date);
                ps4.setBoolean(4, true);
                ps4.setInt(5, 1);
                ps4.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);

            String sqlSubjectIds = "SELECT DISTINCT subject_id FROM Order_Detail WHERE order_id = ?";
            PreparedStatement psSubjectIds = connection.prepareStatement(sqlSubjectIds);
            psSubjectIds.setInt(1, orderId);
            ResultSet subjectIdsResultSet = psSubjectIds.executeQuery();

            List<Integer> subjectIds = new ArrayList<>();
            while (subjectIdsResultSet.next()) {
                int subjectId = subjectIdsResultSet.getInt("subject_id");
                subjectIds.add(subjectId);
            }
            String sqlCourses = "SELECT DISTINCT course_id FROM Course WHERE subject_id IN (" + String.join(", ", Collections.nCopies(subjectIds.size(), "?")) + ")";
            PreparedStatement psCourses = connection.prepareStatement(sqlCourses);
            for (int i = 0; i < subjectIds.size(); i++) {
                psCourses.setInt(i + 1, subjectIds.get(i));
            }
            ResultSet coursesResultSet = psCourses.executeQuery();

            String sqlInsertLearnerCourse = "INSERT INTO Learner_Course (learner_id, course_id, status_id, rate) VALUES (?, ?, 1, 0)";
            PreparedStatement psInsertLearnerCourse = connection.prepareStatement(sqlInsertLearnerCourse);
            psInsertLearnerCourse.setInt(1, a.getAccount_id());

            while (coursesResultSet.next()) {
                int courseId = coursesResultSet.getInt("course_id");
                psInsertLearnerCourse.setInt(2, courseId);
                psInsertLearnerCourse.executeUpdate();
            }

            String sqlCourseIds = "SELECT DISTINCT course_id FROM Learner_Course WHERE learner_id = ?";
            PreparedStatement psCourseIds = connection.prepareStatement(sqlCourseIds);
            psCourseIds.setInt(1, a.getAccount_id());
            ResultSet courseIdsResultSet = psCourseIds.executeQuery();
            List<Integer> courseIds = new ArrayList<>();
            while (courseIdsResultSet.next()) {
                int courseId = courseIdsResultSet.getInt("course_id");
                courseIds.add(courseId);
            }

            String sqlChapters = "SELECT DISTINCT chapter_id FROM Chapter WHERE course_id IN (" + String.join(", ", Collections.nCopies(courseIds.size(), "?")) + ")";
            PreparedStatement psChapters = connection.prepareStatement(sqlChapters);
            for (int i = 0; i < courseIds.size(); i++) {
                psChapters.setInt(i + 1, courseIds.get(i));
            }
            ResultSet chaptersResultSet = psChapters.executeQuery();

            String sqlInsertLearnerChapter = "INSERT INTO Learner_Chapter (learner_id, chapter_id, status_id) VALUES (?, ?, 1)";
            PreparedStatement psInsertLearnerChapter = connection.prepareStatement(sqlInsertLearnerChapter);
            psInsertLearnerChapter.setInt(1, a.getAccount_id());

            String sqlInsertQuizResult = "INSERT INTO Quiz_Result (learner_id, quiz_id, mark, status_id) VALUES (?, ?, 0, 1)";
            PreparedStatement psInsertQuizResult = connection.prepareStatement(sqlInsertQuizResult);
            psInsertQuizResult.setInt(1, a.getAccount_id());

            while (chaptersResultSet.next()) {
                int chapterId = chaptersResultSet.getInt("chapter_id");

                // Kiểm tra xem đã chèn vào Learner_Chapter chưa
                String sqlCheckChapter = "SELECT COUNT(*) FROM Learner_Chapter WHERE learner_id = ? AND chapter_id = ?";
                PreparedStatement psCheckChapter = connection.prepareStatement(sqlCheckChapter);
                psCheckChapter.setInt(1, a.getAccount_id());
                psCheckChapter.setInt(2, chapterId);
                ResultSet checkChapterResultSet = psCheckChapter.executeQuery();
                checkChapterResultSet.next();

                int chapterCount = checkChapterResultSet.getInt(1);

                if (chapterCount == 0) {
                    // Chèn vào Learner_Chapter
                    psInsertLearnerChapter.setInt(2, chapterId);
                    psInsertLearnerChapter.executeUpdate();

                    // Chèn vào Quiz_Result
                    psInsertQuizResult.setInt(2, chapterId);
                    psInsertQuizResult.executeUpdate();
                }
            }

            String sqlGetChapterIds = "SELECT DISTINCT chapter_id FROM Chapter WHERE course_id IN (" + String.join(", ", Collections.nCopies(courseIds.size(), "?")) + ")";
            PreparedStatement psGetChapterIds = connection.prepareStatement(sqlGetChapterIds);
            for (int i = 0; i < courseIds.size(); i++) {
                psGetChapterIds.setInt(i + 1, courseIds.get(i));
            }
            ResultSet chapterIdsResultSet = psGetChapterIds.executeQuery();

            List<Integer> chapters = new ArrayList<>();
            while (chapterIdsResultSet.next()) {
                int chapterId = chapterIdsResultSet.getInt("chapter_id");
                chapters.add(chapterId);
            }

            String sqlGetLessonIdsForChapters = "SELECT DISTINCT l.lesson_id FROM Lesson l "
                    + "WHERE l.chapter_id IN (" + String.join(", ", Collections.nCopies(chapters.size(), "?")) + ")";
            PreparedStatement psGetLessonIdsForChapters = connection.prepareStatement(sqlGetLessonIdsForChapters);
            for (int i = 0; i < chapters.size(); i++) {
                psGetLessonIdsForChapters.setInt(i + 1, chapters.get(i));
            }
            ResultSet lessonIdsResultSet = psGetLessonIdsForChapters.executeQuery();

            String sqlInsertLearnerLesson = "INSERT INTO Learner_Lesson (learner_id, lesson_id, status_id) VALUES (?, ?, 0)";
            PreparedStatement psInsertLearnerLesson = connection.prepareStatement(sqlInsertLearnerLesson);
            psInsertLearnerLesson.setInt(1, a.getAccount_id());

            // ...
// Check and insert into Learner_Lesson
            while (lessonIdsResultSet.next()) {
                int lessonId = lessonIdsResultSet.getInt("lesson_id");

                // Kiểm tra xem đã chèn vào Learner_Lesson chưa
                String sqlCheckLesson = "SELECT COUNT(*) FROM Learner_Lesson WHERE learner_id = ? AND lesson_id = ?";
                PreparedStatement psCheckLesson = connection.prepareStatement(sqlCheckLesson);
                psCheckLesson.setInt(1, a.getAccount_id());
                psCheckLesson.setInt(2, lessonId);
                ResultSet checkLessonResultSet = psCheckLesson.executeQuery();
                checkLessonResultSet.next();

                int lessonCount = checkLessonResultSet.getInt(1);

                if (lessonCount == 0) {
                    // Chèn vào Learner_Lesson
                    psInsertLearnerLesson.setInt(2, lessonId);
                    psInsertLearnerLesson.executeUpdate();
                }
            }

// ...
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e2) {
            }
            System.out.println(e);
        }

        return null;
    }

    private Date calculateOneYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return new Date(calendar.getTime().getTime());
    }

    public List<Order> getOrdersByLecturerId(int lecturerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.order_date, COUNT(DISTINCT o.order_id) AS total_orders, SUM(o.total_money) AS total_money\n"
                + "FROM Order o\n"
                + "JOIN Order_Detail od ON o.order_id = od.order_id\n"
                + "JOIN Subject s ON od.subject_id = s.subject_id\n"
                + "WHERE s.lecturer_id = ?\n"
                + "GROUP BY o.order_date;";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, lecturerId); // Truyền giá trị của lecturerId vào câu lệnh SQL
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Date orderDate = rs.getDate("order_date");
                    double totalMoney = rs.getDouble("total_money");

                    Order order = new Order(orderDate, totalMoney);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving orders: " + e.getMessage());
        }
        return orders;
    }

    public void updateSubjectStatus(int learnerId, int subjectId, int statusId) {
        PreparedStatement ps;
        ResultSet rs;
        String sql = "UPDATE Learner_Subject SET status_id = ? WHERE learner_id = ? AND subject_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, statusId);
            ps.setInt(2, learnerId);
            ps.setInt(3, subjectId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getBoughtSubjectsByLearnerId(int learnerId) {
        List<Integer> boughtSubjects = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT subject_id FROM Learner_Subject WHERE learner_id = ? AND active = 1"; // status_id = 1 có nghĩa là đã mua
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, learnerId);
            rs = ps.executeQuery();
            while (rs.next()) {
                boughtSubjects.add(rs.getInt("subject_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boughtSubjects;
    }

    public Learner_Subject getLearnerSubjectBySubjectId(int learner_id, int subject_id) {
        String sql = "SELECT *\n"
                + "  FROM Learner_Subject\n"
                + "  where Learner_Subject.learner_id = ?\n"
                + "  and Learner_Subject.subject_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, learner_id);
            st.setInt(2, subject_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Learner_Subject c = new Learner_Subject();
                c.setId(rs.getString("id"));
                c.setLearner_id(rs.getString("learner_id"));
                c.setSubject_id(rs.getString("subject_id"));
                c.setEnrolled_date(rs.getDate("enrolled_date"));
                c.setStatus_id(rs.getString("status_id"));
                c.setActive(rs.getString("active"));
                return c;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public void enrollLearnerSubject(int learnerId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Learner_Subject (learner_id, subject_id, enrolled_date, status_id, active) VALUES (?, ?, NOW(), 1, 1)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, learnerId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        }
    }

    public void enrollLearnerCourse(int learnerId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Learner_Course (learner_id, course_id, status_id, rate) "
                + "SELECT ?, c.course_id, 1, 0 FROM Course c WHERE c.subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, learnerId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        }
    }

    public void enrollLearnerChapter(int learnerId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Learner_Chapter (learner_id, chapter_id, status_id) "
                + "SELECT ?, ch.chapter_id, 1 FROM Chapter ch "
                + "JOIN Course c ON ch.course_id = c.course_id WHERE c.subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, learnerId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        }
    }

    public void enrollLearnerLesson(int learnerId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Learner_Lesson (learner_id, lesson_id, status_id) "
                + "SELECT ?, l.lesson_id, 1 FROM Lesson l "
                + "JOIN Chapter ch ON l.chapter_id = ch.chapter_id "
                + "JOIN Course c ON ch.course_id = c.course_id WHERE c.subject_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, learnerId);
            ps.setInt(2, subjectId);
            ps.executeUpdate();
        }
    }

}
