<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Регистрация нового пользователя
<sec:authorize var="loggedIn" access="isAuthenticated()" />
<br />
<c:choose>
    <c:when test="${loggedIn}">
        You are loged in
    </c:when>
    <c:otherwise>
        You are logged out
        ${loggedIn}
    </c:otherwise>
</c:choose>

<sec:authorize access="isAnonymous()">
    SEC anonimous
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    ${pageContext.request.userPrincipal.name}
    SEC authenticated
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <br />SEC NO authenticated
</sec:authorize>