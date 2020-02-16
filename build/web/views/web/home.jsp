<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>        
        <link href='<c:url value="/template/vendor/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
        <link href='<c:url value="/template/css/shop-homepage.css"/>' rel="stylesheet">
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="<c:url value="/trang-chu"/>">SE1215 SHOP</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <c:if test="${empty FULLNAME1}">
                            <li class="nav-item">
                                <a class="nav-link" href='<c:url value="/dang-ky"/>'>Register</a>
                            </li>
                            <li>
                                <a class="nav-link" href='<c:url value="/dang-nhap"/>'>Login</a>
                            </li>
                            <li>
                                <a class="nav-link" href='<c:url value="/gio-hang"/>'>Show Cart</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty FULLNAME1}">
                            <li>
                                <c:url var="updateAccount" value="/cap-nhat-tai-khoan">
                                    <c:param name="id" value="${id1}"/>
                                </c:url>
                                <a class="nav-link" href='${updateAccount}'>Welcome, ${FULLNAME1}</a>
                            </li>
                            <li>
                                <a class="nav-link" href='<c:url value="/gio-hang"/>'>Show Cart</a>
                            </li>
                            <li>
                                <a class="nav-link" href='<c:url value="/thoat"/>'>Logout</a>
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
                        <c:forEach var="item" items="${manufacturers}">
                            <a href="<c:url value="/san-pham?manufacturerId=${item.id}"/>" class="list-group-item">${item.name}</a>
                        </c:forEach>                                              
                    </div>

                </div>
                <!-- /.col-lg-3 -->

                <div class="col-lg-9">

                    <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <img class="d-block img-fluid" src="<c:url value="template/images/1.JPG"/>" alt="First slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="<c:url value="template/images/2.jpg"/>" alt="Second slide">
                            </div>
                            <div class="carousel-item">
                                <img class="d-block img-fluid" src="<c:url value="template/images/3.jpg"/>" alt="Third slide">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                    <div class="row">
                        <c:forEach var="item" items="${products}">
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card h-100">
                                    <img class="card-img-top" src="<c:url value="/repository/${item.picture}"/>" alt="">
                                    <div class="card-body">
                                        <h4 class="card-title">
                                            <a href="#">${item.name}</a>
                                        </h4>
                                        <h5>Price: $ ${item.price}</h5>
                                        <p class="card-text" style="font-family: fantasy">Description: ${item.description}</p>
                                    </div>
                                    <div class="card-footer">
                                    <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                                    </div>
                                    <div class="card-footer">
                                        <c:if test="${item.quantity == 0}">
                                            <small class="text-muted">Hết hàng</small>
                                        </c:if>
                                        <c:if test="${item.quantity != 0}">
                                            <small class="text-muted"><a href="<c:url value="/them-san-pham-gio-hang?productId=${item.id}"/>">Add to cart</a></small>
                                        </c:if>

                                    </div>                                   
                                </div>
                            </div>    
                        </c:forEach>                                              

                        <!-- /.row -->

                    </div>
                    <!-- /.col-lg-9 -->

                </div>
                <!-- /.row -->

            </div>
        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; hieufusw 2018</p>
            </div>
            <!-- /.container -->
        </footer>
        <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
        <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
    </body>
</html>

