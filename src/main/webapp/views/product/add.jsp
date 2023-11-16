<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>add.jsp
<h1>Create Product</h1>

<%--@elvariable id="product" type=""--%>
<f:form action="/create-product" method="post" modelAttribute="product">
    <label for="productName">ProductName</label>
    <f:input type="text" id="productName" path="nameProduct" /><br>
    <label for="price">PRICE</label>
    <f:input type="text" id="price" path="price" /><br>
    <label >Choose Category</label>
    <select class="form-control" name="category.categoryId">
        <c:forEach items="${category}" var="item">
            <option value="${item.categoryId}">${item.categoryName}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="ADD" name="action"/>
</f:form>
</body>
</html>
