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
        <title>User Profile</title>

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
        <link rel="stylesheet" href="css/style-profile.css">
        <style>
            .err{
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="main-body">

                <!-- Breadcrumb -->
                <nav aria-label="breadcrumb" class="main-breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="home">Home</a></li>
                        <!--                        <li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>-->
                        <li class="breadcrumb-item active" aria-current="page"><a href="profile">User Profile</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Edit Profile</li>
                    </ol>
                </nav>
                <!-- /Breadcrumb -->

                <div class="row gutters-sm">
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex flex-column align-items-center text-center">
                                    <!--Ảnh-->
                                    <img src="img/avatar-default.png" alt="Admin" class="rounded-circle" width="150">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <form action="editprofile" id="profileForm" method="post" >
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Full Name</h6>
                                        </div>
                                        <input class="col-sm-9 text-secondary form-control" name="fullname" value="${account.getFullname()}"/>
                                        <div class="err">${error_fullname}</div>
                                        <!--Hiển Thị Full Name-->
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Email</h6>
                                        </div>
                                        <input class="col-sm-9 text-secondary form-control" value="${account.getEmail()}" readonly />
                                        <!--Hiển Thị Email-->
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">DOB</h6>
                                        </div>
                                        <input type="Date"class="col-sm-9 text-secondary form-control" name="birthday" value="${account.getDob()}"/>
                                        <div class="err">${error_dob}</div>
                                        <!--Hiển Thị DOB-->
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">Phone Number</h6>
                                        </div>
                                        <input class="col-sm-9 text-secondary form-control" name="phone" value="${account.getPhone()}" />
                                        <div class="err">${error_phoneNumber}</div>
                                        <!--Hiển Thị Phone Number-->
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="submit" class="btn btn-info">Save</button>
                                            <a class="btn btn-info" href="home">Back To Home</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--        <script>
                    function enableEdit() {
                        inputFields.forEach(function (field) {
                            field.removeAttribute("readonly");
                        });
        
                        // Hiển thị nút "Lưu" và "Hủy" (nếu cần)
                    }
        
                    // Hàm để ngăn chặn chỉnh sửa các trường nhập
                    function disableEdit() {
                        inputFields.forEach(function (field) {
                            field.setAttribute("readonly", "readonly");
                        });
        
                        // Ẩn nút "Lưu" và "Hủy" (nếu cần)
                    }
                </script>-->

    </body>