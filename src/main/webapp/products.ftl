<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product list</title>
<#include "resources/ftl/head.ftl">
</head>
<body>
<div class="container">
    <h1>Products</h1>
    <select id="size-select"
            onchange="onSizeChange()">
        <option selected>10</option>
        <option>20</option>
        <option>50</option>
    </select>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <td>Product name</td>
            <td>Price</td>
            <td></td>
        </tr>
        </thead>
        <tbody id="products"></tbody>
    </table>
    <!--TODO:прилепить внизу страницы и отключать кнопки в зависимости от количества-->
    <div class="text-center">
        <button id="prev-page"
                class="btn btn-primary"
                onclick="getProducts(currentPage - 1, currentSize)">
            Previous
        </button>
        <button id="next-page" class="btn btn-primary"
                onclick="getProducts(currentPage + 1, currentSize)">
            Next
        </button>
    </div>
</div>
<script src="resources/js/product.js"></script>
</body>
</html>