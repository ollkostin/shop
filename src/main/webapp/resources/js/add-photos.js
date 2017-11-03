let ids = ['file-2', 'file-3', 'file-4', 'file-5'];

function onAddPhoto() {
    if (ids.length > 0) {
        let i = ids.pop();
        if (ids.length == 0) {
            $("#add-file").hide();
        }
        let file = $('<div class="btn-group ' + i + '" >' +
            '<input class="btn ' + i + '" type="file" name="photos" required/>' +
            '<button type="button" ids="' + i + '" class="btn btn-danger" onclick="onDeletePhoto(event)">-</button>' +
            '</div>');
        $('#photos').append(file);
    }
}

function onDeletePhoto(event) {
    let btn = $(event.target);
    let parents = btn.parents();
    if (ids.length == 0){
        $("#add-file").show();
    }
    ids.push(btn.attr('ids'));
    parents.find('div.' + btn.attr('ids')).remove();
}