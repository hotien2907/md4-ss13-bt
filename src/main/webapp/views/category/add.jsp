
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create Category</h1>


<%--@elvariable id="category" type=""--%>
<f:form action="/create-category" method="post" modelAttribute="category">
    <label for="categoryName">categoryName</label>
    <f:input type="text" id="categoryName" path="categoryName" /><br>
    <label >Trang Thai</label>
    <f:radiobutton  path="status" value="0"/><span>An</span>
    <f:radiobutton  path="status" value="1" checked="checked"/><span>Hien</span><br>
    <input type="submit" value="ADD" name="action"/>
</f:form>
</body>
</html>