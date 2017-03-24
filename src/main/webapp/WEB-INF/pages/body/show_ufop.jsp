<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Суб'єкт господарювання</a></li>
        <li><a href="#tabs-2">КВЕД</a></li>
        <li><a href="#tabs-3">Комерційні об'єкти</a></li>
        <li><a href="#tabs-4">Перевірки</a></li>
        <li><a href="#tabs-5">Контактна інформація</a></li>
    </ul>

    <div id="tabs-1">
        ${ufop.id}
        ${ufop.ufop_code}
        ${ufop.ufop_is}
        ${ufop.ufop_name}
    </div>
    <div id="tabs-2">
        <p>Перелік КВЕД</p>

    </div>
    <div id="tabs-3">

        <table>
            <caption>Перелік комерційних об'єктів</caption>
            <tr>
                <th>НАЗВА</th>
                <th>ТИП</th>
                <th>АДРЕСА</th>
                <th>ПРИМІТКА</th>

            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <form method="post" action="/show_ufop_create_commobj">
                        <input type="hidden" name="ufop" value="${ufop}" />
                        <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}"/>
                        <input type="submit" value="add obj"/>
                    </form>

                </td>
            </tr>
        </table>
    </div>
    <div id="tabs-4">
        <p>Перелік планових та позапланових перевірок</p>

    </div>
    <div id="tabs-5">
        <p>Перелік контактних осіб</p>

    </div>
</div>