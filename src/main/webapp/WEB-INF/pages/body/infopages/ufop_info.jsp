<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.05.2017
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;"><h2>${title}</h2></div>
<table width="100%">
    <caption>
        ВІДОМОСТІ ПРО СУБ'ЄКТА ГОСПОДАРЮВАННЯ
    </caption>
    <tr>
        <td>Id в базі</td>
        <td>${ufop.id}</td>
        <td>
        </td>
    </tr>
    <tr>
        <td>ІПН</td>
        <td>${ufop.ufop_code}</td>
        <td></td>
    </tr>
    <tr>
        <td>Статус</td>
        <td>
            <c:if test="${ufop.ufop_is == 0}">
                Фізична особа - підприємець
            </c:if>
            <c:if test="${ufop.ufop_is == 1}">
                Юридична особа
            </c:if>
        </td>
        <td></td>
    </tr>
    <tr>
        <td>
            <c:if test="${ufop.ufop_is == 0}">
                ПІБ суб'єкта господарювання
            </c:if>
            <c:if test="${ufop.ufop_is == 1}">
                Назва юридичної особи
            </c:if>
        </td>
        <td>${ufop.ufop_name}</td>
        <td></td>
    </tr>
    <c:if test="${not empty fulladdress}">
        <tr>
            <td>Адреса реєстрації</td>
            <td>
                <c:forEach items="${fulladdress}" var="fulladdress">
                    ${fulladdress.stype}: ${fulladdress.name}<br/>
                </c:forEach>
                <c:if test="${ufop.n_place_of_reg ne ''}">
                    Дім: ${ufop.n_place_of_reg}<br/>
                </c:if>
                <c:if test="${ufop.b_place_of_reg ne ''}">
                    Корпус: ${ufop.b_place_of_reg}<br/>
                </c:if>
                <c:if test="${ufop.f_place_of_reg ne ''}">
                    Квартира: ${ufop.f_place_of_reg}<br/>
                </c:if>
            </td>
            <td></td>
        </tr>
    </c:if>
</table>
<table width="100%">
    <caption>
        ВІДОМОСТІ ПРО КОМЕРЦІЙНІ ОБ'ЄКТИ
    </caption>
    <c:forEach var="co_list" items="${co_list}">

    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td></td>
    </tr>
</table>
