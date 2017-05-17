$(function () {
    $("#menu").menu();
    $("#chiefmenu").menu();
    $("#adminmenu").menu();
});

$(function () {
    $("#tabs").tabs();
    $("#ufoptabs").tabs();
    $("#eventtabs").tabs();
    $("#searchtabs").tabs();
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
    $("#event_datepicker_begin").datepicker();
    $("#event_datepicker_end").datepicker();
    $("#service_date").datepicker();
    $("#plan_date").datepicker();
    $("#fact_date").datepicker();
    $("#filed_date").datepicker();
    $.datepicker.setDefaults(
        $.extend(
            {'dateFormat': 'yy-mm-dd'},
            $.datepicker.regional['uk']
        )
    );


});

function look(type) {
    param = document.getElementById(type);
    if (param.style.display == "none") {

        param.style.display = "block";
    }
    else param.style.display = "none";
};

function lookstructure(type,treemark) {
    var param = document.getElementById(type);
    var aj = "param1="+treemark;
    if (param.style.display == "none") {

        $("#"+type+"").load("/ajax_getUsersByStructureLink", aj);
        param.style.display = "block";
    }
    else param.style.display = "none";
};
//popup location
$(document).ready(function () {
    load_commobj();
    //Скрыть PopUp при загрузке страницы
    // PopUpHide();
    $("#popup1").hide();
    $("#popup2").hide();
    $("#popup3").hide();
    $("#popup4").hide();
    $("#popup5").hide();
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
            $("#a_place_of_reg").val($("#my_selecttop1 option:selected").val());
        } else {
            $("#a_place_of_reg").val("1");
        }
    }
    ;
    if (count_loc == 2) {
        if ($("#my_selecttop2 option:selected").val().length > 1) {
            $("#a_place_of_reg").val($("#my_selecttop2 option:selected").val());
        } else {
            $("#a_place_of_reg").val($("#my_selecttop1 option:selected").val());
        }
    }
    ;
    if (count_loc == 3) {
        if ($("#my_selecttop3 option:selected").val().length > 1) {
            $("#a_place_of_reg").val($("#my_selecttop3 option:selected").val());
        } else {
            $("#a_place_of_reg").val($("#my_selecttop2 option:selected").val());
        }
    }
    ;
    if (count_loc == 4) {
        if ($("#my_selecttop4 option:selected").val().length > 1) {
            $("#a_place_of_reg").val($("#my_selecttop4 option:selected").val());
        } else {
            $("#a_place_of_reg").val($("#my_selecttop3 option:selected").val());
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
//----------------BASIC GROUP OF GOODS ADD-----------------------//
function GoodsPopUpShow() {
    $("#popup4").show();
}
//Функция скрытия PopUp
var count_goods = 1;
function GoodsPopUpHide() {

    if (count_goods == 1) $("#goodsname").val($("#my_selecttop1 option:selected").val());
    if (count_goods == 2) {
        if ($("#my_selecttop2 option:selected").val().length > 1) {
            $("#goodsname").val($("#my_selecttop2 option:selected").val());
        } else {
            $("#goodsname").val($("#my_selecttop1 option:selected").val());
        }
    }
    ;
    if (count_goods == 3) {
        if ($("#my_selecttop3 option:selected").val().length > 1) {
            $("#goodsname").val($("#my_selecttop3 option:selected").val());
        } else {
            $("#goodsname").val($("#my_selecttop2 option:selected").val());
        }
    }
    ;
    if (count_goods == 4) {
        if ($("#my_selecttop4 option:selected").val().length > 1) {
            $("#goodsname").val($("#my_selecttop4 option:selected").val());
        }
        else {
            $("#goodsname").val($("#my_selecttop3 option:selected").val());
        }

    }
    ;
    if (count_goods == 5) {
        if ($("#my_selecttop5 option:selected").val().length > 1) {
            $("#goodsname").val($("#my_selecttop5 option:selected").val());
        }
        else {
            $("#goodsname").val($("#my_selecttop4 option:selected").val());
        }

    }
    ;
    $("#popup4").hide();
}
function loopgoodsdown(level) {
    count_goods = level;
    if (level == 2) {
        var aj = "treemark=" + $("#my_selecttop1 option:selected").val() + "&nlevel=2";
        $("#GoodsType2").load("/ajax_select_goods", aj);
        $("#GoodsType3").html("");
        $("#GoodsType4").html("");
    }
    if (level == 3) {
        var aj = "treemark=" + $("#my_selecttop2 option:selected").val() + "&nlevel=3";
        $("#GoodsType3").load("/ajax_select_goods", aj);
        $("#GoodsType4").html("");
    }
    if (level == 4) {
        var aj = "treemark=" + $("#my_selecttop3 option:selected").val() + "&nlevel=4";
        $("#GoodsType4").load("/ajax_select_goods", aj);
    }
    if (level == 5) {
        var aj = "treemark=" + $("#my_selecttop4 option:selected").val() + "&nlevel=5";
        $("#GoodsType5").load("/ajax_select_goods", aj);
    }
};
//----------------ARTICLES LAW ADD-----------------------//
function ArticlesPopUpShow() {
    $("#popup5").show();
}
//Функция скрытия PopUp
var count_articles = 1;
function ArticlesPopUpHide() {

    if (count_articles == 1) $("#articles").val($("#my_selecttop1 option:selected").val());

    if (count_articles == 2) {
        if ($("#my_selecttop2 option:selected").val().length > 1) {
            $("#articles").val($("#my_selecttop2 option:selected").val());
        } else {
            $("#articles").val($("#my_selecttop1 option:selected").val());
        }
    }
    ;

    if (count_articles == 3) {
        if ($("#my_selecttop3 option:selected").val().length > 1) {
            $("#articles").val($("#my_selecttop3 option:selected").val());
        } else {
            $("#articles").val($("#my_selecttop2 option:selected").val());
        }
    }
    ;
    if (count_articles == 4) {
        if ($("#my_selecttop4 option:selected").val().length > 1) {
            $("#articles").val($("#my_selecttop4 option:selected").val());
        }
        else {
            $("#articles").val($("#my_selecttop3 option:selected").val());
        }

    }
    ;
    if (count_articles == 5) {
        if ($("#my_selecttop5 option:selected").val().length > 1) {
            $("#articles").val($("#my_selecttop5 option:selected").val());
        }
        else {
            $("#articles").val($("#my_selecttop4 option:selected").val());
        }

    }
    ;

    $("#popup5").hide();
}
function looparticlesdown(level) {
    count_articles = level;
    if (level == 2) {
        var aj = "treemark=" + $("#my_selecttop1 option:selected").val() + "&nlevel=2";
        $("#ArticlesType2").load("/ajax_select_articles", aj);
        $("#ArticlesType3").html("");
        $("#ArticlesType4").html("");
    }
    if (level == 3) {
        var aj = "treemark=" + $("#my_selecttop2 option:selected").val() + "&nlevel=3";
        $("#ArticlesType3").load("/ajax_select_articles", aj);
        $("#ArticlesType4").html("");
    }
    if (level == 4) {
        var aj = "treemark=" + $("#my_selecttop3 option:selected").val() + "&nlevel=4";
        $("#ArticlesType4").load("/ajax_select_articles", aj);
    }
    if (level == 5) {
        var aj = "treemark=" + $("#my_selecttop4 option:selected").val() + "&nlevel=5";
        $("#ArticlesType5").load("/ajax_select_articles", aj);
    }
};
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

//-----------comm_obj_sheet--------------
function load_commobj() {
    $("#add_comm_obj").load("/addcommobj #area1");
}

function load_ufop() {
    $("#load_ufop_point").load("/addufop #this_ufop_from_ajax");

}
//-----------DATA of UFOP (Registration)-------------------//
function pPass() {
    $("#ufop_name_1").show();
    $("#ufop_name_2").hide();
    $("#ufop_pass_1").show();
    $("#passinfo").show();
}
function uPass() {
    $("#ufop_name_2").show();
    $("#ufop_name_1").hide();
    $("#ufop_pass_1").hide();
    $("#passinfo").hide();
}
function isViolations0() {
    $(".is_violation").hide();

}
function isViolations1() {
    $(".is_violation").show();
}
function isFiled_on_action0() {
    $(".is_filed_on_action").hide();
}
function isFiled_on_action1() {
    $(".is_filed_on_action").show();
}