///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package dao;
//
//import dal.DBContext;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//import model.Category;
//import model.Course;
//import model.Learner_Course;
//import model.Learner_Subject;
//import model.Subject;
//
///**
// *
// * @author trungnh
// */
//public class Subject1DAO extends DBContext {
//
//    // Lay tat ca cac Subject
//    public List<Subject> getAllSubject() {
//        List<Subject> list = new ArrayList<>();
//        String query = "SELECT\n"
//                + "Subject.*,\n"
//                + "Account.fullname,\n"
//                + "ROUND(\n"
//                + "(SELECT AVG(avg_rate) \n"
//                + "FROM\n"
//                + "(SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
//                + "FROM Learner_Course\n"
//                + "INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
//                + "GROUP BY Course.course_id) AS course_rate\n"
//                + "WHERE course_rate.course_id IN (SELECT Course.course_id\n"
//                + "FROM Course\n"
//                + "WHERE Course.subject_id = Subject.subject_id)), 1) AS subject_rate\n"
//                + "FROM Subject\n"
//                + "INNER JOIN Account ON Subject.lecturer_id = Account.account_id";
//        try {
//            PreparedStatement st = connection.prepareStatement(query);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                byte[] imageByte = rs.getBytes("image");
//                String image = new String(Base64.getEncoder().encode(imageByte));
//                Subject s = new Subject();
//                s.setSubject_id(rs.getString("subject_id"));
//                s.setSubject_name(rs.getString("subject_name"));
//                s.setDescription(rs.getString("description"));
//                s.setImage(image);
//                s.setPrice(rs.getInt("price"));
//                s.setDiscount(rs.getString("discount"));
//                s.setSold(rs.getString("sold"));
//                s.setCreated_date(rs.getString("created_date"));
//                s.setUpdated_date(rs.getString("updated_date"));
//                s.setCategory_id(rs.getString("category_id"));
//                s.setLecturer_id(rs.getString("lecturer_id"));
//                s.setLecturer_name(rs.getString("fullname"));
//                s.setRate_subject(rs.getString("subject_rate"));
//                list.add(s);
//            }
//        } catch (SQLException e) {
//        }
//        return list;
//    }
//
//    // Lay tat ca cac category 
//    public List<Category> getAllCategory() {
//        List<Category> list = new ArrayList<>();
//        String sql = "SELECT *\n"
//                + "  FROM Category";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Category cy = new Category();
//                cy.setCategory_id(rs.getString("category_id"));
//                cy.setCategory_name(rs.getString("category_name"));
//
//                list.add(cy);
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return list;
//    }
//
//    // Lay tat ca subject theo categoryId
//    public List<Subject> getAllSubjectByCtegoryId(int category_id) {
//        List<Subject> list = new ArrayList<>();
//        String sql = "SELECT\n"
//                + "Subject.*,\n"
//                + "Account.fullname,\n"
//                + "ROUND(\n"
//                + "(SELECT AVG(avg_rate) \n"
//                + "FROM\n"
//                + "(SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
//                + "FROM Learner_Course\n"
//                + "INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
//                + "GROUP BY Course.course_id) AS course_rate\n"
//                + "WHERE course_rate.course_id IN (SELECT Course.course_id\n"
//                + " FROM Course\n"
//                + " WHERE Course.subject_id = Subject.subject_id)), 1) AS subject_rate\n"
//                + "FROM Subject\n"
//                + "INNER JOIN Account ON Subject.lecturer_id = Account.account_id\n"
//                + "INNER JOIN Category ON Subject.category_id = Category.category_id\n"
//                + "                and Category.category_id = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, category_id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                byte[] imageByte = rs.getBytes("image");
//                String image = new String(Base64.getEncoder().encode(imageByte));
//                Subject s = new Subject();
//                s.setSubject_id(rs.getString("subject_id"));
//                s.setSubject_name(rs.getString("subject_name"));
//                s.setDescription(rs.getString("description"));
//                s.setImage(image);
//                s.setPrice(rs.getInt("price"));
//                s.setDiscount(rs.getString("discount"));
//                s.setSold(rs.getString("sold"));
//                s.setCreated_date(rs.getString("created_date"));
//                s.setUpdated_date(rs.getString("updated_date"));
//                s.setCategory_id(rs.getString("category_id"));
//                s.setLecturer_id(rs.getString("lecturer_id"));
//                s.setLecturer_name(rs.getString("fullname"));
//                s.setRate_subject(rs.getString("subject_rate"));
//                list.add(s);
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return list;
//    }
//
//    // Lay related Subject
//    public List<Subject> getRelatedSubjectByCategoryID(int subject_id, int category_id) {
//        List<Subject> list = new ArrayList<>();
//        String sql = "SELECT\n"
//                + "Subject.*,\n"
//                + "Account.fullname,\n"
//                + "ROUND(\n"
//                + "(SELECT AVG(avg_rate) \n"
//                + "FROM\n"
//                + "(SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
//                + "FROM Learner_Course\n"
//                + "INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
//                + "GROUP BY Course.course_id) AS course_rate\n"
//                + "WHERE course_rate.course_id IN (SELECT Course.course_id\n"
//                + "                                     FROM Course\n"
//                + "                                     WHERE Course.subject_id = Subject.subject_id)), 1) AS subject_rate\n"
//                + "FROM Subject\n"
//                + "INNER JOIN Account ON Subject.lecturer_id = Account.account_id\n"
//                + "INNER JOIN Category ON Subject.category_id = Category.category_id\n"
//                + "                and Category.category_id = ?\n"
//                + "                and Subject.subject_id <> ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, category_id);
//            st.setInt(2, subject_id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                byte[] imageByte = rs.getBytes("image");
//                String image = new String(Base64.getEncoder().encode(imageByte));
//                Subject s = new Subject();
//                s.setSubject_id(rs.getString("subject_id"));
//                s.setSubject_name(rs.getString("subject_name"));
//                s.setDescription(rs.getString("description"));
//                s.setImage(image);
//                s.setPrice(rs.getInt("price"));
//                s.setDiscount(rs.getString("discount"));
//                s.setSold(rs.getString("sold"));
//                s.setCreated_date(rs.getString("created_date"));
//                s.setUpdated_date(rs.getString("updated_date"));
//                s.setCategory_id(rs.getString("category_id"));
//                s.setLecturer_id(rs.getString("lecturer_id"));
//                s.setLecturer_name(rs.getString("fullname"));
//                s.setRate_subject(rs.getString("subject_rate"));
//                list.add(s);
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return list;
//    }
//
//    // Lay Subject 
//    public Subject getSubject(int subject_id) {
//        String sql = "SELECT\n"
//                + " Subject.*,\n"
//                + " Account.fullname,\n"
//                + " ROUND(\n"
//                + " (SELECT AVG(avg_rate) \n"
//                + " FROM\n"
//                + " (SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
//                + " FROM Learner_Course\n"
//                + " INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
//                + " GROUP BY Course.course_id) AS course_rate\n"
//                + " WHERE course_rate.course_id IN (SELECT Course.course_id\n"
//                + " FROM Course\n"
//                + " WHERE Course.subject_id = Subject.subject_id))\n"
//                + "   , 1) AS subject_rate\n"
//                + "FROM Subject\n"
//                + "INNER JOIN Account ON Subject.lecturer_id = Account.account_id\n"
//                + "where Subject.subject_id = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, subject_id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                byte[] imageByte = rs.getBytes("image");
//                String image = new String(Base64.getEncoder().encode(imageByte));
//                Subject s = new Subject();
//                s.setSubject_id(rs.getString("subject_id"));
//                s.setSubject_name(rs.getString("subject_name"));
//                s.setDescription(rs.getString("description"));
//                s.setImage(image);
//                s.setPrice(rs.getInt("price"));
//                s.setDiscount(rs.getString("discount"));
//                s.setSold(rs.getString("sold"));
//                s.setCreated_date(rs.getString("created_date"));
//                s.setUpdated_date(rs.getString("updated_date"));
//                s.setCategory_id(rs.getString("category_id"));
//                s.setLecturer_id(rs.getString("lecturer_id"));
//                s.setLecturer_name(rs.getString("fullname"));
//                s.setRate_subject(rs.getString("subject_rate"));
//                return s;
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return null;
//    }
//
//    public List<Subject> filterSubject(String[] categories, String[] prices, String key) {
//        List<Subject> list = new ArrayList<>();
//        String sql = "SELECT\n"
//                + "Subject.*,\n"
//                + "Account.fullname,\n"
//                + "ROUND(\n"
//                + "(SELECT AVG(avg_rate) \n"
//                + "FROM  \n"
//                + "(SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
//                + "FROM Learner_Course\n"
//                + "INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
//                + "GROUP BY Course.course_id) AS course_rate\n"
//                + "WHERE course_rate.course_id IN (SELECT Course.course_id\n"
//                + "FROM Course\n"
//                + "WHERE Course.subject_id = Subject.subject_id))\n"
//                + "   , 1) AS subject_rate\n"
//                + "FROM Subject\n"
//                + "INNER JOIN Account ON Subject.lecturer_id = Account.account_id, Category \n"
//                + "WHERE Subject.category_id = Category.category_id";
//
//        //Filter by categories
////        if ((categories == null || categories.length == 0) && (prices == null || prices.length == 0) && key.isEmpty()  ) {
////            sql += "";
////        } else 
//        if (categories.length != 0) {
////            sql += "   and Category.category_name in (0";
//////            for (String categorie : categories) {
//////                sql += "," + categorie;
//////            }
//////            sql += ")";
//
//            sql += " and Category.category_name in (";
//            for (int i = 0; i < categories.length; i++) {
//                sql = sql + "'" + categories[i] + "'" + ",";
//            }
//            if (sql.endsWith(",")) {
//                sql = sql.substring(0, sql.length() - 1);
//            }
//            sql += ") ";
//        }
//        if (prices.length != 0) {
//            if (prices.length == 4) {
//                sql += " and Subject.price between 0 and 99999999";
//            } else if (prices.length == 3) {
//                if (prices[0].equals("1")) { // < 1tr
//                    if (prices[1].equals("2") && prices[2].equals("3")) {
//                        sql += " and Subject.discount > 0 "
//                                + "and (Subject.price between 100 and 500"
//                                + " or Subject.price < 100) ";
//                    }
//                    if (prices[1].equals("2") && prices[2].equals("4")) {
//                        sql += " and Subject.discount > 0 \n"
//                                + "and (Subject.price < 100 "
//                                + "or Subject.price > 500) ";
//                    }
//                    if (prices[1].equals("3") && prices[2].equals("4")) {
//                        sql += " and Subject.discount > 0\n"
//                                + "and  Subject.price between 100 and 500"
//                                + "and  Subject.price > 500";
//                    }
//
//                } else if (prices[0].equals("2")) {
//                    if (prices[1].equals("3") && prices[2].equals("4")) {
//                        sql += " and (Subject.price between 100 and 500\n"
//                                + "or Subject.price > 500"
//                                + "or Subject.price < 100)";
//                    }
//                }
//            } else if (prices.length == 2) {
//                if (prices[0].equals("1")) { // < 1tr
//                    if (prices[1].equals("2")) {
//                        sql += " and Subject.price < 100"
//                                + " and Subject.discount > 0";
//                    }
//                    if (prices[1].equals("3")) {
//                        sql += " and Subject.price between 100 and 500\n"
//                                + "and Subject.discount > 0";
//                    }
//                    if (prices[1].equals("4")) {
//                        sql += " and Subject.price > 500\n"
//                                + "and Subject.discount > 0";
//                    }
//
//                } else if (prices[0].equals("2")) {
//                    if (prices[1].equals("3")) {
//                        sql += " and (Subject.price < 100 \n"
//                                + "or Subject.price between 100 and 500)";
//                    }
//                    if (prices[1].equals("4")) {
//                        sql += " and (Subject.price < 100\n"
//                                + "or Subject.price > 500) ";
//                    }
//                } else if (prices[0].equals("3")) {
//                    if (prices[1].equals("4")) {
//                        sql += " and (Subject.price between 100 and 500 \n"
//                                + "or Subject.price > 500)";
//                    }
//                }
//            } else {
//                if (prices[0].equals("1")) {
//                    sql += "and Subject.discount > 0 ";
//                }
//                if (prices[0].equals("2")) {
//                    sql += "and Subject.price < 100 ";
//                }
//                if (prices[0].equals("3")) {
//                    sql += " and Subject.price between 100 and 500 ";
//                }
//                if (prices[0].equals("4")) {
//                    sql += " and Subject.price > 500 ";
//                }
//            }
//        }
//        if (!key.isEmpty()) {
//            sql += " and Subject.subject_name like '%" + key + "%' ";
//        }
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                byte[] imageByte = rs.getBytes("image");
//                String image = new String(Base64.getEncoder().encode(imageByte));
//                Subject s = new Subject();
//                s.setSubject_id(rs.getString("subject_id"));
//                s.setSubject_name(rs.getString("subject_name"));
//                s.setDescription(rs.getString("description"));
//                s.setImage(image);
//                s.setPrice(rs.getInt("price"));
//                s.setDiscount(rs.getString("discount"));
//                s.setSold(rs.getString("sold"));
//                s.setCreated_date(rs.getString("created_date"));
//                s.setUpdated_date(rs.getString("updated_date"));
//                s.setCategory_id(rs.getString("category_id"));
//                s.setLecturer_id(rs.getString("lecturer_id"));
//                s.setLecturer_name(rs.getString("fullname"));
//                s.setRate_subject(rs.getString("subject_rate"));
//                list.add(s);
//            }
//        } catch (SQLException e) {
//        }
//
//        return list;
//    }
//
//    // Lay active subject by LearnerId
//    public int getSubjectActive(int subject_id, int account_id) {
//        String sql = "SELECT Learner_Subject.active\n"
//                + "  FROM Learner_Subject, Account, Subject\n"
//                + "  where Learner_Subject.learner_id = Account.account_id\n"
//                + "  and Subject.subject_id = Learner_Subject.subject_id\n"
//                + "  and Subject.subject_id = ?\n"
//                + "  and Account.account_id = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, subject_id);
//            st.setInt(2, account_id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                return rs.getInt("active");
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return 0;
//    }
//
//    // Update status course
//    public void updateStatusSubject(Learner_Subject s) {
//        String sql = "UPDATE Learner_Subject\n"
//                + "   SET status_id = 1\n"
//                + " WHERE Learner_Subject.subject_id = ?\n"
//                + " and Learner_Subject.learner_id = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, s.getSubject_id());
//            st.setString(2, s.getLearner_id());
//
//            st.executeUpdate();
//        } catch (SQLException e) {
//
//        }
//    }
//
//    // Lay va tra ve trang thai cua course cua learner
//    public Learner_Subject getLearnerSubjectBySubjectId(int learner_id, int subject_id) {
//        String sql = "SELECT *\n"
//                + "  FROM Learner_Subject\n"
//                + "  where Learner_Subject.learner_id = ?\n"
//                + "  and Learner_Subject.subject_id = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, learner_id);
//            st.setInt(2, subject_id);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Learner_Subject c = new Learner_Subject();
//                c.setId(rs.getString("id"));
//                c.setLearner_id(rs.getString("learner_id"));
//                c.setSubject_id(rs.getString("subject_id"));
//                c.setEnroll_date(rs.getString("enrolled_date"));
//                c.setStatus_id(rs.getString("status_id"));
//                c.setActive(rs.getString("active"));
//                return c;
//            }
//
//        } catch (SQLException e) {
//
//        }
//
//        return null;
//    }
//
//    // Lay Learner_Subject
//    public List<Learner_Subject> getLearnerSubject() {
//        List<Learner_Subject> list = new ArrayList<>();
//        String sql = "SELECT *\n"
//                + "  FROM Learner_Subject";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//            while (rs.next()) {
//                Learner_Subject c = new Learner_Subject();
//                c.setId(rs.getString("id"));
//                c.setLearner_id(rs.getString("learner_id"));
//                c.setSubject_id(rs.getString("subject_id"));
//                c.setEnroll_date(rs.getString("enrolled_date"));
//                c.setStatus_id(rs.getString("status_id"));
//                c.setActive(rs.getString("active"));
//                list.add(c);
//            }
//        } catch (SQLException e) {
//        }
//        return list;
//    }
//} //end dao
