<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>
<@c.page>
    <#if post_id?has_content>
        <#assign p = c.findBy(posts, "id", post_id)!/>
    </#if>
    <#if p?has_content>
        <@c.make_post p "true"/>
    <#else>
        No posts.
    </#if>
</@c.page>