<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Homepage</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>

<div>
    <div style="background-color: lightblue">
        <header>
            <nav class="container container--70">
                <ul class="nav--actions">
                    <li class="logged-user">
                        Witaj ${user.firstName}
                        <ul class="dropdown">
                            <li><a href="#">Profil</a></li>
                            <li><a href="#">Moje zbiórki</a></li>
                            <li>
                                <form action="<c:url value="/logout"/>" method="post">
                                    <button type="submit" class="btn btn--without-border">Wyloguj
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </header>
    </div>

    <div>
        <div>
            <h1 style="text-align: center">Admin Page</h1>
        </div>

        <div style="background-color: lightgreen">
            <h3><span>Użytkownicy</span></h3>
            <table style="width: 50%" border="1">
                <thead>
                <th>Id</th>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email</th>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Ola</td>
                    <td>Nowak</td>
                    <td>ola@ola</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>