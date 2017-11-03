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

function cartButton(productId) {
    let button = $('<button id="cart-btn-' + productId + '" class="btn btn-success">Add to cart</button>');
    button.click(addToCart);
    return button;
}

function buildImg(pathToPhoto, width, height) {
    let img = $('<img width="' + width + '" height="' + height + '"/>');
    img.prop('alt', 'product image');
    img.prop('src', pathToPhoto);
    return img;
}

function addToCart() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    addProductToCart(productId, function (resp) {
        $('#cart-btn-' + productId).replaceWith(alreadyInCartMsg);
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
}

function cartButtonOrAlreadyInCartMessage(productId) {
    if (productListIds.includes(productId)) {
        return alreadyInCartMsg;
    }
    else {
        return cartButton(productId);
    }
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
}