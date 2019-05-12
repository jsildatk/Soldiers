getItem = (itemId) => {
    return $.ajax({
        url: "/admin/items/" + itemId,
        type: "GET"
    });
}

getDescription = (e, itemId) => {
    e.preventDefault();
    let promise = getItem(itemId);
    promise.then(data => $("#descriptionModal .modal-body").text(data.description)).catch(error => swal("Wystąpił błąd", "Błąd serwera", "error"));
}

getImage = (e, itemId) => {
    e.preventDefault();
    let promise = getItem(itemId);
    promise.then(data => $("#imageModal .modal-body").attr("src", data.image)).catch(error => swal("Wystąpił błąd", "Błąd serwera", "error"));
}

$(() => {
    $("#addItemForm").submit((e) => {
        e.preventDefault();
        let form = $("#addItemForm")[0];
        $.ajax({
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            data: new FormData(form),
            cache: false,
            type: $('#addItemForm').attr('method'),
            url: $('#addItemForm').attr('action'),
            success: (data) => {
                swal("Dodano przedmiot", data.item, "success")
                    .then(() => {
                        window.location.reload();
                    });
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");
            }
        });
    });

    $("#updateItemForm").submit((e) => {
        e.preventDefault();
        let form = $("#updateItemForm")[0];
        $.ajax({
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            data: new FormData(form),
            cache: false,
            url: "/admin/items/" + $("#updateItemForm #id").val(),
            type: "PUT",
            success: (data) => {
                swal("Zedytowano przedmiot", data.item, "success")
                    .then(() => {
                        window.location.reload();
                    });
            },
            error: () => {
                swal("Coś poszło nie tak", "Błąd", "error");
            }
        });
    });
});

updateItem = (e, itemId) => {
    e.preventDefault();
    let promise = getItem(itemId);
    promise.then(data => {
        $("#updateModal #updateItemForm #item").val(data.item);
        $("#updateModal #updateItemForm #description").val(data.description);
        $("#updateModal #path").val(data.image);
        $("#updateModal #id").val(itemId);
    });
}

deleteItem = (e, itemId) => {
    $.ajax({
        url: "/admin/items/" + itemId,
        type: "DELETE",
        success: (data) => {
            if (data != "Coś poszło nie tak") {
                swal("Usunięto", data, "success");
                $("tr[id="+itemId+"]").remove();
            } else {
                swal("Wystąpił błąd", data, "error");
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Błąd serwera", "error")
        }
    });
}