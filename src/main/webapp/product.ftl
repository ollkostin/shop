<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Product</title>
    <#include "resources/ftl/head.ftl">
    <script src="<@spring.url '/resources/js/common.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-product.js'/>"></script>
    <script src="<@spring.url '/resources/js/product.js'/>"></script>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div id="product-photos"></div>
        </div>
        <div id="product-info" class="col-xs-12 col-sm-6 col-md-6">
            <h2 id="product-name"></h2>
            <span>
                <label for="product-price">Price:</label>
                <p id="product-price"></p>
            </span>
        </div>
    </div>
</div>
</body>
</html>