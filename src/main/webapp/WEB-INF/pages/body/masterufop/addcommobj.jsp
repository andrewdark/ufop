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
            <input id="search_ufopcode_input" type="text" /><br />
            <input id="search_ufopcode_button" type="button" value="search" />
            <input id="create_ufop_button" type="button" value="Create ufop" onclick="javascript:load_ufop()"/>

        </div>
        <div class="post_content">
            <div id="co_message"></div>
            <div id="load_ufop_point"></div>

            <form:form method="post" action="/addcommobjpost">
                <form:hidden path="ufop_link"/>
                <table>
                    <tr>
                        <td>Назва об'єкта</td>
                        <td><form:input path="obj_name"/></td>
                        <td><form:errors path="obj_name"/> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Записати"/></td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
            <hr />
            Інформація про суб'єкт господарювання<br />
            ${ufop.id}<br />
            ${ufop.ufop_name}<br />
            ${ufop.ufop_code}
            <hr/>
            Інформація  про уповноважену особу суб'єкта господарювання <br />
            Test: ${test.id}
        </div>
        <div class="clr"></div>
    </div>
    <div class="d_right_brdr_red"><a href="/"><img src="resources/images/done.jpg"/></a></div>
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