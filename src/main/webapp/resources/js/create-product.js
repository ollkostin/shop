$(document).ready(() => {
    $('input[type=file]').click(function () {
        $('input[type=file]').change(function (evt) {
            let filename = evt.target.files[0].name;
            $(evt.target).attr('data-file', filename);
            let dataFileId = $(evt.target).data('fileId');
            $('ul[data-file-id=' + dataFileId + ']').attr('data-file', filename);
        });

    });

    $('form#create-product').submit(event => {
        event.preventDefault();
        clearErrorMessages();
        let productDTO = new FormData($('form#create-product')[0]);
        createProduct(productDTO,
            resp => {
                location.href = '../products/' + resp;
            },
            resp => {
                let errors = resp.responseJSON;
                for (let key in errors) {
                    for (let k in errors[key]) {
                        let el = $('<li>' + errors[key][k] + '</li>');
                        if (keys.includes(key)) {
                            $('#' + key + '-error').append(el);
                        } else {
                            $('ul[data-file="' + key + '"]').append(el);
                        }
                    }
                }
            });
    })
})

let fileIndex = [2, 3, 4, 5];
let keys = ['name', 'description', 'price'];

function onAddPhoto() {
    let ind = fileIndex.shift();
    if (fileIndex.length === 0) {
        $('#add-file').hide();
    }
    let fileEl = $('<div class="col-sm-6 btn-group" data-file-id="' + ind + '">' +
        '<input class="btn" type="file" name="photos" required data-file-id="' + ind + '"/>' +
        '<button class="btn btn-danger" data-file-id="' + ind + '" onclick="onDeletePhoto(event)">-</button>' +
        '</div>' +
        '<div class="col-sm-push-3 col-sm-12">' +
        '<ul data-file-id="' + ind + '" data-file></ul>' +
        '</div>')
    $('#photos').append(fileEl);
}

function onDeletePhoto(event) {
    let btn = $(event.target);
    let fileId = btn.data('fileId');
    fileIndex.unshift(fileId);
    $('[data-file-id=' + fileId + ']').remove();
    $('#add-file').show();
}

function clearErrorMessages() {
    for (let fileId = 1; fileId < 6; fileId++) {
        $('ul[data-file-id=' + fileId + ']').empty();
    }
    for (let key in keys) {
        $('#' + keys[key] + '-error').empty();
    }
}