<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<form name="f" action="<@spring.url '/register'/>" method="post">
    <fieldset>
        <legend>Register</legend>
        <label for="email">Email</label>
        <input type="text" id="email" name="email" required/>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required/>
        <label for="password-confirm">Confirm password</label>
        <input type="password" id="password-confirm" name="confirm" required/>
        <div class="form-actions">
            <button type="submit" class="btn">Register</button>
        </div>
        <#if error??>
            ${error}
        </#if>
    </fieldset>
    <a href="login"> login </a>
</form>
</body>
</html>