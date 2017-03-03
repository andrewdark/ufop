<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2017
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Size: ${size}
<table>
    <caption>tablo</caption>
    <th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th><th>7</th><th>8</th>
    <c:forEach var="worktime" items="${worktime}">

        <tr>
            <td>
                <c:if test="${worktime.type_of_action==0}">
                    <font color="red" size="4">Покинув р.м.</font>
                </c:if>
                <c:if test="${worktime.type_of_action==1}">
                    <font color="green" size="4">Заступив на&nbsp;р.м.</font>
                </c:if>
            </td>
            <td>${worktime.datereg}</td>
            <td>${worktime.s_cause_link}</td>

            <td>
                <c:if test="${worktime.user_accepted_link==0}">
                    не розглянуто
                </c:if>
                <c:if test="${worktime.user_accepted_link!=0}">
                    <c:if test="${worktime.accepted}">
                        <div class="stamp_green"><font color="green" size="2"><p>ПОГОДЖЕНО</p></font></div>
                    </c:if>
                    <c:if test="${not worktime.accepted}">
                        <div class="stamp_red"><font color="red" size="2"><p>ВІДХИЛЕНО</p></font></div>
                    </c:if>

                </c:if>
            </td>
            <td> <a href="/userinfo?name=${worktime.s_user_accepted_link}">${worktime.s_user_accepted_link}</a></td>
                <td>${worktime.dateaccept}</td>
            <td></td>
            <td></td>
        </tr>

    </c:forEach>
</table>