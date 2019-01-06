<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Purple Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="Template/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="Template/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="Template/css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="Template/images/favicon.png" />
  <!-- Start My Style -->
  <link href="msStyle/msStyle.css" rel="stylesheet" type="text/css"/>
  <!-- End My Style -->
  <!-- Start Script -->
  <script src="script/jquery-3.2.1.min.js" type="text/javascript"></script>
  <!--<script src="script/login.js" type="text/javascript"></script>-->
  <!-- End Script -->
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="Template/images/logo.svg">
              </div>
              <h4>Hello! let's get started</h4>
              <h6 class="font-weight-light">Sign in to continue.</h6>
              <div class="pt-3">
                <div class="form-group">
                    <input type="email" class="form-control form-control-lg" id="email" name="email" placeholder="Email">
                    <div class="emailError">email Error </div>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="password" name="password" placeholder="Password">
                    <div class="passwordError">Password Error </div>
                </div>
                <div class="mt-3">
                    <a class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" id="signIn">SIGN IN</a>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      Keep me signed in
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black">Forgot password?</a>
                </div>
<!--                <div class="mb-2">
                  <button type="button" class="btn btn-block btn-facebook auth-form-btn">
                    <i class="mdi mdi-facebook mr-2"></i>Connect using facebook
                  </button>
                </div>-->
                <div class="text-center mt-4 font-weight-light">
                  Don't have an account? <a href="register.html" class="text-primary">Create</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="Template/vendors/js/vendor.bundle.base.js"></script>
  <script src="Template/vendors/js/vendor.bundle.addons.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="Template/off-canvas.js"></script>
  <script src="Template/misc.js"></script>
  <!-- endinject -->
  <!-- Start Script -->
  <script src="script/login.js" type="text/javascript"></script>
  <!-- End Script -->
</body>

</html>
