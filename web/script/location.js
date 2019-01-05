
load();
load2();
function load() {
    $.ajax({
        url: "LocationController",
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

function load2() {
    $.ajax({
        url: "LocationController",
        type: "POST",
        cache: false,
        data: {op: 'load2'},
        success: function (data, textStatus, jqXHR) {
            remplir2(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error")
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

function loadById(id) {
    $.ajax({
        url: "LocationController",
        type: "POST",
        cache: false,
        data: {op: 'loadById', id: id},
        success: function (data, textStatus, jqXHR) {
            $('#nom').val(data.habitant.nom);
            $('#prenom').val(data.habitant.prenom);
            $('#cin').val(data.habitant.cin);
            $('#telephone').val(data.habitant.telephone);
            $('#email').val(data.habitant.email);
            $('#password').val(data.habitant.password);
            $('#type').val(data.habitant.type);
            let immeuble_id = data.appartement.immeubleId.id;
            let appartement_id = data.appartement.id;
            loadAppartementContainer(immeuble_id, appartement_id);
            $('#immeuble').val(immeuble_id);
            $('#dateDebut').val(moment(new Date(data.id.dateDebut)).format('L').replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2"));
            $('#dateFin').val(moment(new Date(data.dateFin)).format('L').replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2"));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("error")
        }
    });
}

$('#mTable').on("click", ".operation", function () {
    if ($(this).html() == "modifier") {
        $('#update').val($(this).attr("indice"));
        $('#immeublecontainer').attr('hidden', false);
        loadById($(this).attr("indice"));
        $('#save').html('Modifier');
    } else if ($(this).html() == "supprimer") {

        swal({
            title: "Are you sure?",
            text: "Once deleted, you will not be able to recover this!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
                    if (willDelete) {
                        $.ajax({
                            url: "LocationController",
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
                        swal("Poof! Your imaginary file has been deleted!", {
                            icon: "success",
                        });
                    }
                });


        
    }
});

$('#type').change(() => {
    if ($('#type').val() != "syndic") {
        $('#immeublecontainer').attr('hidden', false);
    } else {
        $('#immeublecontainer').attr('hidden', true);
    }
});

$("#save").click(() => {
    let nom = $('#nom').val();
    let prenom = $('#prenom').val();
    let cin = $('#cin').val();
    let telehpone = $('#telephone').val();
    let email = $('#email').val();
    let password = $('#password').val();
    let type = $('#type').val();
    let appartement = $('#appartement').val();
    let dateDebut = $('#dateDebut').val();
    let dateFin = $('#dateFin').val();
    let id_update = $('#update').val();

    let df = "";
    if (dateFin != "")
        df = moment(dateFin).format('L');

    if ($('#save').html() == "Ajouter") {
        if ($('#type').val() != "syndic") {
            $.ajax({
                url: "LocationController",
                type: "POST",
                cache: false,
                data: {op: 'add', nom: nom, prenom: prenom, cin: cin, telephone: telehpone, email: email, password: password, type: type, dateDebut: moment(dateDebut).format('L'), dateFin: df, appartement: appartement},
                success: function (data, textStatus, jqXHR) {
                    remplir(data);
                    $('#immeublecontainer').attr('hidden', true);
                    clearFields();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error")
                }
            });
        } else {
            $.ajax({
                url: "LocationController",
                type: "POST",
                cache: false,
                data: {op: 'add2', nom: nom, prenom: prenom, cin: cin, telephone: telehpone, email: email, password: password, type: type},
                success: function (data, textStatus, jqXHR) {
                    remplir2(data);
                    $('#immeublecontainer').attr('hidden', true);
                    clearFields();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error")
                }
            });
        }

    } else if ($('#save').html() == "Modifier") {
        $.ajax({
            url: "LocationController",
            type: "POST",
            cache: false,
            data: {op: 'update', nom: nom, prenom: prenom, cin: cin, telephone: telehpone, email: email, password: password, type: type, dateDebut: moment(dateDebut).format('L'), dateFin: df, appartement: appartement, id: id_update},
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
    $('#nom').val("");
    $('#prenom').val("");
    $('#cin').val("");
    $('#telephone').val("");
    $('#email').val("");
    $('#password').val("");
    $('#type').select(0);
    $('#montant').val("");
    $('#dateDebut').val("");
    $('#dateFin').val("");
    $("#appartementContainer").empty();
}



function remplir(data) {
    let container = $("#mTable");
    let row = "";
    for (var i = 0; i < data.length; i++) {
        let dd = moment(new Date(data[i].id.dateDebut)).format('L').replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
        let df = "-------";
        if (data[i].dateFin != undefined) {
            df = moment(new Date(data[i].dateFin)).format('L').replace(/(\d\d)\/(\d\d)\/(\d{4})/, "$3-$1-$2");
        }
        row += '<tr><td>' + data[i].habitant.nom + '</td><td>' + data[i].habitant.prenom + '</td><td>' + data[i].habitant.cin + '</td><td>' + data[i].habitant.telephone + '</td><td>' + data[i].habitant.email + '</td><td>' + data[i].habitant.type + '</td><td>' + data[i].appartement.immeubleId.nom + '</td><td>Appartement ' + data[i].appartement.numero + '</td><td>' + dd + '</td><td>' + df + '</td><td><button class="btn btn-gradient-success btn-rounded btn-fw operation" indice="' + data[i].habitant.id + '">supprimer</button></td><td><button class="btn btn-gradient-info btn-rounded btn-fw operation" indice="' + data[i].habitant.id + '">modifier</button></td></tr>'
    }

    container.empty();
    container.html(row);
}

function remplir2(data) {
    let container = $("#mTable2");
    let row = "";
    for (var i = 0; i < data.length; i++) {
        row += '<tr><td>' + data[i].nom + '</td><td>' + data[i].prenom + '</td><td>' + data[i].cin + '</td><td>' + data[i].telephone + '</td><td>' + data[i].email + '</td><td>' + data[i].type + '</td></tr>'
    }

    container.empty();
    container.html(row);
}