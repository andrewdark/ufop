<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="labelusername" value="login" />
<c:set var="labelpwd" value="password" />
<h2 class="star"><span>Авторизація</span></h2>
<div class="clr"></div>
<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
<sec:authorize access="isAnonymous()">

    <form class="modal-content animate" name="loginForm" action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <div class="containerLogin">


            <input type="text" name="username" id="login" placeholder="${labelusername}" class="form-control"
                   required="required"/><%--
                                            --%><input type="password" name="password" id="password"
                                                       placeholder="${labelpwd}" required="required"
                                                       class="form-control"/>

            <input name="submit" type="submit" class="btn btn-default" id="submitBtn" value="Увійти"/>
            <div class="reg-restore">
                <span><a href=/registration>Реєстрація</a></span>
                <span><a href="/restore">Відновити пароль</a></span>
            </div>

        </div>
    </form>
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <p style="color: red; ">

            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>

        </p>
    </c:if>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username"/>
    <a id="welcome" href="/my_office"><span>welcome</span> <span class="user_name">${username}</span></a>
    <a href="<c:url value='/logout' />" > Вийти</a>
</sec:authorize>

