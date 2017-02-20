<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
    <div id="co_message"></div>

            <table>
                <tr>
                    <td>ID ФОП:</td>
                    <td><input type="text" disabled="disabled" value="${ufop_id}" id="fopidco"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Адреса: </td>
                    <td><input type="text" id="addressco"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>№ будівлі: </td>
                    <td><input type="text" id="nbudco"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Тип об'єкта: </td>
                    <td><select>
                        <option value="1">МАФ</option>
                        <option value="2">Ларьок</option>
                        <option value="3">Магазин</option>
                    </select></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Назва</td>
                    <td><input type="text" id="nameco"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit" onclick="inject_co()"><img src="resources/images/add.jpg"/></button></td>
                    <td></td>
                </tr>
            </table>

    </div>
    <div class="clr"></div>
</div>
<div class="d_right"><a href="/"><img src="resources/images/done.jpg"/></a></div>