<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <!-- Search Form -->
    <form action="<%=request.getContextPath()%>/StudentServlet" method="GET">
        <input type="text" name="search-name">
        <input type="submit" value="SEARCH" name="action">
    </form>
    <form action="<%=request.getContextPath()%>/StudentServlet" method="GET">
        <label>Sort</label>
        <select name="sort">
            <option value="studentName">Name</option>
            <option value="age">Age</option>
        </select>
        <br>
        <label>By</label>
        <select name="by">
            <option value="ASC">Tăng Dần</option>
            <option value="DESC">Giảm Dần</option>
        </select>
        <input type="submit" value="SORT" name="action">
        <br>
    </form>
    <!-- Student Table -->
    <div class="row">
        <div class="col-lg-6">
            <table class="table">
                <thead>
                <tr>
                    <th>ProductId</th>
                    <th>ProductName</th>
                    <th>price</th>
                    <th>image</th>
                    <th>category</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>l
                <c:forEach items="${products}" var="pr">
                    <tr>
                        <td>${pr.id}</td>
                        <td>${pr.nameProduct}</td>
                        <td>${pr.price}</td>

                        <td><img src="<c:url value="uploads"/>/images/${pr.image}" width="150px" alt="loi anh"></td>
                        <td>${pr.category.categoryName}</td>

                        <td>

                            <a href="edit-product/${pr.id}">EDIT</a>
                            <a href="delete-product/${pr.id}">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a href="/add-product"> Create Product</a>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
