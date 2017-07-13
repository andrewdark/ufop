<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    select {
        width: 300px; /* Ширина списка в пикселах */
    }
</style>
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
        <li><a href="#tabs-3">Комерційні об'єкти</a></li>
        <li><a href="#tabs-4">Контакти</a></li>
        <li><a href="#tabs-5">Відвантаження до Excel</a></li>
    </ul>

    <div id="tabs-1">
        <div class="searchmenu">
            <b>Пошук за реєстратором</b>
            <form action="/viewslistbycreator/1/" method="get">
                <select name="id">
                    <c:forEach var="item" items="${inspectorsList}">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Переглянути"/>
            </form>
        </div>

        <br />
        <div class="searchmenu">
            <b>Пошук за перевіряючим органом</b>
        </div>
        <br />
        <div class="searchmenu">
            <b>Пошук без перевірок</b>
            <form action="/viewslistwithoutevent/1/" method="get">
                <select name="id" >
                    <option value="1">Без перевірок</option>
                    <option value="2">Без перевірок за останній рік</option>
                </select>
                <input type="submit" value="Переглянути"/>
            </form>

        </div>

    </div>
    <div id="tabs-2">
        Детальний пошук перевірок<br /><br />
    </div>
    <div id="tabs-3">
        Детальний пошук комерційних об'єктів<br /><br />
    </div>
    <div id="tabs-4">
        Детальний пошук контактів<br /><br />
    </div>
    <div id="tabs-5">
        <li /> <a href="/excel">Вигрузити всіх суб'єктів господарювання до EXCEL</a>
    </div>
</div>
