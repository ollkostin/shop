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
        <table class="table">
            <thead>
            <tr>
                <th>id</th>
                <th>image</th>
                <td>Product name</td>
                <td>Price</td>
                <td>Count</td>
            </tr>
            </thead>
            <tbody id="products"></tbody>
        </table>
        <div class="text-right">
            <h4>Total price:</h4>
            <p id="total-price"></p>
            <div class="btn-group">
                <button class="btn btn-primary">Create order</button>
                <button class="btn btn-danger" onclick="onClickClearCart()">Clear cart</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>