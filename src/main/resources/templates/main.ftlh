<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <!--форма будет отправляться на PostMapping-->
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение"/>
            <input type="text" name="tag" placeholder="Tэг">
            <input type="file" name="file">
            <!--это токен от мусташа с доп функционалом против хакеров. добавляется во все поля POST запросов-->
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <!--добавляем разметку и новый маппинг filter-->
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter?ifExists}">
        <button type="submit">Найти</button>
    </form>
    <!--вывод всех сообщений-->
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>4{tag}}</i>
            <strong>${message.authorName}</strong>
            <#--отображение картинки-->
            <div>
                <#--проверка, что в месседже есть поле filename-->
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>
