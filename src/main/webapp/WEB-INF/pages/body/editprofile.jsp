<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 13.01.2017
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Изменить пароль
<form:form method="POST" action="${pageContext.servletContext.contextPath}/editprofilepost">
    <form:hidden path="username"/>
    <form:hidden path="email"/>
    <table>
            <%--
                    <tr>
                        <td><form:label path="useremail">${label_email}</form:label></td>
                        <td><form:input path="useremail"  class="form-control" maxlength="50"/></td>
                        <td><span class="error" ><form:errors path="useremail" /></span></td>
                    </tr>
            --%>
        <tr>
            <td><form:label path="pwd">Password</form:label></td>
            <td><form:input path="pwd" type="password" maxlength="64" htmlEscape="true"/></td>
            <td><span class="error"><form:errors path="pwd"/></span></td>
        </tr>
        <tr>
            <td><form:label path="confirm_pwd">Confirm pwd</form:label></td>
            <td><form:input path="confirm_pwd" type="password" maxlength="64" htmlEscape="true"/></td>
            <td><span class="error"><form:errors path="confirm_pwd"/></span></td>
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