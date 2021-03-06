<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.03.2017
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="img">


    </div>
    <div class="post_content_wide">
        <div id="precaution_list">
            <c:forEach items="${precaution_list}" var="precaution_list">
                <li/>
                ${precaution_list.precaution_name}
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/deleteprecaution?id=${precaution_list.id}&EventId=${precaution_list.check_event_link}"
                   onclick="return confirmDelete();">Видалити</a>

            </c:forEach>
        </div>
        <c:if test="${checkEvent.check_violation==0}"><p>Порушеннь не виявлено</p>
            <p>В ході даної перевірки порушень не знайдено<br/><a href="/show_event?id=${event.id}">Повернутись до
                перевірки</a></p>
        </c:if>
        <c:if test="${empty checkEvent}"><p>Відсутнє посилання на перевірку</p><br/>
            <p><span class="error"><form:errors path="check_event_link"/></span><br/><a
                    href="/show_event?id=${event.id}">Повернутись до перевірки</a></p>
        </c:if>

        <c:if test="${checkEvent.check_violation==1}">
            <form:form method="post" action="${actionlink}">
                <table>
                    <tr>
                        <td><form:hidden path="id"/></td>
                        <td><form:hidden path="check_event_link"/></td>
                        <td><span class="error"><form:errors path="check_event_link"/></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="precaution_catalog_link">№ Рішення</form:label></td>
                        <td><form:input path="decision_number"/></td>
                        <td><span class="error"><form:errors path="decision_number"/></span></td>
                    </tr>
                    <tr>
                        <c:if test="${command.id == 0}">
                            <td><form:label path="precaution_catalog_link">Вкажіть захід</form:label></td>
                            <td><form:select path="precaution_catalog_link" items="${precautioncatalog}"/></td>
                            <td><span class="error"><form:errors path="precaution_catalog_link"/></span></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td><form:label path="service_date">Дата прийняття заходу</form:label></td>
                        <td><form:input path="service_date" id="service_date"/></td>
                        <td><span class="error"><form:errors path="service_date"/></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="plan_date">Планова дата виконання</form:label></td>
                        <td><form:input path="plan_date" id="plan_date"/></td>
                        <td><span class="error"><form:errors path="plan_date"/></span></td>
                    </tr>
                    <tr>
                        <td><form:label path="fact_date">Фактична дата виконання</form:label></td>
                        <td><form:input path="fact_date" id="fact_date"/></td>
                        <td><span class="error"><form:errors path="fact_date"/></span></td>
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
                    <td>Номер</td>
                    <td>${checkEvent.event_number}</td>
                </tr>
                <tr>
                    <td>Тип</td>
                    <td>
                        <c:if test="${checkEvent.check_type==0}">Планова</c:if>
                        <c:if test="${checkEvent.check_type==1}">Позапланова</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Порушення</td>
                    <td>
                        <c:if test="${checkEvent.check_violation==0}">Порушень не знайдено</c:if>
                        <c:if test="${checkEvent.check_violation==1}">Порушення виявлено</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Опис</td>
                    <td>
                        <c:if test="${checkEvent.check_violation==0}">N/A</c:if>
                        <c:if test="${checkEvent.check_violation==1}"> ${checkEvent.event_result}</c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="clr"></div>
</div>
