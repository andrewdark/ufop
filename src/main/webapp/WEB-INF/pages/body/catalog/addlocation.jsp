<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 16.01.2017
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

add location
<select id="my_select" name="my_select" onchange="looplocation()">
<c:forEach items="${loctype}" var="loctype">
    <option value="${loctype.id}">${loctype.type}</option>
</c:forEach>
</select>

<div id="setlocselection"></div>
${loca}

