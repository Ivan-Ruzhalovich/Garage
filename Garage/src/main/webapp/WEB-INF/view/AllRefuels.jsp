<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>
        All refuels list
    </title>
    <style>
        <%@include file='css/new-style.css' %>
    </style>
</head>
<body>
<header>
</header>
<main>
    <div class="refuel-wrapper">
        <form:form action="#" modelAttribute="Car">
            <c:url var="backButton" value="/carInfo/${Car.id}"/>
            <c:set var="TotalVolume" scope="page" value="${0}"/>
            <c:set var="TotalPrice" scope="page" value="${0}"/>
            <c:forEach var="total" items="${Car.refuels}">
                <p ${TotalVolume = TotalVolume + total.volume}></p>
                <p ${TotalPrice = TotalPrice + total.price*total.volume}></p>
            </c:forEach>
            <div class="line">
                <div class="buttonBox" style="padding-left: 35px;margin-top: 13px">
                    <button style="margin-right: 24px" type="button" onclick="window.location.href='${backButton}'">Back</button>
                </div>
                <div class="wrapper-h-info">
                    <div class="card">
                        <div class="icon"><i class="fas fa-info-circle"></i></div>
                        <div class="h-box-refuel">
                            <div class="subject">
                                <p style="font-size: 16px; margin-left: 15px">${Car.brand} ${Car.model}
                            </div>
                        </div>
                        <p style="color:#909092 ;font-size: 16px">
                            Залито топлива:
                                <fmt:formatNumber type="Number" maxFractionDigits="1" value="${TotalVolume}"/> л.
                            <br>
                            Потрачено:
                                <fmt:formatNumber type="Number" maxFractionDigits="1" value="${TotalPrice}"/> руб.
                            <br>
                    </div>
                </div>
            </div>
        </form:form>
        <form:form action="/Garage/saveUser" modelAttribute="RefuelsList">
        <div class="line">
            <section class="main-refuelContent">
                <div class="refuelContainer">
                    <c:forEach var="refuel" items="${RefuelsList}">
                        <c:url var="deleteRefuel" value="/refuel/deleteRefuel">
                            <c:param name="refuelId" value="${refuel.id}"/>
                            <c:param name="carId" value="${refuel.carId}"/>
                        </c:url>
                        <div class="buttonBox">
                            <button style="height: 60px" type="button" onclick="window.location.href='${deleteRefuel}'">Удалить</button>
                        </div>
                        <div style="font-size: 14px" class="refuelBox">
                            <div class="r">
                                <p style="color:#909092" class="hStyle">Дата:<br>${refuel.date} </p><br>
                            </div>
                            <div class="r">
                                <p style="color:#909092" class="hStyle">Тип топлива:<br>АИ${refuel.typeFuel}  </p>
                            </div>
                            <div class="r">
                                <p style="color:#909092" class="hStyle">Объем:<br>${refuel.volume} л </p>
                            </div>
                            <div class="r">
                                <p style="color:#909092" class="hStyle">Пробег:<br> ${refuel.kilometrage} км </p>
                            </div>
                            <p style="color:#909092" class="hStyle">Цена:<br> <fmt:formatNumber type="number"
                                                                                                maxFractionDigits="1"
                                                                                                value="${refuel.price * refuel.volume}"/> руб</p>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>
        <section>
            <div class="box">
                <c:url var="addRefuelToCar" value="/refuel/newRefuel">
                    <c:param name="carId" value="${Car.id}"/>
                </c:url>
                <a href="${addRefuelToCar}">Заправить машину.</a>
            </div>
        </section>
    </div>
    </form:form>
</main>
</body>
</html>