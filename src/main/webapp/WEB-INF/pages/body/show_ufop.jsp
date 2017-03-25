<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Суб'єкт господарювання</a></li>
        <li><a href="#tabs-2">Комерційні об'єкти</a></li>
        <li><a href="#tabs-4">Перевірки</a></li>
        <li><a href="#tabs-4">КВЕД</a></li>
        <li><a href="#tabs-5">Контактна інформація</a></li>
    </ul>

    <div id="tabs-1">
        ${ufop.id}
        ${ufop.ufop_code}
        ${ufop.ufop_is}
        ${ufop.ufop_name}
    </div>
    <div id="tabs-2">

        <table>
            <caption>Перелік комерційних об'єктів</caption>
            <tr>
                <th>НАЗВА</th>
                <th>ТИП</th>
                <th>АДРЕСА</th>


            </tr>
            <c:forEach items="${co}" var="co">
                <tr>
                    <td>${co.obj_name}</td>
                    <td>${co.obj_type}</td>
                    <td>
                        <c:forEach items="${co.locationCatalog}" var="locationCatalog">
                            ${locationCatalog.name}
                        </c:forEach>
                    </td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>

                <td>
                    <sec:authorize access="isAuthenticated()">
                        <form:form action="/show_ufop_create_commobj" method="post" commandName="command_ufop">
                            <form:hidden path="id"/>
                            <form:hidden path="ufop_is"/>
                            <form:hidden path="ufop_name"/>
                            <form:hidden path="ufop_code"/>
                            <input type="submit" value="Додати комерційний об'єкт"/>
                        </form:form>
                    </sec:authorize>
                </td>
            </tr>
        </table>
    </div>
    <div id="tabs-3">
        <p>Перелік планових та позапланових перевірок</p>

    </div>
    <div id="tabs-4">
        <p>Перелік КВЕД</p>

    </div>
    <div id="tabs-5">
        <p>Перелік контактних осіб</p>

    </div>
</div>