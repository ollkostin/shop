 <#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div>
    <form name="f" action="login" method="post">
        <fieldset>
            <legend>Login</legend>
            <label for="email">Email</label>
            <input type="text" id="email" name="email"/>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
            <#if RequestParameters["error"]??>
                <p>The email or password you have entered is invalid, try again.</p>
            </#if>
        </fieldset>
    </form>
    <a href="register"> register </a>
</body>
</html>