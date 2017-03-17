<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15.03.2017
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        ${co.ufop_link}
        ${co.obj_name}
        <form:form action="#" method="post">
            <table>
                <tr>
                    <td><input type="button" value="Додати" /></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <form action="/searchufop" method="get">
            <input type="submit" value="Завершити" />
        </form>
    </div>
    <div class="clr"></div>
</div>