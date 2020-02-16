<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="postAPI" value="/api-product"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="alert alert-${alert}">
            ${messageResponse}
        </div>
        <h3 style="color: royalblue; font-family: fantasy; text-align: center;">Product</h3>
        <div class="container">
            <form id="formEdit">
                <div class="form-group">              
                    <select name="manufacturerId">
                        <c:if test="${model.manufacturerId == 0}">
                            <c:forEach var="item" items="${manufacturers}">
                                <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </c:if>
                        <c:if test="${model.manufacturerId != 0}">
                            <c:forEach var="item" items="${manufacturers}">
                                <option value="${item.id}" <c:if test="${item.id eq model.manufacturerId}">selected="selected"</c:if>>
                                    ${item.name}
                                </option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" placeholder="Product Name" name="name" value="${model.name}" required>
                </div>
                <div class="form-group">                   
                    <input type="number" class="form-control" placeholder="Quantity" name="quantity" value="${model.quantity}" required>
                </div>
                <div class="form-group">                   
                    <input type="number" class="form-control" placeholder="Price" name="price" value="${model.price}" required>
                </div>
                <div class="form-group">                   
                    <input type="text" class="form-control" placeholder="Description" name="description" value="${model.description}" required>
                </div>
                <div class="form-group">                   
                    <input type="file" name="file" class="textbox" id="uploadImage"/>
                </div>
                <input type="hidden" value="${model.id}" name="id"/>
                <c:if test="${model.id != 0}">
                    <button type="button" class="btn btn-info" id="addOrUpdateProduct">Update Product</button>
                </c:if>
                <c:if test="${model.id == 0}">
                    <button type="button" class="btn btn-info" id="addOrUpdateProduct">Add Product</button>
                </c:if>
                <a href="<c:url value="/product-list"/>" class="btn btn-info" role="button">Return</a>    
            </form>
        </div>
        <script>
            var base64Image = '';
            var nameImage = '';
            $(document).ready(function () {

            });
            $('#uploadImage').change(function (e) {
                var files = $('#uploadImage')[0].files[0];
                var reader = new FileReader();
                reader.onload = function (e) {
                    base64Image = e.target.result;
                    nameImage = files.name;
                }
                reader.readAsDataURL(files);
            });
            $('#addOrUpdateProduct').click(function () {
                var formData = $("#formEdit").serializeArray();
                var dataArray = {};
                $.each(formData, function (i, v) {
                    dataArray["" + v.name + ""] = v.value;
                });
                dataArray['base64Image'] = base64Image;
                dataArray['nameImage'] = nameImage;
                addOrUpdatePost(dataArray);
            });
            function addOrUpdatePost(data) {
                $.ajax({
                    url: '${postAPI}',
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    success: function (res) {
                        window.location.href = "<c:url value='/product-list'/>";
                    },
                    error: function (res) {
                        window.location.href = "<c:url value='/product-list'/>";
                    }
                });
            }
        </script>
        <footer style="margin-top: 137px" class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white" style="font-family: cursive; font-size: 15px">Copyright &copy; hieufusw 2018</p>
            </div></div>
        <!-- /.container -->
    </footer>
    <script src='<c:url value="/template/vendor/jquery/jquery.min.js"/>'></script>
    <script src='<c:url value="/template/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>
</body>

</html>
