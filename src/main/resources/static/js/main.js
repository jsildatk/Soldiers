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
        url: 'registrationValidation/checkUsername/' + username,
        success: (msg) => {
            if (msg == 'Istnieje już taki użytkownik w bazie') {
                submit.prop('disabled', true);
                response.css('color', '#CE1A06');
            }
            response.html(msg);
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
        url: 'registrationValidation/checkEmail/' + email,
        async: false,
        success: (msg) => {
            if (msg == 'Istnieje już użytkownik o takim adresie e-mail') {
                submit.prop('disabled', true);
                response.css('color', '#CE1A06');
            }
            response.html(msg);
        }
    });
}

// Check everything and let register
check = () => {
    let submit = $('#register-submit');
    if (passwordMatch() && checkUsername() && checkEmail()) {
        submit.prop('disabled', false);
    }
}

// Forms
$(() => {
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
});