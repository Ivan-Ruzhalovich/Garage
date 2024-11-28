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
        <div class="line">
            <div class="buttonBox">
                <button type="button" name="back" onclick="history.back()">Back</button>
            </div>
            <p class="hStyle">Заполните информацию об обслуживании или ремонте</p>
        </div>
        <div class="add-car-input-field">
            <p class="labelSelectField">Вид работы*</p>

                <form:select cssClass="select" path="work.id">
                    <form:options items="${NewTechCar.works}"/>
                </form:select>
                <label></label>
        </div>
        <div class="input-field">
            <input type="date" name="date" required/>
            <label>Дата*</label>
        </div>
        <div class="input-field">
            <input type="text" name="kilometrage" required>
            <label>Пробег*</label>
        </div>
        <div class="input-field">
            <input type="text" name="price" required/>
            <label>Цена*</label>
        </div>
        <button type="submit">Добавить</button>
        <br>
        <br>
        <p class="warnField">* - Поля, обязательные для заполнения</p>
    </form:form>
</div>
</body>
</html>