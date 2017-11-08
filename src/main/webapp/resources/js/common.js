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
    productListIds.push(Number(productId));
}

function onSuccessRemoveFromCart(resp, productId, deleteCb) {
    $('#remove-btn-' + productId).replaceWith(addToCartButton(productId, deleteCb));
    productListIds.splice(productListIds.indexOf(Number(productId)),1);
}

function onSuccessLoadCart(resp) {
    productListIds = resp;
}

function onErrorAlert(resp) {
    alert('code: ' + resp.responseJSON.code + '\n Message: ' + resp.responseJSON.error);
}

function removeFromCartButton(productId, cb) {
    let removeBtn = $('<button id="remove-btn-' + productId + '" class="btn btn-sm btn-danger">Remove from cart</button>');
    removeBtn.click(cb);
    return removeBtn;
}

function setPagination(page) {
    if (page.totalPages === 1 || page.numberOfElements === 0) {
        $('#next-page').hide();
        $('#prev-page').hide();
    } else {
        if (page.first) {
            $('#next-page').show();
            $('#prev-page').hide();
        } else if (page.last) {
            $('#prev-page').show();
            $('#next-page').hide();
        } else {
            $('#prev-page').show();
            $('#next-page').show();
        }
    }
}

function removeProductButton(productId, cb) {
    let removeBtn = $('<button id="remove-btn-' + productId + '" class="btn btn-sm btn-danger">Remove product</button>');
    removeBtn.click(cb);
    return removeBtn;
}