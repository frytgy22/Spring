<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post">
        <label>
            <input type="text" name="text" placeholder="Введите сообщение" />
        </label>
        <label>
            <input type="text" name="tag" placeholder="Тэг">
        </label>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
<form method="get" action="/main">
    <label>
        <input type="text" name="filter" value="${filter!''}">
    </label>
    <button type="submit">Найти</button>
</form>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${(message.author.username)!"&lt;none&gt;"}</strong>
<#--    author != null ? author.getUsername() : "<none>-->
</div>
<#else>
No message
<#--    if list is empty-->
</#list>
</@c.page>
