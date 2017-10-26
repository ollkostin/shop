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

function cartButton() {
    let button = $('<button class="btn btn-success">Add to cart</button>');
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
        alert('Add product');
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
}