<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%--<html lang="en">--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        ${carInfo.car.brand} ${carInfo.car.model}
    </title>
    <style>
        <%@include file='css/carInfo.css' %>
    </style>
</head>
<body>
<div class="container">
    <form:form action="#" modelAttribute="carInfo">
        <c:url var="backButton" value="/userInfo/${carInfo.car.userId}"/>
        <div class="line">
            <div class="buttonBox">
                <button style="width: 80%" type="button" onclick="window.location.href='${backButton}'">Back</button>
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
                    <p style="color:#909092">Средний расход за все время:
                            <fmt:formatNumber type="number" maxFractionDigits="1"
                                              value="${carInfo.averageFuelConsumption}"/> л/100 км <br>
                        Средний расход за последний год:
                            <fmt:formatNumber type="number" maxFractionDigits="1"
                                              value="${carInfo.averageFuelConsumptionOfYear}"/> л/100 км <br>
                        Средняя цена километра за все время:
                            <fmt:formatNumber type="number" maxFractionDigits="1"
                                              value="${carInfo.averagePriceOfKilometer}"/> руб <br>
                        Средняя цена километра за последний год:
                            <fmt:formatNumber type="number" maxFractionDigits="1"
                                              value="${carInfo.averagePriceOfKilometerOfYear}"/> руб <br>
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
                <c:url var="allRefuelsButton" value="/refuel/AllRefuels/${carInfo.car.id}">
                    <c:param name="carId" value="${carInfo.car.id}"/>
                </c:url>
                <button style="margin-top: 20px" type="button" onclick="window.location.href='${allRefuelsButton}'">
                    Список всех заправок
                </button>
                <div class="footerButtonBox">
                    <c:url var="allWorksButton" value="/works/allTechCar/${carInfo.car.id}"/>
                    <button type="button" style="margin-left: 20%" name="back"
                            onclick="window.location.href='${allWorksButton}'">Список всех технических работ
                    </button>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>