let productListIds = [];

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

function addToCartButton(productId, cb) {
    let button = $('<button id="add-btn-' + productId + '" class="btn btn-success">Add to cart</button>');
    button.click(cb);
    return button;
}

function buildImg(pathToPhoto, width, height) {
    let img = $('<img width="' + width + '" height="' + height + '"/>');
    img.prop('alt', 'product image');
    img.prop('src', pathToPhoto);
    return img;
}

function addToOrRemoveFromCartButton(productId, addCb, deleteCb) {
    return productListIds.includes(productId) ? removeFromCartButton(productId, deleteCb) : addToCartButton(productId, addCb);
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
}

function onSuccessAddToCart(resp, productId, deleteCb) {
    $('#add-btn-' + productId).replaceWith(removeFromCartButton(productId, deleteCb));
}

function onSuccessRemoveFromCart(resp, productId, deleteCb) {
    $('#remove-btn-' + productId).replaceWith(addToCartButton(productId, deleteCb));
}

function onSuccessLoadCart(productList) {
    productListIds = productList.map(product => product['id'])
}

function onErrorAlert(resp) {
    alert('code: ' + resp.responseJSON.code + '\n Message: ' + resp.responseJSON.error);
}

function removeFromCartButton(productId, cb) {
    let removeBtn = $('<button id="remove-btn-' + productId + '" class="btn btn-sm btn-danger">Remove from cart</button>');
    removeBtn.click(cb);
    return removeBtn;
}