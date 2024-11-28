<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="UTF-8">
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
        <div class="line">
            <div class="buttonBox">
                <button type="button" name="back" onclick="history.back()">Back</button>
            </div>
            <p class="hStyle">Заполните информацию</p>
        </div>
        <div class="input-field">
            <input type="text" name="type_fuel" required/>
            <label>Тип топлива*</label>
        </div>
        <div class="input-field">
            <input type="text" name="volume" required/>
            <label>Объем*</label>
        </div>
        <div class="input-field">
            <input type="text" name="price" required/>
            <label>Цена за литр*</label>
        </div>
        <div class="input-field">
            <input type="text" name="kilometrage" required>
            <label>Пробег*</label>
        </div>
        <div class="input-field">
            <input type="date" name="date" required/>
            <label>Дата*</label>
        </div>
        <button type="submit">Добавить</button>
        <br>
        <p class="warnField">* - Поля, обязательные для заполнения</p>
    </form:form>
</div>
</body>
</html>