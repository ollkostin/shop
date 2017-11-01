<#import "spring.ftl" as spring />
<html lang="en">
<head>
<#include "resources/ftl/head.ftl">
    <title>Create product</title>
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
                <input id="product-name" type="text" name="name" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="product-description" class="control-label col-sm-2">Description</label>
            <div class="col-sm-10">
                <input id="product-description" type="text" name="description" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="product-price" class="control-label col-sm-2">Price</label>
            <div class="col-sm-10">
                <input id="product-price" type="number" name="price" class="form-control" required/>
            </div>
        </div>
        <div class="form-group">
            <label for="file" class="control-label col-sm-2">Photo</label>
            <div class="col-sm-10">
                <input id="photo" type="file" name="photo"/>
            </div>
        </div>
        <#if error??>
            ${error}
        </#if>
        <input type="submit" class="btn btn-success" value="Save">
    </form>
</div>
</body>
</html>