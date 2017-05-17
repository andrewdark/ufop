<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.01.2017
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UFOP</title>
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/resources/images/od_obl.png"/>
    <link rel="icon" type="image/x-icon" href="${pageContext.servletContext.contextPath}/resources/images/od_obl.png"/>
    <link rel="logo" href="${pageContext.servletContext.contextPath}/resources/images/od_obl.png"/>
    <tiles:insertAttribute name="head_include" ignore="true"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui-i18n-uk.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/confirm_delete.js"></script>
</head>
<body>
<div class="main">
    <div class="header">
        <tiles:insertAttribute name="header" ignore="true"/>
    </div>
    <div class="content">
        <div class="content_resize">
            <div class="mainbar">
                <tiles:insertAttribute name="body" ignore="true"/>
            </div>
            <div class="sidebar">
                <tiles:insertAttribute name="sidebar" ignore="true"/>
            </div>
            <div class="clr"></div>
        </div>
    </div>

    <div class="footer">
        <tiles:insertAttribute name="footer" ignore="true"/>
    </div>
</div>
</body>

</html>
