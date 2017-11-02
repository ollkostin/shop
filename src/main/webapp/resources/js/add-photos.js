function onAddPhoto() {
    let input = $('<input class="file-input" type="file" name="photos"/> <button type="button" class="btn btn-danger" onclick="onDeletePhoto(event)">-</button>');
    $('#photos').append(input);
}

function onDeletePhoto(event) {
   $(event.target).parents().closest('.file-input').remove();
}