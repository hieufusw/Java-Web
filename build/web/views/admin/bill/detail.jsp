<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Page</title>        
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
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Customer name</th>
                                <th>Product name</th>
                                <th>Quantity</th>                        
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${orderDetailModels}">
                                <tr>
                                    <td>${item.customerName}</td>
                                    <td>${item.productName}</td>                                    
                                    <td>${item.quantity}</td>                                                              
                                    <td>${item.price}</td>
                                    <td>${item.total}</td>
                                </tr>
                            </c:forEach>                           
                        </tbody>
                    </table>
                    <c:if test="${order.status == 0}">
                        <a href='<c:url value="/bill-change-status?id=${order.id}"/>' class="btn btn-info" role="button">Confirm</a>
                    </c:if>                                    
                    <a href="<c:url value="/bill-list"/>" class="btn btn-info" role="button">Back</a>
                </div>                  
            </div>
            <!-- /.row -->
        </div>
         <footer style="margin-top: 298px" class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white" style="font-family: cursive; font-size: 15px">Copyright &copy; hieufusw 2018</p>
            </div>
            <!-- /.container -->
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>

