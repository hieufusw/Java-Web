<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pay Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="alert alert-${alert}">
            ${messageResponse}
        </div>
        <h3 style="color: royalblue; font-family: fantasy; text-align: center;">Payment</h3>
        <div class="container">
            <form action='<c:url value="/check-out"/>' method="POST">
                <div class="form-group">                   
                    <input type="text" class="form-control" placeholder="Customer Name" name="orderName" required>
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" placeholder="Address" name="address" required>
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" placeholder="Phone" name="phone" required>
                </div>
                <div class="form-group">                   
                    <input type="number" class="form-control" name="total" value="${model.total}" readonly>
                </div>
                <button type="submit" class="btn btn-info">Pay</button>
                <a href="<c:url value="/gio-hang"/>" class="btn btn-info" role="button">Back</a>
            </form>
        </div>
        <footer style="margin-top: 213px" class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white" style="font-family: cursive; font-size: 15px">Copyright &copy; hieufusw 2018</p>
            </div>
            <!-- /.container -->
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>
