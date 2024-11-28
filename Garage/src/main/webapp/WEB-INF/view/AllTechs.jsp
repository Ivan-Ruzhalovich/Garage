<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        Works List
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
            <c:set var="TotalPrice" scope="page" value="${0}"/>
            <c:forEach var="total" items="${Car.techs}">
                <p ${TotalPrice = TotalPrice + total.price}></p>
            </c:forEach>
            <div class="line">
                <div class="buttonBox" style="padding-left: 35px;margin-top: 13px">
                    <button style="margin-right: 24px" type="button" name="back" onclick="history.back()">Back</button>
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
                            <br>
                            Всего потрачено на ремонт и обслуживание:
                                <fmt:formatNumber type="Number" maxFractionDigits="1" value="${TotalPrice}"/> руб.
                            <br>
                    </div>
                </div>
            </div>
        </form:form>
        <form:form action="/Garage/saveUser" modelAttribute="WorksList">
        <div class="line">
            <section class="main-refuelContent">
                <div class="refuelContainer">
                    <c:forEach var="work" items="${WorksList}">
                        <c:url var="deleteWork" value="/works/deleteWork">
                            <c:param name="workId" value="${work.id}"/>
                            <c:param name="carId" value="${work.carId}"/>
                        </c:url>
                        <div class="buttonBox">
                            <button style="height: 60px" type="button" onclick="window.location.href='${deleteWork}'">Удалить</button>
                        </div>
                        <div class="refuelBox">
                            <div class="r">
                            <p style="color:#909092" class="hStyle">Дата:<br>${work.date} </p>
                            </div>
                            <div class="r">
                            <p style="color:#909092" class="hStyle">Тип работы:<br>${work.work.description}  </p>
                            </div>
                            <div class="r">
                            <p style="color:#909092" class="hStyle">Пробег:<br>${work.kilometrage} км  </p>
                            </div>
                            <p style="color:#909092" class="hStyle">Цена:<br><fmt:formatNumber type="number"
                                                                                            maxFractionDigits="1"
                                                                                            value="${work.price}"/> руб</p>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </div>
    </div>
    </form:form>
</main>
</body>
</html>