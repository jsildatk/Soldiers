searchSoldier = () => {
    let lastName = $("#searchSoldier").val();
    $.ajax({
        type: "GET",
        url: "/admin/searchByLastName/" + lastName,
        success: (data) => {
            if (data.length != 0) {
                $("#soldiersTable tbody tr").remove();
                for (let i = 0; i < data.length; i++) {
                    $("#soldiersTable").append('<tr id="' + data[i].id + '"><td>'+ data[i].id +'</td><td>' + data[i].firstName + ' ' + data[i].lastName + '</td><td>' + data[i].rank.rank + '</td>' +
                        '<td>' + data[i].personalEvidenceNumber + '</td><td>' + data[i].birthDate + '</td><td>' + data[i].address.street + ' ' + data[i].address.city + ' ' +
                        data[i].address.postalCode + '</td><td>' + data[i].team.team + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event, ' + data[i].id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data[i].id + ')">Usuń</button></td></tr>');
                }
            } else {
                swal("Brak informacji", "Nie znaleziono żołnierza o takim nazwisku.", "info");
            }
        }
    });
}

searchUser = () => {
    let username = $("#searchUser").val();
    $.ajax({
        type: "GET",
        url: "/admin/users/searchByUsername/" + username,
        success: (data) => {
            if (data.length != 0) {
                $("#usersTable tbody tr").remove();
                $("#usersTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.username + '</td><td>' + data.email + '</td>' +
                    '<td>' + data.role.role + '</td><td>' + data.enabled + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateUser(event, ' + data.id + ')">Edytuj</button></td>' +
                    '<td><button type="button" class="btn btn-danger update" onclick="deleteUser(event, ' + data.id + ')">Usuń</button></td></tr>');
            } else {
                swal("Brak informacji", "Nie znaleziono użytkownika o tak nazwie.", "info");
            }
        }
    });
}

deleteSoldier = (e, soldierId) => {
    $.ajax({
        type: "DELETE",
        url: "/admin/soldiers/" + soldierId,
        success: (msg) => {
            swal("Usunięto", msg, "success");
            $("tr[id="+soldierId+"]").remove();
        },
        error: (msg) => {
            swal("Wystąpił błąd", msg, "error");
        }
    });
    e.preventDefault();
}

$(() => {
    $("#addSoldierForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $('#addSoldierForm').serialize(),
            type: $('#addSoldierForm').attr('method'),
            url: $('#addSoldierForm').attr('action'),
            success: (data) => {
                if (data != "") {
                    $("#soldiersTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.firstName + ' ' + data.lastName + '</td><td>' + data.rank.rank + '</td>' +
                        '<td>' + data.personalEvidenceNumber + '</td><td>' + data.birthDate + '</td><td>' + data.address.street + ' ' + data.address.city + ' ' +
                        data.address.postalCode + '</td><td>' + data.team.team + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event, ' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Dodano żołnierza", "success");
                } else {
                    swal("Coś poszło nie tak", "Wystąpił błąd z walidacją", "info");
                }
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");;
            }
        });
    });

    $("#updateSoldierForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $("#updateSoldierForm").serialize(),
            type: "PUT",
            url: "/admin/soldiers",
            success: (data) => {
                if (data != "") {
                    $("#soldiersTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.firstName + ' ' + data.lastName + '</td><td>' + data.rank.rank + '</td>' +
                        '<td>' + data.personalEvidenceNumber + '</td><td>' + data.birthDate + '</td><td>' + data.address.street + ' ' + data.address.city + ' ' +
                        data.address.postalCode + '</td><td>' + data.team.team + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event,' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Zedytowano żołnierza", "success");
                } else {
                    swal("Coś poszło nie tak", "Wystąpił błąd z walidacją", "info");
                }
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");;
            }
        });
    });
});

updateSoldier = (e, soldierId) => {
    e.preventDefault();
    $.ajax({
        type: "GET",
        url: "/admin/soldiers/" + soldierId,
        success: (data) => {
            $("#updateModal #updateSoldierForm #firstName").val(data.firstName);
            $("#updateModal #updateSoldierForm #lastName").val(data.lastName);
            $("#updateModal #updateSoldierForm #personalEvidenceNumber").val(data.personalEvidenceNumber);
            $("#updateModal #updateSoldierForm #birthDate").val(data.birthDate);
            $("#updateModal #updateSoldierForm #rank option[value='" + data.rank.id + "']").prop('selected', true);
            $("#updateModal #updateSoldierForm #address option[value='" + data.address.id + "']").prop('selected', true);
            $("#updateModal #updateSoldierForm #team option[value='" + data.team.id + "']").prop('selected', true);
            $("#updateModal #updateSoldierForm #id").val(soldierId);
        }
    });
}