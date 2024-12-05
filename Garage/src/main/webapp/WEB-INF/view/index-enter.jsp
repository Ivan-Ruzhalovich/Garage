<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        <%@include file='css/index-style.css' %>
    </style>
</head>
<body>
<form:form action="auth" modelAttribute="Message">
    <div class="wrapper">
        <h2>Login</h2>
        <div class="input-field">
            <input type="text" name="login"  autocomplete="off" required>
            <label>Логин</label>
        </div>
        <div class="input-field">
            <input type="password" name="password" autocomplete="off" required>
            <label>Пароль</label>
        </div>
        <div class="forget">
            <label for="remember">
                <input type="checkbox" id="remember">
                <p>Запомнить меня</p>
            </label>
            <a href="#">Забыли пароль?</a>
        </div>
        <button type="submit">Войти</button>
        <div class="register">
            <p>Еще нет аккаунта? <a href="addNewUser">Регистрация</a></p>
            <br>
            <p style="color: red">${Message}</p>
        </div>
    </div>
</form:form>
</body>
</html>