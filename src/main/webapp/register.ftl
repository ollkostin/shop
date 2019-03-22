<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <#include "resources/ftl/head.ftl">
    <title>Login</title>
</head>
<body>
<div class="container">
    <form name="user" action="<@spring.url '/register'/>" method="post" class="form-horizontal">
        <fieldset>
            <legend>Register</legend>
            <div class="form-group">
                <label for="email" class="control-label col-sm-2">Email</label>
                <div class="col-sm-10">
                    <input type="text" id="email" name="email" value="${(user.email)!''}" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="control-label col-sm-2">Password</label>
                <div class="col-sm-10">
                    <input type="password" id="password" name="password" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="password-confirm" class="control-label col-sm-2">Confirm password</label>
                <div class="col-sm-10">
                    <input type="password" id="password-confirm" name="confirmPassword" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="role" class="control-label col-sm-2">Role</label>
                <div class="col-sm-10">
                    <select id="role" name="role">
                        <option>vendor</option>
                        <option>user</option>
                    </select>
                </div>
            </div>
            <div class="form-actions">
                <button type="submit" class="btn">Register</button>
            </div>
            <#if error??>
                <p>${error}</p>
            </#if>
        </fieldset>
        <br>
        <div>
            <a href="<@spring.url '/login'/>"> Log in </a>
        </div>
    </form>
</div>
</body>
</html>