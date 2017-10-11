<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload file</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="api/products/{productId}/photos/">
        <label for="product-ud">Product id</label>
        <input id="product-id" type="number" name="productId"/>
        <label for="file">Photo</label>
        <input id="file" type="file" name="file">
        <input type="submit" value="Save">
</form>
</body>
</html>