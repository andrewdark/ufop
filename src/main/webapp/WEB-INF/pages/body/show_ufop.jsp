<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="frt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="navigation" class="navblock">
    <sec:authorize access="isAuthenticated()">
        <table width="100%">
            <tr>
                <form:form id="form1" action="/show_ufop_create_new" method="post" commandName="command_ufop">
                    <td>

                        <form:hidden path="id"/>
                        <form:hidden path="ufop_is"/>
                        <form:hidden path="ufop_name"/>
                        <form:hidden path="ufop_code"/>
                        <form:radiobutton path="nav" value="1"/> комерційний об'єкт
                        <form:radiobutton path="nav" value="2"/> перевірку
                        <form:radiobutton path="nav" value="3"/> КВЕДи
                        <form:radiobutton path="nav" value="4"/> контакти
                    </td>

                    <td><input type="submit" value="Створити"/></td>
                </form:form>
            </tr>
        </table>

    </sec:authorize>
</div>
<div id="ufoptabs">
    <ul>
        <li><a href="#tabs-1">Суб'єкт господарювання</a></li>
        <li><a href="#tabs-2">Комерційні об'єкти</a></li>
        <li><a href="#tabs-3">Перевірки</a></li>
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
                <th>Основна група товарів</th>
                <th></th>

            </tr>
            <c:forEach items="${co_list}" var="co">
                <tr>
                    <td>${co.obj_name}</td>
                    <td>${co.obj_type}</td>
                    <td>
                        <c:forEach items="${co.locationCatalog}" var="locationCatalog">
                            ${locationCatalog.name}
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${co.goodsList}" var="goodsList">
                            ${goodsList.name}
                        </c:forEach>
                    </td>

                    <td><sec:authorize access="isAuthenticated()">
                        <a href="/">редагувати</a>
                    </sec:authorize>
                    </td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <br/><br/>

                </td>
            </tr>
        </table>
    </div>

    <div id="tabs-3">
        <p>Перелік планових та позапланових перевірок</p>
        <table width="100%">
            <caption></caption>

            <th>Дата</th>
            <th>Тип</th>
            <th>Результати</th>
            <th>Стан</th>
            <th></th>
            <c:forEach items="${checkEventList}" var="checkEventList">
                <tr>
                    <td>${checkEventList.event_date_begin}</td>
                    <td>
                        <c:if test="${checkEventList.check_type == 0}"><span
                                style="color: black; ">Планова</span></c:if>
                        <c:if test="${checkEventList.check_type == 1}"><span
                                style="color: black; ">Позапланова</span></c:if>
                    </td>
                    <td>
                        <c:if test="${checkEventList.check_violation == 0}">
                            <span style="color: green; ">Порушень не виявлено</span>
                        </c:if>
                        <c:if test="${checkEventList.check_violation == 1}">
                            <span style="color: red; ">Порушення виявлені</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${checkEventList.check_violation == 0}">
                            <span style="color: black; "> N/A </span>
                        </c:if>
                        <c:if test="${checkEventList.check_violation == 1}">
                            <span style="color: black; ">${checkEventList.current_state}</span>
                        </c:if>

                    </td>
                    <td>
                        <a href="/show_event?id=${checkEventList.id}">деталі</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div id="tabs-4">
        <p>Перелік КВЕД</p>
        <c:forEach items="${kveds}" var="kveds">
            ${kveds.kved_catalog_label} -  ${kveds.kved_catalog_name}<br/>
        </c:forEach>
    </div>

    <div id="tabs-5">
        <p>Перелік контактних осіб</p>
        <c:forEach items="${ci_list}" var="ci">
            ${ci.last_name} ${ci.first_name} ${ci.patronymic_name} <br/>
        </c:forEach>
    </div>
</div>