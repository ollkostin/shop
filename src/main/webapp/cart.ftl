<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Product list</title>
<#include "resources/ftl/head.ftl">
    <script src="<@spring.url '/resources/js/common.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-cart.js'/>"></script>
    <script src="<@spring.url '/resources/js/cart.js'/>"></script>
</head>
<body>
<div class="container">
<#include "resources/ftl/navbar.ftl">
    <h1>Your cart</h1>
    <div id="empty-cart"> Cart is empty</div>
    <div id="cart">
        <#include "resources/ftl/cart-table.ftl">
        <div class="text-right">
            <h4>Total price:</h4>
            <p id="total-price"></p>
            <div class="btn-group">
                <a class="btn btn-primary" href="<@spring.url '/order'/>">Create order</a>
                <button class="btn btn-danger" onclick="onClickClearCart()">Clear cart</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>