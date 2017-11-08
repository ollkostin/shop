$(document).ready(function () {
    $('#next-page').click(nextPage);
    $('#prev-page').click(previousPage);
    getProductInCartIds('', function (productList) {
        onSuccessLoadCart(productList);
        getProducts(currentProductPage, currentProductPageSize, showProducts);
    }, onErrorAlert);
});

function showProducts(productPage) {
    let products = $('#products');
    clearChildNodes(products);
    setPagination(productPage);
    productPage.content.forEach(product => {
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
        tr.append(buildTableData(removeProductButton(product['id'], onRemoveProduct)));
        products.append(tr);
    });
}


function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentProductPageSize !== newSize) {
        currentProductPageSize = newSize;
        getProducts(currentProductPage, currentProductPageSize, showProducts)
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

function nextPage() {
    getProducts(currentProductPage + 1, currentProductPageSize, showProducts);
}

function previousPage() {
    getProducts(currentProductPage - 1, currentProductPageSize, showProducts);
}

function onRemoveProduct() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    deleteProduct(productId, '', function (resp) {
        currentRow.remove();
        alert('Product was deleted');
    }, onErrorAlert);
}