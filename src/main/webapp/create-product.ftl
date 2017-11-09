<#import "spring.ftl" as spring />
<html lang="en">
<head>
<#include "resources/ftl/head.ftl">
    <title>Create product</title>
    <script src="<@spring.url '/resources/js/api-product.js'/>"></script>
    <script src="<@spring.url '/resources/js/create-product.js'/>"></script>
</head>
<body>
<div class="container">
<#include "resources/ftl/navbar.ftl">
    <form id="create-product" method="post" enctype="multipart/form-data" name="product" class="form-horizontal">
        <div class="form-group">
            <label for="product-name" class="control-label col-sm-2">Name</label>
            <div class="col-sm-5">
                <input id="product-name" type="text" name="name" class="form-control""
                required/>
            </div>
            <div class="col-sm-5">
                <ul id="name-error"></ul>
            </div>
        </div>
        <div class="form-group">
            <label for="product-description" class="control-label col-sm-2">Description</label>
            <div class="col-sm-5">
                <textarea id="product-description"
                          name="description" class="text-area" required></textarea>
            </div>
            <div id="description-error" class="col-sm-5"></div>
        </div>
        <div class="form-group">
            <label for="product-price" class="control-label col-sm-2">Price</label>
            <div class="col-sm-5">
                <input id="product-price" type="number" step="0.01" min="0.01" max="99999.999" name="price" class="form-control"
                       required/>
            </div>
            <div class="col-sm-5">
                <ul id="price-error"></ul>
            </div>
        </div>
        <div class="form-group">
            <label for="file" class="control-label col-sm-2">
                Photo
                <button id="add-file" type="button" class="btn btn-success" onclick="onAddPhoto()">+</button>
            </label>

            <div class="col-sm-10">
                <div id="photos" class="row">
                    <div class="col-sm-6 btn-group">
                        <input class="btn" type="file" name="photos" required data-file-id="1"/>
                    </div>
                    <div class="col-sm-push-3 col-sm-12">
                        <ul data-file-id="1" data-file></ul>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="pull-right">
            <input type="submit" class="btn btn-success" value="Save">
        </div>
    </form>
</div>
</body>
</html>