changePassword = (userId) => {
    swal({
        text: "Podaj stare hasło: ",
        content: {
            element: "input",
            attributes: {
                type: "password",
            },
        },
    }).then((oldPassword) => {
            $.ajax({
                url: "/settings/" + userId + "/" + `${oldPassword}`,
                type: "GET",
                success: (data) => {
                    if (data.username != null) {
                        swal({
                            text: "Podaj nowe hasło: ",
                            content: {
                                element: "input",
                                attributes: {
                                    type: "password",
                                },
                            },
                        }).then((newPassword) => {
                            $.ajax({
                                url: "/settings/" + userId + "/" + `${newPassword}`,
                                type: "PUT",
                                success: (data) => {
                                    if (data.username != null) {
                                        swal("Ustawiono nowe hasło", "Zostaniesz wylogowany", "success")
                                            .then(() => {
                                                window.location.href = ("/logout");
                                            });
                                    } else {
                                        swal("Coś poszło nie tak", "Wystąpił błąd", "info");
                                    }
                                },
                                error: () => {
                                    swal("Błąd", "Wystąpił błąd serwera", "error");
                                }
                            })
                        });
                    } else {
                        swal("Błąd", "Niepoprawne hasło", "info");
                    }
                },
                error: () => {
                    swal("Błąd", "Wystąpił błąd serwera", "error");
                }
            });
        });
}

$(() => {
    $("#updateUserForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: {
                username: $("#updateUserForm #username").val(),
                email: $("#updateUserForm #email").val()
            },
            url: "/settings/" + $("#updateUserForm #id").val(),
            type: "PUT",
            success: (data) => {
                if (data.username != null) {
                    swal("Wszystko przebiegło pomyślnie", "Zostaniesz wylogowany", "success")
                        .then(() => {
                            window.location.href = ("/logout");
                        });
                } else {
                    swal("Wystąpił problem", data, "info");
                }
            },
            error: () => {
                swal("Błąd", "Wystąpił błąd serwera", "error");
            }
        });
    });
});