<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <form:form action="${pageContext.servletContext.contextPath}/kved" method="get">
            <table>
                <tr>
                    <td>Id фіз. Особи:</td>
                    <td><input type="text" value="${sendContact.id}" disabled/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Код ІПН:</td>
                    <td><input type="text" value="${sendContact.rntc}" disabled/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Місце реєстрації:</td>
                    <td><input type="text"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Ступінь ризику:</td>
                    <td><select>
                        <option value="1">Висока</option>
                        <option value="2">Середня</option>
                        <option value="3">Низька</option>
                    </select></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Платник ПДВ:</td>
                    <td><input type="checkbox"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit"><img src="resources/images/next_step.jpg"/></button>
                    </td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="clr"></div>
</div>
