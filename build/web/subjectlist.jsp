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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
        <title>Course List</title>

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
        <link href="css/subjectlist.css" rel="stylesheet" type="text/css"/>
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
                                Subjects List
                            </h1>
                            <p class="mx-auto text-white  mt-20 mb-40">
                            </p>
                            <div class="link-nav">
                                <span class="box">
                                    <a href="home">Home</a>
                                    <i class="lnr lnr-arrow-right"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>


            <div class="container">
                <div class="inner-head">
                    <div class="inner-decs text-center">
                        <div class="purple-ribbon">
                            <h2 style="font-size: 36px;">TOP 3 NEWEST COURSE</h2>
                        </div>
                    </div>
                </div> 
            </div>



            <!-- Start top-category-widget Area -->

            <section class="subjectlist">
            <c:forEach items="${listTop3}" var="o">
                <div class="col-lg-4">
                    <div class="single-cat-widget">
                        <div class="content relative">
                            <div class="overlay overlay-bg"></div>

                            <div class="thumb">
                                <img class="content-image img-fluid d-block mx-auto" src="${o.image}" alt="">
                            </div>
                            <div class="content-list">
                                <h4 class="content-title mx-auto text-uppercase">${o.subject_name}</h4>
                                <span></span>

                            </div>
                        </div>
                        <a href="course-list?subject_id=${o.subject_id}" class="inner-button">
                            <button class="btn" >View</button>
                        </a>
                        <c:choose>
                            <c:when test="${o.bought}">
                                <a class="inner-button" >
                                    <button class="btn" disabled>Bought Subject</button>
                                </a>
                            </c:when>
                            <c:when test="${o.price == 0}">
                                <a href="enrollment?subject_id=${o.subject_id}" class="inner-button">
                                    <button class="btn">Enroll for Free</button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="buysubject?subject_id=${o.subject_id}" class="inner-button">
                                    <button class="btn" >Add to Cart</button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>


            </div>
        </c:forEach>
    </section>

    <div style="border-radius: 1rem; border-style: double" class="filter-sidebar card h-100">
        <div style="margin-left: 1rem;">
            <div class="form-group">
                <label for="search_subject">Search:</label>
                <input id="txtSearch" oninput="displaySubject()" type="text" class="form-control" placeholder="Search here">
            </div>
            <div class="form-group">
                <label for="category_filter">Category: </label><br/>
                <c:forEach items="${listCate}" var="c">
                    <div class="form-check">
                        <input onclick="displaySubject()" type="checkbox" class="form-check-input" name="cate" value="${c.category_id}">
                        <label class="form-check-label">${c.category_name}</label>
                    </div>
                </c:forEach>
            </div>
            <label for="sort-price">Price filter</label>
            <div class="form-group price-filter"> <div class="form-group price-filter" style=""> 

                    <select  onchange="displaySubject()" id="sort">
                        <option  value="">Select</option>
                        <option  value="asc">Price increasing</option>
                        <option  value="desc">Price decreasing</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="row ">

            <<h1>${mess}</h1>
            <div class="col-md-12">
                <div id="showSubjectList" class="row inner-content">

                </div>
            </div>
        </div>     


        <script type="text/javascript">

            var cate = [];
            $(document).ready(function () {
                displaySubject();
            });
            function displaySubject() {
                checkedCate();
                var dataSendToController = {
                    checkedCategory: cate.toString(),
                    txtSearch: document.getElementById('txtSearch').value,
                    sort: document.getElementById('sort').value
                };
                $.ajax({
                    async: false,
                    url: "/learnx/subjectlist",
                    type: 'POST',
                    dataType: 'json',
                    data: dataSendToController,
                    success: function (data) {
                        console.log(data);
                        var html = '';
                        if (data.length === 0) {
                            html = '<h1>There are no courses available</h1>';
                        } else {
                            for (var i = 0; i < data.length; i++) {
                                html += `<div class="">
                                <div class="col-md-12 card h-100">
                                    <div class="">
                                        <img src="` + data[i].image + `" class="card-img-top" alt="` + data[i].subject_name + `">
                                        <div class="card-body">
                                            SUBJECT NAME: <h5 class="card-title text-uppercase">` + data[i].subject_name + `</h5>
                                            DESCRIPTION: <p class="card-text"><i class=""></i> ` + data[i].description + `</p>                                   
                                            PRICE<p class="card-text"><i class="fas fa-tags"></i> ` + data[i].price + `$</p>
                                            DISCOUNT: <p class="card-text"><i class=""></i> ` + data[i].discount + ` %</p>
                                        </div>
                                        <a href="course-list?subject_id=` + data[i].subject_id + `" class="inner-button">
                                            <button class="btn">View</button>
                                        </a>`;
                                if (data[i].bought) {
                                    html += `<a class="inner-button">
                                     <button class="btn" disabled>Bought Subject</button> 
                                 </a>`;
                                } else if (data[i].price == 0) {
                                    html += `<a href="enrollment?subject_id=` + data[i].subject_id + `" class="inner-button">
                                     <button class="btn">Enroll for Free</button>
                                 </a>`;
                                } else {
                                    html += `<a href="buysubject?subject_id=` + data[i].subject_id + `" class="inner-button">
                                     <button class="btn">Add to Cart</button>
                                 </a>`;
                                }
                                html += `</div>
                                </div>     
                            </div>`;
                            }
                        }
                        document.getElementById('showSubjectList').innerHTML = html;
                    },
                    error: function (err) {
                        console.log(err);
                    }
                });
            }
            function checkedCate() {
                var checkedCate = document.getElementsByName('cate');
                var temp = [];
                for (var i = 0; i < checkedCate.length; i++) {
                    if (checkedCate[i].checked && !temp.includes(checkedCate[i].value)) {
                        temp.push(checkedCate[i].value);
                        temp.push(checkedCate[i].value);
                    } else {
                        temp.splice(i, 1);
                    }
                }
                cate = temp;
                console.log(cate.toString());
            }


        </script>

        <!-- ================ start footer Area ================= -->
        <jsp:include page="footer.jsp"></jsp:include> 
        <!-- ================ End footer Area ================= -->
</body>
</html>

