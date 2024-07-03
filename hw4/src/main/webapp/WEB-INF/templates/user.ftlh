<#-- @ftlvariable name="user_id" type="java.lang.String" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle?has_content>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#elseif user_id?has_content>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>

    <#if u?has_content>
        <#assign fields = [
        {"name": "id", "value": u.id},
        {"name": "handle", "value": u.handle},
        {"name": "name", "value": u.name}
        ]/>
        <div class="user">
            <div class="user_info">
                <header>
                    <a href="#">${u.name}</a>
                </header>
                <ul>
                    <#list fields as field>
                        <li>${field.name}: ${field.value}</li>
                    </#list>
                    <li><a href="/posts?user_id=${u.id}">posts</a>: ${countOfPosts(u)}</li>
                </ul>
            </div>
            <div class="photo">
                <img src="/img/user-photo.png">
            </div>

            <#assign u_next = c.findNext(users, "handle", u.handle)!/>
            <#assign u_previous = c.findPrevious(users, "handle", u.handle)!/>
        </div>
        <nav>
            <div class="previous">
                <#if u_previous?has_content>
                    <@c.userlink u_previous u_previous.handle "←" "true"/>
                <#else>
                    ${"←"}
                </#if>
            </div>
            <div class="next">
                <#if u_next?has_content>
                    <@c.userlink u_next u_next.handle "→" "true"/>
                <#else>
                    ${"→"}
                </#if>
            </div>
        </nav>
    <#else>
        No such user
    </#if>
</@c.page>

<#function countOfPosts user>
    <#assign count = 0/>
    <#list posts as post>
        <#if post.user_id == user.id>
            <#assign count++/>
        </#if>
    </#list>
    <#return count/>
</#function>