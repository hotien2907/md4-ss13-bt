<%--
  Created by IntelliJ IDEA.
  User: Admin 88
  Date: 11/17/2023
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Form upload file</h2>
<form action="save-file" method="post" enctype="multipart/form-data">
    <input type="file" name="img">
    <button type="submit">Upload</button>
</form>
</body>
</html>
