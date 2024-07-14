<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Certificate Template</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Lora:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                font-family: 'Roboto', sans-serif;
            }
            .container {
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                width: 900px;
                max-width: 100%;
                border: 5px solid #003366;
                overflow: hidden;
                display: flex;
                flex-direction: column;
            }
            header {
                display: flex;
                justify-content: center;
                align-items: center;
                background: linear-gradient(to right, #003366, #336699);
                color: white;
                padding: 15px 25px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                font-family: 'Roboto', sans-serif;
                width: 100%;
            }
            header a {
                text-decoration: none !important;
                color: white !important;
                font-weight: bold;
                font-size: 16px;
            }
            header a:hover {
                color: #ffd700 !important;
                transition: color 0.3s ease-in-out;
            }
            .content {
                display: flex;
                padding: 20px;
                flex: 1;
            }
            .sidebar {
                width: 30%;
                padding: 20px;
                background: #f4f4f4;
                border-right: 2px solid #004494;
            }
            .main-content {
                width: 100%;
                padding: 20px;
                background: #fff;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
            }
            .cursive {
                font-family: 'Lora', serif;
                font-size: 36px;
            }
            .sans {
                font-family: 'Roboto', sans-serif;
            }
            .bold {
                font-weight: bold;
            }
            .underline {
                border-bottom: 1px solid #777;
                padding-bottom: 5px;
                margin-bottom: 15px;
            }
            .certificate-title {
                margin: 20px 0;
                font-size: 24px;
                color: #003366;
                text-align: center;
            }
            .certificate-name {
                font-size: 22px;
                margin: 20px 0;
                padding-bottom: 10px;
                border-bottom: 2px solid #003366;
                text-align: center;
            }
            .certificate-details {
                margin: 20px 0;
                font-size: 18px;
                text-align: center;
            }
            .certificate-footer {
                margin-top: 40px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                font-size: 14px;
                width: 100%;
            }
            .certificate-footer div {
                text-align: center;
            }
            .certificate-footer .underline {
                border-bottom: 1px solid #777;
                padding-bottom: 5px;
                margin-bottom: 5px;
            }
            .footer-item {
                width: 30%;
            }
            .course-list {
                text-align: left;
                padding: 0 20px;
                font-size: 16px;
            }
            .course-list h3 {
                font-size: 20px;
                color: #fff;
                background-color: #003366;
                padding: 10px;
                border-radius: 5px;
                text-align: center;
                margin-bottom: 20px;
            }
            .course-list ul {
                list-style-type: none;
                padding: 0;
            }
            .course-list li {
                margin-bottom: 10px;
                padding: 5px 0;
                border-bottom: 1px solid #ddd;
            }
            .course-list li:last-child {
                border-bottom: none;
            }
            .no-course .sidebar {
                display: none;
            }
            .no-course .main-content {
                width: 100%;
            }


        </style>
    </head>
    <body>
        <div class="container">
            <header>
                <a href="#" onclick="back()">Back To Management Certificate</a>
            </header>
            <div class="content">
                <c:if test="${not empty data_course}">
                    <div class="sidebar">
                        <div class="course-list">
                            <h3>${data_course.size()} Courses</h3>
                            <ul>
                                <c:forEach items="${data_course}" var="courseitem">
                                    <li>${courseitem.getCourse_name()}</li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:if>

                <div class="main-content">
                    <div class="certificate-title">
                        <div class="cursive center">${course.getCourse_name() != null ? course.getCourse_name() : subject.getSubject_name()}</div>
                    </div>
                    <div class="certificate-name underline">
                        <div class="sans bold center">${account.getFullname()}</div>
                    </div>
                    <div class="certificate-details">
                        <p class="sans bold center">has successfully completed the online</p>
                        <p class="sans center">${course.getDescription() != null ? course.getDescription() : subject.getDescription()}</p>
                    </div>
                    <div class="certificate-footer">
                        <div>
                            <p class="sans"><span class="head">Learn <i class="fa-brands fa-x-twitter"></i></span></p>
                            <p class="underline"></p>
                            <p class="sans bold">${lecturer.getFullname()}</p>
                        </div>
                        <div></div>
                        <div>
                            <p class="sans">Date Completed</p>
                            <p class="underline"></p>
                            <p class="sans bold">${certificate.getIssue_date()}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function back() {
                window.history.back();
            }
        </script>
    </body>
</html>
