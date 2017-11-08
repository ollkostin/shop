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

function getProduct(id, success , error) {
    $.ajax({
        type: 'GET',
        url: '../api/products/' + id,
        success: success,
        error: error
    });
}