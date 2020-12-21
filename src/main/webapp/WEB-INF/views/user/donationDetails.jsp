<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Donation Details</title>
</head>
<body>
<h3>Informacje o donacji</h3>
<table style="width: auto" border="1">
    <tr>
        <th>Odbiorca</th>
        <td>${donation.institution.name}</td>
    </tr>
    <tr>
        <th>Data utworzenia wpisu</th>
        <td>${donation.created}</td>
    </tr>
    <tr>
        <th>Data odbioru daru</th>
        <td>${donation.pickUpDate} ${donation.pickUpTime}</td>
    </tr>
    <tr>
        <th>Status</th>
        <td>
            <c:choose>
                <c:when test="${donation.pickedUp}">
                    Odebrany
                </c:when>
                <c:otherwise>
                    Nieodebrany
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
    <tr>
        <th>Przekazane dary</th>
        <td>
            <c:forEach items="${donation.categories}" var="item">
                ${item.name}<br>
            </c:forEach>
        </td>
    </tr>
</table>
<h3>Zmień status daru:
    <form:form action="changeStatus" method="post" modelAttribute="donation">
        <form:hidden path="id"/>
        <form:hidden path="quantity"/>
        <form:hidden path="created"/>
        <form:hidden path="city"/>
        <form:hidden path="pickUpComment"/>
        <form:hidden path="street"/>
        <form:hidden path="zipCode"/>
        <form:hidden path="appUser"/>
        <form:hidden path="institution"/>
        <form:hidden path="categories"/>
        <form:checkbox path="pickedUp"/>
        <button type="submit">Zapisz</button>
    </form:form>
</h3>
<h3><a href="/donations">Powrót</a></h3>
</body>
</html>
