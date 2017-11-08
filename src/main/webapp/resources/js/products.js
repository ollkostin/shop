$(document).ready(function () {
    getCart('', function (productList) {
        onSuccessLoadCart(productList);
        getProducts(currentPage, currentSize, showProducts);
    }, onErrorAlert);
});

function showProducts(productList) {
    let products = $('#products');
    clearChildNodes(products);
    productList.forEach(product => {
        let tr = $('<tr></tr>');
        tr.append(buildTableData(product['id']));
        tr.append(
            buildTableData(
                buildImg('api/products/' + product['id'] + '/photos/' + product['pathToPhoto'], 50, 50)
            )
        );
        tr.append(buildTableData(buildProductLink(product)));
        tr.append(buildTableData(product['price']));
        tr.append(buildTableData(addToOrRemoveFromCartButton(product['id'], addToCartProductListPageCb, removeFromCartProductListPageCb)));
        products.append(tr);
    });
}


function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentSize !== newSize) {
        currentSize = newSize;
        getProducts(currentPage, currentSize, showProducts)
    }
}

function addToCartProductListPageCb() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    addToCart(productId, '',
        function (resp) {
            onAdd(resp, productId);
        }, onErrorAlert);
}

function onAdd(resp, productId) {
    onSuccessAddToCart(resp, productId, removeFromCartProductListPageCb);
}

function onRemove(resp, productId) {
    onSuccessRemoveFromCart(resp, productId, addToCartProductListPageCb);
}

function removeFromCartProductListPageCb() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    removeFromCart(productId, '', true, function (resp) {
        onRemove(resp, productId);
    }, onErrorAlert);
}