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
    <div class="img"></div>


    <div class="post_content_wide">
        <c:if test="${empty checkEvent}"><span class="error"><p>Відсутнє посилання на перевірку</p></span><br/>
            <p><span class="error"><form:errors path="check_event_link"/></span><br/><a
                    href="/show_event?id=${event.id}">Повернутись
                до перевірки</a></p>
        </c:if>
        <c:if test="${checkEvent.check_violation==0}"><p>Порушеннь не виявлено</p>
            <p>В ході даної перевірки порушень не знайдено<br/><a href="/show_event?id=${event.id}">Повернутись до
                перевірки</a></p>
        </c:if>
        <c:if test="${checkEvent.check_violation==1}">

                <form:form method="post" action="/addsanctionspost">
                    <table>
                        <tr>
                            <td></td>
                            <td><form:hidden path="check_event_link"/></td>
                            <td><span class="error"><form:errors path="check_event_link"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="articles_law_link">Виберіть статтю</form:label></td>
                            <td><form:input path="articles_law_link" id="articles"
                                            onclick="javascript:ArticlesPopUpShow();"/></td>
                            <td><span class="error"><form:errors path="articles_law_link"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="charged_amount_str">Сума</form:label></td>
                            <td><form:input path="charged_amount_str"/> грн.</td>
                            <td><span class="error"><form:errors path="charged_amount"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="service_date">Дата вручення постанови</form:label></td>
                            <td><form:input path="service_date" id="service_date"/></td>
                            <td><span class="error"><form:errors path="service_date"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="plan_date">Планова дата</form:label></td>
                            <td><form:input path="plan_date" id="plan_date"/></td>
                            <td><span class="error"><form:errors path="plan_date"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="fact_date">Фактична дата</form:label></td>
                            <td><form:input path="fact_date" id="fact_date"/></td>
                            <td><span class="error"><form:errors path="fact_date"/></span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="записати"></td>
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
                    <td>Тип</td>
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
<div class="b-popup" id="popup5">
    ${ex}<br/>
    <div class="b-popup-content" id="setarticles_popup">
        <select id="my_selecttop1" name="my_selecttop" onchange="looparticlesdown(2)">
            <option value=""></option>
            <c:forEach items="${articlesTop}" var="articlesTop">
                <option value="${articlesTop.treemark}">${articlesTop.caption}</option>
            </c:forEach>
        </select><br/>
        <div id="ArticlesType2"></div>
        <div id="ArticlesType3"></div>
        <div id="ArticlesType4"></div>
        <div id="ArticlesType5"></div>
        <a href="javascript:ArticlesPopUpHide()">Додати закон</a>
    </div>
</div>