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
        <input type="text" id="email" name="email"/>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
        <label for="password-confirm">Confirm password</label>
        <input type="password" id="password-confirm" name="password"/>
        <div class="form-actions">
            <button type="submit" class="btn">Register</button>
        </div>
    </fieldset>
    <a href="login"> login </a>
</form>
</body>
</html>