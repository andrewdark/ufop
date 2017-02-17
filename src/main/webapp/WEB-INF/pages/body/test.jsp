<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.02.2017
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
size: ${test1}
<c:forEach var="test" items="${test}">
    id:${test.id} -- -- Name:${test.name}<br />
</c:forEach>