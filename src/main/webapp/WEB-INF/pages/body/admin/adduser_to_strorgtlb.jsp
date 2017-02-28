<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        function log( message ) {
            $( "<div>" ).text( message ).prependTo( "#log" );
            $( "#log" ).scrollTop( 0 );
        }

        $( "#birds" ).autocomplete({
            source: function( request, response ) {
                $.ajax( {
                    url: "/ajaxtest",
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function( data ) {
                        response( data ) ;
                    }
                } );
            },
            minLength: 1,
            select: function( event, ui ) {
                log( "Selected: " + ui.item.username + " aka " + ui.item.id );
            }
        } );
    } );
</script>
</head>
<body>

<div class="ui-widget">
    <label for="birds">Birds: </label>
    <input id="birds">
</div>

<div class="ui-widget" style="margin-top:2em; font-family:Arial">
    Result:
    <div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
</div>




<form:form method="post" action="">
    <table width="100%">
        <tr>
            <td><form:label path="treemark">Прізвище:</form:label></td>
            <td><form:input htmlEscape="true" path="treemark" class="form-control" maxlength="128"/></td>
            <td><span class="error"><form:errors path="treemark"/></span></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit"><img src="resources/images/add.jpg"/></button>
            </td>
            <td></td>
        </tr>
    </table>



</form:form>
