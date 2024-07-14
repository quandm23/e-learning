<%-- 
    Document   : course-details
    Created on : May 15, 2024, 11:41:55 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

    <head>
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
        <title>Course Details</title>

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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .error {
                color: red;
                font-size: 12px;
                margin-top: 5px;
            }
        </style>
    </head>

    <body>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <!-- ================ Start Header Area ================= -->
        <jsp:include page="header.jsp"></jsp:include>
            <!-- ================ End footer Area ================= -->
            <section class="banner-area">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-12 banner-right">
                            <h1 class="text-white">
                                Course Details
                            </h1>
                            <a href="course-list?subject_id=${subject_id}"><input class="btn" type="submit" value="Back to Course List"/></a>
                        <a href="viewlesson?subject_id=${subject_id}&course_id=${course_id}"><input class="btn" type="submit" value="Let's study"/></a>
                        <div class="error" name="err">${err}</div>
                    </div>
                </div>
            </div>
        </section>

        <div class="container black">
            <!-- Overview -->
            <div class="courese-overview" id="overview">
                <h3 class="value black" style="color: blue;margin-bottom: 20px ; width: 100%">${course.getCourse_name()}</h3>
                <h4>Overview</h4>
            </div>
            <div class="row">
                <div class="col-xl-6 col-lg-6 col-md-12 inner-content">
                    <div class="course-details">
                        <div class="card-text mb-2 inner-wrap" ><div class="inner-head"><i class="fa-solid fa-book li-button"></i> <span class="label">Subject</span></div><span class="value">${subject.getSubject_name()}</span></li></div>
                        <div class="card-text mb-2 inner-wrap" ><div class="inner-head"><i class="fa-solid fa-user-graduate li-button"></i><span class="label">Lecturer</span></div> <span class="value">${account_lecturer.getFullname()}</span></li></div>
                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="ti-file li-button"></i> <span class="label">Course No</span></div> <span class="value">${course.getCourse_no()}</span></li></div>
                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="ti-files li-button"></i> <span class="label">Chapter</span></div> <span class="value">${ data_chapter.size()}</span></li></div>
                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-school li-button"></i> <span class="label">Online</span></li></div></div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-6 col-md-12 courese-overview">
                    <h5 class="m-b5">💡 Course Description</h5>
                    <p style="font-size: 18px;margin-top: 10px">${course.getDescription()}</p>
                    <h5 class="m-b5" style="margin-bottom: 15px">✔ Learning Outcomes</h5>
                    <ul class="list-checked primary">
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Over 37 lectures and 55.5 hours of content!</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>LIVE PROJECT End to End Software Testing Training Included.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Learn Software Testing and Automation basics from a professional trainer from your own desk.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Information packed practical training starting from basics to advanced testing techniques.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Best suitable for beginners to advanced level users and who learn faster when demonstrated.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Course content designed by considering current software testing technology and the job market.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Practical assignments at the end of every session.</li>
                        <li><i class="fa-solid fa-square-check" class="li-button black"></i>Practical learning experience with live project work and examples.cv</li>
                    </ul>
                </div>
            </div>
        </div>
        <!--End Overview -->

        <!--curriculum -->
        <div class="container">
            <div class="m-b30 curriculum" id="curriculum">
                <h4>Curriculum</h4>
                <c:forEach items="${data_chapter}" var="chapter">
                    <div class="card">
                        <div class="card-header" id="heading${chapter.getChapter_no()}">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                        data-target="#collapse${chapter.getChapter_no()}" aria-expanded="true" aria-controls="collapse${chapter.getChapter_no()}">
                                    <h5 class="chapter">Chapter ${chapter.getChapter_no()}:<span class="value">${chapter.getChapter_name()}</span><i class="fa-solid fa-caret-down"></i></h5>
                                </button>
                            </h2>
                        </div>
                        <div id="collapse${chapter.getChapter_no()}" class="collapse" aria-labelledby="heading${chapter.getChapter_no()}" data-parent="#curriculum">
                            <c:forEach items="${data_lesson}" var="lesson">
                                <c:if test="${lesson.getChapter_id().equals(chapter.getChapter_id())}">
                                    <div class="card-body">
                                        <h5 class="lesson">Lesson ${lesson.getLesson_no()}:<span class="value">${lesson.getLesson_name()}</span></h5>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!--End curriculum -->

        <!--Instructor-->
        <div class="m-b30 curriculum container " id="curriculum">
            <h4>Instructor</h4>
        </div>
        <div class="container d-flex justify-content-center align-items-center">
            <div class="row">
                <div class="col-md-4 d-flex justify-content-center">
                    <div class="support-item">
                        <img src="img//email.webp" alt="Email" class="icon">
                        <p class="contact-info">${account_lecturer.getEmail()}</p>
                        <p class="contact-type">Email</p>
                    </div>
                </div>
                <div class="col-md-4 d-flex justify-content-center">
                    <div class="support-item">
                        <img src="img//phonenumber.webp" alt="Phone" class="icon">
                        <p class="contact-info">${account_lecturer.getPhone()}</p>
                        <p class="contact-type">Phone Number</p>
                    </div>
                </div>
            </div>
        </div>
        <div class=" container inner-wrap curriculum">
            <h4 class="mt-4">Related Course</h4>
            <div class="row inner-content">
                <c:forEach items="${data_course}" var="course">
                    <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12">
                        <div class='inner-box'>
                            <div class="card">
                                <img src="img/coursera.png" class="card-img-top" alt="Image Course">
                                <div class="card-body">
                                    <div class="courese-overview">
                                        <h5 class="value" style="color: blue;margin-bottom: 20px ; width: 100%">${course.getCourse_name()}</h5>
                                        <div class="card-text mb-2 inner-wrap"> <div class="inner-head"><i class="fa-solid fa-audio-description"></i> <span class="label">Description</span></div><span class="value">${course.getDescription()}</span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-book li-button"></i> <span class="label">Subject</span> </div><span class="value">${subject.getSubject_name()}</span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-user-graduate li-button"></i><span>Lecturer</span> </div><span class="value">${account_lecturer.getFullname()}</span></div>
                                        <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="ti-file li-button"></i> <span class="label">Course No</span> </div><span class="value">${course.getCourse_no() }</span></div>
                                        <a href="course-details?subject_id=${subject_id}&course_id=${course.getCourse_id()}" class="inner-button"><button class="btn">Join Now</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!--End Instructor-->
        <!-- ================ start footer Area ================= -->
        <jsp:include page="footer.jsp"></jsp:include> 
        <!-- ================ End footer Area ================= -->

        <script src="js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/parallax.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/hexagons.min.js"></script>
        <script src="js/jquery.counterup.min.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/main.js"></script>

    </body>

</html>