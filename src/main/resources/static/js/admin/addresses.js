searchAddress = () => {
    let city = $("#searchAddress").val();
    $.ajax({
        type: "GET",
        url: "/admin/addresses/city/" + city,
        success: (data) => {
            if (data.length != 0) {
                $("#addressesTable tbody tr").remove();
                for (let i = 0; i < data.length; i++) {
                    $("#addressesTable").append('<tr id="' + data[i].id + '"><td>'+ data[i].id +'</td><td>' + data[i].street + '</td><td>' + data[i].city + '</td>' +
                        '<td>' + data[i].postalCode + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateAddress(event, ' + data[i].id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteAddress(event, ' + data[i].id + ')">Usuń</button></td></tr>');
                }
            } else {
                swal("Brak informacji", "Nie znaleziono adresu.", "info");
            }
        }
    });
}

$(() => {
    $("#addAddressForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $("#addAddressForm").serialize(),
            type: $("#addAddressForm").attr("method"),
            url: $("#addAddressForm").attr("action"),
            success: (data) => {
                if (data.street != null) {
                    $("#addressesTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.street + '</td><td>' + data.city + '</td>' +
                        '<td>' + data.postalCode + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateAddress(event, ' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteAddress(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszystko przebiegło pomyślnie", "Dodano adres", "success");
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

    $("#updateAddressForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: {
                street: $("#updateAddressForm #street").val(),
                city: $("#updateAddressForm #city").val(),
                postalCode: $("#updateAddressForm #postalCode").val()
            },
            url: "/admin/addresses/" + $("#updateAddressForm #id").val(),
            type: "PUT",
            success: (data) => {
                if (data.street != null) {
                    $("#addressesTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.street + '</td><td>' + data.city + '</td>' +
                        '<td>' + data.postalCode + '<td><button type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-warning update" onclick="updateAddress(event, ' + data.id + ')">Edytuj</button></td>' +
                        '<td><button type="button" class="btn btn-danger update" onclick="deleteAddress(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszystko przebiegło pomyślnie", "Zedytowano adres", "success");
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
})

updateAddress = (e, addressId) => {
    e.preventDefault();
    $.ajax({
        url: "/admin/addresses/" + addressId,
        type: "GET",
        success: (data) => {
            $("#updateModal #updateAddressForm #street").val(data.street);
            $("#updateModal #updateAddressForm #city").val(data.city);
            $("#updateModal #updateAddressForm #postalCode").val(data.postalCode);
            $("#updateModal #updateAddressForm #id").val(addressId);
        }
    })
}

deleteAddress = (e, addressId) => {
    e.preventDefault();
    $.ajax({
        type: "DELETE",
        url: "/admin/addresses/" + addressId,
        success: (data) => {
            if (data != "Coś poszło nie tak") {
                swal("Usunięto", data, "success");
                $("tr[id=" + addressId + "]").remove();
            } else {
                swal("Wystąpił błąd", data, "error");
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Wystąpił błąd serwera", "error");
        }
    });
}