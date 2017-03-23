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
            ${goodsOfCommObj.id} -
                ${goodsOfCommObj.comm_obj_link}
        </div>
        <form:form action="/addgoodspost" method="post">
            <form:hidden path="comm_obj_link"/>
            <table>
                <tr>
                    <td><form:label path="goods_catalog_link">Віберіть групу товарів</form:label></td>
                    <td><form:input id="goodsname" path="goods_catalog_link" onclick="javascript:GoodsPopUpShow();"/></td>
                    <td><form:errors path="goods_catalog_link"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Додати"/></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <form action="/searchufop" method="get">
            <input type="submit" value="Завершити"/>
        </form>
    </div>
    <div class="clr"></div>
</div>
<div class="b-popup" id="popup4">
    ${ex}<br/>
    <div class="b-popup-content" id="setgoods_popup">
        <select id="my_selecttop1" name="my_selecttop" onchange="loopgoodsdown(2)">
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