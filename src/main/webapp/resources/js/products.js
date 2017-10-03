getProducts(currentPage, currentSize, onSuccessLoadProducts);

function showProducts(productList) {
    let products = $('#products');
    clearChildNodes(products);
    productList.forEach(el => {
        let tr = $('<tr></tr>');
        tr.append(buildTableData(el['id'], buildProductLink));
        tr.append(buildTableData(el['name']));
        tr.append(buildTableData(el['pathToPhoto'], buildImg));
        tr.append(buildTableData(el['price']));
        tr.append(buildTableData(cartButton()));
        products.append(tr);
    });
}

function buildProductLink(id) {
    return $('<a href="products/' + id + '">' + id + '</a>');
}

function buildImg(pathToPhoto) {
    let img = $('<img/>');
    img.prop('alt', 'product image');
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
        getProducts(currentPage, currentSize , onSuccessLoadProducts)
    }
}

function onSuccessLoadProducts (resp) {
    showProducts(resp);
}
