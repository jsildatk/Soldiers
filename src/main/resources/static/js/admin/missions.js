searchMission = () => {
    let mission = $("#searchMission").val();
    $.ajax({
        url: "/admin/missions/mission/" + mission,
        type: "GET",
        success: (data) => {
            if (data != "") {
                let teams = "[";
                for (let i = 0; i < data.teams.length; i++) {
                    if (i < data.teams.length - 1) {
                        teams += data.teams[i].team + " ";
                    } else {
                        teams += data.teams[i].team;
                    }
                }
                teams += "]";
                $("#missionsTable tbody tr").remove();
                $("#missionsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.commander.firstName + ' ' + data.commander.lastName + '</td><td>' + data.mission + '</td><td>' + teams + '</td>' +
                    '<td>' + data.startDate + '</td><td>' + (data.endDate ? data.endDate : 'W trakcie') + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="endMission(event, ' + data.id + ')">Zakończ misję</button></td>' +
                    '<td><button type="button" class="btn btn-danger update" onclick="deleteMission(event, ' + data.id + ')">Usuń</button></td></tr>');
            } else {
                swal("Brak informacji", "Nie znaleziono misji.", "info");
            }
        }
    });
}

$(() => {
    $("#addMissionForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $('#addMissionForm').serialize(),
            type: $('#addMissionForm').attr('method'),
            url: $('#addMissionForm').attr('action'),
            success: (data) => {
                if (data.mission != null) {
                    let teams = "[";
                    for (let i = 0; i < data.teams.length; i++) {
                        if (i < data.teams.length - 1) {
                            teams += data.teams[i].team + " ";
                        } else {
                            teams += data.teams[i].team;
                        }
                    }
                    teams += "]";
                    $("#missionsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.commander.firstName + ' ' + data.commander.lastName + '</td><td>' + data.mission + '</td><td>' + teams + '</td>' +
                        '<td>' + data.startDate + '</td><td>' + (data.endDate ? data.endDate : 'W trakcie') + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="endMission(event, ' + data.id + ')">Zakończ misję</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteMission(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Dodano misję", "success");
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
                }
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");
            }
        });
    });

});

endMission = (e, missionId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/missions/" + missionId,
        type: "PUT",
        success: (data) => {
            if (data.mission != null) {
                let teams = "[";
                for (let i = 0; i < data.teams.length; i++) {
                    if (i < data.teams.length - 1) {
                        teams += data.teams[i].team + " ";
                    } else {
                        teams += data.teams[i].team;
                    }
                }
                teams += "]";
                $("#missionsTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.commander.firstName + ' ' + data.commander.lastName + '</td><td>' + data.mission + '</td><td>' + teams + '</td>' +
                    '<td>' + data.startDate + '</td><td>' + (data.endDate ? data.endDate : 'W trakcie') + '</td><td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="endMission(event, ' + data.id + ')">Zakończ misję</button></td>' +
                    '<td><button type="button" class="btn btn-danger update" onclick="deleteMission(event, ' + data.id + ')">Usuń</button></td></tr>');
                swal("Wszytko przebiegło pomyślnie", "Zakończono misję", "success");
            } else {
                swal("Problem", data, "info");
            }
        }, error: () => {
            swal("Coś poszło nie tak", "Błąd", "error");
        }
    });
}

deleteMission = (e, missionId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/missions/" + missionId,
        type: "DELETE",
        success: (data) => {
            if (data != "Coś poszło nie tak") {
                swal("Usunięto", data, "success");
                $("tr[id=" + missionId + "]").remove();
            } else {
                swal("Wystąpił błąd", data, "error");
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Błąd serwera", "error");
        }
    });
}