<%-- 
    Document   : Revenue
    Created on : 26 déc. 2018, 19:56:03
    Author     : Sinponzakra
--%>

<%@page import="beans.Immeuble"%>
<%@page import="service.ImmeubleService"%>
<%@page import="beans.Appartement"%>
<%@page import="service.AppartementService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Revenue</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="Template/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="Template/vendors/css/vendor.bundle.base.css">
        <!-- endinject -->
        <!-- inject:css -->
        <link rel="stylesheet" href="Template/css/style.css">
        <script src="script/jquery-3.2.1.min.js" type="text/javascript"></script>
        
        
        <!-- endinject -->
        <link rel="shortcut icon" href="Template/images/favicon.png" />
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
                        <div class="col-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Gestion des Revenue</h4>
                                    <p class="card-description">
                                        Nouvelle revenue
                                    </p>
                                    <div class="forms-sample">
                                        <div class="form-group">
                                            <input type="text" name="update" id="update" hidden="" />
                                            <label  for="date">Date</label>
                                            <input type="date" class="form-control" id="date" name="date">
                                        </div>
                                        <div class="form-group">
                                            <label  for="montant">Montant</label>
                                            <input type="number" class="form-control" id="montant" name="montant" placeholder="saisissez le montant">
                                        </div>
                                        <div class="form-group">
                                            <label  for="immeuble">Immeuble</label>
                                            <select id="immeuble" name="immeuble" class="form-control">
                                                <option selected disabled>Selectionnez l'immeuble</option>
                                                <%  ImmeubleService is = new ImmeubleService();
                                                    for(Immeuble i : is.findAll()){ %>
                                                    <option value="<%= i.getId() %>"><%=i.getNom()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="form-group" id="appartementContainer">
                                            
                                        </div>
                                        
                                        <button id="save" type="submit" class="btn btn-gradient-primary mr-2">Ajouter</button>
                                        <button class="btn btn-light">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Tables  -->
                        
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Liste des revenue</h4>
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>
                                                    ID
                                                </th>
                                                <th>
                                                    DATE
                                                </th>
                                                <th>
                                                    MONTANT
                                                </th>
                                                <th>
                                                    IMMEUBLE
                                                </th>
                                                <th>
                                                    APPARTEMENT
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
                                </div>
                            </div>
                        </div>
                        <!-- Tables ends -->
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
        <script src="script/moment.js" type="text/javascript"></script>
        <script src="script/revenue.js" type="text/javascript"></script>
        <!-- End custom js for this page-->
    </body>

</html>
