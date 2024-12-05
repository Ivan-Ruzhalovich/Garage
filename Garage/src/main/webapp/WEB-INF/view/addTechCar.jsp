<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        Ремонт или обслуживание
    </title>
    <style>
        <%@include file='css/index-style.css'%>
    </style>
</head>
<body>
<div class="wrapper">
    <form:form action="/Garage/saveTechCar" modelAttribute="NewTechCar">
        <form:hidden path="carId"/>
<%--        <c:url var="backButton" value="/carInfo/${NewTechCar.carId}"/>--%>
        <div class="line">
            <div class="buttonBox">
                <button type="button" onclick="history.back()">Back</button>
            </div>
            <p class="hStyle">Заполните информацию об обслуживании или ремонте</p>
        </div>
        <br>
        <div class="add-car-input-field">
            <p class="labelSelectField"></p>
            <form:select cssClass="select" path="work.id">
                <form:option label="Вид работы*" value="0"/>
                <form:options items="${NewTechCar.works}"/>
            </form:select>
            <label></label>
        </div>
        <div class="input-field">
            <p class="labelSelectField">Дата*</p>
            <input type="date" name="date" required/>
            <label></label>
        </div>
        <div class="input-field">
            <input type="number" name="kilometrage" required>
            <label>Пробег*</label>
        </div>
        <div class="input-field">
            <input type="number"  name="price" required/>
            <label>Цена*</label>
        </div>
        <button type="submit">Добавить</button>
        <br>
        <br>
        <p class="warnField">* - Поля, обязательные для заполнения</p>
        <p class="warnField">${Message}</p>
    </form:form>
</div>
</body>
</html>