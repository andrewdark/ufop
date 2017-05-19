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
    <c:if test="${not empty contact.series_of_passport}">
    <tr>
        <td><b>Код\серія паспорту:</b></td>
        <td>${contact.series_of_passport}&nbsp;${contact.number_of_passport}</td>
    </tr>
    </c:if>
    <c:if test="${not empty contact.a_stay_address}">
        <tr>
            <td><b>Адреса реєстрації:</b></td>
            <td>
                <c:forEach items="${fulladdress}" var="fulladdress">
                    ${fulladdress.stype}: ${fulladdress.name}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td><b>Номер будівлі:</b></td>
            <td>${contact.n_stay_address}</td>
        </tr>
        <tr>
            <td><b>Номер корпусу:</b></td>
            <td>${contact.b_stay_address}</td>
        </tr>
        <tr>
            <td><b>Номер квартири:</b></td>
            <td>${contact.f_stay_address}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.tel}">
        <tr>
            <td><b>Телефон:</b></td>
            <td>${contact.tel}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.fax}">
        <tr>
            <td><b>Факс:</b></td>
            <td>${contact.fax}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.email}">
        <tr>
            <td><b>Ел. пошта:</b></td>
            <td>${contact.email}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.birthday}">
        <tr>
            <td><b>Дата народження:</b></td>
            <td>${contact.birthday}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.position}">
        <tr>
            <td><b>Посада:</b></td>
            <td>${contact.position}</td>
        </tr>
    </c:if>
    <c:if test="${not empty contact.description}">
        <tr>
            <td><b>Примітки:</b></td>
            <td>${contact.description}</td>
        </tr>
    </c:if>
</table>
