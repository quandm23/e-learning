<%-- 
    Document   : elements
    Created on : May 15, 2024, 11:42:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Blog</title>

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



        <style>
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
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
                                BLOG
                            </h1>
                            <p class="mx-auto text-white  mt-15 mb-40">
                                Unlock your full academic potential with our series of blogs. 

                            </p>
                            <div class="link-nav">
                                <span class="box">
                                    <a href="home">HOME</a>
                                    <i class="lnr lnr-arrow-right"></i>

                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- ================ End banner Area ================= -->

            <!-- Start Align Area -->


            <div style="margin-bottom: 4rem;" class="container">
                <h1 style="color: green;">${mess}</h1> <!-- hiển thị ra thông báo add -->
            <form action="blog" method="get" class="d-flex justify-content-between">
                <input name="searchTitle" value="${param.searchTitle}" type="text" class="form-control" placeholder="Search Here" />

                <input type="submit" value="Search"/>
            </form>
            <c:if test="${searchTitle != ''}">
                <p>Find ${totalRecords} result(s) for '${param.searchTitle}'</p>
            </c:if>
        </div>



        <div class="whole-wrap">
            <div class="container">
                <c:forEach items="${requestScope.listBlog}" var="o">
                    <a href="blogdetail?id=${o.id}">
                        <div style="margin-bottom: 6rem;" class="section-top-border">
                            <h3 class="mb-30">${o.title}</h3>
                            <div class="row">
                                <div class="col-md-3">
                                    <!--display anh cua blog-->
                                    <img src="img/${o.image}" alt="" class="img-fluid">
                                    
                                </div>
                                <div class="col-md-9 mt-sm-20">
                                    <p>${o.description}</p>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
                <div class="pagination">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="blog?p=${i}&searchTitle=${param.searchTitle}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                    </c:forEach>
                </div>

                <div style="margin-top: 7rem" class="section-top-border">
                    <h3 class="mb-30">Block Quotes</h3>
                    <div class="row">
                        <div class="col-lg-12">
                            <blockquote class="generic-blockquote">
                                “Recently, the US Federal government banned online casinos from operating in America by making it illegal to
                                transfer money
                                to them through any US bank or payment system. As a result of this law, most of the popular online casino
                                networks
                                such as Party Gaming and PlayTech left the United States. Overnight, online casino players found themselves
                                being
                                chased by the Federal government. But, after a fortnight, the online casino industry came up with a solution and
                                new
                                online casinos started taking root. These began to operate under a different business umbrella, and by doing
                                that,
                                rendered the transfer of money to and from them legal. A major part of this was enlisting electronic banking
                                systems
                                that would accept this new clarification and start doing business with me. Listed in this article are the
                                electronic
                                banking”
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Align Areaa -->

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