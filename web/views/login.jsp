<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>

        <title>Login Page</title>

        <link type="text/css" rel="stylesheet" href="./public/css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="./public/css/login_style.css"/>
    </head>
    <body class="my-login-page">
        <section class="h-100">
            <div class="container h-100">
                <div class="row justify-content-md-center h-100">
                    <div class="card-wrapper">
                        <div class="brand">
                            <img src="./public/img/logo.png">
                        </div>
                        <div class="card fat">
                            <div class="card-body">
                                <h4 class="card-title">Login</h4>

                                <div class="alert alert-${alert}">
                                    ${messageResponse}
                                </div>
                                <form action='<c:url value="/dang-nhap"/>' method="POST">

                                    <div class="form-group">
                                        <label for="username">User name</label>

                                        <input id="username" type="text" class="form-control" name="userName" value="" required autofocus>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password
                                        </label>
                                        <input id="password" type="password" class="form-control" name="password" required data-eye>
                                        <a href="#" class="float-right">
                                            Forgot Password?
                                        </a>
                                    </div>

                                    <div class="form-group">
                                        <label>
                                            <input type="checkbox" name="remember"> Remember Me
                                        </label>
                                    </div>

                                    <div class="form-group no-margin">
                                        <button type="submit" class="btn btn-primary btn-block">
                                            Login
                                        </button>
                                    </div>
                                    <div class="margin-top20 text-center">
                                        Don't have an account? <a href='<c:url value="/dang-ky"/>'>Create One</a>
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
