<<%-- 
    Document   : A_managesubject
    Created on : May 23, 2024, 11:09:06 PM
    Author     : slhoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Courses | Education</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"
                integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>

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
        <link type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css" rel="stylesheet">
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


        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Handlee&family=Nunito&display=swap" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Flaticon Font -->
        <link href="template course_detail/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="template course_detail/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/managesubject.css" rel="stylesheet">

        <link href="template course_detail/lib/lightbox/css/lightbox.min.css" rel="stylesheet">



        <!-- Customized Bootstrap Stylesheet -->
        <link href="template course_detail/css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.dataTables.min.css">

    </head>

    <body>

        <div style="background: #963ef5;" class="table-wrapper">
            <div style="background: #963ef5;" class="table-title">
                <div class="row">
                    <div  class="col-sm-6 button-group">
                        <h2  style="color: whitesmoke; background: #963ef5" >Manage Subject</h2>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 button-group-area", style="margin-left:  60rem; margin-bottom: 50px">
                <a href="addsubject" class="btn btn-success" data-toggle="modal">
                    <i class="material-icons">&#xE147;</i> <span>Add New Subject</span>
                </a>
                <a href="subjectlist" class="btn btn-success">
                    <i class="material-icons"></i> <span>View Subject List</span>
                </a>

            </div>
        </div>

        <link href="css/dt.css" rel="stylesheet" type="text/css"/>
        <h1>${mess}</h1>
        <table  id="tablesubject", class="cell-border">
            <thead>
                <tr>
                    <td>Subject_ID</td>
                    <td>Subject_Name</td>
                    <td>Description</td>
                    <td>Price</td>
                    <td>Create_Date</td>
                    <td>Image</td>
                    <td>Category</td>
                    <td>Actions</td> </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>


</body>



<!-- JavaScript Libraries -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="template course_detail/lib/easing/easing.min.js"></script>
<script src="template course_detail/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="template course_detail/lib/isotope/isotope.pkgd.min.js"></script>
<script src="template course_detail/lib/lightbox/js/lightbox.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

<!-- Contact Javascript File -->
<script src="template course_detail/mail/jqBootstrapValidation.min.js"></script>
<script src="template course_detail/mail/contact.js"></script>
<!-- Template Javascript -->
<script src="template course_detail/js/main.js"></script>
<script src="https://cdn.datatables.net/2.0.7/js/dataTables.min.js"></script>
<!--CRUD on DataTable & Handle Exception-->
<script type="text/javascript">
    // instance variable
    var dataS = [];


    $(document).ready(function () {
        loadData();
    });

    var table = $('#tablesubject').DataTable({
        data: dataS,
        columns: [
            {
                data: null,
                render: function (data) {
                    return '<p style="weigh = 10px">' + data.subject_id + '</p>';
                }
            },
            {data: null,
                render: function (data) {
                    return '<a href="course-details?sid=' + data.subject_id + '">' + data.subject_name + '</a>';
                }
            },
            {data:
                        'description'
            },
            {data: 'price',
                render: function (data) {
                    return '<p style="color: green;">' + data + '</p>';
                }
            },
            {
                data: 'created_date'
            },
            {data: 'image',
                sortable: false,
                searchable: false,
                render: function (image) {
                    return  '<div style="width: 10rem; height: 10rem; overflow: hidden;" data-image="' + image + '">' +
                            '<img src="' + image + '" alt="Image" style="width: 100%; height: 100%; border-radius: 5px; object-fit: cover"/> </div>';
                }
            },
            {
                data: 'cate.category_name'
            },

            {data: null,
                render: function (data) {

                    if (data.status === '1') {
                        return '<a href="editsubject?sid=' + data.subject_id + '">'
                                + '<button class="btn btn-success btn-sm">Edit</button></br>'
                                + '</a>'
                                + '<a href="blocksubject?sid=' + data.subject_id + '&action=block">'
                                + '<button onclick="return confirm(\' Do you want to BLOCK this SUBJECT \')" class="btn btn-danger btn-sm">Block</button>'
                                + '</a>'
                                + '<a href="managementcourse?subject_id=' + data.subject_id + '">'
                                + '<button class="btn btn-info">Management Course</button></br>'
                                + '</a>'
                                ;
                    } else {
                        return '<a href="editsubject?sid=' + data.subject_id + '&action=active">'
                                + '<button class="btn btn-success btn-sm">Edit</button></br>'
                                + '</a>'
                                + '<a href="blocksubject?sid=' + data.subject_id + '&action=active">'
                                + '<button onclick="return confirm(\' Do you want to ACTIVE this SUBJECT \')"  class="btn btn-primary btn-sm">Active</button>'
                                + '</a>'
                                + '<a href="managementcourse?subject_id=' + data.subject_id + '">'
                                + '<button class="btn btn-info">Management Course</button></br>'
                                + '</a>'
                                ;
                    }


                }
            }
        ]
    });

    function loadData() {

        $.ajax({
            async: false,
            url: "/learnx/subjectmanage",
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                dataS = data; // lay data tu controller 
                table.clear().draw();
                table.rows.add(dataS).draw();
            },
            error: function (err) {
                console.log(err);
            }
        });
    }


</script>

</body>
</html>
