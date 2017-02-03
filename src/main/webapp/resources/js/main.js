$( function() {
    $( "#menu" ).menu();
    $( "#adminmenu" ).menu();
} );

$( function() {
    $( "#tabs" ).tabs();
} );

function looplocation() {
    var aj ="x="+$("#my_select option:selected").val();
    $("#setlocselection").load("/ajax", aj);
};
function looplocationtop() {
    var aj ="x="+$("#my_selecttop option:selected").val();
    //$("#setlocselection").load("/ajax", aj);
};
$( function() {
    $( "#datepicker" ).datepicker();
    $.datepicker.setDefaults(
        $.extend(
            {'dateFormat':'yy-mm-dd'},
            $.datepicker.regional['nl']
        )
    );
} );

function look(type){
    param=document.getElementById(type);
    if(param.style.display == "none") param.style.display = "block";
    else param.style.display = "none";
};
//popup location
$(document).ready(function(){
    //Скрыть PopUp при загрузке страницы
   // PopUpHide();
    $("#popup1").hide();
});
//Функция отображения PopUp
function PopUpShow(){
    $("#popup1").show();
}
//Функция скрытия PopUp
function PopUpHide(){
    $("#loc1").val($("#my_selecttop option:selected").val()+$("#level2").val());
    $("#popup1").hide();
}