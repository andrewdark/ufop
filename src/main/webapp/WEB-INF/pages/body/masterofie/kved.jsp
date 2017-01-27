<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <p>Місце здійснення підприємницької діяльності</p>
        <form:form action="" method="get">
            <table>
                <tr>
                    <td>Назва:</td>
                    <td><input type="text" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Адреса:</td>
                    <td><input type="text" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit"><img src="resources/images/add.jpg"/></button></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>

    <div class="clr"></div>
</div>
<div class="d_right"><a href="/commercialobject"><img src="resources/images/next_step.jpg"/></a></div>