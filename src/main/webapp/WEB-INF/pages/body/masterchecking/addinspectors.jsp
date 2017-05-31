<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.05.2017
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <form:form method="post" action="${actionlink}">
            <form:hidden path="id"/>
            <form:hidden path="check_event_link"/>
            <form:hidden path="accepted"/>
            <form:hidden path="creator_name"/>
            <table>
                <tr>
                    <td></td>
                    <td><form:select path="user_link" items="${inspectorsList}" /></td>
                    <td><span class="error"><form:errors path="user_link"/></span></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="${buttonvalue}"></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <div class="d_right">
            <form method="get" action="/show_event/">
                <input type="hidden" name="id" value="${checkEvent.id}"/>
                <input type="submit" value="Завершити"/>
            </form>
        </div>
    </div>
    <div class="clr"></div>
</div>