let first, last, totalPages;

$(document).ready(function () {
    $('#next-page').click(nextPage);
    $('#prev-page').click(previousPage);
    getProductInCartIds('', function (productList) {
        onSuccessLoadCart(productList);
        getProducts(currentProductPage, currentProductPageSize, showProducts);
    }, onErrorAlert);
});

function productRow(product, showRemove) {
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
    if (showRemove) {
        let button = !product['removed'] ? removeButton(product['id']) : restoreButton(product['id']);
        tr.append(buildTableData(button));
    }
    return tr;
}


function showProducts(productPage) {
    let products = $('#products');
    clearChildNodes(products);
    setPagination(productPage);
    first = productPage.first;
    last = productPage.last;
    totalPages = productPage.totalPages;
    productPage.content.forEach(product =>
        products.append(productRow(product, products.data('showRemoveButton')))
    );
}

function removeButton(productId) {
    return removeProductButton(productId, onRemoveProduct);
}

function restoreButton(productId) {
    return restoreProductButton(productId, onRestoreProduct);
}


function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentProductPageSize !== newSize) {
        currentProductPageSize = newSize;
        if (last && !first) {
            --currentProductPage;
        }
        getProducts(currentProductPage, currentProductPageSize, showProducts, onErrorAlert)
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
    getProducts(currentProductPage + 1, currentProductPageSize, showProducts, onErrorAlert);
}

function previousPage() {
    getProducts(currentProductPage - 1, currentProductPageSize, showProducts, onErrorAlert);
}

function onRemoveProduct() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    deleteProduct(productId, '', function (resp) {
        $("#remove-btn-" + productId).replaceWith(restoreButton(productId));
        // changePageOnDeleteOrRestore();
    }, onErrorAlert);
}

function onRestoreProduct() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    restoreProduct(productId, '', function (resp) {
        $("#restore-btn-" + productId).replaceWith(removeButton(productId));
        // changePageOnDeleteOrRestore();
    }, onErrorAlert);
}

function changePageOnDeleteOrRestore() {
    if ($('#products tr').length === 0) {
        if (first) {
            getProducts(currentProductPage, currentProductPageSize, showProducts, onErrorAlert);
        } else if (last) {
            getProducts(currentProductPage - 1, currentProductPageSize, showProducts, onErrorAlert);
        }
    } else if ((first && totalPages !== 1) || (!first && !last)) {
        getProducts(currentProductPage, currentProductPageSize, showProducts, onErrorAlert);
    }
}