<%-- 
    Document   : managementchapter.jsp
    Created on : Jun 15, 2024, 5:18:58 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chapter Management</title>
        <meta charset="utf-8">
        <title>Courses | Education</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
                integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>


        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="template course_detail/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="template course_detail/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="template course_detail/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="template course_detail/css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/stylemanagementcourse.css"/>
        <style>
            body {
                font-family: Arial, sans-serif;
                padding: 20px;
            }
            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #003366;
                color: white;
                padding: 10px 20px;
                border-radius: 5px;
            }
            h1 {
                text-align: center;
            }
            .button {
                display: inline-block;
                margin: 10px 0;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #007bff;
                color: white;
            }
            .actions {
                text-align: center;
            }
            .action-button {
                margin: 0 5px;
                cursor: pointer;
            }
            .edit {
                color: #ffc107;
            }
            .delete {
                color: #dc3545;
            }
            .view {
                color: #28a745;
            }
            .mess {
                color: green;
                font-size: 12px;
                margin-top: 5px;
            }

        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>${course.getCourse_name()}</h1>
        <c:if test="${checkLearnerSubject == false}">
            <a href="addchapter?subject_id=${subject_id}&course_id=${course_id}" class="button">+ Chapter</a>
        </c:if>
        <a href="managementcourse?subject_id=${subject_id}" class="button">Back to Courses</a>
        <div class="mess" name="mess">${mess}</div>
        <c:choose>
            <c:when test="${data_chapter == null || data_chapter.isEmpty()}">

                <img src="img/isEmpty.png" alt="alt"/>
            </c:when>
            <c:otherwise>
                <table id="courseTable" class="display">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Chapter Name</th>
                            <th>Chapter No</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="counter" value="1" />
                        <c:forEach items="${data_chapter}" var="chapter">
                            <tr>
                                <td class="long-content" >${counter}</td>
                                <td class="long-content">${chapter.getChapter_name()}</td>
                                <td class="long-content">${chapter.getChapter_no()}</td>
                                <td>
                                    <c:if test="${checkLearnerSubject == false}">
                                        <a href="updatechapter?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter.getChapter_id()}"><span class="action-button edit">✏</span></a>
                                        <a href="deletechapter?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter.getChapter_id()}" class="delete"><span class="action-button delete">🗑️</span></a>
                                    </c:if>
                                    <a href="manageLesson?chapterid=${chapter.getChapter_id()}"><span class="action-button view">👁️</span></a>                                </td>

                                <c:set var="counter" value="${counter + 1}" />
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    </tbody>
</table>
<script>
    //Delete

    const deletes = document.querySelectorAll(".delete");
    deletes.forEach(deleteLink => {
        deleteLink.addEventListener("click", (event) => {
            if (!confirm("Are you sure you want to delete Chapter(All Lesson in Chapter)?")) {
                event.preventDefault(); // Ngăn chặn hành động xóa nếu người dùng chọn "Cancel"
            }
        });
    });
</script>
</body>
</html>
