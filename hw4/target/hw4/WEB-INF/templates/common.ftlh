<#-- @ftlvariable name="userColors" type="ru.itmo.web.hw4.model.Color[]" -->
<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user user.handle user.name "true"/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"href": "/index", "name": "Home"},
            {"href": "/misc/help", "name": "Help"},
            {"href": "/contests", "name": "Contests"},
            {"href": "/users", "name": "Users"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if item.href == uri>
                            <a class="current_page" href="${item.href}">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts as post>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body"><@make_text post "false"/></div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user handle text nameOnly>
<#-- @ftlvariable name="user" type="ru.itmo.web.hw4.model.User" -->
    <#if nameOnly == "true">
        <a class="baseColor" href="/user?handle=${handle}">${text}</a>
    <#else>
        <a class="link${user.color}" href="/user?handle=${handle}">${text}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#return find(items, key, value, 0)/>
</#function>

<#function findNext items key value>
    <#return find(items, key, value, 1)/>
</#function>

<#function findPrevious items key value>
    <#return find(items, key, value, 2)/>
</#function>

<#function find items key value flag>
    <#list items as item>
        <#if item[key] == value>
            <#if flag == 0>
                <#return item/>
            <#elseif flag == 1 && item_index + 1 < items?size>
                <#return items[item_index + 1]/>
            <#elseif (flag == 2 && item_index - 1 >= 0) >
                <#return items[item_index - 1]/>
            </#if>
        </#if>
    </#list>
</#function>

<#macro make_text post isFull>
    <#if (350 < post.text?length) && (isFull == "false")>
        ${post.text[0..*350]}...
    <#else>
        ${post.text}
    </#if>
</#macro>

<#macro make_post post isFull>
    <article>
        <#assign u = findBy(users, "id", post.user_id)!/>
        <div class="title">Post ${post.id}</div>
        <div class="information">By <@userlink u u.handle u.handle "false"/></div>
        <div class="body"><@make_text post isFull/></div>
        <div class="footer">
            <div class="left">
                <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                <span class="positive-score">+173</span>
                <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
            </div>
            <div class="right">
                <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                2 days ago
                <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                <a href="#">68</a>
            </div>
        </div>
    </article>
</#macro>
