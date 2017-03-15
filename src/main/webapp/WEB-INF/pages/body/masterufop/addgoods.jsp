<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15.03.2017
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        ${co.ufop_link}
        ${co.obj_name}
        <form:form action="#" method="post">

        </form:form>
    </div>
    <div class="clr"></div>
</div>