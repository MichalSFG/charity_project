<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Fundacje</title>
</head>
<body>
<h3 style="text-align: center">>>>Lista fundacji<<<</h3>
<table style="width: 100%" border="1">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Description</th>
    <th>Action</th>
    </thead>
    <tbody>
    <c:forEach items="${all}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.description}</td>
            <td><a href="/admin/editInst?id=${item.id}">Edytuj</a>
                <a href="/admin/deleteInst?id=${item.id}">Usuń</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2><a href="/admin/addInst">Dodaj nową fundację</a></h2>
<h3><a href="/admin/home">Wróć do strony głównej admina</a></h3>
</body>
</html>
