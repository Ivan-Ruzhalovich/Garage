<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <style>
        <%@include file='css/carInfo.css' %>
    </style>
</head>
<body>
<div class="container">
    <form:form action="history.back()" modelAttribute="carInfo">
        <div class="line">
            <div class="buttonBox">
                <button style="width: 80%" type="button" name="back" onclick="history.back()">Back</button>
            </div>
            <div class="wrapper-h-info">
                <div class="card">
                    <div class="icon"><i class="fas fa-info-circle"></i></div>
                    <div class="box">
                        <div class="subject">
                            <h3>Info</h3>
                            <p>${carInfo.car.brand}${carInfo.car.model}<br>${carInfo.car.year} год <br>
                                    ${carInfo.car.kilometrage}км</p>
                        </div>
                    </div>
                    <p style="color:#909092"><br>Средний расход:
                        <fmt:formatNumber type="number" maxFractionDigits="1"
                                          value="${carInfo.averageFuelConsumption}"/> л <br>
                        Средняя цена километра: <fmt:formatNumber type="number" maxFractionDigits="1"
                                                                  value="${carInfo.averagePriceOfKilometer}"/> руб <br>
                        Средний расход:</p>
                    <div class="icon-times"><i class="fas fa-times"></i></div>
                </div>
            </div>
        </div>
        <div class="line">
        <div class="wrapper-warning">
            <div class="card">
                <div class="icon"><i class="fas fa-exclamation-circle"></i></div>
                <div class="subject">
                    <h3>Warning</h3>
                    <c:forEach var="carRecomendations" items="${carInfo.maintenanceRecommendations}">
                        <c:if test="${carRecomendations.value == 1}">
                            <p>${carRecomendations.key}</p>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="icon-times"><i class="fas fa-times"></i></div>
            </div>
        </div>
        <div class="wrapper-success">
            <div class="card">
                <div class="icon"><i class="fas fa-check-circle"></i></div>
                <div class="subject">
                    <h3>Success</h3>
                    <c:forEach var="carRecomendations" items="${carInfo.maintenanceRecommendations}">
                        <c:if test="${carRecomendations.value == 0}">
                            <p>${carRecomendations.key}</p>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="icon-times"><i class="fas fa-times"></i></div>
            </div>
        </div>
        <div class="wrapper-info">
            <div class="card">
                <div class="icon"><i class="fas fa-info-circle"></i></div>
                <div class="subject">
                    <h3>Info</h3>
                    <p>
                        <c:forEach var="carRecomendations" items="${carInfo.maintenanceRecommendations}">
                        <c:if test="${carRecomendations.value == 99}">
                    <p>${carRecomendations.key}</p>
                    </c:if>
                    </c:forEach>
                </div>
                <div class="icon-times"><i class="fas fa-times"></i></div>
            </div>
        </div>
        </div>
        <div class="refuelBox">
            <div class="buttonBoxWithout">
                <c:url var="allRefuelsButton" value="refuel/AllRefuels">
                    <c:param name="carId" value="${carInfo.car.id}"/>
                </c:url>
                <button style="margin-top: 20px" type="button" onclick="window.location.href='${allRefuelsButton}'">Список всех заправок</button>
                <div class="footerButtonBox">
                    <c:url var="allWorksButton" value="works/allTechCar">
                        <c:param name="carId" value="${carInfo.car.id}"/>
                    </c:url>
                    <button type="button"  style="margin-left: 20%" name="back" onclick="window.location.href='${allWorksButton}'">Список всех технических работ</button>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>