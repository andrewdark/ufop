<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.05.2017
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <form:form method="post" action="${actionlink}">
            <form:hidden path="id"/>
            <form:hidden path="check_event_link"/>
            <form:hidden path="accepted"/>
            <form:hidden path="creator_name"/>
            <table>
                <tr>
                    <td></td>
                    <td><form:select path="user_link" items="${inspectorsList}" /></td>
                    <td><span class="error"><form:errors path="user_link"/></span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="${buttonvalue}"></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <div class="d_right">
            <form method="get" action="/show_event/">
                <input type="hidden" name="id" value="${checkEvent.id}"/>
                <input type="submit" value="Завершити"/>
            </form>
        </div>
        <div class="info_padding">
            <hr />
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
                    <td>Порушення</td>
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
    </div>
    <div class="clr"></div>
</div>