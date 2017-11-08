let productListIds = [];

let alreadyInCartMsg = function () {
    return $('<p>Already in cart</p>');
};

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

function cartButton(productId, cb) {
    let button = $('<button id="cart-btn-' + productId + '" class="btn btn-success">Add to cart</button>');
    button.click(cb);
    return button;
}

function buildImg(pathToPhoto, width, height) {
    let img = $('<img width="' + width + '" height="' + height + '"/>');
    img.prop('alt', 'product image');
    img.prop('src', pathToPhoto);
    return img;
}

function cartButtonOrAlreadyInCartMessage(productId, cb) {
    if (productListIds.includes(productId)) {
        return alreadyInCartMsg;
    } else {
        return cartButton(productId, cb);
    }
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
}

function onSuccessAddToCart(resp, productId) {
    $('#cart-btn-' + productId).replaceWith(alreadyInCartMsg);
}

function onSuccessLoadCart(productList) {
    productListIds = productList.map(product => product['id'])
}

function onErrorLoad(resp) {
    alert('code: ' + resp.responseJSON.code + '\n Message: ' + resp.responseJSON.error);
}