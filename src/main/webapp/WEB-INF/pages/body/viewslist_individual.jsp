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
        <h2><span>ІПН ${ufop.rntc}</span></h2>
        <p class="infopost">Id: ${ufop.id} Date reg: <span class="date">${ufop.datereg}</span> by <a href="#">${ufop.owner}</a> &nbsp;&nbsp;&bull;&nbsp;&nbsp;
            Filed under <a href="#">templates</a>, <a href="#">internet</a> &nbsp;&nbsp;&bull;&nbsp;&nbsp;

        <div class="clr"></div>
        <div class="img"><img src="/resources/images/ufop.png" width="148" height="154" alt="" class="fl"/>
        </div>
        <div class="post_content">
            <p>${ufop.last_name}<br />${ufop.first_name} ${ufop.patronymic_name} </p>
            <p class="spec"><a href="${viewmore}" class="rm">Read more</a></p>
        </div>
        <div class="clr"></div>
    </div>
</c:forEach>