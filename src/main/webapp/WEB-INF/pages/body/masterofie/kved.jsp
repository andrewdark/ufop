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
    <div class="post_content">
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
<div class="d_right"><a href="/commercialobject"><img src="resources/images/next_step.jpg"/></a></div>
<div class="b-popup" id="popup3">
    ${ex}<br/>


    <div class="b-popup-content" id="setkved_popup">

        <select id="my_selecttop1" name="my_selecttop" onchange="loopkveddown(2)">
            <c:forEach items="${kvedTop1}" var="kvedTop1">
                <option value="${kvedTop1.treemark}">${kvedTop1.label} - ${kvedTop1.name}</option>
            </c:forEach>
        </select><br />
        <div id="KvedType2"></div>
        <div id="KvedType3"></div>
        <div id="KvedType4"></div>
        <div id="KvedType5"></div>
        <a href="javascript:KvedPopUpHide()">Додати КВЕД</a>
    </div>
</div>