<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Fundacje</title>
</head>
<body>
<h3>>>>Zmień dane fundacji:<<<</h3>
<form:form method="post" modelAttribute="institution">
    <form:hidden path="activated"/>
    <table style="width: auto">
        <tr>
            <th>Nazwa</th>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <th>Cel fundacji</th>
            <td><form:input path="description"/></td>
        </tr>
    </table>
    <input type="submit" value="Zapisz">
</form:form>
<h3><a href="/admin/home">Wróć do strony głównej admina</a></h3>
</body>
</html>
