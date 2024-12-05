<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            <p class="labelSelectField">Имя*</p>
            <form:input path="name"/>
            <fmt:requestEncoding value="UTF-8"/>
            <p><form:errors path="name"/></p>
                <%--            <label style="color: red">${Message}</label>--%>
        </div>

        <div class="input-field">
            <p class="labelSelectField">Фамилия*</p>
            <a href=""></a>
            <fmt:requestEncoding value="UTF-8"/>
            <form:input path="surname" />
            <form:errors path="surname"/>
                <%--            <label style="color: red">${Message}</label>--%>
        </div>
        <%--        <div class="input-field">--%>
        <%--            <form:input path="phoneNumber"/>--%>
        <%--            <label>Номер телефона*</label>--%>
        <%--        </div>--%>
<%--        <div class="input-field">--%>
<%--            <p class="labelSelectField">Дата рождения*</p>--%>
<%--            <input type="date" name="dateOfBirth" required/>--%>
<%--            <form:errors path="dateOfBirth"/>--%>
<%--            <label></label>--%>
<%--        </div>--%>
<%--        <div class="input-field">--%>
<%--            <p class="labelSelectField">Номер телефона*</p>--%>
<%--            <input type="tel" name="phoneNumber" required placeholder="+7-999-999-99-99" pattern="[+][1-9]{11}"/>--%>
<%--            <label></label>--%>
<%--        </div>--%>
        <div class="input-field">
            <p class="labelSelectField">E-mail*</p>
            <input type="email" name="email" required placeholder="some@some.abc"/>
            <label></label>
        </div>
        <button type="submit">Добавить</button>
        <br>
    </form:form>
    <p class="warnField">* - Поля, обязательные для заполнения</p>
    <form:form action="saveUser" modelAttribute="Message">
        <p class="warnField">${Message}</p>
    </form:form>
</div>
</body>
</html>