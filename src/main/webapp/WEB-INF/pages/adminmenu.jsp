<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.01.2017
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 class="star"><span>Меню адміна</span></h2>
<div class="clr"></div>

<ul id="adminmenu">

    <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <li><div>Користувачі</div>
            <ul>
                <li><div>Перегляд користувачів</div></li>
                <li><div>Видалити користувача</div></li>
                <li><div><a href="/adduser_to_strorgtlb">Редагування користувача</a></div></li>
            </ul>
        </li>
        <li><div>Довідники</div>
            <ul>
                <li><div>location_type</div></li>
                <li><div><a href="/addlocation">location</a></div></li>
                <li><div>kved_catalog</div></li>
            </ul>
        </li>
    </sec:authorize>

</ul>