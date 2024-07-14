<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Lessons</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            .pagination {
                display: flex;
                justify-content: center;
                padding: 10px 0;
            }
            .pagination a {
                color: black;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
                border-radius: 4px;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }
            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .table th, .table td {
                vertical-align: middle;
            }
        </style>
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h1>List Lessons</h1>
                <a href="addLesson?chapterid=${chapterid}" class="btn btn-success">Add Lesson</a>
            </div>
            <c:if test="${not empty mess}">
                <div class="alert alert-success" role="alert">
                    ${mess}
                </div>
            </c:if>
            <form action="manageLesson" method="get" class="d-flex mb-4">
                <input type="hidden" name="chapterid" value="${chapterid}"/>
                <input name="txtSearch" value="${param.txtSearch}" type="text" class="form-control me-2" placeholder="Search Here" />
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
            <c:if test="${param.txtSearch != ''}">
                <p class="text-secondary">Found ${totalRecords} result(s) for '<strong>${param.txtSearch}</strong>'</p>
            </c:if>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                    <tr>
                        <th>No</th>
                        <th>Lesson Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listLesson}">
                        <tr>
                            <td>${item.lesson_no}</td>
                            <td>${item.lesson_name}</td>
                            <td>
                                <a href="editLesson?lessonid=${item.lesson_id}" class="btn btn-primary btn-sm me-2">Edit</a>
                                <a onclick="return confirm('Do you want to delete this lesson, this action can not be undone')" href="deleteLesson?lessonid=${item.lesson_id}&chapterid=${chapterid}" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="manageLesson?chapterid=${chapterid}&p=${i}&txtSearch=${param.txtSearch}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
            </div>
                           <button onclick="goBack()" class="formbold-btn">Back to Chapter</button>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXlLvAwCEt3Gv9Gz87GX4nKCkQqAwNLHJJQGcFUU8O8yDN/4pQ60y1QQB1Sl" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWIY6ZgDMmPBZ+YFfnC1bbVQU4BU8I1v7tft/tV7fRXewp5oBS0b2HN6jJ" crossorigin="anonymous"></script>
        <script>
        function goBack() {
            window.history.back();
        }
        </script>
    </body>
</html>
