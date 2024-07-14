<%-- 
    Document   : questionbank.
    Created on : Jun 9, 2024, 10:53:54 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Courses | Education</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<link rel="manifest" href="site.webmanifest">-->
        <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <!-- CSS here -->
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
        <style>
            #pages li.goPrevious, li.goNext{
                color: #333;
                padding: 0px 5px;
                cursor: pointer;
            }
            #pages li.goPrevious:hover, li.goNext:hover{
                background-color: #CFC;
            }
            #pages .pageInfo{
                padding: 0px 5px;
                border: 0px;
            }
        </style>

    </head>

    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <section class="banner-area">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-12 banner-right">
                            <h1 class="text-white">
                                Lecturer Subject
                            </h1>
                        </div>
                    </div>
                </div>
            </section>

            <div class="courses-area section-padding40 fix" id="paging">
                <div class="container list" id="rows">
                    <div class="row justify-content-center">
                        <div class="col-xl-7 col-lg-8">
                            <div class="section-tittle text-center mb-55">
                                <h1>Lecturer Subject</h1>
                            </div>
                        </div>
                    </div>
                    <form action="searchlecturersubject" method="Get">
                        <div class="d-flex justify-content-end align-items-end">
                            <div class="search-box">
                                <i class="material-icons">&#xE8B6;</i>
                                <input type="text" class="form-control" name="searchsubject" placeholder="Search&hellip;">
                            </div>
                            <div class="inner-button"><button class="btn mt-0" >Search</button></div>
                        </div>
                    </form>
                    <div class="inner-wrap">
                        <div class="row inner-content">
                        <c:forEach items="${data_subject}" var="subject">
                            <div class="col-xl-6 col-lg-6 col-md-12 col-sm-12">
                                <div class='inner-box'>
                                    <div class="card">
                                        <img src="img/coursera.png" class="card-img-top" alt="Image Course">
                                        <div class="card-body">
                                            <div class="courese-overview">
                                                <h5 style="font-size: 35px" class="text-center">Subject Name: </h5><h5 class="value text-center" style="color: blue;margin-bottom: 20px ; width: 100%">${subject.getSubject_name()}</h5>
                                                <a href="questionlist?sid=${subject.getSubject_id()}" class="inner-button"><button class="btn">Manage Questions</button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


            <!-- ================ start footer Area ================= -->
            <jsp:include page="footer.jsp"></jsp:include> 
            <!-- ================ End footer Area ================= -->

    </body>
</html>
