<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <div id="kved_message"></div>

            <table>
                <tr>
                    <td>ID ФОП:</td>
                    <td><input type="text" disabled="disabled" value="myValue" id="fopid"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>КВЕД:</td>
                    <td><input type="text" id="kvedname"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="button" onclick="inject_kved()"><img src="resources/images/add.jpg"/></button></td>
                    <td></td>
                </tr>
            </table>

    </div>

    <div class="clr"></div>
</div>
<div class="d_right"><a href="/commercialobject"><img src="resources/images/next_step.jpg"/></a></div>