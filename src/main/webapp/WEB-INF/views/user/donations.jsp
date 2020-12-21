<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Donacje</title>
</head>
<body>
<h3 style="text-align: center">>>>Moje donacje<<<</h3>
<table style="width: 100% " border="1">
    <thead>
    <th>Nazwa fundacji</th>
    <th>Ilość worków</th>
    <th>Data odbioru</th>
    <th>Status</th>
    <th>Szczegóły</th>
    </thead>
    <tbody>
    <c:forEach items="${pickedUpDon}" var="item">
        <tr>
            <td>${item.institution.name}</td>
            <td>${item.quantity}</td>
            <td>${item.pickUpDate} ${item.pickUpTime}</td>
            <td>Odebrany</td>
            <td><a href="/donationDetails?id=${item.id}">Details</a></td>
        </tr>
    </c:forEach>
    <c:forEach items="${notPickedUpDon}" var="item">
        <tr>
            <td>${item.institution.name}</td>
            <td>${item.quantity}</td>
            <td>${item.pickUpDate} ${item.pickUpTime}</td>
            <td>Nieodebrany</td>
            <td><a href="/donationDetails?id=${item.id}">Details</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3><a href="/home">Powrót</a></h3>
</body>
</html>
