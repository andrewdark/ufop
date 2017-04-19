<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 18.04.2017
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;"><h2>${title}</h2></div>
<h3>${articles.head1}</h3>
<h4>${articles.head2}</h4>

<table>
    <tr>
        <td><b>Назва статті:</b></td>
        <td>${articles.caption}</td>
    </tr>
    <tr>
        <td><b>Текст статті:</b></td>
        <td>${articles.article}</td>
    </tr>
    <c:if test="${not empty articles.punishable}">
        <tr>
            <td><b>Застосовується:</b></td>
            <td>${articles.punishable}</td>
        </tr>
    </c:if>
    <tr>
        <td><b>Розмір&nbsp;штрафу:</b></td>
        <td>${articles.penalty}</td>
    </tr>
</table>
