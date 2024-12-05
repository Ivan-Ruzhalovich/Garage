<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>123</title>
</head>
<body>
<h2>User Security</h2>
<form:form action="saveUser" modelAttribute="NewUser" >
    <table>
        <tr>
            <td>Username:</td><td><input name="username" type="text" /></td>
        </tr>
        <tr>
            <td>Password:</td><td><input name="password" type="text" id="password"/></td>
        </tr>
        <tr>
            <td>Enabled:</td>
            <td>
                <label for="enabled">
                    Enabled or Disabled
                </label>
                <select name="enabled" id="enabled">
                    <option value="1">enabled</option>
                    <option value="2"> disable</option>
                </select>
            </td>
        </tr>
    </table>
    <br><br>
    <table>
        <tr>
            <td>Authority:</td>
            <td>
                <label for="authority">
                    Please choose role
                </label>
                <select name="authority" id="authority">
                    <option value="ROLE_USER"> User</option>
                    <option value="ROLE_ADMINISTRATOR"> Administrator</option>
                </select>
            </td>
        </tr>

    </table>
    <br><br>
    <table>
        <tr>
            <td>Name:</td><td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Surname:</td><td><form:input path="surname"/></td>
        </tr>
        <tr>
            <td>Birthday:</td> <td><form:input path="dateOfBirth"/></td>

        </tr>
        <tr>
            <td>City:</td><td><form:input path="city"/></td>
        </tr>
        <tr>
            <td>Phone Number:</td> <td><form:input path="phoneNumber"/></td>
        </tr>
    </table>
    <br><br>
    <input type="submit" value="Ok" >
</form:form>
</body>
</html>