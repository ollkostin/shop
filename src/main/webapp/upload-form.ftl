<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload file</title>
</head>
<body>
<form method="POST" enctype="multipart/form-data" action="upload">
    <p>
        <input type="file" name="file">
        <input type="submit" value="Отправить"></p>
</form>
<#if message??>
    message
</#if>
</body>
</html>