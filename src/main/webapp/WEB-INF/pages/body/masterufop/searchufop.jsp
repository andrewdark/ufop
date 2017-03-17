<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15.03.2017
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <form:form action="/searchufoppost" method="post">
            <table>
                <tr>
                    <td><form:label path="ufop_code">Ідентифікаційний номер</form:label></td>
                    <td><form:input path="ufop_code" minlenght="8" maxlength="10"/></td>
                    <td><form:errors path="ufop_code"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="перевірити"/></td>
                    <td></td>
                </tr>
            </table>

        </form:form>
    </div>
    <div class="clr"></div>
</div>