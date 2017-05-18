<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 18.05.2017
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;"><h2>${title}</h2></div>
<table>
    <tr>
        <td><b>Прізвище:</b></td>
        <td>${contact.last_name}</td>
    </tr>
    <tr>
        <td><b>Ім'я:</b></td>
        <td>${contact.first_name}</td>
    </tr>
    <tr>
        <td><b>По батькові:</b></td>
        <td>${contact.patronymic_name}</td>
    </tr>
    <c:if test="${not empty contact.rntc}">
        <tr>
            <td><b>ІПН особи:</b></td>
            <td>${contact.rntc}</td>
        </tr>
    </c:if>
    <tr>
        <td><b>Код\серія паспорту:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Адреса реєстрації:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Номер будівлі:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Номер корпусу:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Номер квартири:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Телефон:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Факс:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Ел. пошта:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Дата народження:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Посада:</b></td>
        <td></td>
    </tr>
    <tr>
        <td><b>Примітки:</b></td>
        <td></td>
    </tr>
</table>
