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
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.3"></script>

</head>
<body>
<#include "navbar.ftlh">
<#include "security.ftlh">
<div class="container mt-5">

<h3>User Statistics</h3>
    <br><br><br><br>
<div id="stata"></div>
<script>
    var statsApi = Vue.resource('/vue-statistics');

    Vue.component('stats-row',{
        props:['stat','stats'],
        template: '<form><div>' +
            '<div class="col-md-4 col-6 border">Total Likes: <a><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Facebook_Thumb_icon.svg/640px-Facebook_Thumb_icon.svg.png"width="60px"height="50px" alt="logo" /><strong>{{stat.totalLikes}}</strong></a><br>' +
            'Total Posts: <a><img src="https://images-na.ssl-images-amazon.com/images/I/41NxGNlKzwL.png" width="60px"height="50px" alt="logo"><strong>{{stat.totalMessages}}</strong></a><br>' +
            'Total Subscribers: <a><img src="https://media.istockphoto.com/vectors/subscribe-button-for-social-media-subscribe-to-video-channel-blog-and-vector-id1218933777?k=20&m=1218933777&s=170667a&w=0&h=5ilqxeezisJr0jxtOwHTy1XmfsCzniJkPPd6d3oS1ZM="' +
            'width="120px"height="80px" alt="logo"><strong>{{stat.totalSubscribers}}</strong></a></div><br><br><br><br>' +
            '<div class="card"> <div class="card-body"><div class="card-title"><h6>Most popular message:</h6></div>'+
            '<br> {{stat.mostPopularMessage.text}}<br>'+
            '<br> <i class="far fa-thumbs-up"></i>{{stat.mostPopularMessageLikes}}<br>'+
            '<input type="hidden" name="_csrf" value="${_csrf.token}">'+
            '</div></div>'+
            '</div></form>'
    });
    Vue.component('stats-list',{
        props: ['stats'],
        template: '<div>' +

            '<stats-row v-for="stat in stats" :stat="stat" :stats="stats"/>' +
            '</div>'

    });
    var app = new Vue({
        el: '#stata',
        template: '<stats-list :stats="stats"/>',
        data: {
            stats: []
        },
        created: function(){
            statsApi.get().then(result => result.json().then(data =>
                data.forEach(stat =>this.stats.push(stat))
            ))
        }
    });
</script>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
