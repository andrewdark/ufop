<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.04.2017
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="searchform">
    <form id="formsearch" name="formsearch" method="get" action="/viewslistsearch/1">
                        <span>
                        <input name="stext" class="editbox_search" id="editbox_search" maxlength="80"
                               value="" type="text"/>
                        </span>
        <input src="/resources/images/search.gif" class="button_search"
               type="image"/>
    </form>
</div>
<div class="clr"></div>
<div id="menu_gadget" class="gadget">
    <jsp:include page="menu.jsp" />
</div>
<sec:authorize access="hasAnyRole('ROLE_ADMINISTRATOR,ROLE_CHIEF')">
    <div id="chief_gadget" class="gadget">
        <jsp:include page="chiefmenu.jsp" />
    </div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
    <div id="admin_gadget" class="gadget">
        <jsp:include page="adminmenu.jsp" />
    </div>
</sec:authorize>
<div id="login_gadget" class="gadget">
    <jsp:include page="login.jsp" />
</div>