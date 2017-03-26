<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dark
  Date: 26.03.2017
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="img">


    </div>
    <div class="post_content_wide">
        <div id="co_message"></div>
        <div id="load_ufop_point"></div>
<form:form action="/addeventsupplementedpost" method="post">
<form:hidden path="ufop_link"/>
    <table>
        <tr>
            <td><form:label path="check_type">Тип перевірки</form:label></td>
            <td>
                <form:radiobutton path="check_type" value="0"/>Планова<br />
                <form:radiobutton path="check_type" value="1"/>Позапланова
            </td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="check_violation">Результати перевірки</form:label></td>
            <td>
                <form:radiobutton path="check_violation" value="0"/>Порушення не виявлено<br />
                <form:radiobutton path="check_violation" value="1"/>Порушення виявлено
            </td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="event_result">Результати</form:label></td>
            <td><form:input path="event_result"/></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="check_sampling">Забор матеріалу</form:label></td>
            <td>
                <form:radiobutton path="check_sampling" value="0"/>Не брали<br />
                <form:radiobutton path="check_sampling" value="1"/>Брали
            </td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="result_sampling">Результати проб</form:label></td>
            <td>
                <form:radiobutton path="result_sampling" value="0"/>Відповідає вимогам<br />
                <form:radiobutton path="result_sampling" value="1"/>Не відповідає вимогам
            </td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="event_date_begin">Початок перевірки</form:label></td>
            <td><form:input path="event_date_begin" id="event_datepicker_begin"/></td>
            <td><span class="error"><form:errors path="event_date_begin"/></span></td>
        </tr>
        <tr>
            <td><form:label path="event_date_end">Кінець перевірки</form:label></td>
            <td><form:input path="event_date_end" id="event_datepicker_end"/></td>
            <td><span class="error"><form:errors path="event_date_end"/></span></td>
        </tr>
        <tr>
            <td>
                <c:forEach items="${comobj_list}" var="co">
                    ${co.obj_name}<br />
                </c:forEach>
            </td>
            <td>
                <c:forEach items="${command.commobj_list}" var="co_list" varStatus="status">
                    <form:checkbox path="commobj_list[${status.index}].checking" /> додати до перевірки
                    <form:hidden path="commobj_list[${status.index}].comm_obj_link"/><br/>
                </c:forEach>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Створити перевірку"></td>
            <td></td>
        </tr>
    </table>
</form:form>


    </div>
    <div class="clr"></div>
</div>
