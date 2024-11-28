<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>
        ${carInfo.car.brand} ${carInfo.car.model}
    </title>
    <style>
        <%@include file='../css/carInfo.css' %>
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<form:form action="home" modelAttribute="carInfo">
<h2>${carInfo.car.brand}${carInfo.car.model}</h2>

<h3 style="color: black">Рекомендации</h3>
<table>
    <c:forEach var="carRecomendations" items="${carInfo.maintenanceRecommendations}">
    <tbody>
    <tr style="color: black">
        <td>-</td>
        <c:if test="${carRecomendations.value}==1">
            <td><p>${carRecomendations.key}</p></td>
        </c:if>

    </tr>
    </tbody>
    </c:forEach>
</table>
    <button class="glow-on-hover"  type="button" name="back" onclick="history.back()">Back</button>
    <br>
<br><br>
    <p style="color: black">
        Все заправки:
    </p>
        <c:forEach var="refuel" items="${carInfo.car.refuels}">
            <p style="color: black" >${refuel.date}</p>
            <br>
            <p>${refuel.volume}</p>
            <br>
            <p>${refuel.typeFuel}</p>
            <br>
            <p>${refuel.kilometrage}</p>
            <br>
        </c:forEach>

</form:form>
</body>
</html>