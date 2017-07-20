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
            <form action="/viewslistbyunitandtime/1" method="get">
                <select name="id">
                    <c:forEach var="item" items="${unitList}">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select><br />
                <select name="id1">
                    <c:forEach var="item" items="${utimeList}">
                        <option value="${item.key}">${item.value}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Переглянути"/>
            </form>
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
        <div class="searchmenu">
            <form action="/viewslistevent/1/" method="get">
                <table>
                    <tr>
                        <td>Підрозділ</td>
                        <td>
                            <select name="param0">
                                <c:forEach var="item" items="${unitList}">
                                    <option value="${item.key}">${item.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Початкова дата</td>
                        <td><input type="date" name="param1"/></td>
                    </tr>
                    <tr>
                        <td>Кінцева дата</td>
                        <td><input type="date" name="param2"/></td>
                    </tr>
                    <tr>
                        <td>Тип перевірки</td>
                        <td>
                            <select name="param3" ><br />
                                <option value="-1">Не враховувати</option>
                                <option value="0">Планова</option>
                                <option value="1">Позапланова</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Порушення</td>
                        <td><select name="param4" ><br />
                            <option value="-1">Не враховувати</option>
                            <option value="0">Не виявлені</option>
                            <option value="1">Виявлені</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Переглянути"/></td>
                    </tr>
                </table>
            </form>
        </div>
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
