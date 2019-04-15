searchUser = () => {
    let username = $("#searchUser").val();
    $.ajax({
        type: "GET",
        url: "/admin/users/searchByUsername/" + username,
        success: (data) => {
            if (data.length != 0) {
                $("#usersTable tbody tr").remove();
                $("#usersTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.username + '</td><td>' + data.email + '</td>' +
                    '<td>' + data.role.role + '</td><td>' + (data.enabled ? 'TAK' : 'NIE') + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateUser(event, ' + data.id + ')">Edytuj</button></td>' +
                    '<td><button type="button" class="btn btn-danger update" onclick="deleteUser(event, ' + data.id + ')">Usuń</button></td></tr>');
            } else {
                swal("Brak informacji", "Nie znaleziono użytkownika o tak nazwie.", "info");
            }
        }
    });
}