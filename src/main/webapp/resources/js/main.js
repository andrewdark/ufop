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

function looplocationtop() {
    // var aj = "x=" + $("#my_selecttop option:selected").val();
    // $("#setloc_popup").load("/ajax_select_loc", aj);
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
    $("#popup2").hide();
    $("#popup3").hide();
    $("#setContact_link_popup").hide();
    document.getElementById("contact_link").onkeypress = function (e) {
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
//--------------------LOCATION ADD---------------------------------
var count_loc = 1;

//func search location DOWN
function looplocationdown(level) {
    count_loc = level;
    if (level == 2) {
        var aj = "treemark=" + $("#my_selecttop1 option:selected").val() + "&nlevel=2";
        $("#LocationType2").load("/ajax_select_loc", aj);
        $("#LocationType3").html("");
        $("#LocationType4").html("");
    }
    if (level == 3) {
        var aj = "treemark=" + $("#my_selecttop2 option:selected").val() + "&nlevel=3";
        $("#LocationType3").load("/ajax_select_loc", aj);
        $("#LocationType4").html("");
    }
    if (level == 4) {
        var aj = "treemark=" + $("#my_selecttop3 option:selected").val() + "&nlevel=4";
        $("#LocationType4").load("/ajax_select_loc", aj);
    }
    if (level == 5) {
        var aj = "treemark=" + $("#my_selecttop4 option:selected").val() + "&nlevel=5";
        $("#LocationType4").load("/ajax_select_loc", aj);
    }
};

//Функция отображения PopUp
function LocPopUpShow() {
    $("#popup1").show();
}
//Функция скрытия PopUp
function LocPopUpHide() {
    if (count_loc == 1) {
        if ($("#my_selecttop1 option:selected").val().length > 1) {
            $("#loc1").val($("#my_selecttop1 option:selected").val());
        } else {
            $("#loc1").val("1");
        }
    }
    ;
    if (count_loc == 2) {
        if ($("#my_selecttop2 option:selected").val().length > 1) {
            $("#loc1").val($("#my_selecttop2 option:selected").val());
        } else {
            $("#loc1").val($("#my_selecttop1 option:selected").val());
        }
    }
    ;
    if (count_loc == 3) {
        if ($("#my_selecttop3 option:selected").val().length > 1) {
            $("#loc1").val($("#my_selecttop3 option:selected").val());
        } else {
            $("#loc1").val($("#my_selecttop2 option:selected").val());
        }
    }
    ;
    if (count_loc == 4) {
        if ($("#my_selecttop4 option:selected").val().length > 1) {
            $("#loc1").val($("#my_selecttop4 option:selected").val());
        } else {
            $("#loc1").val($("#my_selecttop3 option:selected").val());
        }
    }
    ;
    $("#popup1").hide();
}

function SetContact_link_Show() {
    $("#setContact_link_popup").show().focus();

}
function SetContact_link_Hide() {
    $("#setContact_link_popup").hide();
}
// ------------------KVED Add------------------------- //
//Функция отображения PopUp
function KvedPopUpShow() {
    $("#popup3").show();
}
//Функция скрытия PopUp
var count_kved = 1;
function KvedPopUpHide() {
    //alert(count_kved);
    if (count_kved == 1) $("#kvedname").val($("#my_selecttop1 option:selected").val());
    if (count_kved == 2) {
        if ($("#my_selecttop2 option:selected").val().length > 1) {
            $("#kvedname").val($("#my_selecttop2 option:selected").val());
        } else {
            $("#kvedname").val($("#my_selecttop1 option:selected").val());
        }
    }
    ;
    if (count_kved == 3) {
        if ($("#my_selecttop3 option:selected").val().length > 1) {
            $("#kvedname").val($("#my_selecttop3 option:selected").val());
        } else {
            $("#kvedname").val($("#my_selecttop2 option:selected").val());
        }
    }
    ;
    if (count_kved == 4) {
        if ($("#my_selecttop4 option:selected").val().length > 1) {
            $("#kvedname").val($("#my_selecttop4 option:selected").val());
        }
        else {
            $("#kvedname").val($("#my_selecttop3 option:selected").val());
        }

    }
    ;
    if (count_kved == 5) {
        if ($("#my_selecttop5 option:selected").val().length > 1) {
            $("#kvedname").val($("#my_selecttop5 option:selected").val());
        }
        else {
            $("#kvedname").val($("#my_selecttop4 option:selected").val());
        }

    }
    ;
    $("#popup3").hide();
}
function SetKved_link_Show() {
    $("#setContact_link_popup").show().focus();

}
function SetKved_link_Hide() {
    $("#setContact_link_popup").hide();
}
function loopkveddown(level) {
    count_kved = level;
    if (level == 2) {
        var aj = "treemark=" + $("#my_selecttop1 option:selected").val() + "&nlevel=2";
        $("#KvedType2").load("/ajax_select_kved", aj);

        $("#KvedType3").html("");
        $("#KvedType4").html("");
    }
    if (level == 3) {
        var aj = "treemark=" + $("#my_selecttop2 option:selected").val() + "&nlevel=3";
        $("#KvedType3").load("/ajax_select_kved", aj);
        $("#KvedType4").html("");
    }
    if (level == 4) {
        var aj = "treemark=" + $("#my_selecttop3 option:selected").val() + "&nlevel=4";
        $("#KvedType4").load("/ajax_select_kved", aj);
    }
    if (level == 5) {
        var aj = "treemark=" + $("#my_selecttop4 option:selected").val() + "&nlevel=5";
        $("#KvedType5").load("/ajax_select_kved", aj);
    }
};
function inject_kved() {
    var param1 = $("#fopid").val();
    var param2 = $("#kvedname").val();
    var param = "param1=" + param1 + "&param2=" + param2;
    $("#kved_message").load("/ajax_add_kved", param);

}
// ------------------COMMERCIAL Obj Add------------------------- //
function inject_co() {
    var p1 = $("#co_input01").val();
    var p2 = $("#loc1").val();
    var p3 = $("#co_input03").val();
    var p4 = $("#co_select01 option:selected").val();
    var p5 = $("#co_input04").val();

    var param = "p1=" + p1 + "&p2=" + p2 + "&p3=" + p3 + "&p4=" + p4 + "&p5=" + p5;
    $("#co_message").load("/ajax_add_co", param);
}
