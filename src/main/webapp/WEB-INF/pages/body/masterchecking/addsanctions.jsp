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
            <c:if test="${empty command.punishmentArticlesList}">
                <span class="error">немає жодної статті для накладання санкцій</span>
            </c:if>
            <c:if test="${not empty command.punishmentArticlesList}">
                <c:forEach items="${command.punishmentArticlesList}" var="pa">
                    <li/>
                    ${pa.caption}
                    <a href="/article_info?id=${punishmentArticles_list.articles_law_link}" target="_blank"><img
                        src="../resources/images/info.png" width="10px"/></a><br/>
                </c:forEach>
            </c:if>
            <c:if test="${not empty testSanction}">
                <c:forEach items="${testSanction}" var="testSanction">
                    <table width="100%">
                        <caption>Накладена санкція</caption>
                        <tr>
                            <td>Сума</td>
                            <td>${testSanction.charged_amount} грн.</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Дата вручення постанови</td>
                            <td>
                                <c:if test="${testSanction.service_date eq '0001-01-01'}">N/A</c:if>
                                <c:if test="${testSanction.service_date ne '0001-01-01'}">${testSanction.service_date}</c:if>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Планова дата</td>
                            <td>
                                <c:if test="${testSanction.plan_date eq '0001-01-01'}">N/A</c:if>
                                <c:if test="${testSanction.plan_date ne '0001-01-01'}">${testSanction.plan_date}</c:if>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Фактична дата</td>
                            <td>
                                <c:if test="${testSanction.fact_date eq '0001-01-01'}">N/A</c:if>
                                <c:if test="${testSanction.fact_date ne '0001-01-01'}">${testSanction.fact_date}</c:if>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>
            <c:if test="${empty testSanction}">
                <form:form method="post" action="/addsanctionspost">
                    <table>
                        <tr>
                            <td></td>
                            <td><form:hidden path="check_event_link"/></td>
                            <td><span class="error"><form:errors path="check_event_link"/></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="charged_amount">Сума</form:label></td>
                            <td><form:input path="charged_amount"/> грн.</td>
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
