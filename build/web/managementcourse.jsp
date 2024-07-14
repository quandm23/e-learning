<%-- 
    Document   : course_detai
    Created on : Sep 19, 2023, 1:31:08 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

    <head>
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
            .error {
                color: red;
                font-size: 12px;
                margin-top: 5px;
            }
            .message {
                color: green;
                font-size: 12px;
                margin-top: 5px;
            }
        </style>

    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>  

            <div class="container">
                <h1 class="text-center">${subject.getSubject_name()}</h1>
            <div class="main-content">
                <c:if test="${checkLearnerSubject == false}">
                    <button class="add-course-btn">+ Course</button>
                </c:if>
                <a href="subjectmanage"><button class="add-course-btn">Back To Management Subject</button></a>
                <div class="error" name="err">${err}</div>
                <div class="message" name="mess">${mess}</div>
                <form id="courseForm" class="hidden" action="managementcourse" method="Get">
                    <label for="course_no">Course No:</label>
                    <input type="text" id="course_no" name="course_no" value="${maxcourseno +1}" readonly>
                    <label for="course_name">Course Name:</label>
                    <input type="text" id="course_name" name="course_name" value="${course_name}">
                    <div class="hidden error" id="error_course_name"></div>
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" >${description}</textarea>
                    <div class="hidden error" id="error_description"></div>
                    <input class="hidden" name="subject_id" value="${subject_id}"/>
                    <input class="hidden"  name="course_id" id="course_id" value="${course_id}"/>
                    <button type="submit" id="submit" name="action"></button>
                    <button type="button" id="cancel" >Cancel</button>

                </form>
            </div>

            <c:choose>
                <c:when test="${data_course == null || data_course.isEmpty()}">
                    <img src="img/isEmpty.png" alt="alt"/>
                </c:when>
                <c:otherwise>
                    <table id="courseTable" class="display">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Course Name</th>
                                <th>Course No</th>
                                <th>Description</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="counter" value="1" />
                            <c:forEach items="${data_course}" var="course">
                                <tr>
                                    <td class="long-content" >${counter}</td>
                                    <td class="long-content">${course.getCourse_name()}</td>
                                    <td class="long-content">${course.getCourse_no()}</td>
                                    <td class="long-content">${course.getDescription()}</td>
                                    <td><img src="img/coursera.png" alt="alt"/></td>
                                    <td>
                                        <c:if test="${checkLearnerSubject == false}">
                                            <a href="#" class="edit mr-2 update" title="Edit" name="${course.getCourse_id()}&${course.getCourse_name()}&${course.getDescription()}&${course.getCourse_no()}" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                            <a href="managementcourse?action=delete&subject_id=${subject_id}&course_id=${course.getCourse_id()}" class="delete mr-2 delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                        </c:if>
                                        <a href="managementchapter?subject_id=${subject_id}&course_id=${course.getCourse_id()}" class="chapter mr-2" title="Management Chapter" data-toggle="tooltip"><i class="fa-solid fa-eye material-icons"></i></a>
                                    </td>

                                    <c:set var="counter" value="${counter + 1}" />
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <%--<jsp:include page="footer.jsp"></jsp:include>--%> 
    <script>
        const alphanumericRegex = /^(?!^\d+$)[a-zA-Z0-9].*$/;
        const addCourse = document.querySelector(".add-course-btn");
        const courseForm = document.querySelector('#courseForm');
        const submit = document.querySelector('#submit');
        const course_name = document.querySelector("#course_name");
        const description = document.querySelector("#description");
        const course_id = document.querySelector("#course_id");
        addCourse.addEventListener("click", () => {
            courseForm.classList.remove('hidden');
            submit.value = "Add";
            submit.innerHTML = "Add";
        });

        const cancel = document.querySelector("#cancel");
        cancel.addEventListener("click", () => {
            submit.value = "Add";
            courseForm.classList.add("hidden");
            course_id.value = "";
            course_name.value = "";
            description.value = "";
            course_no.value = ${maxcourseno +1};
        });
//Update
        const  updates = document.querySelectorAll(".update");
        updates.forEach(update => {
            update.addEventListener("click", () => {
                let course_no = document.querySelector("#course_no");
                courseForm.classList.remove('hidden');
                submit.value = "Update";
                submit.innerHTML = "Update";
                arr = update.name.split("&");
                course_id.value = arr[0];
                course_name.value = arr[1];
                description.value = arr[2];
                course_no.value = arr[3];
            });
        });
        //Delete
        const deletes = document.querySelectorAll(".delete");
        deletes.forEach(deleteLink => {
            deleteLink.addEventListener("click", (event) => {
                if (!confirm("Are you sure you want to delete Course(All Chapter and Lesson in Course)?")) {
                    event.preventDefault(); // Ngăn chặn hành động xóa nếu người dùng chọn "Cancel"
                }
            });
        });
// Validate Form Before Submission
        submit.addEventListener("click", (event) => {
            let course_name_value = course_name.value.trim();
            let description_value = description.value.trim();
            let isValid = true;
            let error_course_name = document.querySelector("#error_course_name");
            let error_description = document.querySelector("#error_description");
            if (course_name_value.length <= 0 || course_name_value.length > 100 || !alphanumericRegex.test(course_name_value)) {
                error_course_name.classList.remove("hidden");
                error_course_name.innerHTML = 'Course name must be between 1 and 100 characters alphanumeric characters.';
                isValid = false;
            } else {
                error_course_name.innerHTML = "";
                error_course_name.classList.add('hidden');
            }

            if (description_value.length <= 0 || description_value.length > 200 || !alphanumericRegex.test(description_value)) {
                error_description.classList.remove("hidden");
                error_description.innerHTML = 'Description must be between 1 and 200 characters alphanumeric characters.';
                isValid = false;
            } else {
                error_description.innerHTML = "";
                error_description.classList.add('hidden');
            }

            if (!isValid) {
                event.preventDefault();
            } else {
                courseForm.submit(); // Submit form if validation passes
            }
        });
    </script>
</body>

</html>
