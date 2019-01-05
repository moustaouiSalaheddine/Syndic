$(document).ready(() => {
    var annee, immeuble;
    $('#annee').change(function () {
        annee = $(this).val();
        if (annee != undefined && immeuble != undefined) {
            $.ajax({
                url: "RevenueController",
                type: "POST",
                cache: false,
                data: {op: 'revenue', annee: moment(new Date(annee)).format('L'), immeuble: immeuble},
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    remplir(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }
    });

    $('#immeuble').change(function () {
        immeuble = $(this).val();
        if (annee != undefined && immeuble != undefined) {
            $.ajax({
                url: "RevenueController",
                type: "POST",
                cache: false,
                data: {op: 'revenue', annee: moment(new Date(annee)).format('L'), immeuble: immeuble},
                success: function (data, textStatus, jqXHR) {
                    console.log(data);
                    remplir(data);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }
    });


    function remplir(data) {
        let container = $("#mTable");
        let row = "";
        for (var i = 0; i < data.length; i++) {
            row += '<tr><td>Appartement ' + data[i].appartement.numero + '</td></tr>';
        }

        container.empty();
        container.html(row);
    }

});