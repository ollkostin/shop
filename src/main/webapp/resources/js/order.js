let totalPrice = 0;

$(document).ready(function () {
    getCart(showCart, onErrorLoad);
});

function showCart(productList) {
    let products = $('#products');
    productList.forEach(product => {
        let tr = $('<tr></tr>');
        tr.append(buildTableData(product['id']));
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