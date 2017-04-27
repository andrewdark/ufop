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
        <div id="kveds_list">
            <c:if test="${not empty kveds_list}">
                <c:forEach items="${kveds_list}" var="kveds_list">
                    <li/>
                    ${kveds_list.kved_catalog_label} - ${kveds_list.kved_catalog_name}  <a
                        href="/deletekveds?id=${kveds_list.id}&ufop_id=${ufop.id}&ufop_nav=${ufop.ufop_nav}">Удалить</a>
                    <br/>
                </c:forEach>
            </c:if>
        </div>

        <form:form action="/addkvedpost" method="post">
            <form:hidden path="ufop_link"/>
            <form:hidden path="nav"/>
            <table width="100%">
                <tr>
                    <td><form:label path="kved_catalog_link">Вкажіть КВЕД</form:label></td>
                    <td><form:input id="kvedname" path="kved_catalog_link" onclick="javascript:KvedPopUpShow()"/></td>
                    <td><span class="error"><form:errors path="kved_catalog_link"/> </span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Додати КВЕД"/></td>
                    <td></td>
                </tr>
            </table>
        </form:form>

        <c:if test="${ufop.ufop_nav==1}">
            <form:form action="/addkved_add_commobj" method="post" commandName="command_ufop">
                <form:hidden path="id"/>
                <form:hidden path="ufop_is"/>
                <form:hidden path="ufop_name"/>
                <form:hidden path="ufop_code"/>
                <form:hidden path="ufop_nav"/>
                <input type="submit" value="перейти до ком.об'єктів"/>
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
            </c:if>
            <c:if test="${ufop.ufop_is==1}">
                <c:set var="u_name" value="Найменування юридичної особи"/>
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
                    <td>${u_name}</td>
                    <td>${ufop.ufop_code}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="clr"></div>
</div>


<div class="b-popup" id="popup3">
    ${ex}<br/>


    <div class="b-popup-content" id="setkved_popup">

        <select id="my_selecttop1" name="my_selecttop" onchange="loopkveddown(2)">
            <option value=""></option>
            <c:forEach items="${kvedTop}" var="kvedTop">
                <option value="${kvedTop.treemark}">${kvedTop.label} - ${kvedTop.name}</option>
            </c:forEach>
        </select><br/>
        <div id="KvedType2"></div>
        <div id="KvedType3"></div>
        <div id="KvedType4"></div>
        <div id="KvedType5"></div>
        <div id="KvedType6"></div>
        <a href="javascript:KvedPopUpHide()">Додати КВЕД</a>
    </div>
</div>