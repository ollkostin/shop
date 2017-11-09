<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Product</title>
    <#include "resources/ftl/head.ftl">
    <script src="<@spring.url '/resources/js/common.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-product.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-cart.js'/>"></script>
    <script src="<@spring.url '/resources/js/product.js'/>"></script>
</head>
<body>
<div class="container">
    <#include "resources/ftl/navbar.ftl">
    <div id="product" class="container">
        <div id="image-carousel" class="col-xs-12 col-sm-6 col-md-6 carousel slide" data-ride="carousel">
            <div id="product-photos" class="carousel-inner"></div>
            <a id="carousel-prev" class="left carousel-control" href="#image-carousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a id="carousel-next" class="right carousel-control" href="#image-carousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <div id="product-info" class="col-xs-12 col-sm-6 col-md-6">
            <h2 id="product-name"></h2>
            <span>
                <label for="product-price">Price:<span id="product-price"></span></label>
            </span>
            <div>
                <p id="product-description"></p>
                <div id="actions" class="btn-group"
                <@security.authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_VENDOR')">
                     data-show-remove-button="true"
                </@security.authorize>>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>