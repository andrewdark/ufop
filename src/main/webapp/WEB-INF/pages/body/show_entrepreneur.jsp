<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.01.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="tabs">
    <ul>
        <li><a href="#tabs-1">ФОП</a></li>
        <li><a href="#tabs-2">КВЕД</a></li>
        <li><a href="#tabs-3">Комерційні об'єкти</a></li>
        <li><a href="#tabs-4">Перевірки</a></li>
        <li><a href="#tabs-5">Контактна інформація</a></li>
    </ul>

    <div id="tabs-1">
        <div class="container">
            <div class="box">
                <div><b>Відомості про фізичну особу підприємця</b></div>
                <div class="ta_r"><c:if test="${ie.if_pdv}">
                    <font color="green">Платник ПДВ</font>
                </c:if>
                    <c:if test="${not ie.if_pdv}">
                        <font color="red">Платник&nbsp;єдиного&nbsp;податку</font>
                    </c:if>
                </div>

            </div>
        </div>

        <p><span class="show_info_title">Ідентифікаційний номер у Державному реєстрі фізичних осіб</span><br/>
            ${ie.rntc}</p>
        <p><span class="show_info_title">Прізвище, ім'я, по батькові</span><br/>
            ${ie.contact.last_name} ${ie.contact.first_name} ${ie.contact.patronymic_name}</p>
        <p><span class="show_info_title">Серія і номер паспорта</span><br/>
            ${ie.series_of_passport}&nbsp;${ie.number_of_passport}</p>
        <p><span class="show_info_title">Місце реєстрації</span><br/>
            <c:if test="${ie.a_place_of_reg ne ''}">
                <c:forEach items="${fulladdress}" var="fulladdress">
                    <br/>${fulladdress.stype}: ${fulladdress.name}
                </c:forEach>
                ,&nbsp;${ie.n_place_of_reg}
            </c:if>
        </p>
    </div>
    <div id="tabs-2">
        <p>Перелік КВЕД</p>
        <table>
            <c:forEach items="${kveds}" var="kveds">
                <tr>
                    <td>${kveds.kved_catalog_label}</td>
                    <td>${kveds.kved_catalog_name}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="tabs-3">

        <table>
            <caption>Перелік комерційних об'єктів</caption>
            <tr>
                <th>НАЗВА</th>
                <th>ТИП</th>
                <th>АДРЕСА</th>
                <th>ПРИМІТКА</th>

            </tr>
            <c:forEach items="${co}" var="co">
                <tr>
                     <td>${co.obj_name}</td>
                     <td>${co.s_obj_type}</td>
                    <td>
                        <c:forEach items="${co.obj_loc}" var="fulladdress">
                            <br/>${fulladdress.stype}: ${fulladdress.name}
                        </c:forEach>
                        ,&nbsp;${co.n_obj_location}

                    </td>
                    <td>${co.description}</td>
                </tr>


            </c:forEach>
        </table>
    </div>
    <div id="tabs-4">
        <p>Перелік планових та позапланових перевірок</p>
        ${ci}
    </div>
    <div id="tabs-5">
        <p>Перелік контактних осіб</p>
        ${ie.contact.id}<br/>
        Прізвище: ${ie.contact.last_name}<br/>
        Ім'я: ${ie.contact.first_name}<br/>
        <c:if test="${not empty ie.contact.patronymic_name and ie.contact.patronymic_name ne ''}">
            По батькові:    ${ie.contact.patronymic_name}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.rntc and ie.contact.rntc ne ''}">
            ІПН: ${ie.contact.rntc}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.series_of_passport and ie.contact.series_of_passport ne ''}">
            Серія та № паспорта: ${ie.contact.series_of_passport} &nbsp;
        </c:if>
        <c:if test="${not empty ie.contact.number_of_passport and ie.contact.number_of_passport ne ''}">
            ${ie.contact.number_of_passport}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.a_stay_address and ie.contact.a_stay_address ne ''}">
            <c:forEach items="${stayaddress}" var="stayaddress">
                <br/>${stayaddress.stype}: ${stayaddress.name}
            </c:forEach>
            ,&nbsp;
        </c:if>
        <c:if test="${not empty ie.contact.n_stay_address and ie.contact.n_stay_address ne ''}">
            ${ie.contact.n_stay_address}<br/><br/>
        </c:if>
        <c:if test="${not empty ie.contact.tel and ie.contact.tel ne ''}">
            Тел: ${ie.contact.tel}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.fax and ie.contact.fax ne ''}">
            Факс: ${ie.contact.fax}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.email and ie.contact.email ne ''}">
            Е-пошта: ${ie.contact.email}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.birthday and ie.contact.birthday ne ''}">
            Дата народження:  ${ie.contact.birthday}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.organization and ie.contact.organization ne ''}">
            Місце роботи: ${ie.contact.organization}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.position and ie.contact.position ne ''}">
            Посада: ${ie.contact.position}<br/>
        </c:if>
        <c:if test="${not empty ie.contact.description and ie.contact.description ne ''}">
            Додаткова інформація: ${ie.contact.description}<br/>
        </c:if>
    </div>
</div>