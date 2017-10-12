getProducts(currentPage, currentSize, onSuccessLoadProducts);

function showProducts(productList) {
    let products = $('#products');
    clearChildNodes(products);
    productList.forEach(product => {
        let tr = $('<tr></tr>');
        tr.append(
            buildTableData(
                buildImg('api/products/' + product['id'] + '/photos/' + product['pathToPhoto'], 50, 50)
            )
        );
        tr.append(buildTableData(buildProductLink(product)));
        tr.append(buildTableData(product['price']));
        tr.append(buildTableData(cartButton()));
        products.append(tr);
    });
}

function buildProductLink(product) {
    return $('<a href="products/' + product['id'] + '">' + product['name'] + '</a>');
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
