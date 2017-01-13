<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
span.error {
color: red;
}
</style>
<form:form method="POST" action="${pageContext.servletContext.contextPath}/restorepost">
    <table>
        <tr>
            <td><form:label path="username">username</form:label></td>
            <td><form:input path="username" maxlength="50" htmlEscape="true"/></td>
            <td><span class="error" ><form:errors path="username" /></span></td>
        </tr>
        <tr>
            <td><form:label path="email">e-mail</form:label></td>
            <td><form:input path="email" maxlength="50" htmlEscape="true"/></td>
            <td><span class="error" ><form:errors path="email" /></span></td>
        </tr>

        <tr>
            <td colspan="2" id="button">
                <input type="submit" value="submit"/>
                <input type="reset" value="reset"/>
            </td>
            <td></td>
            <td></td>
        </tr>
    </table>

</form:form>