<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Garage</title>
    <style>
        <%@include file='../css/new-style.css' %>
    </style>
</head>
<body>
<header>
    <p class="your-garage">Твой гараж</p>
    <div class="container">
                <ul class="h-row">
                    <li><a style="color: white" href="#">База авто</a></li>
                    <li><a style="color: white" href="#">База ТО</a></li>
                    <li><a style="color: white" href="#">База заправок</a></li>
                </ul>
    </div>
</header>
<main>
<h3 align="center">Все пользователи приложения гараж</h3>
    <img src="${pageContext.servletContext.contextPath}view/pic/sgawiLypY7I.jpg" alt="123"/>
    <section class="main-content">

    <c:forEach var="user" items="${allUsers}">
        <c:url var="deleteButton" value="deleteUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <c:url var="registredNewUserButton" value="/registred/">

        </c:url>
            <c:url var="info" value="infoUser">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
        <div class="box">
            <a href=${info} >${user.name} ${user.surname}</a>
            <br>
            <c:forEach var="car" items="${user.cars}">
                <c:url var="infoCar" value="carInfo">
                    <c:param name="carId" value="${car.id}"/>
                </c:url>
                <div class="carBox">
                <p><a href="${infoCar}">${car.brand} ${car.model}</a></p>
                </div>
            </c:forEach>
<%--                <input type="button" value="Удалить" onclick="window.location.href = '${deleteButton}'">--%>
        </div>
        <br>
    </c:forEach>
    </section>
<br>
<input type="button" value="Добавить пользователя"
onclick="window.location.href= '${registredNewUserButton}'">
</main>
<footer>

</footer>
</body>
</html>