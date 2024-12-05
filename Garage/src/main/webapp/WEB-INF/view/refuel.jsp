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
        Заправка автомобиля
    </title>
    <style>
        <%@include file='css/index-style.css'%>
    </style>
</head>
<body>
<div class="wrapper">
    <form:form action="/Garage/saveRefuelCar" modelAttribute="NewRefuel">
        <form:hidden path="carId"/>
<%--        <c:url var="backButton" value="/carInfo/${NewRefuel.carId}"/>--%>
        <div class="line">
            <div class="buttonBox">
                <button type="button" onclick="history.back()">Back</button>
            </div>
            <p class="hStyle">Заполните информацию</p>
        </div>
        <br>
        <div class="add-car-input-field">
            <p class="labelSelectField"></p>
            <form:select cssClass="select" path="typeFuel">
                <form:option label="Тип топлива*" value="null"/>
                <form:option label="Бензин АИ-92" value="92" />
                <form:option label="Бензин АИ-95" value="95" />
                <form:option label="Бензин АИ-98" value="98" />
                <form:option label="Бензин АИ-100" value="100" />
                <form:option label="Дизель" value="diesel"/>
            </form:select>
            <label></label>
        </div>
        <div class="input-field">
            <input type="number" step="0.1" name="volume" required/>
            <label>Объем*</label>
        </div>
        <div class="input-field">
            <input type="number" step="0.1" name="price" required/>
            <label>Цена за литр*</label>
        </div>
        <div class="input-field">
            <input type="number" name="kilometrage" required>
            <label>Пробег*</label>
        </div>
        <div class="input-field">
            <p class="labelSelectField">Дата*</p>
            <input type="date" name="date" required/>
            <label></label>
        </div>
        <button type="submit">Добавить</button>
        <br>
        <p class="warnField">* - Поля, обязательные для заполнения</p>
        <form:form action="#" modelAttribute="Message">
            <p class="warnField">${Message}</p>
        </form:form>
    </form:form>
</div>
</body>
</html>