<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>New Password Form</title>
</head>
<body>
<h3>>>>Procedura zmiany hasła<<<</h3>
<c:if test="${not empty incorrectRePassword}">
    <h4 style="color: red">${incorrectRePassword}</h4>
</c:if>
<form:form action="saveNewPassword" method="post">
    <input type="hidden" name="email" value="${appUser.email}">
    <table style="width: auto">
        <tr>
            <th>Podaj nowe hasło:</th>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <th>Powtórz nowe hasło:</th>
            <td><input type="password" name="rePassword"></td>
        </tr>
    </table>
    <input type="submit" value="Zapisz">
</form:form>
</body>
</html>
