<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
<#--отображение сообщений-->
    ${message?ifExists}
    <@l.login "/login" false/>
</@c.page>