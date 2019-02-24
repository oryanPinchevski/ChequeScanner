$(document).ready(function(){
    $("#clientNameByPicBtn").click(function() {
        getClientNameByPic();
     });
});

function getClientNameByPic() {
    var picName = $("#clientNamePic").attr("src");

    $.ajax({
        type: "GET",
        url: "chequeScanner/" + picName,
        dataType: "text"
    }).done(function (res) {
        console.log("Data: " + res + "\nStatus: " + status);

        $("#clientNameLbl").text(res);
    }).fail(function (jqXHR, textStatus, errorThrown) {
      alert(jqXHR.responseText);
    });
}