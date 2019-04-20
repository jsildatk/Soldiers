// Check if passwords match
passwordMatch = () => {
    let password = $('#password1').val();
    let password2 = $('#confirmPassword').val();
    let submit = $('#register-submit');
    let response = $('#passwordResponse');

    if (password == password2) {
        response.html('');
        return true;
    } else {
        response.html('Hasła nie pasują');
        response.css('color', '#CE1A06');
        submit.prop('disabled', true);
        return false;
    }
}

// Check if username exists in db
checkUsername = () => {
    let username = $('#username1').val();
    let submit = $('#register-submit');
    let response = $('#usernameResponse');

    return $.ajax({
        type: 'GET',
        url: 'registration/username/' + username,
        success: (data) => {
            if (data.username != null) {
                submit.prop('disabled', true);
                response.html("Istnieje już taki użytkownik");
                response.css('color', '#CE1A06');
            } else {
                response.html("");
            }
        }
    });
}

// Check if email exists in db
checkEmail = () => {
    let email = $('#email1').val();
    let submit = $('#register-submit');
    let response = $('#emailResponse');

    return $.ajax({
        type: 'GET',
        url: 'registration/email/' + email,
        success: (data) => {
            if (data.email != null) {
                response.html("Istnieje już użytkownik o takim adresie email");
                submit.prop('disabled', true);
                response.css('color', '#CE1A06');
            } else {
                response.html("");
            }
        }
    });
}

// Check if password is strong
passwordStrength = () => {
    let password = $('#password1').val();
    let submit = $('#register-submit');
    let response = $('#passwordStrengthResponse');
    let strength = 0;
    if (password.length < 8) {
        response.css('color', '#CE1A06');
        response.html('Za krótkie hasło');
        submit.prop('disable', true);
        return false;
    } else {
        strength++;
        // Upper and lowercase letters
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
            strength++;
        }
        // Letter and number
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) {
            strength++;
        }
        // Special character
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) {
            strength++;
        }

        if (strength <= 2) {
            response.css('color', '#CE1A06');
            response.html('Słabe hasło');
            submit.prop('disable', true);
            return false;
        } else if (strength == 3) {
            response.css('color', '#1CB94E');
            response.html('Ok hasło');
        } else {
            response.css('color', '#1CB94E');
            response.html('Silne hasło');
        }
        return true;
    }
}

// Check everything and let register
check = () => {
    let submit = $('#register-submit');
    if (passwordMatch() && checkUsername() && checkEmail() && passwordStrength()) {
        submit.prop('disabled', false);
    }
}

// Forms
$(() => {
    // show login form
    $('#login-form-link').click((e) => {
        $('#usernameResponse').hide();
        $('#emailResponse').hide();
        $('#passwordResponse').hide();
        $("#login").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $('#login-form-link').addClass('active');
        e.preventDefault();
    });
    // show registration form
    $('#register-form-link').click((e) => {
        $('#usernameResponse').show();
        $('#emailResponse').show();
        $('#passwordResponse').show();
        $("#register-form").delay(100).fadeIn(100);
        $("#login").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $('#register-form-link').addClass('active');
        e.preventDefault();
    });
    // submit registration form
    $('#register-form').submit((e) => {
        $.ajax({
            data: $('#register-form').serialize(),
            type: $('#register-form').attr('method'),
            url: $('#register-form').attr('action'),
            success: (data) => {
                if (data.user != null) {
                    swal("Zarejestrowałeś/aś się", 'Możesz się teraz zalogować', 'success');
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
                }
            },
            error: () => {
                swal('Wystąpił błąd', 'Spróbuj ponownie', 'error');
            }
        });
        $('#register-form')[0].reset();
        $('#passwordStrengthResponse').html('');
        e.preventDefault();
    });
});