let totalPrice = 0;

$(document).ready(function () {
    getUserCart(showCart, function (resp) {
        alert(resp.responseJSON.message);
    })
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
            tr.append(buildProductCountElem(product['count']));
            products.append(tr);
            totalPrice += product['count'] * product['price'];
        });
        $('#total-price').text(totalPrice);
    } else {
        $('#cart').hide();
    }
}

function buildProductCountElem(count) {
    let td = $('<td></td>');
    let increaseBtn = $('<button class="btn btn-sm btn-default">+</button>');
    let decreaseBtn = $('<button class="btn btn-sm btn-default">-</button>');
    let countText = $('<p>' + count + '</p>')
    increaseBtn.click(increaseProductCount);
    decreaseBtn.click(decreaseProductCount);
    td.append(increaseBtn);
    td.append(countText);
    td.append(decreaseBtn);
    return td;
}

function increaseProductCount() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    addProductToCart(productId, function (resp) {
        alert('Added product');
        location.reload();
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
}

function decreaseProductCount() {
    let currentRow = $(this).closest("tr");
    let productId = currentRow.find("td:eq(0)").text();
    removeProductFromCart(productId, function (resp) {
        alert('Removed product');
        location.reload();
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
}

function onClickClearCart() {
    clearCart(function (resp) {
        alert('now cart is empty');
        location.reload();
    }, function (resp) {
        alert(resp.responseJSON.message);
    })
}