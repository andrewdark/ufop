<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 26.03.2017
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table width="100%">
    <caption>ПЕРЕВІРКА СУБ'ЄКТА ГОСПОДАРЮВАННЯ</caption>
    <tr>
        <td>Тип перевірки</td>
        <td>${event.check_type}</td>
        <td></td>
    </tr>
    <tr>
        <td>Результати перевірки</td>
        <td>${event.check_violation}  </td>
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
                ${commobj_list.comm_obj_link}<br />
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