<%-- 
    Document   : blogdetail
    Created on : Sep 19, 2023, 12:15:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Blog | Detail</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:900|Roboto:400,400i,500,700" rel="stylesheet" />
        <!-- CSS here -->
        <link rel="stylesheet" href="css/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/css/slicknav.css">
        <link rel="stylesheet" href="css/css/animate.min.css">
        <link rel="stylesheet" href="css/css/hamburgers.min.css">
        <link rel="stylesheet" href="css/css/magnific-popup.css">
        <link rel="stylesheet" href="css/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="css/css/themify-icons.css">
        <link rel="stylesheet" href="css/css/slick.css">
        <link rel="stylesheet" href="css/css/nice-select.css">
        <link rel="stylesheet" href="css/css/style.css">
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
    </head>

    <body>
        <!-- ================ Start Header Area ================= -->
        <jsp:include page="header.jsp"></jsp:include> 
            <!-- Header End -->
            <main>
                <section class="banner-area">
                    <div class="container">
                        <div class="row justify-content-center align-items-center">
                            <div class="col-lg-12 banner-right">
                                <h1 class="text-white">
                                    BLOG DETAIL
                                </h1>
                                <p class="mx-auto text-white  mt-15 mb-40">
                                    Unlock your full academic potential with our series of blogs. 

                                </p>
                                <div class="link-nav">
                                    <span class="box">
                                        <a href="blog">BLOG LIST</a>
                                        <i class="lnr lnr-arrow-right"></i>

                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="blog_area single-post-area section-padding">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8 posts-list">
                                <div class="single-post">
                                    <h1 style="color: #2d2d2d;">${detail.title}
                                </h1>
                                <div class="feature-img">
                                    <img style="width: 10rem; height: 10rem;" src="img/${detail.image}" alt="" >
                                </div>
                                <div class="blog_details">
                                    <h2 style="color: #2d2d2d;">${detail.description}
                                    </h2>
                                    <ul class="blog-info-link mt-3 mb-4">
                                        <li><a href="#"> ${detail.created_date}</a></li>
                                        <li><a href="#"> ${detail.tag}</a></li>
                                    </ul>
                                    <p class="excert">
                                        ${detail.content}
                                    </p>

                                </div>
                            </div>
                            <div class="navigation-top">
                                <div class="d-sm-flex justify-content-between text-center">
                                    <p class="like-info"><span class="align-middle"><i class="fa fa-heart"></i></span> </p>
                                    <div class="col-sm-4 text-center my-2 my-sm-0">
                                        <!-- <p class="comment-count"><span class="align-middle"><i class="fa fa-comment"></i></span> 06 Comments</p> -->
                                    </div>
                                    <ul class="social-icons">
                                        <li><a href="https://www.facebook.com/sai4ull"><i class="fab fa-facebook-f"></i></a></li>
                                        <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fab fa-dribbble"></i></a></li>
                                        <li><a href="#"><i class="fab fa-behance"></i></a></li>
                                    </ul>
                                </div>
                                
                            </div>
                        </div>
                        
                    </div>
                </div>
            </section>
            <!-- Blog Area End -->
        </main> 
        <footer>
            <div class="footer-wrappper footer-bg">
                <!-- Footer Start-->
                <div class="footer-area footer-padding">
                    <div class="container">
                        <div class="row justify-content-between">
                            <div class="col-xl-4 col-lg-5 col-md-4 col-sm-6">
                                <div class="single-footer-caption mb-50">
                                    <div class="single-footer-caption mb-30">
                                        <!-- logo -->
                                        <div class="footer-logo mb-25">
                                            <a href="index.html"><img src="css/img/logo/logo2_footer.png" alt=""></a>
                                        </div>
                                        <div class="footer-tittle">
                                            <div class="footer-pera">
                                                <p>The automated process starts as soon as your clothes go into the machine.</p>
                                            </div>
                                        </div>
                                        <!-- social -->
                                        <div class="footer-social">
                                            <a href="#"><i class="fab fa-twitter"></i></a>
                                            <a href="https://bit.ly/sai4ull"><i class="fab fa-facebook-f"></i></a>
                                            <a href="#"><i class="fab fa-pinterest-p"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-5">
                                <div class="single-footer-caption mb-50">
                                    <div class="footer-tittle">
                                        <h4>Our solutions</h4>
                                        <ul>
                                            <li><a href="#">Design & creatives</a></li>
                                            <li><a href="#">Telecommunication</a></li>
                                            <li><a href="#">Restaurant</a></li>
                                            <li><a href="#">Programing</a></li>
                                            <li><a href="#">Architecture</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-2 col-lg-4 col-md-4 col-sm-6">
                                <div class="single-footer-caption mb-50">
                                    <div class="footer-tittle">
                                        <h4>Support</h4>
                                        <ul>
                                            <li><a href="#">Design & creatives</a></li>
                                            <li><a href="#">Telecommunication</a></li>
                                            <li><a href="#">Restaurant</a></li>
                                            <li><a href="#">Programing</a></li>
                                            <li><a href="#">Architecture</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-2 col-lg-3 col-md-4 col-sm-6">
                                <div class="single-footer-caption mb-50">
                                    <div class="footer-tittle">
                                        <h4>Company</h4>
                                        <ul>
                                            <li><a href="#">Design & creatives</a></li>
                                            <li><a href="#">Telecommunication</a></li>
                                            <li><a href="#">Restaurant</a></li>
                                            <li><a href="#">Programing</a></li>
                                            <li><a href="#">Architecture</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- footer-bottom area -->
                <div class="footer-bottom-area">
                    <div class="container">
                        <div class="footer-border">
                            <div class="row d-flex align-items-center">
                                <div class="col-xl-12 ">
                                    <div class="footer-copy-right text-center">
                                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer End-->
            </div>
        </footer> 
        <!-- Scroll Up -->

        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>

        <!-- JS here -->

        <script src="./css/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Jquery, Popper, Bootstrap -->
        <script src="./css/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="./css/js/popper.min.js"></script>
        <script src="./css/js/bootstrap.min.js"></script>
        <!-- Jquery Mobile Menu -->
        <script src="./css/js/jquery.slicknav.min.js"></script>

        <!-- Jquery Slick , Owl-Carousel Plugins -->
        <script src="./css/js/owl.carousel.min.js"></script>
        <script src="./css/js/slick.min.js"></script>
        <!-- One Page, Animated-HeadLin -->
        <script src="./css/js/wow.min.js"></script>
        <script src="./css/js/animated.headline.js"></script>
        <script src="./css/js/jquery.magnific-popup.js"></script>

        <!-- Date Picker -->
        <script src="./css/js/gijgo.min.js"></script>
        <!-- Nice-select, sticky -->
        <script src="./css/js/jquery.nice-select.min.js"></script>
        <script src="./css/js/jquery.sticky.js"></script>

        <!-- counter , waypoint,Hover Direction -->
        <script src="./css/js/jquery.counterup.min.js"></script>
        <script src="./css/js/waypoints.min.js"></script>
        <script src="./css/js/jquery.countdown.min.js"></script>
        <script src="./css/js/hover-direction-snake.min.js"></script>

        <!-- contact js -->
        <script src="./css/js/contact.js"></script>
        <script src="./css/js/jquery.form.js"></script>
        <script src="./css/js/jquery.validate.min.js"></script>
        <script src="./css/js/mail-script.js"></script>
        <script src="./css/js/jquery.ajaxchimp.min.js"></script>

        <!-- Jquery Plugins, main Jquery -->	
        <script src="./css/js/plugins.js"></script>
        <script src="./css/js/main.js"></script>
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
