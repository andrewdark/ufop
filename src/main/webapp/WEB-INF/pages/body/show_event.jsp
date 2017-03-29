<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 26.03.2017
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="navigation">
    <sec:authorize access="isAuthenticated()">
        <table width="100%">
            <tr>
                <form:form action="/show_eventpost" method="post">
                    <form:hidden path="id"/>
                    <form:hidden path="ufop_link"/>
                    <form:hidden path="event_date_begin"/>
                    <form:hidden path="event_date_end"/>
                    <form:hidden path="check_type"/>
                    <form:hidden path="check_violation"/>
                    <form:hidden path="event_result"/>
                    <form:hidden path="check_sampling"/>
                    <form:hidden path="result_sampling"/>
                    <form:hidden path="creator_link"/>
                    <form:hidden path="structure_catalog_link"/>
                    <form:hidden path="datereg"/>
                    <td></td>
                    <td>
                        <table>
                            <tr>
                                <td><form:radiobutton path="nav" value="1"/> основні групи товарів</td>
                                <td><form:radiobutton path="nav" value="2"/> статті правопорушень</td>
                                <td><form:radiobutton path="nav" value="3"/> комерційні об'єкти</td>

                            </tr>
                            <tr>
                                <td><form:radiobutton path="nav" value="4"/> прийняті заходи</td>
                                <td><form:radiobutton path="nav" value="5"/> внесені санкції</td>
                                <td><form:radiobutton path="nav" value="6"/> судова справа</td>

                            </tr>
                        </table>


                    </td>
                    <td><input type="submit" value="Внести"/></td>

                </form:form>
            </tr>
        </table>
    </sec:authorize>
</div>
<br/>
<div id="eventtabs">
    <ul>
        <li><a href="#tabs-1">Перевірка</a></li>
        <li><a href="#tabs-2">Санкції</a></li>
        <li><a href="#tabs-3">Контролі</a></li>
        <li><a href="#tabs-4">TAB_4</a></li>
        <li><a href="#tabs-5">TAB_5</a></li>
    </ul>
    <div id="tabs-1">
        <table width="100%">
            <caption>ПЕРЕВІРКА СУБ'ЄКТА ГОСПОДАРЮВАННЯ</caption>
            <tr>
                <td>Тип перевірки</td>
                <td>${event.check_type}</td>
                <td></td>
            </tr>
            <tr>
                <td>Результати перевірки</td>
                <td>${event.check_violation} </td>
                <td></td>
            </tr>
            <tr>
                <td>Результати</td>
                <td>${event.event_result}</td>
                <td></td>
            </tr>
            <tr>
                <td>Забор матеріалу</td>
                <td>${event.check_sampling}</td>
                <td></td>
            </tr>
            <tr>
                <td>Результати проб</td>
                <td>${event.result_sampling}</td>
                <td></td>
            </tr>
            <tr>
                <td>Початок перевірки</td>
                <td>${event.event_date_begin}</td>
                <td></td>
            </tr>
            <tr>
                <td>Кінець перевірки</td>
                <td>${event.event_date_end}</td>
                <td></td>
            </tr>
            <tr>
                <td>Перевірені комерційні об'єкти</td>
                <td>
                    <c:forEach items="${event.commobj_list}" var="commobj_list">
                        ${commobj_list.comm_obj_link}<br/>
                    </c:forEach>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Групи товарів</td>
                <td>
                    <c:forEach items="${event.groupofgoods_list}" var="groupofgoods_list">

                    </c:forEach>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Статті правопорушень</td>
                <td>
                    <c:forEach items="${event.offensearticles_list}" var="offensearticles_list">

                    </c:forEach>
                </td>
                <td></td>
            </tr>

        </table>
    </div>
    <div id="tabs-2">
        <table width="100%">
            <caption>САНКЦІЇ</caption>
        </table>
    </div>
    <div id="tabs-3">

    </div>
    <div id="tabs-4">

    </div>
    <div id="tabs-5">

    </div>

</div>