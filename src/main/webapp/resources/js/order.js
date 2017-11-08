let totalPrice = 0;

$(document).ready(function () {
    $('#next-page').click(nextPage);
    $('#prev-page').click(previousPage);
    getCart(currentCartPage, currentCartPageSize, '', function (resp) {
            showCart(resp);
            if (resp && resp.content.length !== 0) {
                getTotalPrice(function (totalPrice) {
                    $('#total-price').val(totalPrice);
                }, onErrorAlert);
            }
        }, onErrorAlert
    );

});

function showCart(productPage) {
    let products = $('#products');
    setPagination(productPage);
    clearChildNodes(products);
    productPage.content.forEach(product => {
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
        getCart(currentCartPage, currentCartPageSize, '', showCart, onErrorAlert);
    }
}

function nextPage() {
    getCart(currentCartPage + 1, currentCartPageSize, '', showCart, onErrorAlert);
}

function previousPage() {
    getCart(currentCartPage - 1, currentCartPageSize, '', showCart, onErrorAlert);
}