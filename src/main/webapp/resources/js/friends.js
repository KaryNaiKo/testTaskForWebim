send(function (data) {
    render(data.response);
});

function send(callback) {
    $.ajax({
        url: "/ajax/friends",
        method: "GET",
        dataType: "json",
        success: callback
    });
}
function render(response) {
    let html = '';
    for(let i=0; i<response.items.length;i++){
        let f = response.items[i];
        html += "<div class=\"card col-sm\">" +
            "<img class=\"card-img-top\" src=\""+f.photo_200+"\">" +
            "<div class=\"card-body\">" +
            "<h5 class=\"card-text\">"+f.first_name+' '+f.last_name+"</h5>" +
            "</div>" +
            "</div>";
    }
    $("#friends").html(html);
}