<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.01.2017
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content">
        <c:set var="bool" value="false"/>
        <c:if test="${empty sendContact.rntc}">
           <c:set var="bool" value="false"/>
       </c:if>

        <form:form action="${pageContext.servletContext.contextPath}/individualentrepreneurpost" method="post">
            <table>
                <tr>
                    <td><form:label path="contact_link">Id фіз. Особи:</form:label></td>
                    <td><form:input htmlEscape="true" path="contact_link" value="${sendContact.id}" /></td>
                    <td><span class="error"><form:errors path="contact_link"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="rntc">ІПН фізичної особи:</form:label></td>
                    <td><form:input htmlEscape="true" path="rntc" class="form-control" maxlength="10" value="${sendContact.rntc}"/></td>
                    <td><span class="error"><form:errors path="rntc"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="series_of_passport">Код\серія паспорту:</form:label></td>
                    <td>
                        <form:input htmlEscape="true" path="series_of_passport" class="form-control" size="2"
                                    maxlength="2" />
                        <form:input htmlEscape="true" path="number_of_passport" class="form-control" size="10"
                                    maxlength="10" />
                    </td>
                    <td>
                        <span class="error"><form:errors path="series_of_passport"/></span>
                        <span class="error"><form:errors path="number_of_passport"/></span>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="a_place_of_reg" >Адреса реєстрації:</form:label></td>
                    <td><form:input id="loc2" htmlEscape="true" path="a_place_of_reg" class="form-control" /></td>
                    <td><span class="error"><form:errors path="a_place_of_reg"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="n_place_of_reg">Номер будівлі:</form:label></td>
                    <td><form:input htmlEscape="true" path="n_place_of_reg" class="form-control" maxlength="10"/></td>
                    <td><span class="error"><form:errors path="n_place_of_reg"/></span></td>
                </tr>
                <tr>
                    <td><form:label path="risk_group">Ступінь ризику:</form:label></td>
                    <td><form:select path="risk_group" items="${risk}" /></td>
                    <td></td>
                </tr>
                <tr>
                    <td><form:label path="if_pdv">Платник ПДВ:</form:label></td>
                    <td><form:checkbox path="if_pdv"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit"><img src="resources/images/next_step.jpg"/></button>
                    </td>
                    <td></td>
                </tr>
            </table>
        </form:form>
        <a onclick="look('hide1'); return false;" href="#">Довідка</a>
        <div id="hide1" style="display: none;">
            <li />Заповніть обов'язково поля ID та код ІПН.<br />
            <li />Серію паспорта заповнюємо латинськими буквами
        </div>
    </div>
    <div class="clr"></div>
</div>
