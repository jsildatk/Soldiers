searchSoldier = () => {
    let lastName = $("#searchSoldier").val();
    $.ajax({
        url: "/admin/soldiers/lastName/" + lastName,
        type: "GET",
        success: (data) => {
            if (data.length != 0) {
                $("#soldiersTable tbody tr").remove();
                for (let i = 0; i < data.length; i++) {
                    $("#soldiersTable").append('<tr id="' + data[i].id + '"><td>'+ data[i].id +'</td><td>' + data[i].firstName + ' ' + data[i].lastName + '</td><td>' + data[i].rank.rank + '</td>' +
                        '<td>' + data[i].personalEvidenceNumber + '</td><td>' + data[i].birthDate + '</td><td>' + (data[i].address ? data[i].address.street + ' ' + data[i].address.city + ' ' +
                        data[i].address.postalCode : 'Brak') + '</td><td>' + (data[i].team ? data[i].team.team : 'Brak') + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event, ' + data[i].id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data[i].id + ')">Usuń</button></td></tr>');
                }
            } else {
                swal("Brak informacji", "Nie znaleziono żołnierza o takim nazwisku.", "info");
            }
        }
    });
}

$(() => {
    $("#addSoldierForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $('#addSoldierForm').serialize(),
            type: $('#addSoldierForm').attr('method'),
            url: $('#addSoldierForm').attr('action'),
            success: (data) => {
                if (data.firstName != null) {
                    $("#soldiersTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.firstName + ' ' + data.lastName + '</td><td>' + data.rank.rank + '</td>' +
                        '<td>' + data.personalEvidenceNumber + '</td><td>' + data.birthDate + '</td><td>' + data.address.street + ' ' + data.address.city + ' ' +
                        data.address.postalCode + '</td><td>' + data.team.team + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event, ' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Dodano żołnierza", "success");
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
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
            data: {
                firstName: $("#updateSoldierForm #firstName").val(),
                lastName: $("#updateSoldierForm #lastName").val(),
                personalEvidenceNumber: $("#updateSoldierForm #personalEvidenceNumber").val(),
                birthDate: $("#updateSoldierForm #birthDate").val(),
                rank: $("#updateSoldierForm #rank").val(),
                address: $("#updateSoldierForm #address").val(),
                team: $("#updateSoldierForm #team").val()
            },
            type: "PUT",
            url: "/admin/soldiers/" + $("#updateSoldierForm #id").val(),
            success: (data) => {
                if (data.firstName != null) {
                    $("#soldiersTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.firstName + ' ' + data.lastName + '</td><td>' + data.rank.rank + '</td>' +
                        '<td>' + data.personalEvidenceNumber + '</td><td>' + data.birthDate + '</td><td>' + data.address.street + ' ' + data.address.city + ' ' +
                        data.address.postalCode + '</td><td>' + data.team.team + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateSoldier(event,' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Zedytowano żołnierza", "success");
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
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
        url: "/admin/soldiers/" + soldierId,
        type: "GET",
        success: (data) => {
            if (data.team != null) {
                $("#updateModal #updateSoldierForm #team option[value='" + data.team.id + "']").prop('selected', true);
            } else {
                $("#updateModal #updateSoldierForm #team option[value='Brak']").prop('selected', true);
            }
            if (data.address != null) {
                $("#updateModal #updateSoldierForm #address option[value='" + data.address.id + "']").prop('selected', true);
            } else {
                $("#updateModal #updateSoldierForm #address option[value='Brak']").prop('selected', true);
            }
            $("#updateModal #updateSoldierForm #firstName").val(data.firstName);
            $("#updateModal #updateSoldierForm #lastName").val(data.lastName);
            $("#updateModal #updateSoldierForm #personalEvidenceNumber").val(data.personalEvidenceNumber);
            $("#updateModal #updateSoldierForm #birthDate").val(data.birthDate);
            $("#updateModal #updateSoldierForm #rank option[value='" + data.rank.id + "']").prop('selected', true);
            $("#updateModal #updateSoldierForm #id").val(soldierId);
        }
    });
}

deleteSoldier = (e, soldierId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/soldiers/" + soldierId,
        type: "DELETE",
        success: (data) => {
            if (data != "Coś poszło nie tak") {
                swal("Usunięto", data, "success");
                $("tr[id="+soldierId+"]").remove();
            } else {
                swal("Wystąpił błąd", data, "error");
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Błąd serwera", "error");
        }
    });
}