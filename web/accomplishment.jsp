<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Certificate Subjects and Courses</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/styleaccomlishment.css"> 

    </head>
    <body>

            <div class="header">
                <button class="header-button"><a href="mysubject">Back to My Subject</a></button>
                <button class="header-button" onclick="showSection('subject-certificates')">Subject Certificates</button>
                <button class="header-button" onclick="showSection('course-certificates')">Course Certificates</button>
            </div>
            <div class="container">
                <h1>My Specializations</h1>

                <div id="subject-certificates" class="section">
                    <h2>Subject Certificates</h2>
                <c:forEach items="${data_subjectcertificate}" var ="subjectcertifiacte">
                    <div class="certificate">
                        <img src="https://dcstore.vn/image/cache/catalog/Anh-san-pham/Tien/13.11/Coursera-600x600.png" alt="Certificate Icon" class="certificate-icon">
                        <div class="certificate-info">
                            <h3>${subjectcertifiacte.getSubject_name()}</h3>
                            <p>${subjectcertifiacte.getLecturer_name()}</p>
                        </div>
                        <button class="linkedin-button"><a href="certificatesubject?subject_id=${subjectcertifiacte.getSubject_id()}">Add to LinkedIn</a></button>
                    </div>
                </c:forEach>

                <!-- Add more subject certificates as needed -->
            </div>

            <div id="course-certificates" class="section" style="display: none;">
                <h2>Course Certificates</h2>
                <c:forEach items="${data_coursecertificate}" var="coursecertificate" >
                    <div class="certificate">
                        <img src="img/coursera.png" alt="Certificate Icon" class="certificate-icon">
                        <div class="certificate-info">
                            <h3>${coursecertificate.getCourse_name()}</h3>
                            <p>${coursecertificate.getLecturer_name()}</p>
                        </div>
                        <button class="linkedin-button"><a href="certificatecourse?subject_id=${coursecertificate.getSubject_id()}&course_id=${coursecertificate.getCourse_id()}">Add to LinkedIn</a></button>
                    </div>
                </c:forEach>

                <!-- Add more course certificates as needed -->
            </div>
        </div>
        <script>
            function showSection(sectionId) {
                var sections = document.querySelectorAll('.section');
                sections.forEach(function (section) {
                    section.style.display = 'none';
                });

                var selectedSection = document.getElementById(sectionId);
                selectedSection.style.display = 'block';
            }

        </script>
    </body>
</html>
