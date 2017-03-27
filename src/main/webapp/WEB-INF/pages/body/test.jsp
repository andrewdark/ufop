<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.02.2017
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
NOT SUPPORT YET
<hr />
<table>
    <caption>Інформація про перевірку</caption>
    <tr>
        <td>ІД</td>
        <td>${event.id}</td>
    </tr>
    <tr>
        <td>Тип</td>
        <td>
            <c:if test="${event.check_type==0}">Планова</c:if>
            <c:if test="${event.check_type==0}">Позапланова</c:if>
        </td>
    </tr>
    <tr>
        <td>Опис</td>
        <td>${event.event_result}</td>
    </tr>
</table>