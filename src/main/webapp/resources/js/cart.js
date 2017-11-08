let totalPrice = 0;

$(document).ready(function () {
    getCart('', showCart, onErrorAlert)
});

function showCart(productList) {
    if (productList.length > 0) {
        $('#empty-cart').hide();
        let products = $('#products');
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
            tr.append(buildProductCountElem(product));
            products.append(tr);
            totalPrice += product['count'] * product['price'];
        });
        $('#total-price').text(totalPrice);
    } else {
        $('#cart').hide();
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
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
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
        row.remove()
    } else {
        p.text(count);
    }
    let price = row.find("td:eq(3)").text();
    totalPrice -= Number(price);
    $('#total-price').text(totalPrice);
    if ($('#products tr').length === 0) {
        onClear();
    }
}

function onRemove(resp, row) {
    let price = row.find("td:eq(3)").text();
    let p = row.find("td:eq(4)").find('p');
    let count = Number(p.text());
    totalPrice -= Number(price) * Number(count);
    $('#total-price').text(totalPrice);
    row.remove();
}

function onClear() {
    $('#cart').hide();
    clearChildNodes($('#cart'));
    $('#empty-cart').show()
}