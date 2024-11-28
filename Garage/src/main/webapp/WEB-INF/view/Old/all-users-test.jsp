<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Glassmorphism Login Form | CodingNepal</title>
    <style>
        <%@include file='../css/style_test.css' %>
    </style>
</head>
<body>
<div class="wrapperAllUsers">
<h2 align="center">Пользователи</h2>
<br>
<table class="table">
    <thead>
    <tr>
        <th>Имя:</th>
        <th>Телефон:</th>
        <th>Город</th>
        <th>Логин</th>
        <th>Operations</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${allUsers}">
        <c:url var="deleteButton" value="deleteUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <c:url var="registredNewUserButton" value="registred/">
        </c:url>
        <tr>
            <c:url var="info" value="infoUser">
                <c:param name="userId" value="${user.id}"/>
            </c:url>

            <td>
                <div class="table-field">
                <a href=${info}>${user.name} ${user.surname}</a>
                </div>
            </td>
            <td>
                    ${user.phoneNumber}
            </td>
            <td>
                    ${user.city}
            </td>
            <td>
                    ${user.userNameAuth.username}
            </td>

            <td>
                <button type="submit" onclick="window.location.href = '${deleteButton}'">Delete</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
    <button type="submit" onclick="window.location.href= '${registredNewUserButton}'">Add New User</button>
</div>
</body>
</html>