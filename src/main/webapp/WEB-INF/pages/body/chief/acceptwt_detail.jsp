<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.05.2017
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        ${wt_user}
        <c:if test="${p_or_e}">
            Користувач ${username} знаходиться на робочому місці<br />
        </c:if>
        <c:if test="${not p_or_e}">
            Користувач ${username} відсутній на робочому місці<br />
        </c:if>
        <c:if test="${not empty worktime}">
            <table>
                <c:forEach var="worktime" items="${worktime}">
                    <tr>
                        <td>${worktime.user_name} </td>
                        <td>${worktime.datereg} </td>
                        <td>${worktime.s_cause_link} </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
