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