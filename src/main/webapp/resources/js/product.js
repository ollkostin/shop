let productId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
$(document).ready(function () {
    getCart('../', function (productList) {
        onSuccessLoadCart(productList);
        getProduct(productId, onSuccessLoadProduct, onErrorLoadProduct);
    }, onErrorAlert);
});

function onSuccessLoadProduct(product) {
    $('#product-name').text(product['name']);
    $('#product-description').text(product['description']);
    $('#product-price').text(product['price']);
    $('#add-cart-btn-div').append(addToOrRemoveFromCartButton(product["id"], addToCartOnProductPageCb, removeFromCartOnProductPageCb));
    $('#product-photos').append(buildProductPhotosBlock(product));
}

function onErrorLoadProduct(resp) {
    if (resp.status === 404) {
        clearChildNodes($('#product'));
        $('#product').append("<h3>" + resp.responseJSON.error + "</h3>")
    }
}

function buildProductPhotosBlock(product) {
    let photos = [];
    for (let index in product['photos']) {
        photos.push(
            buildCarouselItem(
                buildImg('../api/products/' + product['id'] + '/photos/' + product['photos'][index], 500, 500),
                index
            )
        );
    }
    if (photos.length < 2) {
        $('#carousel-prev').hide();
        $('#carousel-next').hide();
    }
    return photos;
}

function buildCarouselItem(img, index) {
    let div = $('<div class="item"></div>');
    if (Number(index) === 0) {
        div.addClass("active")
    }
    div.append(img);
    return div;
}

function addToCartOnProductPageCb() {
    addToCart(productId, '../', onAdd, onErrorAlert);
}

function onAdd(resp) {
    onSuccessAddToCart(resp, productId, removeFromCartOnProductPageCb);
}

function onRemove(resp) {
    onSuccessRemoveFromCart(resp, productId, addToCartOnProductPageCb);
}

function removeFromCartOnProductPageCb() {
    removeFromCart(productId, '../', true, onRemove, onErrorAlert)
}