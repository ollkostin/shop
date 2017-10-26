<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Product list</title>
<#include "resources/ftl/head.ftl">
    <script src="<@spring.url '/resources/js/common.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-cart.js'/>"></script>
    <script src="<@spring.url '/resources/js/order.js'/>"></script>
</head>
<body>
<div class="container">
<#include "resources/ftl/navbar.ftl">
    <div>
        <h1>Create order</h1>
        <div id="cart">
            <table class="table">
                <thead>
                <tr>
                    <th>image</th>
                    <td>Product name</td>
                    <td>Price</td>
                    <td>Count</td>
                </tr>
                </thead>
                <tbody id="products"></tbody>
            </table>
            <form id="create-order" name="order" action="<@spring.url '/api/order/'/>" method="post">
                <div>
                    Total price:
                    <label>
                        <input type="number" id="total-price" name="totalPrice" readonly="true"/>
                    </label>
                </div>
                <div>
                    Address:
                    <label>
                        <input type="text" name="address" value="" required/>
                    </label>
                </div>
                <button type="submit">Create order</button>
            </form>
        </div>
    </div>
</body>
</html>