calculateItems = (amount) => {
    if (amount == 1) {
        return " przedmiot";
    } else if (amount > 1 && amount < 5) {
        return " przedmioty";
    } else {
        return " przedmiotów";
    }
}

$(() => {
    $("#addItem").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $("#addItem").serialize(),
            url: "/equipment/" + $("#id").val(),
            type: "PUT",
            success: (data) => {
                if (data.firstName != null) {
                    $("#equipmentTable tbody tr").remove();
                    for (let i = 0; i < data.items.length; i++) {
                        $("#equipmentTable").append('<tr id="' + data.items[i].id + '"><td>' + data.items[i].item +'</td>'
                            + '<td><button type="button" class="btn btn-danger delete" onclick="deleteItem(event,'+data.items[i].id+','+data.id+')">Usuń</button></td>'
                            + '</tr>');
                    }
                    $("#itemsAmount").text("Posiadasz aktualnie " + data.items.length + calculateItems(data.items.length) + " w ekwipunku.");
                    swal("Wszystko przebiegło pomyślnie", "Dodano przedmiot(y)", "success");
                } else {
                    swal("Problem", data, "info");
                }
            },
            error: () => {
                swal("Wystąpił błąd", "Wystąpił błąd serwera", "error");
            }
        });
    });
});

deleteItem = (e, itemId, soldierId) => {
    e.preventDefault();
    $.ajax({
        url: "/equipment/" + itemId + "/" + soldierId,
        type: "DELETE",
        success: (data) => {
            if (data.firstName != null) {
                $("tr[id=" + itemId + "]").remove();
                $("#itemsAmount").text("Posiadasz aktualnie " + data.items.length + calculateItems(data.items.length) + " w ekwipunku.");
                swal("Usunięto", "Usunięto przedmiot" , "success");

            } else {
                swal("Wystąpił błąd", "Wystąpił problem", "error");
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Wystąpił błąd serwera", "error");
        }
    });
}