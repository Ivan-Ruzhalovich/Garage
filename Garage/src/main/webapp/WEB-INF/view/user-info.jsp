<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        ${UserInfo.name} ${UserInfo.surname}
    </title>
    <style>
        <%@include file='css/new-style.css' %>
    </style>
</head>
<body>
<header>

</header>
<main>
    <form:form action="/Garage/saveUser" modelAttribute="UserInfo">
    <form:hidden path="id"/>
    <c:url var="addCarButton" value="addCar">
        <c:param name="userId" value="${UserInfo.id}"/>
    </c:url>
    <div class="user-wrapper">
        <div class="line">
            <c:url var="userAccountInfo" value="userAccountInfo">
                <c:param name="userId" value="${UserInfo.id}"/>
            </c:url>
            <div class="buttonBox">
                <button type="button"
                        onclick="window.location.href='${userAccountInfo}'">${UserInfo.surname} ${UserInfo.name}</button>
            </div>
            <p class="hStyle">Добро пожаловать, <br>${UserInfo.surname} ${UserInfo.name}!</p>
        </div>
        <p class="hStyle">Твой гараж:</p>
        <div class="line">
            <section class="main-content">
                <div class="container">
                    <c:forEach var="auto" items="${UserInfo.cars}">
                        <c:url var="carInfo" value="/carInfo">
                            <c:param name="carId" value="${auto.id}"/>
                        </c:url>
                        <c:url var="addNewTechToCar" value="works/addNewTechToCar">
                            <c:param name="carId" value="${auto.id}"/>
                        </c:url>
                        <c:url var="addRefuelToCar" value="refuel/newRefuel">
                            <c:param name="carId" value="${auto.id}"/>
                        </c:url>

                        <div class="box">
                            <button style="font-size: 20px" type="button" onclick="window.location.href='${carInfo}'">${auto.brand}<br>${auto.model}</button>
                            <br>
                            <section>

                                <button type="button" onclick="window.location.href='${addNewTechToCar}'">Обслужить</button>
                                <br>
                                <button type="button" onclick="window.location.href='${addRefuelToCar}'">Заправить</button>
                                <br>
                                <c:url var="deleteCar" value="deleteCar">
                                    <c:param name="carId" value="${auto.id}"/>
                                    <c:param name="userId" value="${UserInfo.id}"/>
                                </c:url>
                                <button type="button" onclick="window.location.href='${deleteCar}'">Удалить</button>
                            </section>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>
        <section>
            <div class="box">
                <c:url var="addCar" value="addCar">
                    <c:param name="userId" value="${UserInfo.id}"/>
                </c:url>
                <a href="${addCar}">Купил новую машину? <br>Добавь ее в приложение.</a>
            </div>
        </section>
        </form:form>
</main>
</body>
</html>