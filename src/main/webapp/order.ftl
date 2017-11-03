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
    <h3>${success}</h3>
<#else>
    <div class="container">
        <h1>Create order</h1>
        <div id="cart"></div>
        <#include "resources/ftl/cart-table.ftl">
        <form id="create-order" name="order" action="<@spring.url '/order/'/>"
              method="post" class="form-horizontal">
            <div class="form-group form-inline">
                <label for="total-price" class="control-label col-sm-2"> Total price </label>
                <div class="col-sm-10">
                    <input type="number" id="total-price" name="totalPrice" class="form-control" readonly="readonly"/>
                </div>
            </div>
            <div class="form-group">
                <label for="address" class="control-label col-sm-2">Address</label>
                <div class="col-sm-<#if error??>5<#else>10</#if>">
                    <input id="address" type="text" name="address" class="form-control" value="" required/>
                </div>
                <#if error??>
                    <div class="col-sm-5">
                        <p>${error}</p>
                    </div>
                </#if>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Create order</button>
        </form>
    </div>
</div>
</#if>
</body>
</html>