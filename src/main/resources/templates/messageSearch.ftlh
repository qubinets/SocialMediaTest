<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BuddyBook</title>
    <link rel="stylesheet" href="/static/style.css">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<#include "navbar.ftlh">
<div class="container mt-5">
    <#include "security.ftlh">
    Search results:
    <br>
    <a class="btn btn-primary" href="/main">Back</a>
    <center>
        <div class="table">
            <#list messages as message>

                <div class="card my-3" style="width: 60rem;" data-id="${message.id}">
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
                                <#if message.likes??>
                                    <#assign likecount = message.getLikes()?size>
                                    <i class="far fa-thumbs-up"></i>
                                    ${likecount}
                                </#if>
                            </a>
                            <#if message.author.id == currentUserId>
                                <a  href="/user-messages/${message.author.id}?message=${message.id}" >
                                    <input  class="col btn btn-primary btn-sm mr-1" type="button" style="width:75px;" value="Edit"  />
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
    </center>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>



