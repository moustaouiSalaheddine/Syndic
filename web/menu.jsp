
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
        <li class="nav-item nav-profile">
            <a href="#" class="nav-link">
                <div class="nav-profile-image">
                    <img src="Template/images/faces/face1.jpg" alt="profile">
                    <span class="login-status online"></span> <!--change to offline or busy as needed-->              
                </div>
                <div class="nav-profile-text d-flex flex-column">
                    <span class="font-weight-bold mb-2">David Grey. H</span>
                    <span class="text-secondary text-small">Project Manager</span>
                </div>
                <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="index.jsp">
                <span class="menu-title">Home</span>
                <i class="mdi mdi-home menu-icon"></i>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-title">Gestion</span>
                <i class="menu-arrow"></i>
                <i class="mdi mdi-crosshairs-gps menu-icon"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="ville.jsp">Gestion des villes</a></li>
                    <li class="nav-item"> <a class="nav-link" href="quartier.jsp">Gestion des quartiers</a></li>
                    <li class="nav-item"> <a class="nav-link" href="Immeuble.jsp">Gestion des immeubles</a></li>
                    <li class="nav-item"> <a class="nav-link" href="Revenue.jsp">Gestion des Revenue</a></li>
                    <li class="nav-item"> <a class="nav-link" href="Location.jsp">Gestion des Location</a></li>
                </ul>
            </div>
        </li>
    </ul>
</nav>