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
                    ${title_name}: <b>${ufop.ufop_name} </b>
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
        <small>Page ${page_id} of NAN</small>
        <a href="/viewslisti/${page_id-1}">&laquo;</a><a
            href="/viewslisti/${page_id-1}">${page_id-1}</a><span>${page_id}</span>
    </p>
</c:if>

<c:if test="${not empty ufop and pageid !=1}">
    <p class="pages">
        <small>Page ${page_id} of NAN</small>
        <a href="/viewslisti/${page_id-1}">&laquo;</a><a href="/viewslisti/${page_id-1}">${page_id-1}</a>
        <span>${page_id}</span> <a href="/viewslisti/${page_id+1}">${page_id+1}</a> <a
            href="/viewslisti/${page_id+1}">&raquo;</a>
    </p>
</c:if>
<c:if test="${not empty ufop and pageid ==1}">
    <p class="pages">
        <small>Page ${page_id} of NAN</small>

        <span>${page_id}</span> <a href="/viewslisti/${page_id+1}">${page_id+1}</a> <a
            href="/viewslisti/${page_id+1}">&raquo;</a>
    </p>
</c:if>