let first, last, totalPages;

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
    first = productPage.first;
    last = productPage.last;
    totalPages = productPage.totalPages;
    productPage.content.forEach(function (product) {
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
        if (products.data('showRemoveButton')) {
            tr.append(buildTableData(removeProductButton(product['id'], onRemoveProduct)));
        }
        products.append(tr);
    });
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
        currentRow.remove();
        alert('Product was deleted');
        changePageOnDelete();
    }, onErrorAlert);
}

function changePageOnDelete() {
    if ($('#products tr').length === 0) {
        if (first) {
            getProducts(currentCartPage, currentCartPageSize, showProducts, onErrorAlert);
        } else if (last) {
            getProducts(currentCartPage - 1, currentCartPageSize, showProducts, onErrorAlert);
        }
    } else if ((first && totalPages != 1) || (!first && !last)) {
        getProducts(currentCartPage, currentCartPageSize, showProducts, onErrorAlert);
    }
}