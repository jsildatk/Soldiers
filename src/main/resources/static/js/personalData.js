$(() => {
    $("#updateSoldierData").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: {
                firstName: $("#updateSoldierData #firstName").val(),
                lastName: $("#updateSoldierData #lastName").val(),
                personalEvidenceNumber: $("#updateSoldierData #personalEvidenceNumber").val(),
                birthDate: $("#updateSoldierData #birthDate").val(),
                address: $("#updateSoldierData #address").val()
            },
            url: "/personalData/" + $("#updateSoldierData #id").val(),
            type: "PUT",
            success: (data) => {
                if (data.firstName != null) {
                    swal("Wszystko przebiegło pomyślnie", "Zedytowano dane", "success")
                        .then(() => {
                            window.location.reload();
                        });
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
                }
            },
            error: () => {
                swal("Błąd", "Wystąpił błąd serwera", "error");
            }
        });
    });
});