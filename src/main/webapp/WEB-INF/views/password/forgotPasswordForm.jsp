<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Forgot password</title>
</head>
<body>
<h3>>>>Podaj email, na który wyślemy link do formularza zmiany hasła<<<</h3>
<form:form method="post">
    <table style="width: auto">
        <tr>
            <th>Twój email</th>
            <td><input type="text" name="email"></td>
        </tr>
    </table>
    <input type="submit" value="Wyślij">
</form:form>
</body>
</html>
