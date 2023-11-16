<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update Product</h1>

<%--@elvariable id="product" type=""--%>
<f:form action="/update-product/${product.id}" method="post" modelAttribute="product">
    <input type="hidden" id="id" path="id" value="${product.id}" readonly/><br>
    <label for="productName">ProductName</label>
    <f:input type="text" id="productName" path="nameProduct"  value="${product.nameProduct}" /><br>
    <label for="price">PRICE</label>
    <f:input type="text" id="price" path="price"  value="${product.price}" /><br>
    <label >Choose Category</label>
    <select class="form-control" name="category.categoryId">
        <c:forEach items="${category}" var="item">
            <option value="${item.categoryId}" ${product.getCategory().categoryId == item.categoryId? "selected": ""} >${item.categoryName}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="UPDATE" name="action"/>
</f:form>
</body>
</html>
