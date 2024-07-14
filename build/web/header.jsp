<%-- 
    Document   : header
    Created on : May 17, 2024, 5:09:00 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/style1.css"/>
        <link rel="stylesheet" href="css/style.css"/>

    </head>
    <style>
        .avatar {
            width: 60px;
            height: 60px;
            background-size: cover;
            background-position: bottom;
            border-radius: 50%;
            overflow: hidden;
            margin-bottom: 20px;
        }
    </style>
    <body>
        <header class="default-header">
            <nav class="navbar navbar-expand-lg  navbar-light">
                <div class="container">
                    <a class="navbar-brand" href="home">
                        <span class="head" >Learn <i class="fa-brands fa-x-twitter"></i></span>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="lnr lnr-menu"></span>
                    </button>

                    <div class="collapse navbar-collapse justify-content-end align-items-center" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
                            <li><a href="subjectlist">Subjects</a></li>
                            <!-- Dropdown -->

                            <c:if test="${sessionScope.account.role_id != 4}">
                                <li class="dropdown">
                                    <a  href="blog" >
                                        Blog
                                    </a>
                                </li>
                            </c:if>  
                            <c:if test="${sessionScope.account.role_id == 4}">
                                <li class="dropdown">
                                    <a  class="dropdown-toggle" data-toggle="dropdown" href="blog" >
                                        Blog
                                    </a>
                                    <ul class="dropdown-menu">
                                        <a class="dropdown-item" href="blog">Blog</a>                                                                   
                                        <a class="dropdown-item" href="management-blog">Blog Management</a>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.role_id == 3}">
                                <li class="dropdown">
                                    <a  class="dropdown-toggle" data-toggle="dropdown" href="subjectmanage" >
                                        Lecturer Management
                                    </a>
                                    <ul class="dropdown-menu">
                                        <a class="dropdown-item" href="subjectmanage">Management</a>                                                                   
                                        <a class="dropdown-item" href="lecturersubject">Question Bank</a>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.role_id == 1}">
                                <li class="dropdown">
                                    <a  class="dropdown-toggle" data-toggle="dropdown" href="">
                                        Admin Management
                                    </a>
                                    <ul class="dropdown-menu">
                                        <a class="dropdown-item" href="manageRoleRequests">Manage Role Change Requests</a>                                                                   
                                        <a class="dropdown-item" href="">Manage account Report</a>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.role_id == 2}">
                                <li class="dropdown">
                                    <a href="mysubject">My Subject</a>
                                    <ul class="dropdown-menu">
                                        <a class="dropdown-item" href="accomplishment">My Accomplishment</a>                                                                   
                                    </ul>
                                </li>
                            </c:if>
                        </ul>
                        <c:if test="${sessionScope.account == null}">
                            <a href="login" style="margin-bottom: 20px"><button class="btn">Login</button></a>
                        </c:if>
                        <c:if test="${sessionScope.account == null}">
                            <a href="register" style="margin-bottom: 20px"><button class="btn" >Register</button></a>
                        </c:if>
                        <!--avatar-->
                        <c:if test="${sessionScope.account != null}">
                            <div class="dropdown">
                                <a>
                                    <div class="avatar" style="background-image:
                                         <c:choose>
                                             <c:when test="${not empty sessionScope.account.avatar}">
                                                 url('data:image/jpeg;base64,${sessionScope.account.avatar}')
                                             </c:when>
                                             <c:otherwise>
                                                 url('img/avatar_1.jpg')
                                             </c:otherwise>
                                         </c:choose>;"></div>
                                </a>
                                <div class="dropdown-menu, dropdown-content" id="myDropdown">
                                    <a  href="profile">Profile</a>
                                    <a  href="changepassword">Change password</a>
                                    <c:if test="${sessionScope.account.role_id == 2}">
                                        <a href="requestRoleChange.jsp">Join with us</a>
                                    </c:if>
                                    <a href="logout">Log out</a>
                                </div>
                            </div>
                        </c:if>
                        <li class="button-header"><c:set var="size" value="${sessionScope.size}"/>
                            <div style="margin-left: 20px; margin-bottom:  20px; display: flex;justify-content: center;align-items: end;padding-top: " id="bag">
                                <div>
                                    <img src="img/shoping.png" style="height: 50px;"alt=""/>
                                </div>
                                <div >
                                    <a style="height: 15px" href="cart">(${size})</a>
                                </div>
                            </div>
                        </li>

                    </div>
                    <div id="productExistsAlert" class="alert alert-warning" style="display: none;">
                        <span id="productExistsMessage"></span>
                        <button id="closeProductAlert" type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </nav>
        </header>
    </body>
    <script>
        var productExistsMessage = "${productExistsMessage}";
        if (productExistsMessage !== "") {
            document.getElementById("productExistsMessage").textContent = productExistsMessage;
            document.getElementById("productExistsAlert").style.display = "block";
        }


        document.getElementById("closeProductAlert").addEventListener("click", function () {
            document.getElementById("productExistsAlert").style.display = "none";
        });
    </script>
</html>


