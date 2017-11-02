<#import "spring.ftl" as spring />
<html lang="en">
<head>
<#include "resources/ftl/head.ftl">
    <title>Create product</title>
    <script src="<@spring.url '/resources/js/add-photos.js'/>"></script>
</head>
<body>
<div class="container">
<#include "resources/ftl/navbar.ftl">
    <form method="post" enctype="multipart/form-data"
          name="product"
          action="<@spring.url '/products/create/'/>"
          class="form-horizontal">
        <div class="form-group">
            <label for="product-name" class="control-label col-sm-2">Name</label>
            <div class="col-sm-10">
                <input id="product-name" type="text" name="name" class="form-control" value="${(product.name)!''}" required/>
                <#if errors??>
                    <#if errors["name"]??>
                        <#list errors["name"] as msg>
                            <p>${msg}</p>
                        </#list>
                    </#if>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label for="product-description" class="control-label col-sm-2">Description</label>
            <div class="col-sm-10">
                <textarea id="product-description" name="description" class="text-area" required>${(product.description)!''}</textarea>
                <#if errors??>
                    <#if errors["description"]??>
                        <#list errors["description"] as msg>
                            <p>${msg}</p>
                        </#list>
                    </#if>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label for="product-price" class="control-label col-sm-2">Price</label>
            <div class="col-sm-10">
                <input id="product-price" type="number" name="price" class="form-control" value="${(product.price)!''}" required/>
                <#if errors??>
                    <#if errors["price"]??>
                        <#list errors["price"] as msg>
                            <p>${msg}</p>
                        </#list>
                    </#if>
                </#if>
            </div>
        </div>
        <div class="form-group">
            <label for="file" class="control-label col-sm-2">Photo <button type="button"  class="btn btn-success" onclick="onAddPhoto()">+</button></label>
            <div id="photos" class="col-sm-10">
                <input class="file-input" type="file" name="photos" required/>

            </div>
        </div>
        <input type="submit" class="btn btn-success" value="Save">
    </form>
</div>
</body>
</html>