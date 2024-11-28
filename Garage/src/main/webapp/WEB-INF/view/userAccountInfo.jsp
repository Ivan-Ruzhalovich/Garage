<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <style>
        <%@include file='css/index-style.css' %>
    </style>
</head>
<body>
<div class="wrapper">
    <div style="padding-right: 30px" class="line">
        <div class="buttonBox">
            <button type="button" name="back" onclick="history.back()">Back</button>
        </div>
        <p style="padding-top:10px " class="hStyle">${userAccountInfo.surname} ${userAccountInfo.name}</p>
    </div>
<form:form action="infoUser" modelAttribute="userAccountInfo">
    <c:url var="deleteUser" value="deleteUser">
        <c:param name="userId" value="${userAccountInfo.userId}"/>
    </c:url>
    <br>
    <p class="labelSelectField">Имя:</p>
    <div class="input-field">
        <input type="text" value="${userAccountInfo.name}" required>
    </div>
    <p class="labelSelectField">Фамилия:</p>
    <div class="input-field">
        <input type="text" value="${userAccountInfo.surname}" required>
    </div>
    <p class="labelSelectField">Номер телефона:</p>
    <div class="input-field">
        <input type="text" value="${userAccountInfo.phoneNumber}" required>
    </div>
    <p class="labelSelectField">Логин:</p>
    <div class="input-field">
        <input type="text" value="${userAccountInfo.username}" readonly>
    </div>
    <p class="labelSelectField">Пароль:</p>
    <div class="input-field">
        <input type="password" value="${userAccountInfo.password}" readonly>
    </div>
    <div class="refuelBox">
        <div class="buttonBoxWithout">
            <button style="margin-top: 10px;padding: 5px;margin-right: 20px" type="button" onclick="window.location.href='index-enter'">Выйти из аккаунта</button>
            <div class="buttonBox">
                <button style="padding: 5px;margin-top: 10px;margin-left: 15px" type="button" onclick="window.location.href='${deleteUser}'">Удалить аккаунт</button>
            </div>
        </div>
    </div>
</form:form>
</div>
</body>
</html>