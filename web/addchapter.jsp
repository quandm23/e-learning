<%-- 
    Document   : addchapter.jsp
    Created on : Jun 16, 2024, 4:23:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Chapter</title>
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
             .err {
                color: red;
                font-size: 12px;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include> 
            <div class="container">
                <h1 class="text-center">Add Chapter</h1>
                <div class="err" name="err">${err}</div>
                <div class="main-content">
                    <form id="chapterForm" action="addchapter" method="Post">
                        <label for="chapter_no">Chapter No:</label>
                        <input type="text" id="chapter_no" name="chapter_no" value="${maxchapterno +1}" readonly>
                    <label for="chapter_name">Chapter Name:</label>
                    <input type="text" id="chapter_name" name="chapter_name" value="${chapter_name}">
                    <div class="hidden err" id="error_chapter_name"></div>
                    <input class="hidden" name="subject_id" value="${subject_id}"/>
                    <input class="hidden"  name="course_id" id="course_id" value="${course_id}"/>
                    <button type="submit" id="submit">Add</button>
                    <button type="button" id="cancel" ><a href="managementchapter?subject_id=${subject_id}&course_id=${course_id}">Cancel</a></button>
                </form>
            </div>
        </div>

        <script>
            const alphanumericRegex =  /^(?!^\d+$)[a-zA-Z0-9].*$/;
            const  submit = document.querySelector("#submit");
            const  chapterForm = document.querySelector("#chapterForm");
            submit.addEventListener("click", (event) => {
                let chapter_name = document.querySelector("#chapter_name");
                let chapter_name_value = chapter_name.value.trim();
                let isValid = true;
                let error_chapter_name = document.querySelector("#error_chapter_name");
                if (chapter_name_value.length <= 0 || chapter_name_value.length > 100 || !alphanumericRegex.test(chapter_name_value)) {
                    error_chapter_name.classList.remove("hidden");
                    error_chapter_name.innerHTML = 'Chapter name must be between 1 and 100 characters alphanumeric characters.';
                    isValid = false;
                } else {
                    error_chapter_name.innerHTML = "";
                    error_chapter_name.classList.add('hidden');
                }
                if (!isValid) {
                    event.preventDefault();
                } else {
                    chapterForm.submit(); // Submit form if validation passes
                }
            });
        </script>
    </body>
</html>
