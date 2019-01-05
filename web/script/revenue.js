load();
function load() {
    $.ajax({
        url: "RevenueController",
        type: "POST",
        cache: false,
        data: {op: 'load'},
        success: function (data, textStatus, jqXHR) {
            remplir(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error");
        }
    });
}

$('#immeuble').change(function () {
    loadAppartementContainer($(this).val(), null);
});

function loadAppartementContainer(immeuble_id, selected) {
    $.ajax({
        url: "ImmeubleController",
        type: "POST",
        cache: false,
        data: {op: 'loadAppartementByImmeuble', id: immeuble_id},
        success: function (data, textStatus, jqXHR) {
            $('#appartementContainer').empty();
            var options = "";
            for (var i = 0; i < data.length; i++) {
                if (data[i].id == selected) {
                    options += '<option selected value="' + data[i].id + '">Appartement ' + data[i].numero + '</option>';
                } else {
                    options += '<option value="' + data[i].id + '">Appartement ' + data[i].numero + '</option>';
                }
            }
            $('#appartementContainer').append('<label  for="appartement">Appartement</label>' +
                    '<select id="appartement" name="appartement" class="form-control">' +
                    '<option selected disabled>Selectionnez l\'appartement</option>' +
                    options +
                    '</select>');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error")
        }
    });
}

$('#mTable').on("click", ".operation", function () {
    if ($(this).html() == "modifier") {
        let date = $(this).parent().parent().children().next().html();
        let montant = $(this).parent().parent().children().next().next().html();
        let immeuble = $(this).parent().parent().children().next().next().next().html();
        let appartement = $(this).parent().parent().children().next().next().next().next().html();

        console.log(immeuble);
        console.log(appartement);

        $('#update').val($(this).attr("indice"));
        $('#date').val(date);
        $('#montant').val(montant);
        let immeuble_id = $("#immeuble option").filter(function () {
            if (this.text == immeuble)
                return this;
        }).val();

        let appartement_id = $("#appartement option").filter(function () {
            if (this.text == appartement)
                return this;
        }).val();

        loadAppartementContainer(immeuble_id, appartement_id);
        console.log(immeuble_id);
        $('#immeuble').val(immeuble_id);
        $('#save').html('Modifier');
    } else if ($(this).html() == "supprimer") {
        $.ajax({
            url: "RevenueController",
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
    let date = $('#date').val();
    let montant = $('#montant').val();
    let appartement = $('#appartement').val();
    let update_id = $('#update').val();
    if ($('#save').html() == "Ajouter") {
        $.ajax({
            url: "RevenueController",
            type: "POST",
            cache: false,
            data: {op: 'add', date: moment(date).format('L'), montant: montant, appartement: appartement},
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
            url: "RevenueController",
            type: "POST",
            cache: false,
            data: {op: 'update', date: moment(date).format('L'), montant: montant, appartement: appartement, id: update_id},
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
    $("#date").val("");
    $('#montant').val("");
    $("#immeuble").select(0);
    $("#appartementContainer").empty();
}



function remplir(data) {
    let container = $("#mTable");
    let row = "";
    for (var i = 0; i < data.length; i++) {
        let date = moment(new Date(data[i].date)).locale('cs').format('L').replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
        row += '<tr><td>' + data[i].id + '</td><td>' + date + '</td><td>' + data[i].montant + '</td><td>' + data[i].appartement.immeubleId.nom + '</td><td>Appartement ' + data[i].appartement.numero + '</td><td><button class="btn btn-gradient-success btn-rounded btn-fw operation" indice="' + data[i].id + '">supprimer</button></td><td><button class="btn btn-gradient-info btn-rounded btn-fw operation" indice="' + data[i].id + '">modifier</button></td></tr>'
    }

    container.empty();
    container.html(row);
}

