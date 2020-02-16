<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Manager</title>        
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
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="<c:url value="/home"/>">Bill Manager</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <c:if test="${empty FULLNAME}">

                            <li class="nav-item active">
                                <a class="nav-link" href='<c:url value="/dang-nhap"/>'>Login Admin Page
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>

                        </c:if>
                        <c:if test="${not empty FULLNAME}">
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Welcome ${FULLNAME}
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href='<c:url value="/thoat"/>'>Logout
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

                <div class="col-lg-3">
                    <br/>
                    <div class="list-group">
                        <a href="<c:url value="/product-list"/>" class="list-group-item">Product manager</a>
                        <a href="<c:url value="/customer-list"/>" class="list-group-item">Customer Manager</a>
                        <a href="<c:url value="/manufacturer-list"/>" class="list-group-item">Manufacturer Manager</a>         
                        <a href="<c:url value="/bill-list"/>" class="list-group-item">Bill Manager</a>
                    </div>
                </div>
                <!-- /.col-lg-3 -->

                <div class="col-lg-9">
                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">

                    </div>
                    <div class="row">
                        <c:if test="${(not empty USERNAME) && (not empty model.listResult)}">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Customer Name</th>   
                                        <th>Date</th>
                                        <th>Total</th>
                                        <th>Status</th>                        
                                        <th>View</th>                          
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${model.listResult}">
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.customerName}</td>
                                            <td>${item.date}</td>
                                            <td>${item.total}</td>                                                              
                                            <c:if test="${item.status == 1}">
                                                <td>Done</td>
                                            </c:if>
                                            <c:if test="${item.status == 0}">
                                                <td>Wait</td>
                                            </c:if>                                                
                                            <td><a href="<c:url value="/bill-detail?id=${item.id}"/>">Detail</a></td>
                                        </tr>
                                    </c:forEach>                           
                                </tbody>
                            </table>
                            <div class="col-lg-12">
                                <a href="<c:url value="/home"/>" class="btn btn-info" role="button">Return Home</a>
                            </div>
                        </c:if>
                        <c:if test="${empty USERNAME}">
                            <p style="color: red; font-family: fantasy; font-size: 20px" >Please login before manager !!!</p>
                        </c:if>
                    </div>
                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <footer style="margin-top: 229px"class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
            </div>
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>

