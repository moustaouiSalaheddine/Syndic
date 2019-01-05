<%-- 
    Document   : index
    Created on : Dec 20, 2018, 2:42:07 PM
    Author     : mst
--%>

<%@page import="beans.Ville"%>
<%@page import="service.VilleService"%>
<%@page import="beans.Quartier"%>
<%@page import="service.QuartierService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Immeuble</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="Template/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="Template/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- inject:css -->
        <link rel="stylesheet" href="Template/css/style.css">
        <!-- endinject -->
        <link rel="shortcut icon" href="Template/images/favicon.png" />
        <!-- ms-Style drag and drop-->
        <link href="msStyle/msStyle.css" rel="stylesheet" type="text/css"/>
        <script src="script/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-scroller">
            <!-- partial:partials/_navbar.html -->
            <%@include file="header.jsp"%>
            <!-- partial -->
            <div class="container-fluid page-body-wrapper">
                <!-- partial:partials/_sidebar.html -->
                <%@include file="menu.jsp" %>
                <!-- partial -->
                <div class="main-panel">
                    <div class="content-wrapper">
                        <!-- From -->
                        <div class="col-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Gestion des immeubles</h4>
                                    <p class="card-description">
                                        Nouvelle immeuble
                                    </p>
                                    <div class="forms-sample" >
                                        <div class="form-group">
                                            <label for="nom">Nom : </label>
                                            <input type="text" name="nom" id="nom" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="numero">Numero : </label>
                                            <input type="number" name="numero" id="numero" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="codepostal">Code Postal : </label>
                                            <input type="text" name="codepostal" id="codepostal" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="ville">Ville : </label>
                                            <select id="ville" name="ville" class="form-control">
                                                <option selected disabled>Selectionnez la ville</option>
                                                <%  VilleService vs = new VilleService();
                                                    for (Ville v : vs.findAll()) {%>
                                                <option value="<%= v.getId()%>"><%= v.getNom()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                        <div class="form-group" id="quartierContainer">

                                        </div>
                                        <div class="form-group">
                                            <label for="etage">Nombre des etages : </label>
                                            <input type="number" name="etage" id="etage" class="form-control">
                                            <input type="text" hidden="" name="idupdate" id="idupdate" class="form-control" >
                                        </div>
                                        <div class="form-group">
                                            <label for="appart">Nombre d'ppartement : </label>
                                            <input type="number" name="appart" id="appart" class="form-control">
                                        </div>
                                        <button type="submit" id="save" class="btn btn-gradient-primary mr-2">Valider</button>
                                        <button class="btn btn-light">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- From ends-->
                        <!-- Drag and drop -->
                        <div class="col-12 grid-margin stretch-card" id="appart_container" hidden="">
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-description">
                                        assignment des appartements au etages
                                    </p>
                                    <div class="ms-immeuble">
                                        <!--                            <div class="appart">
                                                                        <span>Etage 1</span>
                                                                        <div data-drop-target="true">
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 1</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 2</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 1</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 3</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 4</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 5</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                            <div id="a1" draggable="true" class="ms-box">appartement 6</div>
                                                                        </div>
                                                                    </div>
                                        
                                                                    <div class="appart">
                                                                        <span>Etage 2</span>
                                                                        <div data-drop-target="true"></div>
                                                                    </div>
                                                                    <div class="appart">
                                                                        <span>Etage 3</span>
                                                                        <div data-drop-target="true"></div>
                                                                    </div>-->
                                    </div>
                                    <button  id="btn-ajouter" hidden="" class="btn btn-gradient-danger btn-rounded btn-fw form-control">Ajouter</button>
                                </div>
                            </div>
                        </div>


                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Liste des Immeubles</h4>
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>
                                                    ID
                                                </th>
                                                <th>
                                                    NOM
                                                </th>
                                                <th>
                                                    NUMERO
                                                </th>
                                                <th>
                                                    CODE POSTAL
                                                </th>
                                                <th>
                                                    VILLE
                                                </th>
                                                <th>
                                                    QUARTIER
                                                </th>
                                                <th>
                                                    SUPPRIMER
                                                </th>
                                                <th>
                                                    MODIFIER
                                                </th>

                                            </tr>
                                        </thead>
                                        <tbody id="mTable">

                                        </tbody>
                                    </table>



                                    <div class="container">
                                        <!-- Trigger the modal with a button -->
                                        <!-- Modal -->
                                        <button hidden="" data-toggle="modal" id="btnModal"  data-target="#myModal">Modal</button>
                                        <div class="modal fade" id="myModal" role="dialog">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Liste des Appartements</h4>
                                                    </div>
                                                    <div class="modal-body" >
                                                        <div class="col-lg-6 grid-margin stretch-card">
                                                            <div class="card">
                                                                <div class="card-body">
                                                                    <table class="table table-hover">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>ID</th>
                                                                                <th>DIMENSION</th>
                                                                                <th>NUMERO</th>
                                                                                <th>ETAGE</th>
                                                                                <th>IMMEUBLE</th>
                                                                            </tr>
                                                                        </thead>
                                                                        <tbody id="modal_data">
                                                                          
                                                                        </tbody>
                                                                    </table>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                            </div>
                        </div>

                        <!-- Drag and drop ends -->
                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:partials/_footer.html -->
                    <footer class="footer">
                        <div class="d-sm-flex justify-content-center justify-content-sm-between">
                            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2017 <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap Dash</a>. All rights reserved.</span>
                            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="mdi mdi-heart text-danger"></i></span>
                        </div>
                    </footer>
                    <!-- partial -->
                </div>
                <!-- main-panel ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->

        <!-- plugins:js -->
        <script src="Template/vendors/js/vendor.bundle.base.js"></script>
        <script src="Template/vendors/js/vendor.bundle.addons.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page-->
        <!-- End plugin js for this page-->
        <!-- inject:js -->
        <script src="Template/js/off-canvas.js"></script>
        <script src="Template/js/misc.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page-->
        <script src="Template/js/dashboard.js"></script>
        <script src="msScript/msScript.js" type="text/javascript"></script>
        <script src="script/immeuble.js" type="text/javascript"></script>
        <!-- End custom js for this page-->
    </body>

</html>
