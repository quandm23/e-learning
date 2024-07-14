<%-- 
    Document   : changepassword
    Created on : May 17, 2024, 5:21:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change password</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="css/linearicons.css" />
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <div class="inner-change">
            <div class="mainDiv">
                <div class="cardStyle">
                    <form action="" method="post" name="signupForm" id="signupForm">

                        <img src="img/changepassword.png" id="signupLogo"/>

                        <h2 class="formTitle">
                            Change Your Password
                        </h2>

                        <div class="inputDiv">
                            <label class="inputLabel" for="oldPassword">Old Password</label>
                            <input type="password" id="oldPassword" name="oldPassword" required>
                        </div>

                        <div class="inputDiv">
                            <label class="inputLabel" for="newPassword">New Password</label>
                            <input type="password" id="newPassword" name="newPassword"required>
                        </div>

                        <div class="inputDiv">
                            <label class="inputLabel" for="confirmPassword">Confirm Password</label>
                            <input type="password" id="confirmPassword" name="confirmPassword"> 
                        </div>
                        
                        <div class="buttonWrapper">

                            <button type="submit" id="submitButton" onclick="validateSignupForm()" class="submitButton pure-button pure-button-primary mb-3">
                                <span>Continue</span>
<!--                                <span id="loader"></span>-->
                            </button>
                            </a>
                        </div>

                    </form>
                    <a href="home"><button class="submitButton">Back to Home</button></a>
                    <td style="color: red">${err}</td>
                    <br/>
                </div>
            </div>
        </div>

    </body>
</html>
