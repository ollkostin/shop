let currentProductPage = 0;
let currentProductPageSize = 10;


function getProducts(page, size, success, error) {
    let options = page || size ? '?' : '';
    if (page)
        options += 'page=' + page;
    if (page && size)
        options += '&';
    if (size)
        options += 'size=' + size;

    $.ajax({
        type: 'GET',
        url: 'api/products/' + options,
        success: function (response) {
            success(response);
            currentProductPage = page;
            currentProductPageSize = size;
        },
        error: error
    });
}

function getProduct(id, success, error) {
    $.ajax({
        type: 'GET',
        url: '../api/products/' + id,
        success: success,
        error: error
    });
}

function deleteProduct(productId, prefix, success, error) {
    $.ajax({
        type: 'DELETE',
        url: prefix + 'api/products/' + productId,
        success: success,
        error: error
    });
}

function createProduct(product, success, error) {
    $.ajax({
        type: 'POST',
        url: '../api/products/create/',
        data: product,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success:success,
        error:error
    });
}