<#import "spring.ftl" as spring />
<html lang="en">
<head>
<#include "resources/ftl/head.ftl">
    <title>Login</title>
</head>
<body>
<div class="container">
    <form name="f" action="login" method="post">
        <fieldset>
            <legend>Login</legend>
            <div class="form-group">
                <label for="email" class="control-label col-sm-2">Email</label>
                <div class="col-sm-10">
                    <input type="text" id="email" name="email" value="${(user.email)!''}" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="control-label col-sm-2">Password</label>
                <div class="col-sm-10">
                    <input type="password" id="password" name="password" value="${(user.password)!''}" required/>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Log in</button>
            </div>
            <#if RequestParameters["error"]??>
                <p>The email or password you have entered is invalid, try again.</p>
            </#if>
        </fieldset>
    </form>
    <div>
        <a href="<@spring.url '/register'/>"> Register </a>
    </div>
</div>
</body>
</html>