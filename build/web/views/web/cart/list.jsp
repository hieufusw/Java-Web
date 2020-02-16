<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>        
        <link href='<c:url value="/template/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
        <link href='<c:url value="/template/css/shop-homepage.css"/>' rel="stylesheet">
    </head>
    <body>
   <style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: bisque;
}
</style>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="<c:url value="/trang-chu"/>">Cart Page</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <c:if test="${empty FULLNAME1}">

                            <li class="nav-item active">
                                <a class="nav-link" href='<c:url value="/dang-ky"/>'>Register
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            
                            <li class="nav-item active">
                                <a class="nav-link" href='<c:url value="/dang-nhap"/>'>Login
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            

                        </c:if>
                        <c:if test="${not empty FULLNAME1}">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Welcome ${FULLNAME1}
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="alert alert-${alert}">
                    ${messageResponse}
                </div>
                <div class="col-lg-12">                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>        
                                <th>ID</th>
                                <th>Name</th>                               
                                <th>Price</th>                                
                                <th>Total</th>
                                <th>Quantity</th>
                                <th>Update</th>
                                <th>Remove</th>                                         
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${model.listResult}">
                                <tr >
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>                                   
                                    <td>${item.price}</td>
                                    <td>${item.total}</td>
                                    <form action="<c:url value="/cap-nhat-cart"/>" method="POST">
                                        <td><input type="text" value="${item.quantity}" name="quantity"/></td>
                                        <td><input type="submit" value="Update"/></td>
                                        <input type="hidden" value="${item.id}" name="id"/>
                                        <input type="hidden" value="${item.productId}" name="productId"/>
                                    </form>                                    
                                    <td>
                                        <a href="<c:url value="/xoa-san-pham-cart?productId=${item.productId}"/>">Remove</a>
                                    </td>
                                </tr>
                            </c:forEach> 
                                <c:if test="${not empty model.listResult}">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td>Total</td>
                                        <td>${total}</td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="<c:url value="/xoa-tat-ca-san-pham-cart"/>">Remove All</a></td>
                                    </tr>
                                </c:if>                                   
                        </tbody>
                    </table>
                    <c:if test="${(not empty USERNAME1) && (not empty model.listResult)}">
                        <a href="<c:url value="/check-out"/>" class="btn btn-info" role="button">Checkout</a>
                    </c:if>
                    <c:if test="${empty USERNAME1}">
                        <p>Please login before checkout</p>
                    </c:if>
                    <a href="<c:url value="/trang-chu"/>" class="btn btn-info" role="button">Home</a>    
                </div>                  
            </div>
            <!-- /.row -->
        </div>
        <footer style="margin-top: 303px" class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; hieufusw 2018</p>
            </div>
            <!-- /.container -->
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>

