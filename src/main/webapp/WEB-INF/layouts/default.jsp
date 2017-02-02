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
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/main.js"></script>
</head>
<body>
<div class="main">
    <div class="header">
        <div class="header_resize">
            <div class="slider">
                <div id="coin-slider"><a href="/"><img src="resources/images/40fe6b04a2ea.png" width="360" height="240"
                                                       alt=""/> </a></div>
            </div>
            <div class="menu_nav">
                <tiles:insertAttribute name="menu_nav" ignore="true"/>
            </div>
            <div class="logo">
                <h1><a href="/"><span>UFOP</span>
                    <small>business activity monitoring</small>
                </a></h1>
            </div>
            <div class="clr"></div>
        </div>
    </div>
    <div class="content">
        <div class="content_resize">
            <div class="mainbar">
                <tiles:insertAttribute name="body" ignore="true"/>
            </div>
            <div class="sidebar">
                <div class="searchform">
                    <form id="formsearch" name="formsearch" method="post" action="#">
                        <span>
                        <input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80"
                               value="Search our ste:" type="text"/>
                        </span>
                        <input name="button_search" src="resources/images/search.gif" class="button_search"
                               type="image"/>
                    </form>
                </div>
                <div class="clr"></div>
                <div id="menu_gadget" class="gadget">
                    <tiles:insertAttribute name="menu_gadget" ignore="true"/>
                </div>
                <sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
                    <div id="admin_gadget" class="gadget">
                        <tiles:insertAttribute name="admin_gadget" ignore="true"/>
                    </div>
                </sec:authorize>
                <div id="login_gadget" class="gadget">
                    <tiles:insertAttribute name="login_gadget" ignore="true"/>
                </div>
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
