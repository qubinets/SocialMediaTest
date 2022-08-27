<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>BuddyBook</title>
    <link rel="stylesheet" href="/static/style.css">
    <style>
        textarea {
            width: 300%;
            height: 150px;
            padding: 20px 20px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            font-size: 10px;
            resize: none;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
</head>
<body>
<#include "navbar.ftlh">
<div class="container mt-5">
    <#if usernameError??>
        <div class="alert alert-danger" role="alert">
           ${usernameError}
        </div>
    </#if>
    <#if success??>
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </#if>
     </form>
    <form method="post" action = "/inbox/${user.id}">
        <table><tbody>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <tr>
       <td> Send to:</td><td>
        <input type="text" name="username" id = "userName" class="form-control" placeholder="Username">
        </td>
    </tr>
        <br>
    <tr>
        <td>Subject:</td><td><input type="text" name="subject" class="form-control"  placeholder="Subject"></td>
    </tr>
        <br>
    <tr>
        <td>Message:</td><td>
            <textarea type="text" name="body" class="form-control" style="width:600px;height:200px" >
            </textarea>
        </td>
    </tr>
    <td> <button type="submit" class="btn btn-primary">Send message</button></td>
        </table></tbody>
    </form>
    <br><br><br><br>
    <h3>Inbox messages</h3>
    <#list messages as message>
    <div class="card my-3" style="width: 40rem;"  data-id="${message.privateMessageId}">
        <div class="m-2">
        Sender :<a class="col align-self-center" href="/user-messages/${message.from.id}"> ${message.from.username}</a></br>
        Subject :<a class="col align-self-center"<i>${message.messageSubject}</i></a></br>
        <span>Message:<br>${message.messageBody}</span><br/>
        </div>
    <div class="card-footer text-muted container">
        <div class="row">
            <form method="post" action="/inbox/delete/${message.to.id}?message=${message.privateMessageId}">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if message??>${message.privateMessageId}</#if>" />
                <button  type="submit" class="col btn btn-primary btn-sm mr-1">Delete</button>
            </form>

        </div>
    </div>
</div>
    </#list>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script>
    var arr=['users'];

    function submitfun(){
        var value = document.getElementById("myInput").value;
        if(arr.includes(value)){
            value = "untitled";
            document.getElementById("myInput").value = value;
        }
    }
</script>
</body>
</html>

