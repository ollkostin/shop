let totalPrice = 0;

$(document).ready(function () {
    getUserCart(showCart, function (resp) {
        alert(resp.responseJSON.message);
    })
});

function showCart(productList) {
    let products = $('#products');
    productList.forEach(product => {
        let tr = $('<tr></tr>');
        tr.append(
            buildTableData(
                buildImg('api/products/' + product['id'] + '/photos/' + product['pathToPhoto'], 50, 50)
            )
        );
        tr.append(buildTableData(product['name']));
        tr.append(buildTableData(product['price']));
        tr.append(buildTableData(product['count']));
        products.append(tr);
        totalPrice += product['count'] * product['price'];
    });
    $('#total-price').val(totalPrice);

}