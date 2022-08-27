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

        <!-- Page body -->

        <h2>User Statistics</h2>
        <br>
        <#if mostpopularmessage??>
            <div class="card">
                <div class="card-body">
                    <div class="card-title"><h6>Total Likes</h6></div>
                    <h3 class="card-text">
                        <a><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Facebook_Thumb_icon.svg/640px-Facebook_Thumb_icon.svg.png"width="60px"height="50px" alt="logo" />${totallikes}</a>
                    </h3>
                </div>
            </div>
            <br>

            <div class="card">
                <div class="card-body">
                    <div class="card-title"><h6>Most popular message</h6></div>

                    <#if mostpopularmessage.filename??>
                        <img src="/img/${mostpopularmessage.filename}" class="card-img-top" />
                    </#if>
                    <div class="m-2">
                        <span>${mostpopularmessage.text!''}</span><br/>
                        <i>#${mostpopularmessage.tag!''}</i>
                    </div>
                    <div class="card-footer text-muted container">
                        <div class="row">
                            <a class="col align-self-center" >
                               <i class="far fa-thumbs-up"></i>
                                ${likesamount}</a>

                            <#if mostpopularmessage.getAuthor().getId() == idcurrentuser>
                                <div>
                                    <a  class="col btn btn-primary btn-sm mr-1" style="width:80px;" href="/user-messages/${mostpopularmessage.author.id}?message=${mostpopularmessage.id}">
                                        Edit
                                    </a>
                                </div>
                                <form method="post" action="/user-messages/delete/${mostpopularmessage.author.id}?message=${mostpopularmessage.id}">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    <input type="hidden" name="id" value="<#if mostpopularmessage??>${mostpopularmessage.id}</#if>" />
                                    <button onclick="return confirm('Are you sure?')" type="submit" style="width:75px;" class="col btn btn-primary btn-sm mr-1">Delete</button>
                                </form>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
            <#else>
            No data available,get some likes and subscribers =)
        </#if>
        <!-- Page body -->

 </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    </body>
    </html>



