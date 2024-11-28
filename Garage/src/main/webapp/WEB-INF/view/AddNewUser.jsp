<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        Регистрация
    </title>
    <style>
        <%@include file='css/index-style.css'%>
    </style>
</head>
<body>
<div class="wrapper">
    <div class="line">
        <div class="buttonBox">
            <button type="button" name="back" onclick="history.back()">Back</button>
        </div>
        <p class="hStyle">Заполните ваши данные </p>
    </div>
    <form:form action="saveUser" modelAttribute="NewUser">
        <c:url var="saveNewUser" value="saveUser">
        <form:hidden path="id"/>
        </c:url>
        <div class="input-field">
            <input type="text" name="login" required/>
            <label>Логин*</label>
        </div>
        <div class="input-field">
            <input type="password" name="password" required/>
            <label>Пароль*</label>
        </div>
        <div class="input-field">
            <form:input path="name"/>
            <label>Имя*</label>
        </div>
        <div class="input-field">
            <form:input  path="surname"/>
            <label>Фамилия*</label>
        </div>
        <div class="input-field">
            <form:input path="phoneNumber"/>
            <label>Номер телефона*</label>
        </div>
<%--        <div class="input-field">--%>
<%--            <form:input path="dateOfBirth"/>--%>
<%--            <label>Дата рождения в формате YYYY-MM-DD*</label>--%>
<%--        </div>--%>
        <div class="input-field">
            <input type="date" name="dateOfBirth" required/>
            <label>Дата рождения*</label>
        </div>
        <div class="input-field">
            <form:input path="city"/>
            <label>Город*</label>
        </div>
        <button type="submit">Добавить</button>
        <br>
    </form:form>
    <p class="warnField">* - Поля, обязательные для заполнения</p>
</div>

</body>
</html>