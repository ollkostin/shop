function getCart(prefix, success, error) {
    $.ajax({
        type: 'GET',
        url: prefix + 'api/cart/',
        success: success,
        error: error
    })
}

function addToCart(productId, prefix, success, error) {
    $.ajax({
        type: 'PUT',
        url: prefix + 'api/cart/product/' + productId,
        success: success,
        error: error
    })
}

function removeFromCart(productId, prefix, removeAll, success, error) {
    let url = prefix + 'api/cart/product/' + productId;
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