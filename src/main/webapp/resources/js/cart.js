let totalPrice = 0;
let first;
let last;
let totalPages;
$(document).ready(function () {
    $('#next-page').click(nextPage);
    $('#prev-page').click(previousPage);
    getCart(currentCartPage, currentCartPageSize, '', onSuccessLoadCart, onErrorAlert);
});

function onSuccessLoadCart(response) {
    showCart(response);
    if (response.numberOfElements > 0) {
        getTotalPrice(
            function (resp) {
                totalPrice = resp;
                $('#total-price').text(totalPrice)
            }, onErrorAlert);
    }
}

function showCart(productPage) {
    if (productPage.numberOfElements > 0) {
        first = productPage.first;
        last = productPage.last;
        totalPages = productPage.totalPages;
        $('#empty-cart').hide();
        setPagination(productPage);
        let products = $('#products');
        clearChildNodes(products);
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
            tr.append(buildProductCountElem(product));
            products.append(tr);
        });
    } else {
        $('#cart').hide();
        $('#size-select').hide();
    }
}

function buildProductCountElem(product) {
    let td = $('<td></td>');
    let divCount = $('<div class="col-xs-2"></div>');
    let increaseBtn = $('<button class="btn btn-sm btn-default">+</button>');
    increaseBtn.click(increaseProductCount);
    divCount.append(increaseBtn);
    let countText = $('<p>' + product['count'] + '</p>');
    divCount.append(countText);
    let decreaseBtn = $('<button class="btn btn-sm btn-default">-</button>');
    divCount.append(decreaseBtn);
    decreaseBtn.click(decreaseProductCount);
    let divDel = $('<div class="col-xs-2"></div>');
    divDel.append(removeFromCartButton(product['id'], deleteProductFromCart));
    td.append(divCount, divDel);
    return td;
}

function increaseProductCount() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    addToCart(productId, '',
        function (resp) {
            onIncreaseCount(resp, currentRow);
        },
        onErrorAlert
    );
}

function decreaseProductCount() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    removeFromCart(productId, '', null,
        function (resp) {
            onDecreaseCount(resp, currentRow);
        },
        onErrorAlert
    );
}

function deleteProductFromCart() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    removeFromCart(productId, '', true,
        function (resp) {
            onRemove(resp, currentRow);
        },
        onErrorAlert
    );
}

function onClickClearCart() {
    clearCart(function (resp) {
        onClear();
    }, onErrorAlert);
}

function onIncreaseCount(resp, row) {
    let p = row.find("td:eq(4)").find('p');
    let count = Number(p.text());
    ++count;
    p.text(count);
    let price = row.find("td:eq(3)").text();
    totalPrice += Number(price);
    $('#total-price').text(totalPrice);
}

function onDecreaseCount(resp, row) {
    let p = row.find("td:eq(4)").find('p');
    let count = Number(p.text());
    --count;
    if (count === 0) {
        row.remove();
    } else {
        p.text(count);
    }
    changePageOnDelete();
    let price = row.find("td:eq(3)").text();
    totalPrice -= Number(price);
    $('#total-price').text(totalPrice);

}

function changePageOnDelete() {
    if ($('#products tr').length === 0) {
        if (last && first) {
            onClear(null);
        } else if (first) {
            getCart(currentCartPage, currentCartPageSize, '', showCart, onErrorAlert);
        } else if (last) {
            getCart(currentCartPage - 1, currentCartPageSize, '', showCart, onErrorAlert);
        }
    } else if ((first && totalPages != 1) || (!first && !last)) {
        getCart(currentCartPage, currentCartPageSize, '', showCart, onErrorAlert);
    }
}

function onRemove(resp, row) {
    let price = row.find("td:eq(3)").text();
    let p = row.find("td:eq(4)").find('p');
    let count = Number(p.text());
    totalPrice -= Number(price) * Number(count);
    $('#total-price').text(totalPrice);
    row.remove();
    changePageOnDelete();
}

function onClear() {
    $('#size-select').hide();
    $('#cart').hide();
    clearChildNodes($('#cart'));
    $('#empty-cart').show();
}

function nextPage() {
    getCart(currentCartPage + 1, currentCartPageSize, '', showCart, onErrorAlert);
}

function previousPage() {
    getCart(currentCartPage - 1, currentCartPageSize, '', showCart, onErrorAlert);
}

