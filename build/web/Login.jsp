<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <link rel="stylesheet" type="text/css" href="css/mainlogin.css">
        <!--===============================================================================================-->
        <script>
            function closeMessage() {
                document.querySelector('.success-message').style.display = 'none';
            }
        </script>
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
                    <form class="login100-form validate-form" action="login" method="POST">
                        <span class="login100-form-title p-b-49">
                            Login
                        </span>

                        <div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired" >
                            <span class="label-input100">Username</span>
                            <input class="input100" required type="text" name="user" placeholder="Type your username" value="${cookie.cuser.value}">
                            <span class="focus-input100" data-symbol="&#xf206;"></span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <span class="label-input100">Password</span>
                            <input class="input100" required type="password" value="${cookie.cpass.value}" name="pass" placeholder="Type your password">
                            <span class="focus-input100" data-symbol="&#xf190;"></span>
                        </div>

                        <div class="d-flex justify-content-between align-items-center p-t-8 p-b-31">
                            <div style="display: flex; align-items: center;">
                                <input type="checkbox" name="remember" value="on" ${cookie.crem != null ? 'checked' : ''} />
                                <a style="color: black; margin-bottom: 0; margin-left: 5px;">Remember me</a>
                            </div>
                            <a href="forgetpassword">
                                Forgot password?
                            </a>
                        </div>

                        <div>
                            <h6 class="text-danger">${mess}</h6>
                        </div>
                        <div class="container-login100-form-btn">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Login
                                </button>
                            </div>
                        </div>

                        <div class="txt1 text-center p-t-54 p-b-20">
                            <span>
                                Or Sign Up Using
                            </span>
                        </div>

                        <div class="flex-c-m">

                            <a href="https://accounts.google.com/o/oauth2/auth?scope=profile%20email&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flearnx%2Flogin&response_type=code&client_id=166140166668-7l6pgr29e9mfhqo6o0trianj33fnl374.apps.googleusercontent.com&approval_prompt=force&access_type=offline" class="login100-social-item bg3">
                                <i class="fa fa-google"></i>
                            </a>
                        </div>
                        <% 
                      String successMessage = (String) request.getSession().getAttribute("successMessage");
                      if (successMessage != null) { 
                          request.getSession().removeAttribute("successMessage");
                        %>
                        <div class="success-message" style="color:green;">
                            <%= successMessage %>
                            <button onclick="closeMessage()">X</button>
                        </div>
                        <% } %>
                        <div class="flex-col-c p-t-155">
                            <span class="txt1 p-b-17">
                                Don't have an account?
                            </span>

                            <a href="register" class="txt2">
                                Sign Up
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js"></script>
        <script src="vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="js/main_1.js"></script>

    </body>
</html>