<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 03.03.2017
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 class="star"><span>Меню керівника</span></h2>
<div class="clr"></div>

<ul id="chiefmenu">

    <sec:authorize access="hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')">
        <li><div>Мій підрозділ</div>
            <ul>
                <li><div><a href="/acceptworktime">Перегляд працівників</a></div></li>
                <li><div>План роботи</div></li>
                <li><div>Графік чергувань</div></li>
                <li><div>Графік відпусток</div></li>
            </ul>
        </li>
        <li><div>Кабінет керівника</div></li>
    </sec:authorize>

</ul>
