let totalPrice = 0;
let first, last, totalPages;

$(document).ready(function () {
    $('#next-page').click(nextOrderPage);
    $('#prev-page').click(previousOrderPage);
    getOrder();
});

function getOrder() {
    getCart(currentCartPage, currentCartPageSize, '', onSuccessLoadOrder, onErrorAlert);
}

function onSuccessLoadOrder(resp) {
    showCart(resp);
    if (resp && resp.numberOfElements !== 0) {
        getTotalPrice(function (resp) {
            $('#total-price').val(resp)
        }, onErrorAlert);
    }
}

function showCart(productPage) {
    let products = $('#products');
    setPagination(productPage);
    clearChildNodes(products);
    first = productPage.first;
    last = productPage.last;
    totalPages = productPage.totalPages;
    productPage.content.forEach(function (product) {
        let tr = $('<tr></tr>');
        tr.append(buildTableData(product['id']));
        tr.append(
            buildTableData(
                buildImg('api/products/' + product['id'] + '/photos/' + product['pathToPhoto'], 35, 35)
            )
        );
        tr.append(buildTableData(product['name']));
        tr.append(buildTableData(product['price']));
        tr.append(buildTableData(product['count']));
        products.append(tr);
    });
}

function onSizeChange() {
    let sizeSelect = document.getElementById("size-select");
    let newSize = sizeSelect.options[sizeSelect.selectedIndex].value;
    if (currentCartPageSize !== newSize) {
        currentCartPageSize = newSize;
        if (last && !first) {
            --currentCartPage;
        }
        getCart(currentCartPage, currentCartPageSize, '', showCart, onErrorAlert);
    }
}

function nextOrderPage() {
    getCart(currentCartPage + 1, currentCartPageSize, '', showCart, onErrorAlert);
}

function previousOrderPage() {
    getCart(currentCartPage - 1, currentCartPageSize, '', showCart, onErrorAlert);
}