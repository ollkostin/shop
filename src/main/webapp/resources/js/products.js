getProducts(currentPage, currentSize, onSuccessLoadProducts);

function showProducts(productList) {
    let products = $('#products');
    clearChildNodes(products);
    productList.forEach(el => {
        let tr = $('<tr></tr>');
        tr.append(buildTableData(buildImg(el)));
        tr.append(buildTableData(buildProductLink(el)));
        tr.append(buildTableData(el['price']));
        tr.append(buildTableData(cartButton()));
        products.append(tr);
    });
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
}

function buildImg(product) {
    let img = $('<img width="50" height="50"/>');
    img.prop('alt', 'product image');
    img.prop('src', 'api/products/' + product['id'] + '/photos/' + product['pathToPhoto']);
    return img;
}


function cartButton() {
    let button = $('<button class="btn btn-success">Add to cart</button>');
    button.click(addToCart);
    return button;
}

function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentSize !== newSize) {
        currentSize = newSize;
        getProducts(currentPage, currentSize, onSuccessLoadProducts)
    }
}

function onSuccessLoadProducts(resp) {
    showProducts(resp);
}
