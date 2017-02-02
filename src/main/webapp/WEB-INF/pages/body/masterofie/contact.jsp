<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <form:form action="${pageContext.servletContext.contextPath}/addContactpost" method="post">
            <table>
                <tr>
                    <td><form:label path="last_name">Прізвище:</form:label></td>
                    <td><form:input htmlEscape="true" path="last_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error" ><form:errors path="last_name" /></span></td>
                </tr>
                <tr>
                    <td><form:label path="first_name">Ім'я:</form:label></td>
                    <td><form:input htmlEscape="true" path="first_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error" ><form:errors path="first_name" /></span></td>
                </tr>
                <tr>
                    <td><form:label path="patronymic_name">По батькові:</form:label></td>
                    <td><form:input htmlEscape="true" path="patronymic_name" class="form-control" maxlength="128"/></td>
                    <td><span class="error" ><form:errors path="patronymic_name" /></span></td>
                </tr>
                <tr>
                    <td><form:label path="rntc">ІПН фізичної особи:</form:label></td>
                    <td><form:input htmlEscape="true" path="rntc" class="form-control" maxlength="10"/></td>
                    <td><span class="error" ><form:errors path="rntc" /></span></td>
                </tr>
                <tr>
                    <td><form:label path="series_of_passport">Код\серія паспорту:</form:label></td>
                    <td>
                        <form:input htmlEscape="true" path="series_of_passport" class="form-control" size="2" maxlength="2"/>
                        <form:input htmlEscape="true" path="number_of_passport" class="form-control" size="10" maxlength="10"/>
                    </td>
                    <td>
                        <span class="error" ><form:errors path="series_of_passport" /></span>
                        <span class="error" ><form:errors path="number_of_passport" /></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><button type="submit"><img src="resources/images/next_step.jpg"/></button></td>
                    <td></td>
                </tr>
            </table>
        </form:form>
    </div>
    <div class="clr"></div>
</div>
