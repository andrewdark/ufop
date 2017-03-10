<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 09.03.2017
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="add_comm_obj"></div>
<div id="add_ufop"></div>
<div id="add_contact"></div>

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
