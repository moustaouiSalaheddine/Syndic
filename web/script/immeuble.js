let k = 0;
$(document).ready(function () {

    $('#ville').change(function () {
        $.ajax({
            url: "QuartierController",
            type: "POST",
            cache: false,
            data: {op: 'loadByVille', ville: $(this).val()},
            success: function (data, textStatus, jqXHR) {
                $('#quartierContainer').empty();
                var options = "";
                for (var i = 0; i < data.length; i++) {
                    options += '<option value="' + data[i].id + '">' + data[i].nom + '</option>';
                }
                $('#quartierContainer').append('<label for="quartier">Quartier : </label>' +
                        '<select id="quartier" name="quartier" class="form-control">' +
                        '<option selected disabled>Selectionnez le quartier</option>' +
                        options +
                        '</select>');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });
    });



    $('#save').on('click', function () {
        let nbrEtage = $("#etage").val();
        let nbrAppart = $("#appart").val();
        let msImmeuble = $('.ms-immeuble');
        console.log("appart " + nbrAppart + " etage " + nbrEtage);
        console.log("appart " + nbrAppart / nbrEtage);
        msImmeuble.empty();
        for (var i = 1; i <= nbrEtage; i++) {
            console.log(i);
            msImmeuble.append('<div id="appart" class="appart" value=' + i + '>'
                    + '<span>Etage ' + i + '</span>'
                    + '<div data-drop-target="true" class="Etages ms-apparts' + i + '"></div>'
                    + ' <div class="ms-more">+</div></div>'
                    );
        }
        let Apparts = $('.ms-apparts1');
        k = 0;
        for ($j = 1; $j <= nbrEtage; $j++) {
            for (var i = 1; i <= Math.ceil(nbrAppart / nbrEtage); i++) {
                k++;
                if (k <= nbrAppart) {
                    Apparts = $('.ms-apparts' + $j);
                    Apparts.append('<div value=' + k + ' id="' + k + '" draggable="true" class="ms-box appartements">appartement ' + k + '<div><input type="number" name="dimonsion" id="' + k + '" class="form-control dimensions" placeholder="Sesissez la dimension en m²"></div><div class="ms-delete">X</div></div>');
                }
            }
        }

        window.onload = DragAndDrop();

        $('.ms-more').on('click', function () {
            k++;
            $(this).parent()[0].childNodes[1].insertAdjacentHTML('beforeend', '<div value=' + k + ' id="' + k + '" draggable="true" class="ms-box appartements">appartement ' + k + '<div><input type="number" name="dimonsion" id="' + k + '" class="form-control dimensions" placeholder="Sesissez la dimension en m²"></div><div class="ms-delete">X</div></div>');
            window.onload = DragAndDrop();
        });

        $('#btn-ajouter').attr("hidden", false);
        $('#appart_container').attr("hidden", false);

    });

    load();
    function load() {
        $.ajax({
            url: "ImmeubleController",
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



    $("#btn-ajouter").click(() => {
        let nom = $('#nom').val();
        let quartier = $('#quartier').val();
        let numero = $('#numero').val();
        let msImmeuble = $('.ms-immeuble');
        let codepostal = $('#codepostal').val();
        let update_id = $('#update').val();



        let tab = [];
        let etage = document.getElementsByClassName("Etages");
        $k = 0;
        for ($i = 0; $i < etage.length; $i++) {
            console.log("etage" + $i);
            let appertements = etage[$i].getElementsByClassName("appartements");
            let dimensions = etage[$i].getElementsByClassName("dimensions");
            let tabx = [];
            for ($j = 0; $j <= appertements.length - 1; $j++) {
                $k++;
                console.log("" + appertements[$j].id);
                console.log("dimensions" + dimensions[$j].value);
                tabx.push({appart: appertements[$j].id + '_' + dimensions[$j].value});
            }
            tab.push({etage: (($i + 1)), appart: tabx});
            console.log("");
        }

        console.log(tab);

        if ($('#btn-ajouter').html() == "Ajouter") {
            $.ajax({
                url: "ImmeubleController",
                type: "POST",
                cache: false,
                data: {op: 'add', nom: nom, quartier: quartier, numero: numero, codepostal: codepostal, tab: JSON.stringify(tab)},
                success: function (data, textStatus, jqXHR) {
                    remplir(data);
                    clearFields();
                    msImmeuble.empty();
                    $('#btn-ajouter').attr("hidden", true);
                    $('#appart_container').attr("hidden", true);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        } else if ($('#btn-ajouter').html() == "Modifier") {
            $.ajax({
                url: "ImmeubleController",
                type: "POST",
                cache: false,
                data: {op: 'update', nom: nom, quartier: quartier, numero: numero, codepostal: codepostal, tab: JSON.stringify(tab), id: update_id},
                success: function (data, textStatus, jqXHR) {
                      remplir(data);
                    clearFields();
                    msImmeuble.empty();
                    $('#btn-ajouter').attr("hidden", true);
                    $('#appart_container').attr("hidden", true);
                    $('#btn-ajouter').html("Ajouter");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("error");
                }
            });
        }


    });


    $('#mTable').on("click", ".cells", function () {
        let immeuble_id = $(this).parent().children().html();
        $('#btnModal').click();

        $.ajax({
            url: "ImmeubleController",
            type: "POST",
            cache: false,
            data: {op: 'loadAppartementByImmeuble', id: immeuble_id},
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                remplirModal(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });

    });


    function loadQuartierContainer(ville_id, selected) {
        $.ajax({
            url: "QuartierController",
            type: "POST",
            cache: false,
            data: {op: 'loadByVille', ville: ville_id},
            success: function (data, textStatus, jqXHR) {
                $('#quartierContainer').empty();
                var options = "";
                for (var i = 0; i < data.length; i++) {
                    if (data[i].nom == selected) {
                        options += '<option selected value="' + data[i].id + '">' + data[i].nom + '</option>';
                    } else {
                        options += '<option value="' + data[i].id + '">' + data[i].nom + '</option>';
                    }
                }
                $('#quartierContainer').append('<label  for="quartier">Quartier :</label>' +
                        '<select id="quartier" name="quartier" class="form-control">' +
                        '<option selected disabled>Selectionnez le quartier</option>' +
                        options +
                        '</select>');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });
    }

    function loadTickets(nbEtage, nbAppart, data) {
        let nbrEtage = nbEtage;
        let nbrAppart = nbAppart;
        let msImmeuble = $('.ms-immeuble');
        console.log("appart " + nbrAppart + " etage " + nbrEtage);
        console.log("appart " + nbrAppart / nbrEtage);
        msImmeuble.empty();
        for (var i = 1; i <= nbrEtage; i++) {
            console.log(i);
            msImmeuble.append('<div id="appart" class="appart" value=' + i + '>'
                    + '<span>Etage ' + i + '</span>'
                    + '<div data-drop-target="true" class="Etages ms-apparts' + i + '"></div>'
                    + ' <div class="ms-more">+</div></div>'
                    );
        }
        let Apparts = $('.ms-apparts1');
        k = 0;
        for ($j = 1; $j <= nbrEtage; $j++) {
            for (var i = 1; i <= Math.ceil(nbrAppart / nbrEtage); i++) {
                var dim = data[k].dimension;
                k++;
                if (k <= nbrAppart) {
                    Apparts = $('.ms-apparts' + $j);
                    Apparts.append('<div value=' + k + ' id="' + k + '" draggable="true" class="ms-box appartements">appartement ' + k + '<div><input type="number" name="dimonsion" value="'+dim+'" id="' + k + '" class="form-control dimensions" placeholder="Sesissez la dimension en m²"></div><div class="ms-delete">X</div></div>');
                }
            }
        }

        window.onload = DragAndDrop();

        $('.ms-more').on('click', function () {
            k++;
            $(this).parent()[0].childNodes[1].insertAdjacentHTML('beforeend', '<div value=' + k + ' id="' + k + '" draggable="true" class="ms-box appartements">appartement ' + k + '<div><input type="number" name="dimonsion" id="' + k + '" class="form-control dimensions" placeholder="Sesissez la dimension en m²"></div><div class="ms-delete">X</div></div>');
            window.onload = DragAndDrop();
        });

        $('#btn-ajouter').attr("hidden", false);
        $('#appart_container').attr("hidden", false);

    }


    function loadAppartementsContainer(immeuble_id) {
        $.ajax({
            url: "ImmeubleController",
            type: "POST",
            cache: false,
            data: {op: 'loadAppartementByImmeuble', id: immeuble_id},
            success: function (data, textStatus, jqXHR) {
                var tabEtage = [];
                for(var i = 0 ; i < data.length ; i++){
                        if(!tabEtage.includes(data[i].etage)){
                            tabEtage.push(data[i].etage);
                        }
                    
                }
                $('#etage').val(tabEtage.length);
                $('#appart').val(data.length);
                loadTickets(tabEtage.length, data.length, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error")
            }
        });
    }

    $('#mTable').on("click", ".operation", function () {
        if ($(this).html() == "modifier") {
            let nom = $(this).parent().parent().children().next().html();
            let numero = $(this).parent().parent().children().next().next().html();
            let codePostal = $(this).parent().parent().children().next().next().next().html();
            let ville = $(this).parent().parent().children().next().next().next().next().html();
            let quartier = $(this).parent().parent().children().next().next().next().next().next().html();

            $('#update').val($(this).attr("indice"));
            $('#nom').val(nom);
            $('#numero').val(numero);
            $('#codepostal').val(codePostal);

            let ville_id = $("#ville option").filter(function () {
                if (this.text == ville)
                    return this;
            }).val();

            $('#ville').val(ville_id);
            loadQuartierContainer(ville_id, quartier);
            loadAppartementsContainer($(this).attr("indice"));
            $('#btn-ajouter').html('Modifier');
        } else if ($(this).html() == "supprimer") {
            $.ajax({
                url: "ImmeubleController",
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

    function remplir(data) {
        let container = $("#mTable");
        let row = "";
        for (var i = 0; i < data.length; i++) {
            row += '<tr><td class="cells">' + data[i].id + '</td><td class="cells">' + data[i].nom + '</td><td class="cells">' + data[i].numero + '</td><td class="cells">' + data[i].codePostal + '</td><td class="cells">' + data[i].quarierId.villeId.nom + '</td><td class="cells">' + data[i].quarierId.nom + '</td><td><button class="btn btn-gradient-success btn-rounded btn-fw operation" indice="' + data[i].id + '">supprimer</button></td><td><button class="btn btn-gradient-info btn-rounded btn-fw operation" indice="' + data[i].id + '">modifier</button></td></tr>'
        }

        container.empty();
        container.html(row);
    }

    function remplirModal(data) {
        let container = $("#modal_data");
        let row = "";
        for (var i = 0; i < data.length; i++) {
            row += '<tr><td>' + data[i].dimension + ' m²</td><td>' + data[i].numero + '</td><td>' + data[i].etage + '</td><td>' + data[i].immeubleId.nom + '</td></tr>'
        }

        container.empty();
        container.html(row);
    }

    function clearFields() {
        $('#nom').val('');
        $('#quartier').val('');
        $('#numero').val('');
        $('#codepostal').val('');
        $('#etage').val('');
        $('#appart').val('');
        $('#quartierContainer').empty();
    }

});

function DragAndDrop() {
    $('.ms-delete').on('click', function () {
        $(this).parent().parent()[0].childNodes[$(this).parent().index()].remove();
        k--;
    });

    //Function handleDragStart(), Its purpose is to store the id of the draggable element.
    function handleDragStart(e) {
        e.dataTransfer.setData("text", this.id); //note: using "this" is the same as using: e.target.
    }//end function


//The dragenter event fires when dragging an object over the target. 
//The css class "drag-enter" is append to the targets object.
    function handleDragEnterLeave(e) {
        if (e.type == "dragenter") {
            this.className = "drag-enter"
        } else {
            this.className = "" //Note: "this" referces to the target element where the "dragenter" event is firing from.
        }
    }//end function



//Function handles dragover event eg.. moving your source div over the target div element.
//If drop event occurs, the function retrieves the draggable element’s id from the DataTransfer object.
    function handleOverDrop(e) {
        e.preventDefault();
        //Depending on the browser in use, not using the preventDefault() could cause any number of strange default behaviours to occur.
        if (e.type != "drop") {
            return; //Means function will exit if no "drop" event is fired.
        }
        //Stores dragged elements ID in var draggedId
        var draggedId = e.dataTransfer.getData("text");
        //Stores referrence to element being dragged in var draggedEl
        var draggedEl = document.getElementById(draggedId);

        //if the event "drop" is fired on the dragged elements original drop target e.i..  it's current parentNode, 
        //then set it's css class to ="" which will remove dotted lines around the drop target and exit the function.
        if (draggedEl.parentNode == this) {
            this.className = "";
            return; //note: when a return is reached a function exits.
        }
        //Otherwise if the event "drop" is fired from a different target element, detach the dragged element node from it's
        //current drop target (i.e current perantNode) and append it to the new target element. Also remove dotted css class. 
        draggedEl.parentNode.removeChild(draggedEl);
        this.appendChild(draggedEl); //Note: "this" references to the current target div that is firing the "drop" event.
        this.className = "Etages";
    }//end Function



//Retrieve two groups of elements, those that are draggable and those that are drop targets:
    var draggable = document.querySelectorAll('[draggable]')
    var targets = document.querySelectorAll('[data-drop-target]');
//Note: using the document.querySelectorAll() will aquire every element that is using the attribute defind in the (..)


//Register event listeners for the"dragstart" event on the draggable elements:
    for (var i = 0; i < draggable.length; i++) {
        draggable[i].addEventListener("dragstart", handleDragStart);
    }

//Register event listeners for "dragover", "drop", "dragenter" & "dragleave" events on the drop target elements.
    for (var i = 0; i < targets.length; i++) {
        targets[i].addEventListener("dragover", handleOverDrop);
        targets[i].addEventListener("drop", handleOverDrop);
        targets[i].addEventListener("dragenter", handleDragEnterLeave);
        targets[i].addEventListener("dragleave", handleDragEnterLeave);
    }
}