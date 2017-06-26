<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <form:form action="${pageContext.servletContext.contextPath}${actionlink}" method="post">
            <form:hidden path="nav"/>
            <form:hidden path="id"/>
            <form:hidden path="organization"/>
            <form:hidden path="creator_link"/>
            <table width="100%">
                <tr>
                    <td><form:label path="last_name">Прізвище:</form:label></td>
                    <td><form:input htmlEscape="true" path="last_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error"><form:errors path="last_name"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="first_name">Ім'я:</form:label></td>
                    <td><form:input htmlEscape="true" path="first_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error"><form:errors path="first_name"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="patronymic_name">По батькові:</form:label></td>
                    <td><form:input htmlEscape="true" path="patronymic_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error"><form:errors path="patronymic_name"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="rntc">ІПН фізичної особи:</form:label></td>
                    <td><form:input htmlEscape="true" path="rntc" class="form-control" maxlength="10"/></td>
                    <td><span class="error"><form:errors path="rntc"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="series_of_passport">Код\серія паспорту:</form:label></td>
                    <td>
                        <form:input htmlEscape="true" path="series_of_passport" class="form-control" size="2"
                                    maxlength="2"/>
                        <form:input htmlEscape="true" path="number_of_passport" class="form-control" size="10"
                                    maxlength="10"/>
                    </td>
                    <td>
                        <span class="error"><form:errors path="series_of_passport"/></span>
                        <span class="error"><form:errors path="number_of_passport"/></span>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="a_stay_address">Адреса реєстрації:</form:label></td>
                    <td><form:input id="a_place_of_reg" htmlEscape="true" path="a_stay_address" class="form-control"
                                    maxlength="24" onclick="javascript:LocPopUpShow()"/></td>
                    <td><span class="error"><form:errors path="a_stay_address"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="n_stay_address">Номер будівлі:</form:label></td>
                    <td><form:input htmlEscape="true" path="n_stay_address" class="form-control" maxlength="6"/></td>
                    <td><span class="error"><form:errors path="n_stay_address"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="n_stay_address">Номер корпусу:</form:label></td>
                    <td><form:input htmlEscape="true" path="b_stay_address" class="form-control" maxlength="5"/></td>
                    <td><span class="error"><form:errors path="n_stay_address"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="n_stay_address">Номер квартири:</form:label></td>
                    <td><form:input htmlEscape="true" path="f_stay_address" class="form-control" maxlength="5"/></td>
                    <td><span class="error"><form:errors path="n_stay_address"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="tel">Телефон:</form:label></td>
                    <td><form:input htmlEscape="true" path="tel" class="form-control"/></td>
                    <td><span class="error"><form:errors path="tel"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="fax">Факс:</form:label></td>
                    <td><form:input htmlEscape="true" path="fax" class="form-control"/></td>
                    <td><span class="error"><form:errors path="fax"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="email">Ел. пошта:</form:label></td>
                    <td><form:input htmlEscape="true" path="email" class="form-control"/></td>
                    <td><span class="error"><form:errors path="email"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="birthday">Дата народження:</form:label></td>
                    <td><form:input path="birthday" id="datepicker"/></td>
                    <td><span class="error"><form:errors path="birthday"/></span></td>
                </tr>

                <tr>
                    <td><form:label path="position">Посада:</form:label></td>
                    <td><form:input htmlEscape="true" path="position" class="form-control"/></td>
                    <td><span class="error"><form:errors path="position"/></span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><form:hidden path="organization"/></td>
                    <td><span class="error"><form:errors path="organization"/></span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="${buttonvalue}"/></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <c:if test="${ufop.ufop_nav==1}">
            <form:form action="/addcontactpost_add_kved" method="post" commandName="command_ufop">
                <form:hidden path="id"/>
                <form:hidden path="ufop_is"/>
                <form:hidden path="ufop_name"/>
                <form:hidden path="ufop_code"/>
                <form:hidden path="ufop_nav"/>
                <form:hidden path="additionalinformation"/>
                <input type="submit" value="перейти до КВЕДів"/>
            </form:form>
        </c:if>
        <div class="d_right">
            <c:if test="${ufop.ufop_nav!=1}">
                <form action="/show_ufop" method="get">
                    <input type="hidden" name="id" value="${ufop.id}"/>
                    <input type="submit" value="Завершити"/>
                </form>
            </c:if>
        </div>
        <div class="info_padding">
            <hr/>
            <h3>Інформація про суб'єкт господарювання</h3><br/>
            <c:if test="${ufop.ufop_is==0}">
                <c:set var="u_name" value="ПІБ фізичної особи"/>
                <c:set var="u_code" value="ІПН фізичної особи"/>
            </c:if>
            <c:if test="${ufop.ufop_is==1}">
                <c:set var="u_name" value="Назва юридичної особи"/>
                <c:set var="u_code" value="ІПН юридичної особи"/>
            </c:if>
            <table>
                <tr>
                    <td>ІД номер</td>
                    <td>${ufop.id}</td>
                </tr>
                <tr>
                    <td>${u_name}</td>
                    <td>${ufop.ufop_name}</td>
                </tr>
                <tr>
                    <td>${u_code}</td>
                    <td>${ufop.ufop_code}</td>
                </tr>
            </table>
        </div>
        <a onclick="look('hide1'); return false;" href="#">Довідка</a>
        <div id="hide1" style="display: none;">
            <li/>
            Заповніть обов'язково поля Прізвище та Ім'я.<br/>
            <li/>
            Серію паспорта заповнюємо латинськими буквами<br/>
            <li/>
            Формат дати: РРРР-ММ-ДД
        </div>
    </div>
    <div class="clr"></div>
</div>
<div class="b-popup" id="popup1">
    ${ex}<br/>
    <div class="b-popup-content" id="setloc_popup">
        <select id="my_selecttop1" name="my_selecttop" onchange="looplocationdown(2)">
            <c:forEach items="${locationTop}" var="locationTop">
                <option value="${locationTop.id}">${locationTop.name}</option>
            </c:forEach>
        </select><br/>
        <div id="LocationType2"></div>
        <div id="LocationType3"></div>
        <div id="LocationType4"></div>
        <div id="LocationType5"></div>
        <a href="javascript:LocPopUpHide()">Додати адресу</a>
    </div>
</div>
