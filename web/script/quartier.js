
load();
function load() {
    $.ajax({
        url: "QuartierController",
        type: "POST",
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
        let ville = $(this).parent().parent().children().next().next().html();

        $('#update').val($(this).attr("indice"));
        $('#nom').val(nom);
       let ville_id =  $("#ville option").filter(function () {
            if(this.text == ville)
                return this;
        }).val();
        $('#ville').val(ville_id);
        $('#save').html('Modifier');
    } else if ($(this).html() == "supprimer") {
        $.ajax({
            url: "QuartierController",
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


$("#save").click(() => {
    let nom = $('#nom').val();
    let ville = $('#ville').val();
    let update_id = $('#update').val();
    if ($('#save').html() == "Ajouter") {
        $.ajax({
            url: "QuartierController",
            type: "POST",
            cache: false,
            data: {op: 'add', nom: nom, ville: ville},
            success: function (data, textStatus, jqXHR) {
                remplir(data);
                clearFields();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });
    } else if ($('#save').html() == "Modifier") {
        $.ajax({
            url: "QuartierController",
            type: "POST",
            cache: false,
            data: {op: 'update', nom: nom, ville: ville, id: update_id},
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
function clearFields() {
    $("#nom").val("");
    $("#ville").select(0);
}



function remplir(data) {
    let container = $("#mTable");
    let row = "";
    for (var i = 0; i < data.length; i++) {
        row += '<tr><td>' + data[i].id + '</td><td>' + data[i].nom + '</td><td>' + data[i].villeId.nom + '</td><td><button class="btn btn-gradient-success btn-rounded btn-fw operation" indice="' + data[i].id + '">supprimer</button></td><td><button class="btn btn-gradient-info btn-rounded btn-fw operation" indice="' + data[i].id + '">modifier</button></td></tr>'
    }

    container.empty();
    container.html(row);
}

