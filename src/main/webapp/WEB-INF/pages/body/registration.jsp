<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css">
    span.error {
        color: red;
    }
</style>
<div class="article">
    <h2><span>РЕЄСТРАЦІЯ</span> НОВОГО КОРИСТУВАЧА</h2>
    <div class="clr"></div>
    <div class="post_content">
<form:form action="${pageContext.servletContext.contextPath}/adduser" method="post">
    <table>
        <tr>
            <td><form:label path="username">username:</form:label></td>
            <td><form:input path="username"></form:input></td>
            <td><span class="error" ><form:errors path="username" /></span></td>
        </tr>
        <tr>
            <td><form:label path="pwd">password:</form:label></td>
            <td><form:password path="pwd" ></form:password></td>
            <td><span class="error" ><form:errors path="pwd" /></span></td>
        </tr>
        <tr>
            <td><form:label path="confirm_pwd">confirm pwd:</form:label></td>
            <td><form:password path="confirm_pwd" ></form:password></td>
            <td><span class="error" ><form:errors path="confirm_pwd" /></span></td>
        </tr>
        <tr>
            <td><form:label path="email">e-mail:</form:label></td>
            <td><form:input path="email" ></form:input></td>
            <td><span class="error" ><form:errors path="email" /></span></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit"/></td>
            <td><input type="reset" value="Reset" /></td>
        </tr>
    </table>



</form:form>
    </div>
    <div class="clr"></div>
</div>