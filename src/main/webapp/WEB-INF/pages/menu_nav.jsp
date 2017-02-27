<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul>
    <sec:authorize access="isAnonymous()">
        <li class="active"><a href="/#" class="disabled"><span>Мій робочий час</span></a></li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li class="active"><a href="/myworktime"><span>Мій робочий час</span></a></li>
    </sec:authorize>

    <li><a href="/chat"><span>Чат</span></a></li>
    <li><a href="/search"><span>Детальний пошук</span></a></li>
    <li><a href="/my_office"><span>Мій кабінет</span></a></li>
    <li><a href="/catalog"><span>Довідник</span></a></li>
</ul>