<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 03.07.2017
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="comobjList" items="${comobjList}">
    <div class="article">
        <h2><span>Назва комерційного об'єкту: ${comobjList.obj_name}</span></h2>
        <p class="infopost">Id: ${comobjList.id} Дата реєстрації: <span class="date">${comobjList.datereg}</span>
            Реєстратор <a
                    href="#">${comobjList.creator_link}</a>
            &nbsp;&nbsp;&bull;&nbsp;&nbsp;
            Filed under <a href="#">templates</a>, <a href="#">internet</a> &nbsp;&nbsp;&bull;&nbsp;&nbsp;

        <div class="clr"></div>
        <div class="img"><img src="/resources/images/shopping-cart.png" width="148" height="154" alt="" class="fl"/>
        </div>
        <div class="post_content">
            <table>
                <tr>
                    <td> Тип об'єкту:</td>
                    <td><b>${comobjList.s_obj_type}</b></td>
                </tr>
                <tr>
                    <td> Група ризику:</td>
                    <td><b>${comobjList.s_degree_risk_link}</b></td>
                </tr>
                <tr>
                    <td> Група товару:</td>
                    <td>
                        <c:if test="${not empty comobjList.goodsList}">
                            <c:forEach items="${comobjList.goodsList}" var="goodsList">
                                <b>${goodsList.name}</b>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty comobjList.goodsList}">
                            <b>Група товару не вказана.</b>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Місце<br/>знаходження:</td>
                    <td>
                        <c:if test="${not empty comobjList.locationCatalog}">
                            <c:forEach items="${comobjList.locationCatalog}" var="fulladdress">
                                <b>${fulladdress.stype}:</b> ${fulladdress.name}<br/>
                            </c:forEach>
                            <c:if test="${comobjList.n_place_of_reg ne ''}">
                                <b>Дім:</b>&nbsp;${comobjList.n_place_of_reg}<br/>
                            </c:if>
                            <c:if test="${comobjList.b_place_of_reg ne ''}">
                                <b>Корпус:</b>&nbsp;${comobjList.b_place_of_reg}<br/>
                            </c:if>
                            <c:if test="${comobjList.f_place_of_reg ne ''}">
                                <b>Квартира:</b>&nbsp;${comobjList.f_place_of_reg}<br/>
                            </c:if>
                        </c:if>
                        <c:if test="${empty comobjList.locationCatalog}">
                           <b>Адреса не встановлена</b>
                        </c:if>
                    </td>
                </tr>
            </table>
            <p class="spec"><a href="/show_ufop?id=${comobjList.ufop_link}#tabs-2/" class="rm">Див.
                деталі</a></p>
        </div>
        <div class="clr"></div>
    </div>
</c:forEach>
<%--paginator--%>
<c:if test="${empty comobjList and pageid ==1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of NAN</small>
        <span>${page_id}</span>
    </p>
</c:if>
<c:if test="${empty comobjList and pageid !=1}">
    ${ex}
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&laquo;</a>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id-1}</a><span>${page_id}</span>
    </p>
</c:if>

<c:if test="${not empty comobjList and pageid !=1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>
        <a href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&laquo;</a><a
            href="/${viewslistlink}/${page_id-1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id-1}</a>
        <span>${page_id}</span> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id+1}</a>
        <a
                href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&raquo;</a>
    </p>
</c:if>
<c:if test="${not empty comobjList and pageid ==1}">
    <p class="pages">
        <small>Page ${page_id} of ${total_page}</small>

        <span>${page_id}</span> <a
            href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">${page_id+1}</a>
        <a href="/${viewslistlink}/${page_id+1}${getparam}/?param0=${param0}&param1=${param1}&param2=${param2}&param3=${param3}&param4=${param4}">&raquo;</a>
    </p>
</c:if>