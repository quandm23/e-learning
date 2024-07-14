<%-- 
    Document   : management-Blog
    Created on : May 22, 2024, 9:57:57 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Management Blog</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/style.css">
        <style>
            /* Global styles */
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Roboto', sans-serif;
            }

            .table-responsive {
                margin: 30px 0;
            }

            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
            }

            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
                min-width: 100%;
            }

            .table-title h2 {
                margin: 8px 0 0;
                font-size: 22px;
            }
            .table-wrapper {
                max-width: 100%;
                overflow-x: auto; /* Thêm thuộc tính này để cuộn nếu nội dung bảng quá rộng */
            }

            table.table {
                width: 100%; /* Đặt chiều rộng bảng là 100% */
                max-width: 100%; /* Giới hạn chiều rộng tối đa của bảng */
                margin-bottom: 20px; /* Thêm khoảng cách dưới bảng nếu cần */
            }


            table.table tr th,
            table.table tr td {
                border-color: #e9e9e9;
                max-width: 15vw;
                overflow: auto
            }
            table.table tr td p{
                display: inline-block
            }

            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }

            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }

            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }

            table.table td:last-child {
                width: 130px;
            }

            table.table td a {
                color: #a0a5b1;
                display: inline-block;
                margin: 0 5px;
            }

            table.table td a.edit {
                color: #FFC107;
            }

            table.table td a.delete {
                color: #E34724;
            }

            table.table td i {
                font-size: 19px;
            }

            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .long-content {
                max-height: 20vh; /* Điều chỉnh theo nhu cầu */
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section class="banner-area">
                <div class="container">
                    <div class="row justify-content-center align-items-center">
                        <div class="col-lg-12 banner-right">
                            <h1 class="text-white">
                                Management BLog
                            </h1>
                        </div>
                    </div>
                </div>
            </section>
            <div class="container-xl">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-8"><h2>Management <b>Blog</b></h2></div>
                                <div class="col-sm-4">
                                    <form action="search-blog" method="Get">
                                        <div class="d-flex justify-content-end align-items-center">
                                            <div class="search-box">
                                                <i class="material-icons">&#xE8B6;</i>
                                                <input type="text" class="form-control" name="search_blog" placeholder="Search&hellip;">
                                                <input class="d-none" name="marketer_id" value="${marketer_id}" ></input>
                                        </div>
                                        <div class="inner-button" ><button class="btn mt-0" >Search</button></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <h2 style="color: green">${mess}</h2>
                        <div class="row">
                            <div class="inner-button">

                                <a href="addblog"><button class="btn">Add Blog</button></a>
                            </div>
                        </div>
                    </div>
                    <div class="table-wrapper">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th>NO</th>
                                    <th>Tittle <i class="fa fa-sort"></i></th>
                                    <th>Content</th>
                                    <th>Description <i class="fa fa-sort"></i></th>
                                    <th>Created Date</th>
                                    <th>Status <i class="fa fa-sort"></i></th>
                                    <th>Tag</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="counter" value="1" />
                                <c:forEach items="${data_blog}" var="blog">
                                    <tr class="height15">
                                        <td class="long-content" >${counter}</td>
                                        <td class="long-content">${blog.title}</td>
                                        <td class="long-content" >${blog.content}</td>
                                        <td class="long-content">${blog.description}</td>
                                        <td class="long-content">${blog.created_date}</td>
                                        <td class="long-content">${blog.status}</td>
                                        <td class="long-content">${blog.tag}</td>
                                        <td>
                                            <a href="editblog?blogid=${blog.id}" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                            <a onclick="return confirm('Do you want to delete this blog ?')" href="deleteBlog?blogid=${blog.id}"
                                               class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                        </td>
                                    </tr>
                                    <c:set var="counter" value="${counter + 1}" />
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination">
                                <li class="page-item disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li>
                                <li class="page-item active"><a href="#" class="page-link">1</a></li>
                                <li class="page-item"><a href="#" class="page-link">2</a></li>
                                <li class="page-item"><a href="#" class="page-link">3</a></li>
                                <li class="page-item"><a href="#" class="page-link">4</a></li>
                                <li class="page-item"><a href="#" class="page-link">5</a></li>
                                <li class="page-item"><a href="#" class="page-link"><i class="fa fa-angle-double-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>  
            </div>
        </div>   
        <jsp:include page="footer.jsp"></jsp:include> 
    </body>
</html>