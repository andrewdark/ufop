<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2017
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<sec:authorize access="isAuthenticated()">

    <sec:authentication property="principal.username" var="username"/>
    <div id="tabs">
        <ul>
            <li><a href="#tabsmwt-1">Керування робочим часом</a></li>
            <li><a href="#tabsmwt-2">Перегляд робочого часу</a></li>
        </ul>
        <div id="tabsmwt-1">
            <p>Керування робочим часом</p>
            <c:if test="${p_or_e}">Користувач ${username} знаходиться на робочому місці<br />
                <c:set var="buttonText" value="Відпочивати"/>
                <c:set var="labelText" value="Вкажіть причину закінчення роботи"/>
            </c:if>
            <c:if test="${not p_or_e}">Користувач ${username} відсутній на робочому місці<br />
                <c:set var="buttonText" value="Працювати"/>
                <c:set var="labelText" value="Вкажіть причину початку роботи"/>
            </c:if>


            <form:form action="/myworktimepost" method="post">
                <table>
                    <tr>
                        <td><form:label path="cause_link">${labelText}</form:label></td>
                        <td><form:select path="cause_link" items="${causes}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="${buttonText}"/></td>
                    </tr>
                </table>
            </form:form>
        </div>

        <div id="tabsmwt-2">
            <table>
                <caption>Перегляд робочого часу</caption>
                <th>Вид</th>
                <th>Час</th>
                <th>Причина</th>
                <th>Погоджено</th>
                <th>Ким погоджено</th>
                    <%--<th>Дата погодження</th>--%>
                <c:forEach var="myWorkTime" items="${myWorkTime}">
                    <tr>
                        <td>
                            <c:if test="${myWorkTime.type_of_action==0}">
                                <font color="red" size="4">Покинув р.м.</font>
                            </c:if>
                            <c:if test="${myWorkTime.type_of_action==1}">
                                <font color="green" size="4">Заступив на&nbsp;р.м.</font>
                            </c:if>
                        </td>
                        <td>${myWorkTime.datereg}</td>
                        <td>${myWorkTime.s_cause_link}</td>

                        <td>
                            <c:if test="${myWorkTime.user_accepted_link==0}">
                                не розглянуто
                            </c:if>
                            <c:if test="${myWorkTime.user_accepted_link!=0}">
                                <c:if test="${myWorkTime.accepted}">
                                    <div class="stamp_green"><font color="green" size="2"><p>ПОГОДЖЕНО</p></font></div>
                                </c:if>
                                <c:if test="${not myWorkTime.accepted}">
                                    <div class="stamp_red"><font color="red" size="2"><p>ВІДХИЛЕНО</p></font></div>
                                </c:if>

                            </c:if>
                        </td>
                        <td> <a href="/userinfo?name=${myWorkTime.s_user_accepted_link}">${myWorkTime.s_user_accepted_link}</a></td>
                            <%-- <td>${myWorkTime.dateaccept}</td> --%>

                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</sec:authorize>

