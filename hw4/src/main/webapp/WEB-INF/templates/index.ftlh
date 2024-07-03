<#import "./common.ftlh" as c>

<@c.page>
    <ul>
        <#list posts?reverse as post>
            <@c.make_post post "false"/>
        </#list>
    </ul>
</@c.page>
