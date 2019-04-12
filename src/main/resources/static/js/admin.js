searchSoldier = () => {
    let lastName = $("#searchSoldier").val();
    $.ajax({
        type: "GET",
        url: "/admin/searchByLastName/" + lastName,
        success: (data) => {
            console.log(data);
            if (data.length != 0) {
                $("#soldiersTable tbody tr").remove();
                for (let i = 0; i < data.length; i++) {
                    $("#soldiersTable").append('<tr id="' + data[i].id + '"><td>'+ data[i].id +'</td><td>' + data[i].firstName + ' ' + data[i].lastName + '</td><td>' + data[i].rank.rank + '</td>' +
                        '<td>' + data[i].personalEvidenceNumber + '</td><td>' + data[i].birthDate + '</td><td>' + data[i].address.street + ' ' + data[i].address.city + ' ' +
                        data[i].address.postalCode + '</td><td>' + data[i].team.team);
                    if (data[i].user != null) {
                        $("#soldiersTable tr:last").append('<td>' + data[i].user.username + '</td><td>' + data[i].user.role.role + '</td>');
                    } else {
                        $("#soldiersTable tr:last").append('<td> </td><td> </td>');
                    }
                    $("#soldiersTable tr:last").append('<td><button type="button" class="btn btn-warning update" onclick="updateSoldier(event,' + data[i].id + ')">Edytuj</button></td>');
                    $("#soldiersTable tr:last").append('<td><button type="button" class="btn btn-danger update" onclick="deleteSoldier(event, ' + data[i].id + ')">Usuń</button></td>');
                }
                $("#soldiersTable").append("</tr>");
            } else {
                swal("Brak informacji", "Nie znaleziono żołnierza o takim nazwisku.", "info");
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