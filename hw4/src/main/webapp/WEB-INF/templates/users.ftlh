<#import "./common.ftlh" as c>

<@c.page>
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td>${u.id}</td>
                    <td>
                        <@c.userlink u u.handle u.handle "false"/>
                    </td>
                    <td>
                        <@c.userlink u u.handle u.name "true"/>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
