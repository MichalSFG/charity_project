<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>" />
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login">Zaloguj</a></li>
            <li class="highlighted"><a href="/register">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <c:if test="${not empty email}">
        <h3 style="color: red">${email}</h3>
    </c:if>
    <c:if test="${not empty password}">
        <h3 style="color: red">${password}</h3>
    </c:if>
    <form:form action="register" method="post" modelAttribute="appUser">
        <h3 style="color: red"><form:errors path="firstName">Wpisz imię!</form:errors></h3>
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" />
        </div>
        <h3 style="color: red"><form:errors path="lastName">Wpisz nazwisko!</form:errors></h3>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" />
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email" />
        </div>
        <h3 style="color: red"><form:errors path="password">Wpisz hasło (min 8 znaków) zawierające: wielką literę, małą literę, cyfrę i znak specjalny</form:errors></h3>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" />
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>

</body>
</html>
