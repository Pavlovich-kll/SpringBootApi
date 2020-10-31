<!--шаблон формы логина-->
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    <!--добавляем проверку-->
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>