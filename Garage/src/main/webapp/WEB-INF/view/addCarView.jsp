<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        Добавление нового авто
    </title>
    <style>
        <%@include file='css/index-style.css'%>
    </style>
</head>
<body>
<div class="wrapper">
    <div class="line">
        <c:url var="backButton" value="/userInfo/${NewCar.userId}"/>
        <div class="buttonBox">
            <button type="button" name="back" onclick="window.location.href='${backButton}'">Back</button>
        </div>
        <p class="hStyle">Заполните характеристики автомобиля </p>
    </div>
    <form:form action="saveCar" modelAttribute="NewCar">
        <form:hidden path="userId"/>
        <div class="input-field">
            <input type="text" name="brand" required/>
            <label>Бренд*</label>
        </div>
        <div class="input-field">
            <input type="text" name="model" required/>
            <label>Модель*</label>
        </div>
        <div class="input-field">
            <input type="text" name="year" required/>
            <label>Год выпуска*</label>
        </div>
        <div class="input-field">
            <input type="text" name="kilometrage" required/>
            <label>Пробег*</label>
        </div>
        <div class="add-car-input-field">
            <p class="labelSelectField"></p>
            <form:select cssClass="select" path="engine">
                <form:option label="Выберите двигатель" value="null"/>
                <form:option label="Бензин" value="petrol"/>
                <form:option label="Дизель" value="diesel"/>
            </form:select>
            <label></label>
        </div>
        <br>
        <div class="add-car-input-field">
            <p class="labelSelectField"></p>
            <form:select cssClass="select" path="transmission">
                <form:option label="Выберите коробку передач" value="null"/>
                <form:option label="Механическая коробка передач" value="MT"/>
                <form:option label="Автоматическая коробка передач" value="AMT"/>
                <form:option label="Роботизированная коробка передач" value="RMT"/>
                <form:option label="Вариатор" value="Variator"/>
            </form:select>
            <label></label>
        </div>
        <br>
        <button type="submit">Добавить</button>
        <br>
    </form:form>
    <p class="warnField">* - Поля, обязательные для заполнения</p>
    <form:form action="#" modelAttribute="Message">
        <p class="warnField">${Message}</p>
    </form:form>
</div>

</body>
</html>