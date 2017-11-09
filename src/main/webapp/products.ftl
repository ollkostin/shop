<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <title>Product list</title>
<#include "resources/ftl/head.ftl">
    <script src="<@spring.url '/resources/js/common.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-product.js'/>"></script>
    <script src="<@spring.url '/resources/js/api-cart.js'/>"></script>
    <script src="<@spring.url '/resources/js/products.js'/>"></script>
</head>
<body>
<div class="container">
<#include "resources/ftl/navbar.ftl">
    <h1>Products</h1>
    <select id="size-select"
            onchange="onSizeChange()">
        <option selected>10</option>
        <option>20</option>
    </select>
    <table class="table">
        <thead>
        <tr>
            <td>id</td>
            <td>image</td>
            <td>Product name</td>
            <td>Price</td>
        </tr>
        </thead>
        <tbody id="products"
               <@security.authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_VENDOR')">
               data-show-remove-button="true"
               </@security.authorize>>
        </tbody>
    </table>
<#include "resources/ftl/pagination-buttons.ftl"/>
</div>
</body>
</html>