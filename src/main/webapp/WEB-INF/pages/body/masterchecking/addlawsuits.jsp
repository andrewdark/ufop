<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.03.2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="img"></div>


    <div class="post_content_wide">
        <c:if test="${empty checkEvent}"><span class="error"><p>Відсутнє посилання на перевірку</p></span><br />
            <p><span class="error"><form:errors path="check_event_link"/></span><br /><a href="/show_event?id=${event.id}">Повернутись до перевірки</a></p>
        </c:if>
        <c:if test="${checkEvent.check_violation==0}"><p>Порушеннь не виявлено</p>
            <p>В ході даної перевірки порушень не знайдено<br /><a href="/show_event?id=${event.id}">Повернутись до перевірки</a></p>
        </c:if>
        <c:if test="${checkEvent.check_violation==1}">
            <form:form method="post" action="/addsanctionspost">
                <table>
                    <tr>
                        <td></td>
                        <td><form:hidden path="check_event_link"/></td>
                        <td><span class="error"><form:errors path="check_event_link"/></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="">Вкажіть</form:label></td>
                        <td>
                            <form:radiobutton path="filed_on_action" value="0" />Не подано позову до суду
                            <form:radiobutton path="filed_on_action" value="1" />Подано позов до суду
                        </td>
                        <td><span class="error"></span> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="записати"></td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
            <form method="get" action="/show_event/">
                <input type="hidden" name="id" value="${checkEvent.id}" />
                <input type="submit" value="Завершити" />
            </form>
        </c:if>
        <div id="co_message"></div>

        <hr/>
        <table width="100%">
            <caption>Інформація про перевірку</caption>
            <tr>
                <td>ІД</td>
                <td>${checkEvent.id}</td>
            </tr>
            <tr>
                <td>Номер</td>
                <td>${checkEvent.event_number}</td>

            </tr>
            <tr>
                <td>Тип</td>
                <td>
                    <c:if test="${checkEvent.check_type==0}">Планова</c:if>
                    <c:if test="${checkEvent.check_type==1}">Позапланова</c:if>
                </td>
            </tr>
            <tr>
                <td>Тип</td>
                <td>
                    <c:if test="${checkEvent.check_violation==0}">Порушень не знайдено</c:if>
                    <c:if test="${checkEvent.check_violation==1}">Порушення виявлено</c:if>
                </td>
            </tr>
            <tr>
                <td>Опис</td>
                <td>
                    <c:if test="${checkEvent.check_violation==0}">N/A</c:if>
                    <c:if test="${checkEvent.check_violation==1}"> ${checkEvent.event_result}</c:if>
                </td>
            </tr>
        </table>
    </div>
    <div class="clr"></div>
</div>
