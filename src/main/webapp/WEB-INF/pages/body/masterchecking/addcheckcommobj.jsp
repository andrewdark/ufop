<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 12.04.2017
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <table width="100%">
            <caption>Існуючі комерційні об'єкти</caption>
            <c:forEach items="${checkingCommObjList_d}" var="d">
                <tr>
                    <td>${d.comm_obj_name}</td>
                </tr>
                <br/>
            </c:forEach>
        </table>
        <c:if test="${empty command.commobj_list}">
            <br/><br/>
            <b>Всі комерційні об'єкти, які були в наявності у <a href="/show_ufop?id=${event.ufop_link}">суб'єкта
                господарювання</a>, були внесені в існуючу перевірку.</b>
        </c:if>
        <c:if test="${not empty command.commobj_list}">
            <form:form action="${actionlink}" method="post">
                <form:hidden path="id"/>
                <table width="100%">
                    <caption>Нові комерційні об'єкти</caption>
                    <tr>
                        <td>
                            <c:forEach items="${command.commobj_list}" var="co_list">
                                ${co_list.check_event_link}- ${co_list.comm_obj_name}<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${command.commobj_list}" var="co_list" varStatus="status">
                                <form:checkbox path="commobj_list[${status.index}].checking"/> додати до перевірки
                                <form:hidden path="commobj_list[${status.index}].comm_obj_link"/>
                                <form:hidden path="commobj_list[${status.index}].check_event_link"/>
                                <form:hidden path="commobj_list[${status.index}].id"/>
                                <br/>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="${buttonvalue}"></td>
                        <td></td>
                    </tr>
                </table>

            </form:form>
        </c:if>
        <div class="d_right">
            <form method="get" action="/show_event/">
                <input type="hidden" name="id" value="${checkEvent.id}"/>
                <input type="submit" value="Завершити"/>
            </form>
        </div>
        <div class="info_padding">
            <hr/>
            <table width="100%">
                <caption>Інформація про перевірку</caption>
                <tr>
                    <td>ІД</td>
                    <td>${checkEvent.id}</td>

                </tr>
                <tr>
                    <td>Тип</td>
                    <td>
                        <c:if test="${checkEvent.check_type==0}">Планова</c:if>
                        <c:if test="${checkEvent.check_type==1}">Позапланова</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Опис</td>
                    <td>${checkEvent.event_result}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="clr"></div>
</div>