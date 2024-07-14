<%-- 
    Document   : course_detai
    Created on : Sep 19, 2023, 1:31:08 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Courses | Education</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="template course_detail/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="template course_detail/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="template course_detail/lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="template course_detail/css/style.css" rel="stylesheet">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/dataTables.jqueryui.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="css/style1.css"/>
        <link rel="stylesheet" href="css/acountmanage.css"/>
    </head>

    <body>
        <jsp:include page="header2.jsp"></jsp:include>  

            <div style="display: flex">
                <div class="table-responsive">
                    <div class="table-wrapper">
                        <div style="display: flex" class="row">
                            <div class="col-md-4">
                                <p style="font-size: 50px">Filter</p>
                                <p style="font-size: 40px">Role</p>
                                <div style="margin-left: 20px">
                                <c:forEach items="${listR}" var="r">
                                    <input style="width: 20px; height: 20px" onclick="addRoleData()" type="checkbox" name="role" class="role" value="${r.role_id}">
                                    <span style="font-weight: bold; font-size: 25px; ">&nbsp;&nbsp;&nbsp; ${r.role_name}</span><br>
                                </c:forEach>

                                <p style="font-size: 40px; margin-left: -20px">Status</p>

                                <input style="width: 20px; height: 20px" onclick="addStatusData()" type="checkbox" name="status" class="status" value="1">
                                <span style="font-weight: bold; font-size: 25px; ">&nbsp;&nbsp;&nbsp; Active</span><br>
                                <input style="width: 20px; height: 20px" onclick="addStatusData()" type="checkbox" name="status" class="status" value="2">
                                <span style="font-weight: bold; font-size: 25px; ">&nbsp;&nbsp;&nbsp; Blocked</span><br>
                            </div>



                        </div>
                        <input id="subject_id" type="hidden" value="${subject_id}"/>
                        <!--Start Table-->
                        <div class="col-md-8">
                            <table style="width: 100%" id="accounttable" class=" table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Fullname</th>
                                        <th>Email</th>
                                        <th>Date of birth</th>
                                        <th>Phone</th>   
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Edit Role</th>
                                        <th>Action</th>


                                    </tr>
                                </thead>

                                <tbody>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Username</th>
                                        <th>Fullname</th>
                                        <th>Email</th>
                                        <th>Date of birth</th>
                                        <th>Phone</th>   
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Edit Role</th>
                                        <th>Action</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!--End Table-->

                    </div>
                </div>
            </div>        
        </div>


        <!-- Delete Modal HTML -->
        <%--<jsp:include page="footer.jsp"></jsp:include>--%>
        <!-- Footer End -->



        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="template course_detail/lib/easing/easing.min.js"></script>
        <script src="template course_detail/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="template course_detail/lib/isotope/isotope.pkgd.min.js"></script>
        <script src="template course_detail/lib/lightbox/js/lightbox.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

        <!-- Contact Javascript File -->

        <!-- Template Javascript -->
        <script src="template course_detail/js/main.js"></script>


        <!--JavaScript DataTable-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="js/listquestionbysubject.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/dataTables.jqueryui.min.js"></script>

        <script type="text/javascript">
                                    let accountData = [];
                                    let role = [];
                                    let active = [];
                                    let aid = '';
                                    let roleEdit = '';
                                    let action = '';


                                    $(document).ready(function () {
                                        getAccountData();
                                    });

                                    function addStatusData() {
                                        var checkStatus = document.getElementsByName('status');
                                        var temp = [];
                                        for (var i = 0; i < checkStatus.length; i++) {
                                            if (checkStatus[i].checked && !temp.includes(checkStatus[i].value)) {
                                                temp.push(checkStatus[i].value);
                                            } else {
                                                temp.splice(i, 1);
                                            }
                                        }accounttable
                                        active = temp;
                                        console.log(active);
                                        getAccountData();
                                    }

                                    function addRoleData() {
                                        var checkRole = document.getElementsByName('role');
                                        var temp = [];
                                        for (var i = 0; i < checkRole.length; i++) {
                                            if (checkRole[i].checked && !temp.includes(checkRole[i].value)) {
                                                temp.push(checkRole[i].value);
                                            } else {
                                                temp.splice(i, 1);
                                            }
                                        }
                                        role = temp;
                                        console.log(role);
                                        getAccountData();
                                    }

                                    function getAccountData() {
                                        let data = {
                                            roleCheck: role.toString(),
                                            activeCheck: active.toString(),
                                            aid: aid,
                                            action: action,
                                            role: roleEdit
                                        };
                                        console.log(data);
                                        $.ajax({
                                            async: false,
                                            url: "/learnx/manageaccount",
                                            type: 'post',
                                            dataType: 'json',
                                            data: data,
                                            success: function (data) {
                                                console.log(data);
                                                accountData = data;
                                                table.clear().draw();
                                                table.rows.add(data).draw();
                                            }
                                        });
                                    }

                                    function blockAccount(id) {
                                        if (confirm("Do you want to BLOCK this Account")) {
                                            aid = id.toString();
                                            action = "block";
                                            getAccountData();
                                        }
                                    }

                                    function activeAccount(id) {
                                        if (confirm("Do you want to ACTIVE this Account")) {
                                            aid = id.toString();
                                            action = "active";
                                            getAccountData();
                                        }
                                    }

                                    function editRole(id) {
                                        roleEdit = document.getElementById('role' + id).value;
                                        action = 'edit';
                                        aid = id;
                                        getAccountData();
                                    }


                                    var table = $('#accounttable').DataTable({
                                        scrollY: 550,
                                        data: accountData,
                                        columns: [
                                            {data: 'username'},
                                            {data: 'fullname'},
                                            {data: 'email'},
                                            {data: 'dob'},
                                            {data: 'phone'},
                                            {data: 'roleName'},
                                            {data: 'active',
                                                render: function (data) {
                                                    if (data === 1) {
                                                        return '<p style="color: green;font-size: 15px;font-weight: bold;">ACTIVE</p>';
                                                    }
                                                    if (data === 2) {
                                                        return '<p style="color: red;font-size: 15px;font-weight: bold;">BLOCKED</p>';
                                                    }
                                                }
                                            },
                                            {data: null,
                                                render: function (data) {
                                                    return '<select onchange="editRole(' + data.account_id + ')" id="role' + data.account_id + '">' +
                                                            '<option ' + (data.role_id === 2 ? 'selected' : '') + ' value="2">Learner</option>' +
                                                            '<option ' + (data.role_id === 3 ? 'selected' : '') + ' value="3">Lecturer</option>' +
                                                            '<option ' + (data.role_id === 4 ? 'selected' : '') + ' value="4">Marketer</option>' +
                                                            '' +
                                                            '</select>';
                                                }
                                            },
                                            {data: null,
                                                render: function (data) {
                                                    if (data.active === 2) {
                                                        return '<button onclick = "activeAccount(' + data.account_id + ')" style="color: green;font-size: 15px;font-weight: bold;">ACTIVE</button>';
                                                    }
                                                    if (data.active === 1) {
                                                        return '<button onclick = "blockAccount(' + data.account_id + ')" style="color: red;font-size: 15px;font-weight: bold;">BLOCKED</button>';
                                                    }
                                                }
                                            }

                                        ]
                                    });

        </script>   

    </body>

</html>
