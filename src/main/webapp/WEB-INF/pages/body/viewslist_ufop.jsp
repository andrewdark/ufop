<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 31.01.2017
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="ufop" items="${ufop}">
    <div class="article">
        <h2><span>ІПН ${ufop.ufop_code}</span></h2>
        <p class="infopost">Id: ${ufop.id} Date reg: <span class="date">${ufop.datereg}</span> by <a
                href="#">${ufop.screator_link}</a>
            &nbsp;&nbsp;&bull;&nbsp;&nbsp;
            Filed under <a href="#">templates</a>, <a href="#">internet</a> &nbsp;&nbsp;&bull;&nbsp;&nbsp;

        <div class="clr"></div>
        <div class="img"><img src="/resources/images/ufop.png" width="148" height="154" alt="" class="fl"/>
        </div>
        <div class="post_content">
            <p>
                <c:if test="${ufop.ufop_is == 0}">
                    ПІБ суб'єкта:
                </c:if>
                <c:if test="${ufop.ufop_is == 1}">
                    Назва підприємства:
                </c:if>
                <b>${ufop.ufop_name} </b>
            </p>
            <p>Тип суб'єкта:
                <b>
                    <c:if test="${ufop.ufop_is == 0}">
                        Фізична особа - підприємець
                    </c:if>
                    <c:if test="${ufop.ufop_is == 1}">
                        Юридична особа
                    </c:if>
                </b>
            </p>

            <p class="spec"><a href="/show_ufop/?id=${ufop.id}" class="rm">Див. деталі</a></p>
        </div>
        <div class="clr"></div>
    </div>
</c:forEach>
<%--paginator--%>
<c:if test="${empty ufop and pageid ==1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of NAN</small>
        <span>${page_id}</span>
    </p>
</c:if>
<c:if test="${empty ufop and pageid !=1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistu}/${page_id-1}${getparam}/?id=${id}">&laquo;</a>
        <a href="/${viewslistu}/${page_id-1}${getparam}/?id=${id}">${page_id-1}</a><span>${page_id}</span>
    </p>
</c:if>

<c:if test="${not empty ufop and pageid !=1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistu}/${page_id-1}${getparam}/?id=${id}">&laquo;</a><a
            href="/${viewslistu}/${page_id-1}${getparam}/?id=${id}">${page_id-1}</a>
        <span>${page_id}</span> <a href="/${viewslistu}/${page_id+1}${getparam}/?id=${id}">${page_id+1}</a> <a
            href="/${viewslistu}/${page_id+1}${getparam}/?id=${id}">&raquo;</a>
    </p>
</c:if>
<c:if test="${not empty ufop and pageid ==1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>

        <span>${page_id}</span> <a href="/${viewslistu}/${page_id+1}${getparam}/?id=${id}">${page_id+1}</a>
        <a href="/${viewslistu}/${page_id+1}${getparam}/?id=${id}">&raquo;</a>
    </p>
</c:if>