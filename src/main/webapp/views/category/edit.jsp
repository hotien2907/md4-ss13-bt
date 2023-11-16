<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Update Category</h1>

<%--@elvariable id="category" type=""--%>
<f:form action="/update-category/${category.categoryId}" method="post" modelAttribute="category">
    <input type="hidden" id="id" path="id" value="${category.categoryId}" readonly/><br>
    <label for="categoryName">categoryName</label>
    <f:input type="text" id="categoryName" path="categoryName" value="${category.categoryName}"/><br>
    <label>Trang Thai</label>
    <f:radiobutton path="status" value="0"  checked="checked"/><span>An</span>
    <f:radiobutton path="status" value="1" /><span>Hien</span><br>
    <input type="submit" value="UPDATE" name="action"/>
</f:form>
</body>
</html>
