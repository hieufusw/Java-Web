<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload User Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="alert alert-${alert}">
            ${messageResponse}
        </div>
        <h3 style="color: royalblue; font-family: fantasy; text-align: center;">Information Customer</h3>
        <div class="container">
            <form action='<c:url value="/cap-nhat-tai-khoan"/>' method="POST">
                <div class="form-group">                   
                    <input type="text" class="form-control" id="userName" placeholder="User Name" name="userName" value="${model.userName}" required="" readonly="">
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" id="fullName" placeholder="Full Name" name="fullName" value="${model.fullName}" required="">
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" id="fullName" placeholder="Address" name="address" value="${model.address}" required="">
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" id="fullName" placeholder="Phone" name="phone" value="${model.phone}" required="">
                </div>
                <div class="form-group">                    
                    <input type="password" class="form-control" id="password" placeholder="Password" name="password" value="${model.password}" required="">
                </div>
                <input type="hidden" value="${model.id}" name="id"/>                
                <button type="submit" class="btn btn-info">Update</button>
            </form>
        </div>
        <footer style="margin-top: 164px" class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white" style="font-family: cursive; font-size: 15px">Copyright &copy; hieufusw 2018</p>
            </div>
            <!-- /.container -->
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>
