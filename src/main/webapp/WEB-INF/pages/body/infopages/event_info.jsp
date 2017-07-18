<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 19.05.2017
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;"><h2>${title}<br />
    <c:if test="${ufop.ufop_is eq 0}">ФОП&nbsp;</c:if>${ufop.ufop_name}</h2></div>
<br />
<table width="100%">
    <caption>ПЕРЕВІРКА СУБ'ЄКТА ГОСПОДАРЮВАННЯ</caption>
    <tr>
        <td>Перевіряючий орган:</td>
        <td>
            ${event.structure_catalog_name}
        </td>
    </tr>
    <tr>
        <td>Тип перевірки</td>
        <td>
            <c:if test="${event.check_type==0}">Планова</c:if>
            <c:if test="${event.check_type==1}">Позапланова</c:if>
        </td>
    </tr>
    <tr>
        <td>Номер акту</td>
        <td>${event.event_number}</td>
    </tr>
    <tr>
        <td>Початок перевірки</td>
        <td>${event.event_date_begin}</td>
    </tr>
    <tr>
        <td>Кінець перевірки</td>
        <td>
            <c:if test="${event.event_date_end eq '0001-01-01'}">N/A</c:if>
            <c:if test="${event.event_date_end ne '0001-01-01'}">${event.event_date_end}</c:if>
        </td>
    </tr>
    <tr>
        <td>Результати перевірки</td>
        <td>
            <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
            <c:if test="${event.check_violation==1}">Порушення виявлені</c:if>
    </tr>
    <tr>
        <td>Результати</td>
        <td>${event.event_result}</td>
    </tr>
    <tr>
        <td>Відбір матеріалу</td>
        <td>
            <c:if test="${event.check_sampling==0}">Не проводився</c:if>
            <c:if test="${event.check_sampling==1}">Проводився</c:if>
        </td>
    </tr>
    <tr>
        <c:if test="${event.check_sampling==1}">
            <td>Результати проб</td>
            <td>
                <c:if test="${event.result_sampling==0}">Відповідають вимогам</c:if>
                <c:if test="${event.result_sampling==1}">Не відповідають вимогам</c:if>
            </td>
        </c:if>
    </tr>
    <tr>
        <td>Перевірені комерційні об'єкти</td>
        <td>
            <c:forEach items="${event.commobj_list}" var="commobj_list">
                <li/>
                ${commobj_list.comm_obj_link} ${commobj_list.comm_obj_name} -
                <c:if test="${commobj_list.checking}">перевірявся</c:if>
                <c:if test="${not commobj_list.checking}">знятий з перевірки</c:if>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td>Групи товарів</td>
        <td>
            <c:forEach items="${event.groupofgoods_list}" var="groupofgoods_list">
                ${groupofgoods_list.s_goods_catalog_link}<br/>
            </c:forEach>
        </td>
    </tr>
</table>
<br/>
<c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
<c:if test="${event.check_violation==1}">
    <table width="100%">
        <caption>РЕЗУЛЬТАТИ ЗАХОДУ НАГЛЯДУ</caption>
        <tr>
            <td>Результат</td>
            <td>Виявлені порушення</td>
        </tr>
        <tr>
            <td>Перелік порушень</td>
            <td>${event.event_result}</td>
        </tr>
        <tr>
            <td>Статті порушень</td>
            <td>
                <c:if test="${not empty offensearticles}">
                    <c:forEach items="${offensearticles}" var="offensearticles">
                        <li/>
                        ${offensearticles.caption}
                        <br/>
                    </c:forEach>
                </c:if>
            </td>
        </tr>
    </table>
    <br/>
    <c:if test="${not empty precaution}">
        <table width="100%">
            <caption>ПРИЙНЯТІ ЗАХОДИ</caption>
            <c:forEach items="${precaution}" var="precaution">
                <tr>
                    <td>Назва заходу</td>
                    <td>${precaution.precaution_name}</td>
                </tr>
                <c:if test="${not empty precaution.decision_number}">
                    <tr>
                        <td>Номер рішення</td>
                        <td>${precaution.decision_number}</td>
                    </tr>
                </c:if>
                <tr>
                    <td>Виконати до</td>
                    <td>
                        <c:if test="${precaution.plan_date eq '0001-01-01'}">N/A</c:if>
                        <c:if test="${precaution.plan_date ne '0001-01-01'}">${precaution.plan_date}</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Виконано по факту</td>
                    <td>
                        <c:if test="${precaution.fact_date eq '0001-01-01'}">N/A</c:if>
                        <c:if test="${precaution.fact_date ne '0001-01-01'}">${precaution.fact_date}</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</c:if><br />
<c:if test="${event.check_violation==0}"></c:if>
<c:if test="${event.check_violation==1}">
    <c:if test="${empty testSanction}"><span>Інформація по накладеним санкціям відсутня</span></c:if>

    <c:if test="${not empty testSanction}">
        <c:forEach items="${testSanction}" var="testSanction">
            <table width="100%">
                <caption>НАКЛАДЕНА САНКЦІЯ № ${testSanction.sanction_number}</caption>
                <tr>
                    <td>Стаття</td>
                    <td>${testSanction.articles_law_caption}</td>
                </tr>
                <tr>
                    <td>Сума</td>
                    <td>${testSanction.charged_amount} грн.</td>
                </tr>
                <tr>
                    <td>Дата вручення постанови</td>
                    <td>
                        <c:if test="${testSanction.service_date eq '0001-01-01'}">N/A</c:if>
                        <c:if test="${testSanction.service_date ne '0001-01-01'}">${testSanction.service_date}</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Планова дата</td>
                    <td>
                        <c:if test="${testSanction.plan_date eq '0001-01-01'}">N/A</c:if>
                        <c:if test="${testSanction.plan_date ne '0001-01-01'}">${testSanction.plan_date}</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Фактична дата</td>
                    <td>
                        <c:if test="${testSanction.fact_date eq '0001-01-01'}">N/A</c:if>
                        <c:if test="${testSanction.fact_date ne '0001-01-01'}">${testSanction.fact_date}</c:if>
                    </td>
                </tr>
            </table>
            <br/>
        </c:forEach><br/>
        <hr/>
        <b>Загальна сума штрафних санкцій складає:&nbsp;&nbsp;</b>${sum}&nbsp; грн.
    </c:if>
</c:if>