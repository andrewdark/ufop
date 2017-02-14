<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10.01.2017
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2 class="star"><span>Меню користувача</span></h2>
<div class="clr"></div>

<ul id="menu">
    <li><div>Перегляд</div>
        <ul>
            <li><div><a href="/viewslisti/1">Фізична особа підприємець</a></div></li>
            <li><div><a href="/viewslistu">Юридична особа</a></div></li>
            <li><div>Комерційні об'єкти</div></li>
        </ul>
    </li>
    <li><div>Реєстрація</div>
        <ul>
            <li><div><a href="/contact">Фізична особа підприємець</a></div></li>
            <li><div><a href="/entitycontact">Юридична особа</a></div></li>
            <li><div>Комерційні об'єкти</div></li>
        </ul>
    </li>
    <li class="ui-state-disabled"><div>Звернення громадян</div>
        <ul>

            <li><div>Реєстрація</div></li>
            <li><div>Пошук</div></li>
        </ul>
    </li>
    <li class="ui-state-disabled"><div>Планові заходи</div></li>
    <li><div>Модуль кадри</div>
        <ul>
            <li><div class="ui-state-disabled">УПРАВЛІННЯ</div>
            <li><div>Безпечності харчових продуктів та ветеринарії</div>
                <ul>
                    <li><div class="ui-state-disabled">ВІДДІЛИ</div>
                    <li><div>безпечності харчових продуктів</div></li>
                    <li><div>державного контролю</div></li>
                    <li><div>організації протиепізоотичної роботи</div></li>
                </ul>
            </li>
            <li><div>Фітосанітарної безпеки</div>
                <ul>
                    <li><div class="ui-state-disabled">ВІДДІЛИ</div>
                    <li><div>карантину рослин</div></li>
                    <li><div>контролю за обігом засобів захисту</div></li>
                    <li><div>організації протиепізоотичної роботи</div></li>
                    <li><div>фітосанітарних заходів на кордоні</div></li>
                </ul>
            </li>
            <li><div>Контролю у сфері насінництва та розсадництва</div>
                <ul>
                    <li><div class="ui-state-disabled">ВІДДІЛИ</div>
                    <li><div>нагляду в насінництві та розсадництві</div></li>
                    <li><div>атестації, сертифікації та аналітичної роботи</div></li>
                </ul>
            </li>
            <li><div>Захисту споживачів</div>
                <ul>
                    <li><div class="ui-state-disabled">ВІДДІЛИ</div>
                    <li><div>ринкового нагляду</div></li>
                    <li><div>контролю у сфері торгівлі, робіт та послуг</div></li>
                    <li><div>контролю за рекламою та дотриманням антитютюнового законодавства</div></li>
                </ul>
            </li>
            <li><div>Державного нагляду за дотриманням санітарного законодавства</div>
                <ul>
                    <li><div class="ui-state-disabled">ВІДДІЛИ</div>
                    <li><div>санітарно-епідемологічного нагляду та організації розслідування спалахів</div></li>
                    <li><div>безпеки середовища життєдіяльності</div></li>
                    <li><div>санітарної охорони території</div></li>
                </ul>
            </li>
        </ul>
    </li>
    <li class="ui-state-disabled"><div>Specials (n/a)</div></li>


</ul>