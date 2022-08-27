<#include "../security.ftlh">
<#import  "pager.ftlh" as p>
<@p.pager url page />

<div class="card-columns" id="message-list">
    <#list page.content as message>
        <div class="card my-3" data-id="${message.id}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top" />
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" href="/user-messages/${message.author.id}">${message.authorName}</a>
                    <a class="col align-self-center" href="/messages/${message.id}/like">
                        <#if message.meLiked>
                            <i class="fas fa-thumbs-up"></i>
                        <#else>
                            <i class="far fa-thumbs-up"></i>
                        </#if>
                        ${message.likes}
                    </a>
                    <#if message.author.id == currentUserId>
                        <a style="width:80px;" class="col btn btn-primary btn-sm mr-1"  href="/user-messages/${message.author.id}?message=${message.id}">
                            Edit
                        </a>

                        <form method="post" action="/user-messages/delete/${message.author.id}?message=${message.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
                            <button onclick="return confirm('Are you sure?')" type="submit" style="width:75px;" class="col btn btn-primary btn-sm mr-1">Delete</button>
                        </form>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>

<@p.pager url page />