$(function () {
    $("#menu").menu();
    $("#adminmenu").menu();
});

$(function () {
    $("#tabs").tabs();
});
//ajax is used here
function looplocation() {
    var aj = "x=" + $("#my_select option:selected").val();
    $("#setlocselection").load("/ajax", aj);
};
function inject_kved() {
   var param1=$("#fopid").val();
   var param2=$("#kvedname").val();
    var param = "param1="+param1+"&param2="+param2;
    $("#kved_message").load("/ajax_add_kved",param);
    // $.ajax({url:"/ajax_add_kved",
    //     data:param,
    //     success:function(result){
    //         $("#kved_message").html(result);
    //
    //     }
    // });


}
function looplocationtop() {
   // var aj = "x=" + $("#my_selecttop option:selected").val();
   // $("#setloc_popup").load("/ajax_select_loc", aj);
    // $.ajax({url:"/ajax_select_loc",
    //
    //     success:function(result){
    //         $("#setloc_popup").html(result)}
    // });
};
//func search location DOWN
function looplocationdown(level) {
    if(level==2){
        var aj = "treemark=" + $("#my_selecttop1 option:selected").val()+"&nlevel=2";
        $("#LocationType2").load("/ajax_select_loc", aj);
        $("#LocationType3").html("");
    }
    if(level==3){
        var aj = "treemark=" + $("#my_selecttop2 option:selected").val()+"&nlevel=3";
        $("#LocationType3").load("/ajax_select_loc", aj);
    }
    if(level==4){
        var aj = "treemark=" + $("#my_selecttop3 option:selected").val()+"&nlevel=4";
        $("#LocationType4").load("/ajax_select_loc", aj);
    }
    // $.ajax({url:"/ajax_select_loc",
    //
    //     success:function(result){
    //         $("#setloc_popup").html(result)}
    // });
};

$(function () {
    $("#datepicker").datepicker();
    $.datepicker.setDefaults(
        $.extend(
            {'dateFormat': 'yy-mm-dd'},
            $.datepicker.regional['nl']
        )
    );
});

function look(type) {
    param = document.getElementById(type);
    if (param.style.display == "none") param.style.display = "block";
    else param.style.display = "none";
};
//popup location
$(document).ready(function () {
    //Скрыть PopUp при загрузке страницы
    // PopUpHide();
    $("#popup1").hide();
    $("#setContact_link_popup").hide();
    document.getElementById("contact_link").onkeypress = function(e) {
        e = e || event;

        if (e.ctrlKey || e.altKey || e.metaKey) return;

        var chr = getChar(e);

        // с null надо осторожно в неравенствах,
        // т.к. например null >= '0' => true
        // на всякий случай лучше вынести проверку chr == null отдельно
        if (chr == null) return;

        if (chr < '0' || chr > '9') {
            return false;
        }
    }
    function getChar(event) {
        if (event.which == null) {
            if (event.keyCode < 32) return null;
            return String.fromCharCode(event.keyCode) // IE
        }

        if (event.which != 0 && event.charCode != 0) {
            if (event.which < 32) return null;
            return String.fromCharCode(event.which) // остальные
        }

        return null; // специальная клавиша
    }
});
//Функция отображения PopUp
function PopUpShow() {
    $("#popup1").show();
}
//Функция скрытия PopUp
function PopUpHide() {
    $("#loc1").val($("#my_selecttop1 option:selected").val() );
    $("#popup1").hide();
}

function SetContact_link_Show() {
    $("#setContact_link_popup").show().focus();

}
function SetContact_link_Hide() {
    $("#setContact_link_popup").hide();
}

