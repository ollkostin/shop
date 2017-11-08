let currentCartPage = 0;
let currentCartPageSize = 5;

function getCart(page, size, prefix, success, error) {
    let options = page || size ? '?' : '';
    if (page)
        options += 'page=' + page;
    if (page && size)
        options += '&';
    if (size)
        options += 'size=' + size;

    $.ajax({
        type: 'GET',
        url: prefix + 'api/cart/' + options,
        success: function (response) {
            success(response);
            currentCartPage = page;
            currentCartPageSize = size;
        },
        error: error
    })
}

function addToCart(productId, prefix, success, error) {
    $.ajax({
        type: 'PUT',
        url: prefix + 'api/cart/products/' + productId,
        success: success,
        error: error
    })
}

function removeFromCart(productId, prefix, removeAll, success, error) {
    let url = prefix + 'api/cart/products/' + productId;
    if (removeAll) {
        url = url + '?removeAll= ' + removeAll;
    }
    $.ajax({
        type: 'DELETE',
        url: url,
        success: success,
        error: error
    })
}

function clearCart(success, error) {
    $.ajax({
        type: 'DELETE',
        url: 'api/cart/',
        success: success,
        error: error
    })
}

function getTotalPrice(success, error) {
    $.ajax({
        type: 'GET',
        url: 'api/cart/total',
        success: success,
        error: error
    })
}

function getProductInCartIds(prefix, success, error) {
    $.ajax({
        type: 'GET',
        url: prefix + 'api/cart/products',
        success: success,
        error: error
    });
}