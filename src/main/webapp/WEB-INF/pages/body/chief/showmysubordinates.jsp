<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2017
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>СТРУКТУРА</span></h2>
    <div class="clr"></div>
    <c:forEach var="structure" items="${structure}">
        <a onclick="lookstructure('hide${structure.id}','${structure.treemark}'); return false;" href="#"><b>+</b></a> ${structure.id} - ${structure.name}  <br/>
        <div id="hide${structure.id}" style="display: none;">

        </div>
    </c:forEach>
    <div class="clr"></div>
</div>



