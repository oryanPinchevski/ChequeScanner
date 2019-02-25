$(document).ready(function(){
    $("#clientNameByPicBtn").click(function() {
        getClientNameByPic();
    });
});

function getClientNameByPic() {
    var fileList = $("#myFile")[0].files;

    if (fileList.length > 0) {
        var file = fileList[0];

        if (file !== null) {
            var formData = new FormData();
            formData.append("file", file);

            $.ajax({
                type: "POST",
                url: "chequeScanner/",
                cache: false,
                processData: false,
                contentType: false,
                data: formData,
            }).done(function (res) {
                console.log("Data: " + res + "\nStatus: " + status);

                $("#clientNameLbl").text(res);
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            });
        }
    }
}