<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Password Change Info</title>
</head>
<body>
<c:if test="${not empty email}">
<h3>A verification email has been sent to ${email}</h3>
</c:if>
<c:if test="${not empty link}">
    <h3>The link is invalid or broken!</h3>
</c:if>
</body>
</html>
