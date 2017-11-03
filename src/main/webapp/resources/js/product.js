let productId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
$(document).ready(function () {
    getProduct(productId, onSuccessLoadProduct, function (resp) {
        if (resp.status === 404) {
            clearChildNodes($('#product'));
            $('#product').append("<h3>"+ resp.responseJSON.error +"</h3>")
        }
    });
});


function onSuccessLoadProduct(product) {
    $('#product-name').text(product['name']);
    $('#product-description').text(product['description']);
    $('#product-price').text(product['price']);
    $('#product-photos').append(buildProductPhotosBlock(product));
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

function buildCarouselItem(img ,index) {
    let div = $('<div class="item"></div>');
    if (Number(index) === 0){
        div.addClass("active")
    }
    div.append(img);
    return div;
}

function productPageCartButton() {
    $.ajax({
        type: 'PUT',
        url: '../api/cart/product/' + productId,
        success:  function (resp) {
            $('#cart-btn').replaceWith(alreadyInCartMsg);
        },
        error: function (resp) {
            alert(resp.responseJSON.message);
        }
    });
}