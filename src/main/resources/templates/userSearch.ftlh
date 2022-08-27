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



    Find user
    <div>
        <form method="post" action = "search">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <input type="text" name ="username" placeholder="Type here"/>
            <button type ="submit" class="btn btn-primary">Search</button>
        </form>

    </div>

    <br>List of users:</br>
    <table>
        <tbody>

        <#list foundusers as user>
            <#if foundusers??>
                <tr>
                    <td><#if logourl??>
                            <img src="${logourl}" width="60px"height="50px" alt="logo"><#else><img src="https://pbs.twimg.com/profile_images/578844000267816960/6cj6d4oZ_400x400.png" width="60px"height="50px" alt="logo">
                        </#if>

                        ${user.username}</td>
                    <td>
                        <a class ="col" href="/user-messages/${user.id}">User page</a>
                    </td>
                </tr>
            <#else>
                <td>Nothing found</td>
            </#if>
        </#list>
        </tbody>
    </table>

</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>




