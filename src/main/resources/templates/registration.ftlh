<!--шаблон формы логина-->
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
Add new user
<!--добавляем проверку-->
${message}
<@l.login "/registration" />
</@c.page>