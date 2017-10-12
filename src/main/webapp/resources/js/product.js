let productId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);

getProduct(productId, onSuccessLoadProduct);


function onSuccessLoadProduct(product) {
    $('#product-name').text(product['name']);
    $('#product-price').text(product['price']);
    $('#product-photos').append(buildProductPhotosBlock(product));
    $('#product-info').append(cartButton());
}


function buildProductPhotosBlock(product) {
    let photos = [];
    product.photos.forEach(path => {
        photos.push(
            buildImg('../api/products/' + product['id'] + '/photos/' + path, 100, 100)
        );
    });
    return photos;
}