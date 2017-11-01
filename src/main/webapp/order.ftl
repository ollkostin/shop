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
<#if success??>
    ${success}
<#else>
    <h1>Create order</h1>
    <div>
        <div id="cart">
            <#include "resources/ftl/cart-table.ftl">
            <form id="create-order" name="order" action="<@spring.url '/order/'/>" method="post">
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
                <button type="submit" class="btn btn-primary">Create order</button>
                <#if error??>
                ${error}
                </#if>
            </form>
        </div>
    </div>
</#if>
</body>
</html>