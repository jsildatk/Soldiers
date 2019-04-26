pad = (n) => {
    return n < 10 ? "0" + n : n;
}

convertDate = (date) => {
    let addDate = new Date(date);
    return addDate.getFullYear() + "-" + pad(addDate.getMonth()+1) + "-" + pad(addDate.getDate()) + " " + addDate.getHours() + ":"
        + addDate.getMinutes() + ":" + addDate.getSeconds() + ".0";
}

searchNews = () => {
    let title = $("#searchNews").val();
    $.ajax({
        url: "/news/rest/title/" + title,
        type: "GET",
        success: (data) => {
            if (data != "") {
                $("#newsTable tbody tr").remove();
                $("#newsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.title + '</td>'
                    + '<td>' + data.user.username + '</td><td>' + convertDate(data.addDate) + '</td>'
                    + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#contentModal" onclick="getContent(event, ' + data.id + ')">Treść</button></td>'
                    + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateNews(event, ' + data.id + ')">Edytuj</button></td>'
                    + '<td><button type="button" class="btn btn-danger delete" onclick="deleteNews(event, ' + data.id + ')">Usuń</button></td></tr>');
            } else {
                swal("Brak informacji", "Nie znaleziono ogłoszenia.", "info");
            }
        }
    });
}

getContent = (e, newsId) => {
    e.preventDefault();
    $.ajax({
        url: "/news/rest/" + newsId,
        type: "GET",
        success: (data) => {
            $("#contentModal .modal-body").html(data.content);
        }
    });
}

$(() => {
    $("#addNewsForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: $("#addNewsForm").serialize(),
            url: $("#addNewsForm").attr("action"),
            type: $("#addNewsForm").attr("method"),
            success: (data) => {
                if (data.title != null) {
                    $("#newsTable").append('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.title + '</td>'
                        + '<td>' + data.user.username + '</td><td>' + convertDate(data.addDate) + '</td>'
                        + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#contentModal" onclick="getContent(event, ' + data.id + ')">Treść</button></td>'
                        + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateNews(event, ' + data.id + ')">Edytuj</button></td>'
                        + '<td><button type="button" class="btn btn-danger delete" onclick="deleteNews(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Dodano ogłoszenie", "success");
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
                }
            },
            error: () => {
                swal("Wystąpił błąd", "Błąd serwera", "error");
            }
        });
    });

    $("#updateNewsForm").submit((e) => {
        e.preventDefault();
        $.ajax({
            data: {
                title: $("#updateNewsForm #title").val(),
                content: $("#updateNewsForm #content").val()
            },
            url: "/news/rest/" + $("#updateNewsForm #id").val(),
            type: "PUT",
            success: (data) => {
                if (data.title != null) {
                    $("#newsTable #" + data.id).replaceWith('<tr id="' + data.id + '"><td>'+ data.id +'</td><td>' + data.title + '</td>'
                        + '<td>' + data.user.username + '</td><td>' + convertDate(data.addDate) + '</td>'
                        + '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#contentModal" onclick="getContent(event, ' + data.id + ')">Treść</button></td>'
                        + '<td><button type="button" class="btn btn-warning update" data-toggle="modal" data-target="#updateModal" onclick="updateNews(event, ' + data.id + ')">Edytuj</button></td>'
                        + '<td><button type="button" class="btn btn-danger delete" onclick="deleteNews(event, ' + data.id + ')">Usuń</button></td></tr>');
                    swal("Wszytko przebiegło pomyślnie", "Zedytowano ogłoszenie", "success");
                } else {
                    let validation = "";
                    for (let i = 0; i < data.length; i++) {
                        validation += "* " + data[i].defaultMessage + "\n";
                    }
                    swal("Wystąpił problem z walidacją", validation , "info");
                }
            },
            error: () => {
                swal("Wystąpił błąd", "Błąd serwera", "error");
            }
        });
    });
})

updateNews = (e, newsId) => {
    e.preventDefault();
    $.ajax({
        url: "/news/rest/" + newsId,
        type: "GET",
        success: (data) => {
            $("#updateNewsForm #title").val(data.title);
            $("#updateNewsForm #content").val(data.content);
            $("#updateNewsForm #id").val(newsId);
        }
    });
}

deleteNews = (e, newsId) => {
    e.preventDefault();
    $.ajax({
        url: "/news/rest/" + newsId,
        type: "DELETE",
        success: (data) => {
            if (data == "Coś poszło nie tak") {
                swal("Wystąpił błąd", data, "error");
            } else {
                swal("Usunięto", data, "success");
                $("tr[id="+newsId+"]").remove();
            }
        },
        error: () => {
            swal("Wystąpił błąd", "Błąd serwera", "error");
        }
    });
}