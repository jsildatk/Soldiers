deleteMission = (e, missionId) => {
    e.preventDefault();
    $.ajax({
        url: "/missions/" + missionId,
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