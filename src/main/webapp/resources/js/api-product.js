let currentPage = 0;
let currentSize = 10;


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
        url: 'api/product/' + options,
        success: function (response) {
            success(response);
            currentPage = page;
            currentSize = size;
        },
        error: error
    });
}

function getProduct(id, success , error) {
    $.ajax({
        type: 'GET',
        url: 'api/product/' + id,
        success: success,
        error: error
    });
}