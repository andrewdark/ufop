<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.05.2017
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;"><h2>${title} ${ufop.ufop_name}</h2></div>
<table width="100%">
    <caption>
        ВІДОМОСТІ ПРО СУБ'ЄКТА ГОСПОДАРЮВАННЯ
    </caption>
    <tr>
        <td>Id в базі</td>
        <td>${ufop.id}</td>
        <td></td>
    </tr>
    <tr>
        <td>ІПН</td>
        <td>${ufop.ufop_code}</td>
        <td></td>
    </tr>
    <tr>
        <td>Тип суб'єкта господарювання</td>
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
    <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
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
<br/>
<table width="100%">
    <caption>ПЕРЕЛІК КОМЕРЦІЙНИХ ОБ'ЄКТІВ</caption>
    <tr>
        <th>НАЗВА</th>
        <th>ТИП</th>
        <th>ГРУПА РИЗИКУ</th>
        <th>АДРЕСА</th>
        <th>ОСНОВНА ГРУПА ТОВАРІВ</th>
        <th></th>

    </tr>
    <c:forEach items="${co_list}" var="co">
        <tr class="border_bottom">
            <td>${co.obj_name}</td>
            <td align="center">${co.s_obj_type}</td>
            <td align="center">${co.s_degree_risk_link}</td>
            <td align="left">

                <c:if test="${not empty co.locationCatalog}">
                    <c:forEach items="${co.locationCatalog}" var="fulladdress">
                        <b>${fulladdress.stype}:</b> ${fulladdress.name}<br/>
                    </c:forEach>
                    <c:if test="${co.n_place_of_reg ne ''}">
                        <b>Дім:</b>&nbsp;${co.n_place_of_reg}<br/>
                    </c:if>
                    <c:if test="${co.b_place_of_reg ne ''}">
                        <b>Корпус:</b>&nbsp;${co.b_place_of_reg}<br/>
                    </c:if>
                    <c:if test="${co.f_place_of_reg ne ''}">
                        <b>Квартира:</b>&nbsp;${co.f_place_of_reg}<br/>
                    </c:if>
                </c:if>
            </td>
            <td width="50px" align="center">
                <c:forEach items="${co.goodsList}" var="goodsList">
                    <span style="font-size: 14px;"> ${goodsList.name}</span><br/>
                </c:forEach>
            </td>
            <td align="center"></td>
        </tr>
    </c:forEach>
</table>
<br/>
<table width="100%">
    <caption>ПЕРЕЛІК ПЛАНОВИХ ТА ПОЗАПЛАНОВИХ ПЕРЕВІРОК</caption>
    <th>НОМЕР АКТУ</th>
    <th>ДАТА</th>
    <th>ТИП</th>
    <th>РЕЗУЛЬТАТИ</th>
    <th>СТАН</th>

    <c:forEach items="${checkEventList}" var="checkEventList">
        <tr>
            <td>${checkEventList.event_number}</td>
            <td align="center">
                    ${checkEventList.event_date_begin}
                <c:if test="${checkEventList.event_date_end ne '0001-01-01'}">
                    &mdash; ${checkEventList.event_date_end}
                </c:if>
            </td>
            <td align="center">
                <c:if test="${checkEventList.check_type == 0}"><span
                        style="color: black; ">Планова</span></c:if>
                <c:if test="${checkEventList.check_type == 1}"><span
                        style="color: black; ">Позапланова</span></c:if>
            </td>
            <td align="center">
                <c:if test="${checkEventList.check_violation == 0}">
                    <span style="color: green; ">Порушень не виявлено</span>
                </c:if>
                <c:if test="${checkEventList.check_violation == 1}">
                    <span style="color: red; ">Порушення виявлені</span>
                </c:if>
            </td>
            <td align="center">
                <c:if test="${checkEventList.check_violation == 0}">
                    <span style="color: black; "> N/A </span>
                </c:if>
                <c:if test="${checkEventList.check_violation == 1}">
                    <span style="color: black; ">${checkEventList.current_state}</span>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
