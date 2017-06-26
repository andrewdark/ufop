<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="area1">
    <div class="article">
        <h2><span>${title}</span></h2>
        <div class="clr"></div>
        <div class="img">


        </div>
        <div class="post_content_wide">
            <div id="co_message"></div>
            <div id="load_ufop_point"></div>
            <span class="error"><form:errors path="ufop_link"/> </span>
            <form:form method="post" action="${actionlink}">
                <form:hidden path="id"/>
                <form:hidden path="ufop_link"/>
                <form:hidden path="creator_link"/>
                <table>
                    <tr>
                        <td><form:label path="obj_name">Назва об'єкта</form:label></td>
                        <td><form:input path="obj_name"/></td>
                        <td><span class="error"><form:errors path="obj_name"/></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="obj_type">Тип об'єкта</form:label></td>
                        <td><form:select path="obj_type" items="${it}"/></td>
                        <td><span class="error"></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="degree_risk_link">Група ризику</form:label></td>
                        <td><form:select path="degree_risk_link" items="${degree_risk_list}"/></td>
                        <td><span class="error"></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="a_place_of_reg">Адреса</form:label></td>
                        <td><form:input path="a_place_of_reg" id="a_place_of_reg"
                                        onclick="javascript:LocPopUpShow();"/></td>
                        <td><span class="error"><form:errors path="a_place_of_reg"/></span></td>
                    </tr>
                    <tr>
                        <td>Буд./Корпус/Кв.</td>
                        <td><form:input path="n_place_of_reg" size="2" maxlength="6"/>
                            <form:input path="b_place_of_reg" size="2" maxlength="5"/>
                            <form:input path="f_place_of_reg" size="2" maxlength="5"/>
                        </td>
                        <td>
                            <span class="error"><form:errors path="n_place_of_reg"/></span>
                        </td>
                    </tr>
                    <tr>
                        <td>Примітки</td>
                        <td><form:textarea path="description"/></td>
                        <td><span class="error"><form:errors path="description"/></span></td>
                    </tr>

                    <tr>
                        <td>
                            Додати основні групи товарів <form:checkbox path="additionalinformation"/>
                        </td>
                        <td><input type="submit" value="${buttonvalue}"/></td>
                        <td></td>
                    </tr>

                </table>
            </form:form>
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
                    <c:set var="u_ipn" value="ІПН фізичної особи"/>
                </c:if>
                <c:if test="${ufop.ufop_is==1}">
                    <c:set var="u_name" value="Назва юридичної особи"/>
                    <c:set var="u_ipn" value="ІПН юридичної особи"/>
                </c:if>
                <table>
                    <th></th>
                    <tr>
                        <td>ІД номер</td>
                        <td>${ufop.id}</td>
                    </tr>
                    <tr>
                        <td>${u_name}</td>
                        <td>${ufop.ufop_name}</td>
                    </tr>
                    <tr>
                        <td>${u_ipn}</td>
                        <td>${ufop.ufop_code}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="clr"></div>
    </div>

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