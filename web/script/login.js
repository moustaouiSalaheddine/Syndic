$(document).ready(function () {
    $('body').on("click", "#signIn", function () {
        let email = $("#email").val();
        let password = $("#password").val();
        console.log(email + " password : " + password);
        $.ajax({
            url: "LoginController",
            type: "POST",
            cache: false,
            data: {login: 'signIn',email: email, password: password},
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                if (data == "emailError") {
                    $('.emailError').css({"display": "block"});
                } else {
                    $('.emailError').css({"display": "none"});
                }
                if (data == "passwordError") {
                    $('.passwordError').css({"display": "block"});
                } else {
                    $('.passwordError').css({"display": "none"});
                }
                if (data == "index") {
                    location.replace("index.jsp");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            }
        });
    });
    $('body').on("click", "#signOut", function () {
        $.ajax({
            url: "LoginController",
            type: "POST",
            cache: false,
            data: {login: 'signOut'},
            success: function (data, textStatus, jqXHR) {
                console.log("out "+data);
                
                if (data == "signOut") {
                    location.replace("login.jsp");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("error");
            }
        });
    });
});