<%-- 
    Document   : blog-home
    Created on : May 15, 2024, 11:41:24 PM
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
        <title>Blog Home</title>

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
        <link rel="stylesheet" href="css/stylehome.css">
        <style>
            body {
                font-family: 'Playfair Display', serif;
                background-color: #e3f2fd;
                margin: 0;
                padding: 0;
            }

            .top-courses {
                background: #fff;
                padding: 20px;
                margin-bottom: 20px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }

            .top-courses h2 {
                margin-top: 0;
                color: #333;
                font-size: 1.8em;
                text-align: center;
                position: relative;
            }

            .top-courses h2::after {
                content: '';
                width: 50px;
                height: 3px;
                background: #E53935;
                display: block;
                margin: 10px auto;
            }

            .course-list {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                justify-content: center;
            }

            .course-item {
                background: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                overflow: hidden;
                flex: 1 1 calc(33.333% - 20px);
                box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s, box-shadow 0.3s;
                position: relative;
            }

            .course-item:hover {
                transform: translateY(-10px);
                box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            }

            .course-image {
                width: 100%;
                height: 180px;
                overflow: hidden;
                background-color: #ccc;
            }

            .course-image img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                transition: transform 0.3s ease;
            }

            .course-item:hover .course-image img {
                transform: scale(1.1);
            }

            .course-details {
                padding: 15px;
                text-align: center;
            }

            .course-title {
                font-size: 1.4em;
                margin: 0 0 10px;
                color: #333;
                font-weight: bold;
            }

            .course-info {
                color: #666;
                font-size: 0.9em;
            }

            .price-container {
                font-size: 1.5em;
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 10px;
            }

            .new-price {
                color: #4CAF50;
                font-weight: bold;
                margin-right: 10px;
            }

            .old-price {
                text-decoration: line-through;
                color: #888;
                font-size: 0.9em;
            }

            .currency-symbol {
                font-size: 1em;
                margin-right: 5px;
            }

            .discount-badge {
                background-color: #FF5722;
                color: #fff;
                padding: 5px 10px;
                border-radius: 5px;
                font-size: 0.8em;
                position: absolute;
                top: 10px;
                right: 10px;
            }

            @media (max-width: 768px) {
                .course-item {
                    flex: 1 1 calc(50% - 20px);
                }
            }

            @media (max-width: 480px) {
                .course-item {
                    flex: 1 1 100%;
                }
            }

            .hidden {
                display: none;
            }

            .rate-course {
                margin-top: 30px;
                border: 1px solid #ddd;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #fff;
                position: relative;
            }

            .rate-course h3 {
                font-size: 1.5em;
                margin-bottom: 10px;
            }

            .rating {
                display: flex;
                justify-content: center;
                direction: rtl;
            }

            .rating input[type="radio"] {
                display: none;
            }

            .rating label {
                font-size: 2em;
                color: #ddd;
                cursor: pointer;
                transition: color 0.2s;
            }

            .rating input[type="radio"]:checked ~ label {
                color: #f5c518;
            }

            .rating label:hover,
            .rating label:hover ~ label {
                color: #f5c518;
            }

            .rate-course button {
                display: block;
                margin: 20px auto 0;
                padding: 10px 20px;
                background: #28a745;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                transition: background 0.3s;
            }

            .rate-course button:hover {
                background: #218838;
            }

            .rate-course p {
                text-align: center;
                color: #888;
            }

            .rate-course .btm-close {
                top: -34px;
                right: -25px;
            }

            .pagination {
                display: flex;
                justify-content: center;
                margin-top: 20px;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color 0.3s;
                margin: 0 4px;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
        </style>

    </head>

    <body>
        <!-- ================ Start Header Area ================= -->
        <jsp:include page="header.jsp"></jsp:include> 
            <!-- ================ End Header Area ================= -->

            <!-- ================ start banner Area ================= -->
            <section class="banner-area">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-12 banner-right">
                            <h1 class="text-white">
                                Home Page
                            </h1>
                            <div class="link-nav">
                                <span class="box">
                                    <a href="home">Home </a>
                                    <i class="lnr lnr-arrow-right"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ End banner Area ================= -->

            <div class="container">
                <div class="top-courses">
                    <h2>Top 3 Khóa Học Yêu Thích</h2>
                    <div class="course-list" id="favorite-courses">
                    <c:forEach items="${data_subjectfavorite}" var="subject"  >
                        <div class="course-item">
                            <div class="course-image">
                                <img src="${subject.getImage()}" alt="alt"/>
                            </div>
                            <div class="course-details">
                                <h3 class="course-title">${subject.getSubject_name()}</h3>
                                <p class="course-info">${subject.getDescription()}</p>
                                <div class="price-container">
                                    <i class="fa-solid fa-money-bill-1-wave"></i>
                                    <c:choose>
                                        <c:when test="${subject.getDiscount() eq '0'}">
                                            <span class="new-price">${subject.getPrice()}$</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="new-price red">${subject.getPrice() - subject.getPrice() *subject.getDiscount()/100}$</span>
                                            <span class="old-price">${subject.getPrice()}$</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="rating">
                                    <input type="radio" name="rating" id="star5" value="5" ${subject.getRate_subject() eq '5' ? 'checked="checked"' : ''}><label for="star5" title="5 stars">☆</label>
                                    <input type="radio" name="rating" id="star4" value="4"${subject.getRate_subject() eq '4' ? 'checked="checked"' : ''}><label for="star4" title="4 stars">☆</label>
                                    <input type="radio" name="rating" id="star3" value="3" ${subject.getRate_subject() eq '3' ? 'checked="checked"' : ''}><label for="star3" title="3 stars">☆</label>
                                    <input type="radio" name="rating" id="star2" value="2" ${subject.getRate_subject() eq '2' ? 'checked="checked"' : ''}><label for="star2" title="2 stars">☆</label>
                                    <input type="radio" name="rating" id="star1" value="1" ${subject.getRate_subject() eq '1' ? 'checked="checked"' : ''}><label for="star1" title="1 star">☆</label>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
            <div class="top-courses">
                <h2>Top 5 Khóa Học Bán Chạy</h2>
                <div class="course-list" id="best-selling-courses">
                    <c:forEach items="${data_subjectsold}" var="subject" >
                        <div class="course-item ">
                            <div class="course-image">
                                <c:choose>
                                    <c:when test="${subject.getImage() eq null}">
                                        <img src="img/coursera.png" alt="alt"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${subject.getImage()}" alt="alt"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="course-details">
                                <h3 class="course-title">${subject.getSubject_name()}</h3>
                                <p class="course-info">${subject.getDescription()}</p>
                                <div class="price-container">
                                    <i class="fa-solid fa-money-bill-1-wave"></i>
                                    <c:choose>
                                        <c:when test="${subject.getDiscount() eq '0'}">
                                            <span class="new-price">${subject.getPrice()}$</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="new-price red">${subject.getPrice() - subject.getPrice() *subject.getDiscount()/100}$</span>
                                            <span class="old-price">${subject.getPrice()}$</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="rating">
                                    <input type="radio" name="rating" id="star5" value="5" ${subject.getRate_subject() eq '5' ? 'checked="checked"' : ''}><label for="star5" title="5 stars">☆</label>
                                    <input type="radio" name="rating" id="star4" value="4"${subject.getRate_subject() eq '4' ? 'checked="checked"' : ''}><label for="star4" title="4 stars">☆</label>
                                    <input type="radio" name="rating" id="star3" value="3" ${subject.getRate_subject() eq '3' ? 'checked="checked"' : ''}><label for="star3" title="3 stars">☆</label>
                                    <input type="radio" name="rating" id="star2" value="2" ${subject.getRate_subject() eq '2' ? 'checked="checked"' : ''}><label for="star2" title="2 stars">☆</label>
                                    <input type="radio" name="rating" id="star1" value="1" ${subject.getRate_subject() eq '1' ? 'checked="checked"' : ''}><label for="star1" title="1 star">☆</label>
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

        <script src="js/vendor/jquery-2.2.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
        <script src="js/main.js"></script>
    </body>

</html>