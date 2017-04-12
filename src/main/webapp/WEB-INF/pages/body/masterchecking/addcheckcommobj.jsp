<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 12.04.2017
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        NOT SUPPORT YET
        <form method="get" action="/show_event/">
            <input type="hidden" name="id" value="${checkEvent.id}" />
            <input type="submit" value="Завершити" />
        </form>
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
                    <c:if test="${checkEvent.check_type==1}">Позапланова</c:if>
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