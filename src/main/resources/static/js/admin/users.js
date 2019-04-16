searchUser = () => {
    let username = $("#searchUser").val();
    $.ajax({
        type: "GET",
        url: "/admin/users/searchByUsername/" + username,
        success: (data) => {
            if (data.length != 0) {
                $("#usersTable tbody tr").remove();
                $("#usersTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.username + '</td><td>' + data.email + '</td>' +
                    '<td>' + data.role.role + '</td><td>' + (data.enabled ? 'TAK' : 'NIE') + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateUser(event, ' + data.id + ')">Edytuj</button></td>');
            } else {
                swal("Brak informacji", "Nie znaleziono użytkownika o tak nazwie.", "info");
            }
        }
    });
}

$(() => {
    $("#updateUserForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: {
                role: $("#updateUserForm #role").val(),
                enabled: $("#updateUserForm #enabled").val()
            },
            type: "PUT",
            url: "/admin/users/" + $("#updateUserForm #id").val(),
            success: (data) => {
                if (data != "") {
                    $("#usersTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.username + '</td><td>' + data.email + '</td>' +
                        '<td>' + data.role.role + '</td><td>' + (data.enabled ? 'TAK' : 'NIE') + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateUser(event,' + data.id + ')">Edytuj</button></td>');
                    swal("Wszytko przebiegło pomyślnie", "Zedytowano użytkownika", "success");
                }
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");;
            }
        });
    });
});

updateUser = (e, userId) => {
    e.preventDefault();
    $.ajax({
        type: "GET",
        url: "/admin/users/" + userId,
        success: (data) => {
            $("#updateModal #updateUserForm #role option[value='" + data.role.id + "']").prop('selected', true);
            $("#updateModal #updateUserForm #enabled option[value='" + (data.enabled ? 'true' : 'false') + "']").prop('selected', true);
            $("#updateModal #updateUserForm #id").val(userId);
        }
    });
}