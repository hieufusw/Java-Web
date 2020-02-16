<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>

        <title>Register Page</title>

        <link type="text/css" rel="stylesheet" href="./public/css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="./public/css/login_style.css"/>


    </head>
    <body class="my-login-page">
        <div class="alert alert-${alert}">
            ${messageResponse}
        </div>
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center h-100">
                    <div class="card-wrapper">
                        <div class="brand">
                            <img src="./public/img/logo.png">
                        </div>
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Register</h4>

                                <form action='<c:url value="/dang-ky"/>' method="POST">
                                    <div class="form-group">
                                        <label for="cname">FullName</label>
                                        <input id="cname" type="text" class="form-control" name="fullName" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label for="address">Address</label>
                                        <input id="address" type="text" class="form-control" name="address" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input id="username" type="text" class="form-control" name="phone" required=""/>
                                    </div>
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input id="username" type="text" class="form-control" name="userName" required=""/>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input id="password" type="password" class="form-control" name="password" required=""/>
                                    </div>

                                    <div class="form-group">
                                        <label>
                                            <input type="checkbox" name="aggree" value="1" checked=""/> I agree to the Terms and Conditions
                                        </label>
                                    </div>

                                    <div class="form-group no-margin">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Register
                                        </button>
                                    </div>
                                    <div class="margin-top20 text-center">
                                        Already have an account? <a href='<c:url value="/dang-nhap"/>'>Login</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="footer">
                            Copyright &copy; hieufusw 2018
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="./public/js/jquery-3.3.1.min.js"></script>
        <script src="./public/js/bootstrap.min.js"></script>
        <script>
            if (window.history.replaceState) {
                window.history.replaceState(null, null, window.location.href);
            }
        </script>
    </body>
</html>
