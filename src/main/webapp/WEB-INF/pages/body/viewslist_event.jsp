<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 03.07.2017
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="event" items="${eventList}">
    <div class="article">
        <h2><span>Номер акту ${event.event_number}</span></h2>
        <p class="infopost">Id: ${event.id} Дата реєстрації: <span class="date">${event.datereg}</span> Реєстратор <a
                href="#">${event.creator_link}</a>
            &nbsp;&nbsp;&bull;&nbsp;&nbsp;
            Filed under <a href="#">templates</a>, <a href="#">internet</a> &nbsp;&nbsp;&bull;&nbsp;&nbsp;

        <div class="clr"></div>
        <div class="img"><img src="/resources/images/check_event_ico.png" width="148" height="154" alt="" class="fl"/>
        </div>
        <div class="post_content">
            <table>
                <tr>
                    <td> Перевіряючий підрозділ:</td>
                    <td><b>${event.structure_catalog_name}</b></td>
                </tr>
                <tr>
                    <td> Результати перевірки</td>
                    <td>
                        <c:if test="${event.check_violation==0}">Порушень не виявлено</c:if>
                        <c:if test="${event.check_violation==1}">Порушення виявлені</c:if>
                    </td>
                </tr>
                <tr>
                    <td> Тип перевірки</td>
                    <td>
                        <c:if test="${event.check_type==0}">Планова</c:if>
                        <c:if test="${event.check_type==1}">Позапланова</c:if>
                    </td>
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
            </table>
            <p class="spec"><a href="/show_event/?id=${event.id}" class="rm">Див. деталі</a></p>
        </div>
        <div class="clr"></div>
    </div>

</c:forEach>
<%--paginator--%>
<c:if test="${empty eventList and pageid ==1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of NAN</small>
        <span>${page_id}</span>
    </p>
</c:if>
<c:if test="${empty eventList and pageid !=1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&laquo;</a>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id-1}</a><span>${page_id}</span>
    </p>
</c:if>

<c:if test="${not empty eventList and pageid !=1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&laquo;</a><a
            href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id-1}</a>
        <span>${page_id}</span> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id+1}</a> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&raquo;</a>
    </p>
</c:if>
<c:if test="${not empty eventList and pageid ==1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>

        <span>${page_id}</span> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id+1}</a>
        <a href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&raquo;</a>
    </p>
</c:if>