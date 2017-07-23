<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 27.02.2017
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<style>
    .ui-autocomplete-loading {
        background: white right center no-repeat;
    }

    select {
        width: 300px; /* Ширина списка в пикселах */
    }

    input.new-input {
        width: 300px; /* Ширина списка в пикселах */
    }

    input.login-input {
        width: 560px; /* Ширина списка в пикселах */
    }

</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        function log(message) {
            $("<div>").text(message).prependTo("#log");
            $("#log").scrollTop(0);
        }

        $("#birds").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "/ajaxtest",
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function (data) {
                        response($.map(data, function (item) {
                            return {
                                label: item.username,
                                value: item.username,
                                id: item.id,
                                email: item.email,
                                structure: item.structure_link,
                                u_enable: item.enabled,
                                role_name: item.role_name
                            };


                        }))
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                log("Selected: " + ui.item.value + " aka " + ui.item.label);
                $(".idu").val(ui.item.id);
                $("#user_email_old").val(ui.item.email);
                $("#user_email_new").val(ui.item.email);
                $("#user_structure_old").val(ui.item.structure);
                $("#user_enabled_old").val(ui.item.u_enable);
                $("#user_role_old").val(ui.item.role_name);
            }
        });
    });
</script>
</head>
<body>
<div class="article">
    <h2><span>${title}</span></h2>
    <div class="clr"></div>
    <div class="post_content_wide">
        <div class="ui-widget">
            <br/>
            <label for="birds">Логін:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label>
            <input id="birds" class="login-input"/>
        </div>

        <div class="ui-widget" style="margin-top:2em; font-family:Arial">
            Результати:
            <div id="log" style="height: 200px; width: 620px; overflow: auto;" class="ui-widget-content"></div>
        </div>

        <br/><br/>


        <input id="user_id" class="idu" type="hidden" name="id"/>
        <table>
            <tr>
                <th>НАЗВА</th>
                <th>ПОТОЧНЕ ЗНАЧЕННЯ</th>
                <th>НОВЕ ЗНАЧЕННЯ</th>
            </tr>
            <tr>
                <td>ID користувача</td>
                <td><input type="text" class="idu" disabled/></td>
                <td><input type="text" class="idu new-input" disabled/></td>
            </tr>
            <tr>
                <td>E-mail користувача</td>
                <td><input type="text" id="user_email_old" disabled/></td>
                <td><input type="text" id="user_email_new" class="new-input"/></td>
            </tr>
            <tr>
                <td>Підрозділ</td>
                <td><input type="text" id="user_structure_old" disabled/></td>
                <td>
                    <select id="structure_treemark" name="structure_link">
                        <c:forEach var="item" items="${structureList}">
                            <option value="${item.treemark}">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>

            </tr>
            <tr>
                <td>Роль</td>
                <td><input type="text" id="user_role_old" disabled/></td>
                <td>
                    <select id="role_id" name="role_link">
                        <c:forEach var="item" items="${roleList}">
                            <option value="${item.id}">${item.role_name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Активований</td>
                <td><input type="text" id="user_enabled_old" disabled/></td>
                <td>
                    <select id="user_enabled_new" name="role_link">
                        <option value="true">Увімкнути</option>
                        <option value="false">Вимкнути</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td>
                    <button onclick='javascript:userstart();'>Внести зміни</button>
                </td>
            </tr>
        </table>

    </div>
    <div class="clr"></div>
</div>


