<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <table width="100%">
            <caption>
                <c:if test="${p_or_e}">
                    <h3>Користувач ${command.user_name} знаходиться на робочому місці</h3>
                </c:if>

                <c:if test="${not p_or_e}">
                    <h3> Користувач ${command.user_name} відсутній на робочому місці</h3>
                </c:if>
            </caption>
            <tr>
                <td>
                    <h3> ${wt_user} </h3>
                </td>
                <td align="center" valign="top">
                    <c:if test="${p_or_e}">
                        <form:form action="/acceptwt_detailpost" method="post">
                            <form:hidden path="user_link"/>
                            <form:hidden path="user_name"/>
                            <form:hidden path="s_user_accepted_link"/>
                            <form:select path="cause_link" items="${causes}"/>&nbsp;
                            <input type="submit" value="Завершити роботу"/>
                        </form:form>
                    </c:if>
                </td>
            </tr>
        </table>

        <br/><br/>
        <c:if test="${not empty worktime}">
            <table width="100%">
                <caption>
                    ДЕТАЛЬНИЙ РОЗКЛАД РОБОЧОГО ЧАСУ
                </caption>
                <tr>
                    <th></th>
                    <th>Час</th>
                    <th>Причина</th>
                </tr>
                <c:forEach var="worktime" items="${worktime}">
                    <tr>
                        <td>${worktime.user_name} </td>
                        <td>${fn:substring (worktime.datereg,0,19)} </td>
                        <td>${worktime.s_cause_link} </td>

                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </div>
    <div class="clr"></div>
</div>
