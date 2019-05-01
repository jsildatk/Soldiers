pad = (n) => {
    return n < 10 ? "0" + n : n;
}

convertDate = (date) => {
    let addDate = new Date(date);
    return addDate.getFullYear() + "-" + pad(addDate.getMonth()+1) + "-" + pad(addDate.getDate()) + " " + addDate.getHours() + ":"
        + addDate.getMinutes() + ":" + addDate.getSeconds() + ".0";
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