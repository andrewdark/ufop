<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 27.03.2017
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">

        <form:form method="post" action="/addpunishmentarticlespost">
            <form:hidden path="check_event_link"/>
            <table>
                <tr>
                    <td><form:label path="articles_law_link">Виберіть статтю</form:label></td>
                    <td><form:input path="articles_law_link" id="articles"
                                    onclick="javascript:ArticlesPopUpShow();"/></td>
                    <td><span class="error"><form:errors path="articles_law_link" /></span> </td>
                </tr>
                <tr>
                    <td><form:checkbox path="additionalinformation"/>Продовжити</td>
                    <td><input type="submit" value="додати статтю"></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <hr />
        <table width="100%">
            <caption>Інформація про перевірку</caption>
            <tr>
                <td>ІД</td>
                <td>${checkEvent.id}</td>
            </tr>
            <tr>
                <td>Тип</td>
                <td>
                    <c:if test="${checkEvent.check_type==0}">Планова</c:if>
                    <c:if test="${checkEvent.check_type==0}">Позапланова</c:if>
                </td>
            </tr>
            <tr>
                <td>Опис</td>
                <td>${checkEvent.event_result}</td>
            </tr>
        </table>
    </div>
    <div class="clr"></div>
</div>
<div class="b-popup" id="popup5">
    ${ex}<br/>
    <div class="b-popup-content" id="setarticles_popup">
        <select id="my_selecttop1" name="my_selecttop" onchange="looparticlesdown(2)">
            <option value=""></option>
            <c:forEach items="${articlesTop}" var="articlesTop">
                <option value="${articlesTop.treemark}">${articlesTop.caption}</option>
            </c:forEach>
        </select><br/>
        <div id="ArticlesType2"></div>
        <div id="ArticlesType3"></div>
        <div id="ArticlesType4"></div>
        <div id="ArticlesType5"></div>
        <a href="javascript:ArticlesPopUpHide()">Додати закон</a>
    </div>
</div>