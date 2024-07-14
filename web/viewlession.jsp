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
        <title>View Lesson</title>

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
        <link rel="stylesheet" href="css/stylerate.css">
        <!--<link rel="stylesheet" href="css/stylecatificate.css">-->
    </head>
    <style>
        .message {
            color: white;
            font-size: 25px;
            margin-top: 5px;
        }
        .error {
            color: red;
            font-size: 12px;
            margin-top: 5px;
        }
    </style>

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
                                Lession View
                            </h1>
                            <a  href="course-details?subject_id=${subject_id}&course_id=${course_id}" ><input class="btn" type="submit" value="Back to Course Details"/></a>
                        <div class="row right">
                            <h3 class="message">${message}</h3>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="black d-flex">
            <button id="show" class="btm close"><i class=" fa-solid fa-bars"></i></button>
            <div id="menu" class="inner-menu open">
                <ul class="menu_items">
                    <div class="container">
                        <div class="m-b30 curriculum" id="curriculum">
                            <h5>Chapter Lesson</h5>
                            <c:forEach items="${data_chapter}" var="chapter">
                                <div class="card">
                                    <div class="card-header" id="heading${chapter.getChapter_no()}">
                                        <h2 class="mb-0">
                                            <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                                    data-target="#collapse${chapter.getChapter_no()}" aria-expanded="true" aria-controls="collapse${chapter.getChapter_no()}">
                                                <div class="card-body d-flex">
                                                    <h5 class="chapter">Chapter ${chapter.getChapter_no()}:<span class="value">${chapter.getChapter_name()}</span><i class="fa-solid fa-caret-down"></i></h5>
                                                            <c:if test="${chapter.getStatus_id() eq '2'}">
                                                        <i class="fa-solid fa-check ml-3 text-success"></i>
                                                    </c:if>
                                                </div>
                                            </button>
                                        </h2>
                                    </div>
                                    <div id="collapse${chapter.getChapter_no()}" class="collapse" aria-labelledby="heading${chapter.getChapter_no()}" data-parent="#curriculum">
                                        <c:forEach items="${data_lesson}" var="lesson">
                                            <c:if test="${lesson.getChapter_id().equals(chapter.getChapter_id())}">
                                                <div class="card-body d-flex">
                                                    <a href="viewlesson?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter.getChapter_id()}&lesson_id=${lesson.getLesson_id()}" class="view"><h5 class="lesson">Lesson ${lesson.getLesson_no()}:<span class="value">${lesson.getLesson_name()}</span></h5></a>
                                                            <c:if test="${lesson.status_id eq '2'}">
                                                        <i class="fa-solid fa-check ml-3 text-success"></i>
                                                    </c:if>
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </ul>
                <button id="close" class="btm-close">X</button>
                <div class="mb-4 ml-3 d-flex align-content-center" id="rate"><a class="catificate ">🌟 <span class="ml-3">Rate Star</span></a></div>

                <div class="d-flex align-content-center mb-4 ml-3 close" id="formrate">
                    <div class="rate-course">
                        <h3>Rate this course</h3>
                        <form action="rateCourse" method="get">
                            <div class="rating">
                                <input type="radio" name="rating" id="star5" value="5" ${learnercourse.getRate() eq '5' ? 'checked="checked"' : ''}><label for="star5" title="5 stars">☆</label>
                                <input type="radio" name="rating" id="star4" value="4" ${learnercourse.getRate() eq '4' ? 'checked="checked"' : ''}><label for="star4" title="4 stars">☆</label>
                                <input type="radio" name="rating" id="star3" value="3" ${learnercourse.getRate() eq '3' ? 'checked="checked"' : ''}><label for="star3" title="3 stars">☆</label>
                                <input type="radio" name="rating" id="star2" value="2" ${learnercourse.getRate() eq '2' ? 'checked="checked"' : ''}><label for="star2" title="2 stars">☆</label>
                                <input type="radio" name="rating" id="star1" value="1" ${learnercourse.getRate() eq '1' ? 'checked="checked"' : ''}><label for="star1" title="1 star">☆</label>
                            </div>
                            <input name="subject_id" value="${subject_id}" class="hidden">
                            <input  name="course_id" value="${course_id}" class="hidden"}/>
                            <button id="closeformrate" class="btm-close">X</button>
                            <button type="submit" class="close" id="submitformrate">Submit</button>
                        </form>
                    </div>
                </div>

                <c:if test="${learnercourse.getStatus_id() eq 2}">
                    <div class=" ml-3 d-flex align-content-center"><a href="certificatecourse?subject_id=${subject_id}&course_id=${course_id}" class="catificate">🏅 <span class="ml-3">Get Certificate</span></a></div>
                </c:if>

            </div>

            <div class="inner-body flex-1 m-lg-4">
                <!-- Overview -->
                <c:if test="${ empty lesson_id}">
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
                </c:if>
                <c:if test="${not empty lesson_id}">
                    <div class="m-b30 curriculum container " id="curriculum">
                        <h4>Lesson ${lesson.getLesson_no()}:<span class="value">${lesson.getLesson_name()}</span></h4>
                        <h4>Description:<span class="value">${lesson.getDescription()}</span></h4>
                    </div>
                    <hr>
                    <c:choose>
                        <c:when test="${lesson.getVideo() != null}">
                            <iframe width="100%" height="400px" src="${lesson.getVideo()}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                            <div class="row right">
                                <a class="inner-button" href="updatelearnerlesson?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&lesson_id=${lesson_id}"><button class=" btn">Completed</button></a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <img src="img/videoerr.webp">
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <!--End Overview -->

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
            </div>
        </div>
        <!--End Instructor-->

        <!--Comment-->
        <c:if test="${ not empty lesson_id}">
            <section>
                <div class="container d-flex ">
                    <div class="col-xl-8">
                        <c:forEach items="${datacommentlesson}" var="commentlesson">
                            <div class="media g-mb-30 media-comment">
                                <div class="media-body u-shadow-v18 g-bg-secondary g-pa-30">
                                    <div class="g-mb-15">
                                        <h5 class="h5 g-color-gray-dark-v1 mb-0">${commentlesson.getFullname()}</h5>
                                        <span class="g-color-gray-dark-v4 g-font-size-12">${commentlesson.getComment_date()}</span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <p>${commentlesson.getComment()}</p>
                                        <c:choose>
                                            <c:when test="${commentlesson.getAccount_id().equals(account.getAccount_id().toString())}">
                                                <ul>
                                                    <a class="list-inline-item g-mr-20 update" name="${commentlesson.getComment()}&${commentlesson.getComment_id()}">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </a>
                                                    <a class="list-inline-item g-mr-20 delete" href="deletecommentlesson?comment_id=${commentlesson.getComment_id()}&subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                        <i class="fa-solid fa-trash"></i>
                                                    </a>
                                                </ul>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="list-inline-item g-mr-20 report" href="reportComment?comment_id=${commentlesson.getComment_id()}&subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&lesson_id=${lesson_id}">
                                                    Report
                                                </a>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <ul class="list-inline d-sm-flex my-0">
                                        <li class="list-inline-item g-mr-20">
                                            <a class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover">
                                                <i class="fa fa-thumbs-up g-pos-rel g-top-1 g-mr-3"></i>
                                            </a>
                                        </li>
                                        <li class="list-inline-item g-mr-20 mr-5">
                                            <a class="u-link-v5 g-color-gray-dark-v4 g-color-primary--hover">
                                                <i class="fa fa-thumbs-down g-pos-rel g-top-1 g-mr-3"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>

                        <c:if test="${noOfPages > 1}">
                            <div class="pagination">
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <a href="viewlesson?subject_id=${subject_id}&course_id=${course_id}&chapter_id=${chapter_id}&lesson_id=${lesson_id}&page=${i}" class="${page == i ? 'active' : ''}">${i}</a>
                                </c:forEach>
                            </div>
                        </c:if>

                    </div>
                    <div class="col-xl-4">
                        <form id="alginform" action="addcommentlesson" method="Post" >
                            <div class="form-group">
                                <h4 style="color: white">Leave a comment</h4>
                                <label for="comment">Message</label>
                                <textarea name="comment" id="comment" msg cols="30" rows="5" class="form-control"></textarea>
                                <div class="error" id="errcomment"></div>
                            </div>
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" name="name" id="fullname" class="form-control" value="${account.getFullname()}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" name="email" id="email" class="form-control" value="${account.getEmail()}" readonly>
                            </div>
                            <div class="form-group">
                                <input type="text" name="lesson_id" id="lesson_id" class="form-control" value="${lesson_id}" style="display: none">
                                <input type="text" name="subject_id" id="subject_id" class="form-control" value="${subject_id}" style="display: none">
                                <input type="text" name="course_id" id="course_id" class="form-control" value="${course_id}" style="display: none">
                                <input type="text" name="chapter_id" id="chapter_id" class="form-control" value="${chapter_id}" style="display: none">
                                <input type="text" name="comment_id" id="comment_id" class="form-control" style="display: none">
                            </div>
                            <div class="form-group">
                                <button type="submit" id="submit" class="btn">Post Comment</button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </c:if>
        <!--End Comment-->

        <!-- ================ start footer Area ================= -->
        <jsp:include page="footer.jsp"></jsp:include> 

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const  updates = document.querySelectorAll(".update");
                updates.forEach(update => {
                    update.addEventListener("click", () => {
                        const comment = document.querySelector("#comment");
                        const alginform = document.querySelector("#alginform");
                        const comment_id = document.querySelector("#comment_id");
                        alginform.action = "updatecommentlesson";
                        if (update && update.name) {
                            var arr = update.name.split("&");
                            comment.value = arr[0];
                            comment_id.value = arr[1];
                            console.log(comment_id.value);
                        } else {
                            arr = [];
                        }
                    });
                });

                // Submit comment
                const submit = document.querySelector("#submit");
                submit.addEventListener("click", (event) => {
                    let  errcomment = document.querySelector("#errcomment");
                    const comment = document.querySelector("#comment").value.trim();
                    if (comment.length < 1 || comment.length > 1000) {
                        errcomment.innerHTML = 'Comment must be between 1 and 1000 characters alphanumeric characters.';
                        event.preventDefault();
                    }
                });

                //Delete
                const deletes = document.querySelectorAll(".delete");
                deletes.forEach(deleteLink => {
                    deleteLink.addEventListener("click", (event) => {
                        if (!confirm("Are you sure you want to delete this comment?")) {
                            event.preventDefault(); // Ngăn chặn hành động xóa nếu người dùng chọn "Cancel"
                        }
                    });
                });
            });
        </script>
        <!-- ================ End footer Area ================= -->

        <script src="js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
        <script src="js/parallax.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>
        <script src="js/hexagons.min.js"></script>
        <script src="js/jquery.counterup.min.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/main.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const rate = document.querySelector("#rate");
                const closeformrate = document.querySelector("#closeformrate");
                const formrate = document.querySelector("#formrate");
                const submitformrate = document.querySelector("#submitformrate");
                const rating = document.querySelectorAll(".rating input");
                const buttonShow = document.querySelector("#show");
                const buttonClose = document.querySelector("#close");
                const menu = document.querySelector("#menu");
                if (buttonShow && buttonClose && menu && rate && formrate && closeformrate && rating && submitformrate) {
                    buttonShow.addEventListener("click", () => {
                        menu.classList.remove("close");
                        menu.classList.add("open");
                        buttonShow.classList.add("close");
                        buttonShow.classList.remove("open");
                    });
                    buttonClose.addEventListener("click", () => {
                        menu.classList.remove("open");
                        menu.classList.add("close");
                        buttonShow.classList.remove("close");
                        buttonShow.classList.add("open");
                    });

                    rate.addEventListener("click", () => {
                        formrate.classList.remove("close");
                    });

                    closeformrate.addEventListener("click", (e) => {
                        formrate.classList.add("close");
                        e.preventDefault();
                    });

                    rating.forEach(input => {
                        input.addEventListener("change", function () {
                            // Khi người dùng thay đổi giá trị đánh giá, hiển thị nút Submit
                            submitformrate.classList.remove("close");
                        });
                    });

                }
            });

        </script>

    </body>

</html>