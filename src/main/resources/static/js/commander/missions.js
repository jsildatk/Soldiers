endMission = (e, missionId) => {
    e.preventDefault();
    $.ajax({
        url: "/missions/" + missionId,
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
                $("#missionsTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>' + data.mission + '</td><td>' + teams + '</td>' +
                    '<td>' + data.startDate + '</td><td>' + data.endDate + '</td><td><button type="button" class="btn btn-danger update" onclick="deleteMission(event, ' + data.id + ')">Usuń</button><td></td></tr>');
                swal("Wszytko przebiegło pomyślnie", "Zakończono misję", "success");
            } else {
                swal("Problem", data, "info");
            }
        }, error: () => {
            swal("Coś poszło nie tak", "Błąd", "error");
        }
    });
}