function getCart(success, error) {
    getUserCart('api/cart/', success, error)
}

function getUserCart(url, success, error) {
    $.ajax({
        type: 'GET',
        url: url,
        success: success,
        error: error
    })
}

function addProductToCart(url, success, error) {
    $.ajax({
        type: 'PUT',
        url: url,
        success: success,
        error: error
    })
}


function removeProductFromCart(productId, success, error) {
    $.ajax({
        type: 'DELETE',
        url: 'api/cart/product/' + productId,
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