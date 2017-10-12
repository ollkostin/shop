function buildTableData(value, mapper) {
    let td = $('<td></td>');
    if (mapper) {
        td.append(mapper(value));
    } else {
        td.append(value);
    }
    return td;
}

function clearChildNodes(element) {
    element.empty()
}

function cartButton() {
    let button = $('<button class="btn btn-success">Add to cart</button>');
    button.click(addToCart);
    return button;
}

function buildImg(pathToPhoto, width, height) {
    let img = $('<img width="' + width + '" height="' + height + '"/>');
    img.prop('alt', 'product image');
    img.prop('src', pathToPhoto);
    return img;
}

function addToCart(e) {
    console.log('not implemented yet');
}