<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <form:form action="${pageContext.servletContext.contextPath}/individualentrepreneur" method="get">
            <table>
                <tr>
                    <td>Найменування:</td>
                    <td><input type="text" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Код ЕРДПОУ:</td>
                    <td><input type="text" maxlength="8"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Місце знаходження:</td>
                    <td><input type="text" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Примітка:</td>
                    <td><input type="text" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit"><img src="resources/images/next_step.jpg"/></button></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="clr"></div>
</div>
