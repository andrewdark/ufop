<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 31.01.2017
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="contacttList" items="${contacttList}">

</c:forEach>
<%--paginator--%>
<c:if test="${empty contacttList and pageid ==1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of NAN</small>
        <span>${page_id}</span>
    </p>
</c:if>
<c:if test="${empty contacttList and pageid !=1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}">&laquo;</a>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}">${page_id-1}</a><span>${page_id}</span>
    </p>
</c:if>

<c:if test="${not empty contacttList and pageid !=1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}">&laquo;</a><a
            href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}">${page_id-1}</a>
        <span>${page_id}</span> <a href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}">${page_id+1}</a> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}">&raquo;</a>
    </p>
</c:if>
<c:if test="${not empty contacttList and pageid ==1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>

        <span>${page_id}</span> <a href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}">${page_id+1}</a>
        <a href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}">&raquo;</a>
    </p>
</c:if>