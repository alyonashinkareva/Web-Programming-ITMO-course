<#-- @ftlvariable name="comments" type="ru.itmo.wp.domain.Post[]" -->
<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#import "/spring.ftl" as spring/>
<#import "commons.ftlh" as c>
<@c.page>
    <#if post??>
       <@c.post post/>
       <#if user??>
           <div class="form">
               <div class="header">Write comment</div>
               <div class="body">
                   <form method="post" action="">
                       <div class="field">
                           <input type="hidden" name="action" value="writeComment">
                           <label for="text">Your comment:</label>
                           <div class="value">
                               <div class="enter-comment">
                                   <textarea id = "text" name="text"></textarea>
                               </div>
                           </div>
                           <@c.error "comment.text"/>
                       </div>
                       <div class="button-field">
                           <input type="submit" value="Write">
                       </div>
                   </form>
               </div>
           </div>
       </#if>
       <#list post.comments as comment>
           <div class="comment">
               <div class="user_name">
                   ${comment.user.login}
               </div>
               <div class="text">${comment.text}</div>
               <div class="time">
                   At ${comment.user.creationTime}
               </div>
           </div>
       </#list>
    </#if>
</@c.page>