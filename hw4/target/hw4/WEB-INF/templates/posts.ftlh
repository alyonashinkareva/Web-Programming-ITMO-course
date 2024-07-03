<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>
<@c.page>
    <#if user_id?has_content>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <ul>
            <#list posts?filter(p -> p.user_id == u.id) as p>
                <li><@c.make_post p "true"/></li>
            </#list>
        </ul>
    <#else>
        No such user
    </#if>
</@c.page>