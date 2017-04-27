<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15.03.2017
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <div id="goods_list">
            <c:forEach items="${checkingGoods}" var="checkingGoods">
                <li/>
                ${checkingGoods.s_goods_catalog_link}
                <a href="/deletecheckinggroupofgoods?id=${checkingGoods.id}&EventId=${checkingGoods.check_event_link}">Видалити</a><br/>
            </c:forEach>
        </div>
        <form:form action="/addcheckgoodspost" method="post">
            <form:hidden path="check_event_link"/>
            <form:hidden path="nav"/>
            <table>
                <tr>
                    <td><form:label path="goods_catalog_link">Віберіть групу товарів</form:label></td>
                    <td><form:input id="goodsname" path="goods_catalog_link"
                                    onclick="javascript:GoodsPopUpShow();"/></td>
                    <td>
                        <span class="error">
                            <form:errors path="goods_catalog_link"/>
                            <form:errors path="check_event_link"/>
                        </span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                            <%-- form:checkbox path="additionalinformation"/>Завершити --%>
                    </td>
                    <td><input type="submit" value="Додати"/></td>
                </tr>
            </table>
        </form:form>
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
                    <td>Тип</td>
                    <td>
                        <c:if test="${checkEvent.check_type==0}">Планова</c:if>
                        <c:if test="${checkEvent.check_type==1}">Позапланова</c:if>
                    </td>
                </tr>
                <tr>
                    <td>Опис</td>
                    <td>${checkEvent.event_result}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="clr"></div>
</div>
<div class="b-popup" id="popup4">
    ${ex}<br/>
    <div class="b-popup-content" id="setgoods_popup">
        <select id="my_selecttop1" name="my_selecttop" onchange="loopgoodsdown(2)">
            <option value=""></option>
            <c:forEach items="${goodsTop}" var="goodsTop">
                <option value="${goodsTop.treemark}">${goodsTop.name}</option>
            </c:forEach>
        </select><br/>
        <div id="GoodsType2"></div>
        <div id="GoodsType3"></div>
        <div id="GoodsType4"></div>
        <div id="GoodsType5"></div>
        <a href="javascript:GoodsPopUpHide()">Додати групу товарів</a>
    </div>
</div>