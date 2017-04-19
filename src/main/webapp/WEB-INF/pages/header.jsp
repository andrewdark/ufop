<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.01.2017
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header_resize">
    <div class="slider">
        <div id="coin-slider"><a href="/"><img src="/resources/images/40fe6b04a2ea.png" width="360" height="240"
                                               alt=""/> </a></div>
    </div>
    <div class="menu_nav">
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
    </div>
    <div class="logo">
        <h1><a href="/"><span>UFOP</span>
            <small>business activity monitoring</small>
        </a></h1>
    </div>
    <div class="clr"></div>
</div>