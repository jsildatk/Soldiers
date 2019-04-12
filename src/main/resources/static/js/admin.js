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
                    $("#soldiersTable").append('<tr><td>'+ data[i].id +'</td><td>' + data[i].firstName + ' ' + data[i].lastName + '</td><td>' + data[i].rank.rank + '</td>' +
                        '<td>' + data[i].personalEvidenceNumber + '</td><td>' + data[i].birthDate + '</td><td>' + data[i].address.street + ' ' + data[i].address.city + ' ' +
                        data[i].address.postalCode + '</td><td>' + data[i].team.team);
                    if (data[i].user != null) {
                        $("#soldiersTable tr:last").append('<td>' + data[i].user.username + '</td><td>' + data[i].user.role.role + '</td>');
                    } else {
                        $("#soldiersTable tr:last").append('<td> </td><td> </td>');
                    }
                    $("#soldiersTable tr:last").append('<td><a href="/admin/soldiers/' + data[i].id + '"><button type="button" id="updateSoldierButton" class="btn btn-warning update">Edytuj</button></a></td>');
                    $("#soldiersTable tr:last").append('<td><a href="/admin/soldiers/' + data[i].id + '"><button type="button" id="deleteSoldierButton" class="btn btn-danger update">Usuń</button></a></td>');
                }
                $("#soldiersTable").append("</tr>");
            } else {
                swal("Brak informacji", "Nie znaleziono żołnierza o takim nazwisku.", "info");
            }
        }
    });
}