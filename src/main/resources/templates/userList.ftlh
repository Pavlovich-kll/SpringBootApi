<#import "parts/common.ftlh" as c>

<#--макросы используют @, а предопределенные директивы #-->
<@c.page>
    List of users
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr>
            <td>${user.username}</td>
        <#--сепаратор <#sep> указывает, как будут разделяться роли в списке-->
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">edit</a></td>
        </tr>
        </#list>
        </tbody>
    </table>
</@c.page>