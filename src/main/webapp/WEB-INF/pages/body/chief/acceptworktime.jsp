<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2017
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>Робочий час <b>${mySubdivision}</b></span></h2>
    <div class="clr"></div>
    <div class="img">
        <h3>Дата: ${searchdate}</h3>
    </div>
    <div class="post_content">
        <br/>
        <form method="get" action="/acceptworktime">
            <input id="datepicker" type="text" name="date"/> <input type="submit" value="Показати">
        </form>
    </div>


    <table>

        <th>Користувач</th>
        <th>Стан</th>
        <th>Час</th>
        <th>Причина</th>
        <th>Погоджено</th>
        <th>Ким погоджено</th>
        <th>Дата погодження</th>
        <th>Затвердити</th>
        <c:forEach var="worktime" items="${worktime}">
            <tr>
                <td><a href="/acceptwt_detail?id=${worktime.user_link}${dateparam}">${worktime.user_name}</a> </td>
                <td>
                    <c:if test="${worktime.type_of_action==0}">
                        <font color="red" size="4">Покинув р.м.</font>
                    </c:if>
                    <c:if test="${worktime.type_of_action==1}">
                        <font color="green" size="4">Заступив на&nbsp;р.м.</font>
                    </c:if>
                </td>
                <td>${fn:substring (worktime.datereg,0,19)}</td>
                <td>${worktime.s_cause_link}</td>

                <td>
                    <c:if test="${worktime.user_accepted_link==0}">
                        не розглянуто
                        <c:set var="accept" value="true"/>
                        <c:set var="acceptedText" value="погодити"/>
                    </c:if>
                    <c:if test="${worktime.user_accepted_link!=0}">
                        <c:if test="${worktime.accepted}">
                            <div class="stamp_green"><font color="green" size="2"><p>ПОГОДЖЕНО</p></font></div>
                            <c:set var="accept" value="false"/>
                            <c:set var="acceptedText" value="заперечити"/>
                        </c:if>
                        <c:if test="${not worktime.accepted}">
                            <div class="stamp_red"><font color="red" size="2"><p>ВІДХИЛЕНО</p></font></div>
                            <c:set var="accept" value="true"/>
                            <c:set var="acceptedText" value="погодити"/>
                        </c:if>

                    </c:if>
                </td>
                <td><a href="/userinfo?name=${worktime.s_user_accepted_link}">${worktime.s_user_accepted_link}</a></td>
                <td>${fn:substring (worktime.dateaccept,0,19)}</td>
                <td>
                    <form action="/acceptingWorkTimepost" method="post">
                        <input name="id" type="hidden" value="${worktime.id}"/>
                        <input name="accept" type="hidden" value="${accept}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="${acceptedText}"/>
                    </form>

                </td>

            </tr>

        </c:forEach>
    </table>


    <div class="clr"></div>
</div>
