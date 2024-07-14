<%-- 
    Document   : addsubject
    Created on : May 27, 2024, 12:19:13 AM
    Author     : slhoa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Subject</title>

        <style>
            /* CSS cho form thêm môn học */
            .add-subject-form {
                background-color: #512DA8; /* Màu tím chủ đạo */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            .form-group label {
                color: white; /* Màu chữ trắng cho nhãn */
            }

            .form-control {
                background-color: #673AB7; /* Màu nền input */
                border: 1px solid #7B1FA2; /* Viền input */
                color: white; /* Màu chữ trắng trong input */
            }
            .btn {
                background-color: #9C27B0; /* Màu tím nổi bật */
                border: none;
                color: #fff; /* Chữ nút màu trắng */
            }
            .btn:hover {
                background-color: #BA68C8; /* Hiệu ứng hover */
            }
        </style>

        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Favicon -->
        <link rel="shortcut icon" href="img/fav.png" />
        <!-- Author Meta -->
        <meta name="author" content="colorlib" />
        <!-- Meta Description -->
        <meta name="description" content="" />
        <!-- Meta Keyword -->
        <meta name="keywords" content="" />
        <!-- meta character set -->
        <meta charset="UTF-8" />
        <!-- Site Title -->
        <title>Subject Add</title>

        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:900|Roboto:400,400i,500,700" rel="stylesheet" />
        <!--
          CSS
          =============================================
        -->
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.css" />
        <link rel="stylesheet" href="css/magnific-popup.css" />
        <link rel="stylesheet" href="css/owl.carousel.css" />
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/hexagons.min.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/themify-icons/0.1.2/css/themify-icons.css" />
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        <link href="css/subjectlist.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <section class="banner-area">
            <div class="container">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-12 banner-right">
                        <h1 class="text-white">
                            Edit Subject
                        </h1>
                        <a href="subjectlist"><input class="btn" type="submit" value="Back to Subject List"/></a>
                        <a href="subjectmanage.jsp"><input class="btn" type="submit" value="Back to Subject Manage"/></a>
                    </div>
                    
                </div>
            </div>
        </section>
        <h1 style="color: red">${mess}</h1>
        <form action="editsubject" method="post" class="add-subject-form mt-4">
            <input type="hidden" name="subject_id" value="${subject.subject_id}"/>
            <div class="form-group">
                <label for="subjectName">Name:</label>
                <input value="${subject.subject_name}" type="text" class="form-control" id="subjectName" name="subjectname" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required>${subject.description}</textarea>
            </div>
            <div class="form-group">
                <label for="image">Image :</label>
                <input value="${subject.image}" class="form-control" id="description" name="image" required></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input placeholder="Price > 0" value="${subject.price}" type="number" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="discount">Discount: </label>
                <input placeholder="discount need to 0->100%"
                    value="${subject.discount}" type="number" class="form-control" id="discount" name="discount" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>

                <select class="form-control" id="category" name="category">
                    <c:forEach items ="${listCate}" var="o" >
                        <option ${o.category_id == subject.cate.category_id ? 'selected' : ''} value="${o.category_id}"> ${o.category_name} </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Edit Subject</button>
        </form>

    </body>
</html>

</body>
</html>
