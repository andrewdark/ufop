<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 13.01.2017
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="searchtabs">
    <ul>
        <li><a href="#tabs-1">Суб'єкт господарювання</a></li>
        <li><a href="#tabs-2">Перевірки</a></li>
    </ul>

    <div id="tabs-1">
        Детальний пошук суб'єктів господарювання <br />
        <form action="/viewslistbycreator/1/" method="get">
            <select name="id">
                <c:forEach var="item" items="${inspectorsList}">
                    <option value="${item.key}">${item.value}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Переглянути"/>
        </form>
        <br />
        <a href="/excel">Вигрузити всіх суб'єктів господарювання до EXCEL</a>
    </div>
    <div id="tabs-2">
        Детальний пошук перевірок
    </div>
</div>
