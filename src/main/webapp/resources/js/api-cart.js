function getUserCart(success, error) {
    $.ajax({
        type: 'GET',
        url: 'api/cart/',
        success: success,
        error: error
    })
}

function addProductToCart(productId, success, error) {
    $.ajax({
        type: 'PUT',
        url: 'api/cart/product/' + productId,
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