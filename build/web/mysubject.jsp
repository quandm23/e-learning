<%-- 
    Document   : courselist
    Created on : May 17, 2024, 1:27:30 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <title>My Subject</title>

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
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/style.css">
        
    </head>
    <body>
        <!-- ================ Start Header Area ================= -->
        <jsp:include page="header.jsp"></jsp:include>
            <!-- ================ End footer Area ================= -->
            <section class="banner-area">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-12 banner-right">
                            <h1 class="text-white">
                                My Subject
                            </h1>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Content -->
            <div class="container">
                <!--                <div class="inner-head">
                                    <h2 class="inner-title text-center">${subject.getSubject_name()}</h2>
                                <div class="inner-content d-flex align-items-center" style="color: black ; font-size: 12px ; overflow: hidden;word-wrap: break-word; word-break: break-word;">
                                    <i class="fa-solid fa-audio-description mr-2"></i>
                                    <span class="label mr-2">Description:</span>
                                    <span class="value" style="flex: 1">${subject.getDescription()}</span></div>
                            </div>-->

            <div class="inner-wrap">
                <div class="row inner-content">
                    <c:forEach items="${dataMySubject}" var="mySubject">
                        <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12">
                            <div class='inner-box'>
                                <div class="card">
                                    <img src="img/coursera.png" class="card-img-top" alt="Image Course">
                                    <div class="card-body">
                                        <div class="courese-overview">
                                            <h5 class="value text-center" style="color: blue;margin-bottom: 20px ; width: 100%">${mySubject.getSubject_name()}</h5>
                                            <div class="card-text mb-2 inner-wrap"> <div class="inner-head"><i class="fa-solid fa-audio-description"></i> <span class="label">Description</span></div><span class="value">${mySubject.getDescription()}</span></div>
                                            <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="fa-solid fa-user-graduate li-button"></i><span>Lecturer</span> </div><span class="value">${mySubject.getFullname()}</span></div>
                                            <div class="card-text mb-2 inner-wrap"><div class="inner-head"><i class="ti-file li-button"></i> <span class="label">Enrolled Date</span> </div><span class="value">${mySubject.getEnrolled_date()}</span></div>
                                            <a href="course-list?subject_id=${mySubject.getSubject_id()}" class="inner-button"><button class="btn">${mySubject.getStatus_id()}</button></a>
                                                <c:choose>
                                                    <c:when test="${mySubject.getPercent() eq '100.00'}">
                                                    <a href="certificatesubject?subject_id=${mySubject.getSubject_id()}" class="inner-button">
                                                        <button class="btn">Certificate Subject</button>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="inner-button">
                                                        <button class="btn mr-3">${mySubject.getPercent()} <i class="fa-solid fa-percent"></i></button>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!-- End Content -->

        <!-- ================ start footer Area ================= -->
        <jsp:include page="footer.jsp"></jsp:include> 
        <!-- ================ End footer Area ================= -->
    </body>
</html>
