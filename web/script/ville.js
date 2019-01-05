
load();
function load() {
    $.ajax({
        url: "VilleController",
        type: "GET",
        cache: false,
        data: {op: 'load'},
        success: function (data, textStatus, jqXHR) {
            remplir(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error")
        }
    });
}

$('#mTable').on("click", ".operation", function () {
    if ($(this).html() == "modifier") {
        let nom = $(this).parent().parent().children().next().html();
        $('#update').val($(this).attr("indice"));
        $('#nom').val(nom);
        $('#save').html('Modifier');
    } else if ($(this).html() == "supprimer") {
        $.ajax({
            url: "VilleController",
            type: "POST",
            cache: false,
            data: {op: 'delete', id: $(this).attr("indice")},
            success: function (data, textStatus, jqXHR) {
                remplir(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });
    }
});


$("#save").click(()=>{
    let nom = $('#nom').val();
    let update_id = $('#update').val();
    if($('#save').html() == "Ajouter"){
      $.ajax({
            url: "VilleController",
            type: "POST",
            cache: false,
            data: {op: 'add', nom:nom},
            success: function (data, textStatus, jqXHR) {
                remplir(data); 
                clearFields();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });  
    }else if($('#save').html() == "Modifier"){
      $.ajax({
            url: "VilleController",
            type: "POST",
            cache: false,
            data: {op: 'update', nom:nom, id:update_id},
            success: function (data, textStatus, jqXHR) {
                remplir(data); 
                clearFields();
                $('#save').html("Ajouter");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });  
    }
      
    
});
function clearFields(){
    $("#nom").val("");
}



function remplir(data) {
    let container = $("#mTable");
    let row = "";
    for (var i = 0; i < data.length; i++) {
        row += '<tr><td>' + data[i].id + '</td><td>' + data[i].nom + '</td><td><button class="btn btn-gradient-success btn-rounded btn-fw operation" indice="' + data[i].id + '">supprimer</button></td><td><button class="btn btn-gradient-info btn-rounded btn-fw operation" indice="' + data[i].id + '">modifier</button></td></tr>'
    }

    container.empty();
    container.html(row);
}

