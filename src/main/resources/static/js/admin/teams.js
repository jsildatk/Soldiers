getMissions = (e, teamId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/teams/missions/" + teamId,
        type: "GET",
        success: (data) => {
            let missions = "";
            for (let i = 0; i < data.length; i++) {
                missions += "* " + data[i].mission + "<br />";
            }
            $("#missionsModal .modal-body").html(missions);
        }
    });
}

getSoldiers = (e, teamId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/teams/soldiers/" + teamId,
        type: "GET",
        success: (data) => {
            let soldiers = "";
            for (let i = 0; i < data.length; i++) {
                soldiers += "* " + data[i].firstName + " " + data[i].lastName + "<br />";
            }
           $("#soldiersModal .modal-body").html(soldiers);
        }
    });
}

searchTeam = () => {
    let team = $("#searchTeam").val();
    $.ajax({
        url: "/admin/teams/team/" + team,
        type: "GET",
        success: (data) => {
            if (data != "") {
                $("#teamsTable tbody tr").remove();
                $("#teamsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.team + '</td>'
                    + '<td><button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#missionsModal" onclick="getMissions(event, ' + data.id + ')">Misje</button></td>'
                    + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#soldiersModal" onclick="getSoldiers(event, ' + data.id + ')">Żołnierze</button></td>'
                    + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateTeam(event, ' + data.id + ')">Edytuj</button></td>'
                    + '<td><button type="button" class="btn btn-danger delete" onclick="deleteTeam(event, ' + data.id + ')">Usuń</button></td></tr>');
            } else {
                swal("Brak informacji", "Nie znaleziono grupy.", "info");
            }
        }
    });
}

$(() => {
    $("#addTeamForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $("#addTeamForm").serialize(),
            url: $("#addTeamForm").attr("action"),
            type: $("#addTeamForm").attr("method"),
            success: (data) => {
                if (data.team != null) {
                    $("#teamsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.team + '</td>'
                        + '<td><button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#missionsModal" onclick="getMissions(event, ' + data.id + ')">Misje</button></td>'
                        + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#soldiersModal" onclick="getSoldiers(event, ' + data.id + ')">Żołnierze</button></td>'
                        + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateTeam(event, ' + data.id + ')">Edytuj</button></td>'
                        + '<td><button type="button" class="btn btn-danger delete" onclick="deleteTeam(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszystko przebiegło pomyślnie", "Dodano grupę", "success");
                } else {
                    swal("Wystapił problem z walidacją", data[0].defaultMessage, "info");
                }
            },
            error: () => {
                swal("Wystąpił błąd", "Coś poszło nie tak", "error");
            }
        });
    });

    $("#updateTeamForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: { team: $("#updateTeamForm #team").val() },
            url: "/admin/teams/" + $("#updateTeamForm #id").val(),
            type: "PUT",
            success: (data) => {
                if (data.team != null) {
                    $("#teamsTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.team + '</td>'
                        + '<td><button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#missionsModal" onclick="getMissions(event, ' + data.id + ')">Misje</button></td>'
                        + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#soldiersModal" onclick="getSoldiers(event, ' + data.id + ')">Żołnierze</button></td>'
                        + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateTeam(event, ' + data.id + ')">Edytuj</button></td>'
                        + '<td><button type="button" class="btn btn-danger delete" onclick="deleteTeam(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszystko przebiegło pomyślnie", "Zedytowano grupę", "success");
                } else {
                    swal("Wystapił problem z walidacją", data[0].defaultMessage, "info");
                }
            },
            error: () => {
                swal("Wystąpił błąd", "Coś poszło nie tak", "error");
            }
        });
    });
});

updateTeam = (e, teamId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/teams/" + teamId,
        type: "GET",
        success: (data) => {
            $("#updateTeamForm #team").val(data.team);
            $("#updateTeamForm #id").val(teamId);
        }
    });
}


deleteTeam = (e, teamId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/teams/" + teamId,
        type: "DELETE",
        success: (data) => {
            if (data == "Coś poszło nie tak") {
                swal("Wystąpił błąd", data, "error");
            } else {
                swal("Usunięto", data, "success");
                $("tr[id="+teamId+"]").remove();
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Coś poszło nie tak", "error");
        }
    });
}