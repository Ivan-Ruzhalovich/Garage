<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Glassmorphism Login Form | CodingNepal</title>
    <style>
        <%
        <%@include file='../css/style_test.css'% >
    </style>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}src/main/webapp/WEB-INF/view/css/style.css" type="text/css">--%>
</head>
<body>
<div class="wrapper">
    <form:form action="saveCar" modelAttribute="NewCar">
        <h2>Add Car</h2>
        <form:hidden path="userId"/>
        <div class="select-field">
<%--            <input type="text" required>--%>
            <form:select path="brand" >
                <form:option value="" label="Марка"/>
                <form:options items="${NewCar.carBrands}"/>
            </form:select>

        </div>
        <div class="select-field">
            <form:select path="model">
                <form:option value="" label="Модель"/>
                <form:options items="${NewCar.carModels}"/>
            </form:select>

        </div>
        <div class="input-field">
            <input name="year" type="text" required>
            <label>Год выпуска</label>
        </div>
        <div class="input-field">
            <input name="kilometrage" type="text" required>
            <label>Пробег</label>
        </div>
        <div class="select-field">
            <form:select path="engine.id">
                <form:options items="${NewCar.engines}"/>
            </form:select>
        </div>
        <div class="select-field">
            <form:select path="transmission.id">
                <form:options items="${NewCar.transmitions}"/>
            </form:select>
        </div>

<%--        <div class="forget">--%>
<%--            <label for="remember">--%>
<%--                <input type="checkbox" id="remember">--%>
<%--                <p>Remember me</p>--%>
<%--            </label>--%>
<%--            <a href="#">Forgot password?</a>--%>
<%--        </div>--%>
        <button type="submit">Готово</button>
<%--        <div class="register">--%>
<%--            <p>Don't have an account? <a href="#">Register</a></p>--%>
<%--        </div>--%>
    </form:form>
</div>
</body>
</html>