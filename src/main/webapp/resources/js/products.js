$(document).ready(function () {
    getCart(function (productList) {
        onSuccessLoadCart(productList);
        getProducts(currentPage, currentSize, showProducts);
    }, onErrorLoad);
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
        tr.append(buildTableData(cartButtonOrAlreadyInCartMessage(product['id'], addToCartProductListPageButton)));
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

function addToCartProductListPageButton() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    addProductToCart('api/cart/product/' + productId,
        function (resp) {
            onSuccessAddToCart(resp, productId);
        },
        onErrorLoad);
}
