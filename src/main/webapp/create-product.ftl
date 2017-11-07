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
            <div class="col-sm-<#if errors?? && errors["name"]??>5 <#else>10</#if>">
                <input id="product-name" type="text" name="name" class="form-control" value="${(product.name)!''}"
                       required/>
            </div>
        <#if errors?? && errors["name"]??>
            <div class="col-sm-5">
                <p>${errors["name"][0]}</p>
            </div>
        </#if>
        </div>
        <div class="form-group">
            <label for="product-description" class="control-label col-sm-2">Description</label>
            <div class="col-sm-<#if errors?? && errors["description"]??>5<#else>10</#if>">
                <textarea id="product-description"
                          name="description"
                          class="text-area"
                          required>${(product.description)!''}</textarea>
            </div>
        <#if errors?? && errors["description"]??>
            <div class="col-sm-5">
                <p>${errors["description"][0]}</p>
            </div>
        </#if>
        </div>
        <div class="form-group">
            <label for="product-price" class="control-label col-sm-2">Price</label>
            <div class="col-sm-<#if errors?? && errors["price"]??>5<#else>10</#if>">
                <input id="product-price" type="number" step="0.01" min="0.01" name="price" class="form-control"
                       value="${(product.price)!''}" required/>
            </div>
        <#if errors?? && errors["price"]??>
            <div class="col-sm-5">
                <p>${errors["price"][0]}</p>
            </div>
        </#if>
        </div>
        <div class="form-group">
            <label for="file" class="control-label col-sm-2">Photo</label>
            <div class="col-sm-10">
                <button id="add-file" type="button" class="btn btn-success" onclick="onAddPhoto()">+</button>
                <div id="photos" class="col-sm-10">
                    <div class="col-xs-12 btn-group file-1">
                        <input class="btn file-1" type="file" name="photos" required"/>
                    </div>
                </div>
            </div>
        </div>
    <#if errors??>
        <div class="col-sm-push-2 col-sm-10">
            <#list errors?keys as key>
                <#if key !="name" && key != "description" && key != "price">
                    <#list errors[key]>
                        <ul>
                            <#items as msg>
                                <li>${msg}</li>
                            </#items>
                        </ul>
                    </#list>
                </#if>
            </#list>
        </div>
    </#if>
        <div class="pull-right">
            <hr>
            <input type="submit" class="btn btn-success" value="Save">
        </div>
    </form>
</div>
</body>
</html>