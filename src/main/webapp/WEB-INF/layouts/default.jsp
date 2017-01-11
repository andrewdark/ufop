<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/main.css"/>
</head>
<body>
<div class="main">
    <div class="header">
        <div class="header_resize">
            <div class="slider">
                <div id="coin-slider"><a href="#"><img src="resources/images/slide1.jpg" width="960" height="500"
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
