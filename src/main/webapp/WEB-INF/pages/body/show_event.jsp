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
<div id="navigation" class="navblock">
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
                                <c:if test="${event.check_violation==1}">
                                    <td><form:radiobutton path="nav" value="2"/> статті правопорушень</td>
                                    <td><form:radiobutton path="nav" value="3"/> комерційні об'єкти</td>
                                </c:if>
                            </tr>
                            <tr>
                                <c:if test="${event.check_violation==1}">
                                    <td><form:radiobutton path="nav" value="4"/> прийняті заходи</td>
                                    <td><form:radiobutton path="nav" value="5"/> внесені санкції</td>
                                    <td><form:radiobutton path="nav" value="6"/> судова справа</td>
                                </c:if>
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
        <li><a href="#tabs-2">Результати нагляду</a></li>
        <li><a href="#tabs-3">Санкції</a></li>
        <li><a href="#tabs-4">Судові рішення</a></li>
        <li><a href="#tabs-5">Контролі</a></li>
    </ul>
    <div id="tabs-1">
        <table width="100%">
            <caption>ПЕРЕВІРКА <a href="/show_ufop?id=${event.ufop_link}">СУБ'ЄКТА ГОСПОДАРЮВАННЯ</a></caption>
            <tr>
                <td>Тип перевірки</td>
                <td>
                    <c:if test="${event.check_type==0}">Планова</c:if>
                    <c:if test="${event.check_type==1}">Позапланова</c:if>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Результати перевірки</td>
                <td>
                    <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
                    <c:if test="${event.check_violation==1}">Порушення виявлені</c:if>
                <td></td>
            </tr>
            <tr>
                <td>Результати</td>
                <td>${event.event_result}</td>
                <td></td>
            </tr>
            <tr>
                <td>Відбір матеріалу</td>
                <td>
                    <c:if test="${event.check_sampling==0}">Не проводився</c:if>
                    <c:if test="${event.check_sampling==1}">Проводився</c:if>

                </td>
                <td></td>
            </tr>
            <tr>
                <td>Результати проб</td>
                <td>
                    <c:if test="${event.result_sampling==0}">Відповідають вимогам</c:if>
                    <c:if test="${event.result_sampling==1}">Не відповідають вимогам</c:if>
                </td>
                <td></td>
            </tr>
            <tr>
                <td>Початок перевірки</td>
                <td>${event.event_date_begin}</td>
                <td></td>
            </tr>
            <tr>
                <td>Кінець перевірки</td>
                <td>
                    <c:if test="${event.event_date_end eq '0001-01-01'}">N/A</c:if>
                    <c:if test="${event.event_date_end ne '0001-01-01'}">${event.event_date_end}</c:if>

                </td>
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
                       ${groupofgoods_list.s_goods_catalog_link}<br />
                    </c:forEach>
                </td>
                <td></td>
            </tr>

        </table>
    </div>
    <div id="tabs-2">
        <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
        <c:if test="${event.check_violation==1}">
            <table width="100%">
                <caption><span style="font-size: 150%;">РЕЗУЛЬТАТИ ЗАХОДУ НАГЛЯДУ</span></caption>

                <tr>
                    <td>Результат</td>
                    <td>Виявлені порушення</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Перелік порушень</td>
                    <td>${event.event_result}</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Статті порушень</td>
                    <td>
                        <c:if test="${not empty offensearticles}">
                            <c:forEach items="${offensearticles}" var="offensearticles">
                                <li/>
                                ${offensearticles.caption} <br/>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td></td>
                </tr>
            </table>
            <br/>
            <c:if test="${not empty precaution}">
                <c:forEach items="${precaution}" var="precaution">
                    <table width="100%">
                        <caption><span style="font-size: 150%;">ПРИЙНЯТІ ЗАХОДИ</span></caption>
                        <br/>
                        <tr>
                            <td>Назва заходу</td>
                            <td>
                                    ${precaution.precaution_name}
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Виконати до</td>
                            <td>
                                <c:if test="${precaution.plan_date eq '0001-01-01'}">N/A</c:if>
                                <c:if test="${precaution.plan_date ne '0001-01-01'}">${precaution.plan_date}</c:if>

                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Виконано по факту</td>
                            <td>
                                <c:if test="${precaution.fact_date eq '0001-01-01'}">N/A</c:if>
                                <c:if test="${precaution.fact_date ne '0001-01-01'}">${precaution.fact_date}</c:if>
                            </td>
                            <td></td>
                        </tr>
                    </table>
                </c:forEach>
            </c:if>

        </c:if>
    </div>
    <div id="tabs-3">
        <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
        <c:if test="${event.check_violation==1}">
            <c:if test="${empty testSanction}"><span>Інформація по накладеним санкціям відсутня</span></c:if>
            <c:if test="${not empty punishmentarticles}">
                <c:forEach items="${punishmentarticles}" var="punishmentarticles">
                    <li /> ${punishmentarticles.name}
                </c:forEach>
            </c:if>
            <c:if test="${not empty testSanction}">
                <c:forEach items="${testSanction}" var="testSanction">
                    <table width="100%">
                        <caption><span style="font-size: 150%;">НАКЛАДЕНА САНКЦІЯ</span></caption>
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
        </c:if>

    </div>

    <div id="tabs-4">
        <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
        <c:if test="${event.check_violation==1}">
            <c:if test="${empty lawsuits}"><span>Інформація по судовій справі відсутня</span></c:if>
            <c:if test="${not empty lawsuits}">
                <c:forEach items="${lawsuits}" var="lawsuits">
                    <table width="100%">
                        <caption>Судові справа</caption>
                        <tr>
                            <td></td>
                            <td>
                                <c:if test="${lawsuits.filed_on_action==0}">
                                    <span>Справа до суду не передавалась</span>
                                </c:if>
                                <c:if test="${lawsuits.filed_on_action==1}">
                                    <span>Справу передано до суду</span>
                                </c:if>
                            </td>
                            <td></td>
                        </tr>
                        <c:if test="${lawsuits.filed_on_action==0}">

                        </c:if>
                        <c:if test="${lawsuits.filed_on_action==1}">
                            <tr>
                                <td>Дата подання</td>
                                <td>
                                    <c:if test="${lawsuits.filed_date eq '0001-01-01'}">N/A </c:if>
                                    <c:if test="${lawsuits.filed_date ne '0001-01-01'}"> ${lawsuits.filed_date}</c:if>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Результат</td>
                                <td>${lawsuits.sresult_link}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>Примітки</td>
                                <td>${lawsuits.description}</td>
                                <td></td>
                            </tr>
                        </c:if>

                    </table>
                </c:forEach>
            </c:if>
        </c:if>


    </div>
    <div id="tabs-5">
        <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
        <c:if test="${event.check_violation==1}">

        </c:if>
    </div>

</div>