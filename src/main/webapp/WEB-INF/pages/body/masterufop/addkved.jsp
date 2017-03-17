<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <div id="kved_message"></div>

        <table>
            <tr>
                <td>ID ФОП:</td>
                <td><input type="text" disabled="disabled" value="${sendIE.id}" id="fopid"/></td>
                <td></td>
            </tr>
            <tr>
                <td>КВЕД:</td>
                <td><input type="text" id="kvedname" onclick="javascript:KvedPopUpShow()"/></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="button" onclick="inject_kved()"><img src="resources/images/add.jpg"/></button>
                </td>
                <td></td>
            </tr>
        </table>

    </div>

    <div class="clr"></div>
</div>

<form method="post" action="/kvedpost">
<input type="hidden" id="ufop_id" name="ufop_id" value="${sendIE.id}" />
    <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}"/>
    <div class="d_right"><button type="submit"><img src="/resources/images/next_step.jpg"/></button></div>
</form>


<div class="b-popup" id="popup3">
    ${ex}<br/>


    <div class="b-popup-content" id="setkved_popup">

        <select id="my_selecttop1" name="my_selecttop" onchange="loopkveddown(2)" onmousedown="$(':first-child', this).remove(); this.onmousedown = null;">
            <option value=""></option>
            <c:forEach items="${kvedTop1}" var="kvedTop1">
                <option value="${kvedTop1.treemark}">${kvedTop1.label} - ${kvedTop1.name}</option>
            </c:forEach>
        </select><br />
        <div id="KvedType2"></div>
        <div id="KvedType3"></div>
        <div id="KvedType4"></div>
        <div id="KvedType5"></div>
        <div id="KvedType6"></div>
        <a href="javascript:KvedPopUpHide()">Додати КВЕД</a>
    </div>
</div>