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
    <div class="post_content_wide">
        <c:set var="bool" value="false"/>
        <c:if test="${empty sendContact.rntc}">
            <c:set var="bool" value="false"/>
        </c:if>


        <div id="this_ufop_from_ajax">
            <form:form action="/addufoppost" method="post">
                <%-- <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}"/> --%>
                <table>
                    <tr>
                        <td>ТИП</td>
                        <td>
                            <form:radiobutton path="ufop_is" value="0" onchange="javascript:pPass();"/>Фіз. особа
                            <form:radiobutton path="ufop_is" value="1" onchange="javascript:uPass();"/>Юр. особа
                        </td>
                        <td><span class="error"><form:errors path="ufop_is"/> </span></td>
                    </tr>
                    <tr>
                        <td>

                            <span id="ufop_name_1">ПІБ Фіз.Особи</span>
                            <span id="ufop_name_2" style="display: none;">Найменування Юр. особи</span>
                        </td>
                        <td><form:input path="ufop_name"/></td>
                        <td><span class="error"><form:errors path="ufop_name"/></span></td>
                    </tr>
                    <tr>
                        <td>Ідентифікаційний номер</td>
                        <td><form:input path="ufop_code" maxlength="10"/></td>
                        <td><span class="error"><form:errors path="ufop_code"/></span></td>
                    </tr>

                    <tr>

                        <td><span id="ufop_pass_1">Серія та № паспорта</span></td>
                        <td>
                            <div id="passinfo">
                                <form:input path="series_of_passport" maxlength="2" size="2"/>
                                <form:input path="number_of_passport" maxlength="10" size="6"/>
                            </div>

                        </td>
                        <td>
                                <span class="error">
                                    <form:errors path="series_of_passport"/>
                                    <form:errors path="number_of_passport"/>
                                </span>
                        </td>

                    </tr>

                    <tr>
                        <td>Місце реєстрації</td>
                        <td><form:input path="a_place_of_reg" id="a_place_of_reg"
                                        onclick="javascript:LocPopUpShow();"/></td>
                        <td><span class="error"><form:errors path="a_place_of_reg"/></span></td>
                    </tr>
                    <tr>
                        <td>Буд./Кв./Корпус</td>
                        <td><form:input path="n_place_of_reg" size="2"/>
                            <form:input path="f_place_of_reg" size="2"/>
                            <form:input path="b_place_of_reg" size="2"/>
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
                        <td>Додати додаткові данні <form:checkbox path="additionalinformation"/></td>
                        <td><input type="submit" value="Записати"/></td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
        </div>
        <a onclick="look('hide1'); return false;" href="#">Довідка</a>
        <div id="hide1" style="display: none;">
            <li/>
            Заповніть обов'язково поля ID та код ІПН.<br/>
            <li/>
            Серію паспорта заповнюємо латинськими буквами
        </div>
    </div>
    <div class="clr"></div>
</div>
<div class="b-popup" id="setContact_link_popup">

    <div class="b-popup-content">
        Not support yet<br/>
        <a href="javascript:SetContact_link_Hide()">Змінити Contact_Link</a>
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
        <div id="LocationType6"></div>
        <a href="javascript:LocPopUpHide()">Додати адресу</a>
    </div>
</div>
<c:if test="${ufop.ufop_is == 0}">
    <script type="text/javascript">
        pPass();
    </script>
</c:if>
<c:if test="${ufop.ufop_is == 1}">
    <script type="text/javascript">
        uPass();
    </script>
</c:if>