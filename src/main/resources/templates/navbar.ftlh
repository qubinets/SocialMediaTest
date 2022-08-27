<#include "security.ftlh">
<#import "loginPage.ftlh" as l>
<nav class="navbar sticky-top navbar-expand-lg  navbar-dark bg-primary">
  <a class="navbar-brand" href="/"><img src="https://i.ibb.co/dp7MRhP/Screenshot-15.png" width="60px" height="50px" alt="logo"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <form class="form-inline" method="post" action="/search">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input class="form-control mr-sm-2" type="text" name="username" placeholder="Find user" aria-label="Search">
    <button class="btn btn-info my-2 my-sm-0" type="submit" href="user-search">Search</button>
  </form>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">

        <a class="nav-link" href="/"><i class="fas fa-home mr-1"></i>Main</a>

      </li>

      <#if user??>
        <li class="nav-item">
          <a class="nav-link" href="/main"><i class="fas fa-newspaper mr-1"></i>Timeline</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/user-messages/${currentUserId}"><i class="fas fa-comment mr-1"></i>My page</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/inbox/${currentUserId}"><i class="fas fa-inbox mr-1"></i>Inbox</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/user/profile"><i class="fas fa-user mr-1"></i>Profile</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/vue-stats"><i class="fas fa-chart-bar mr-1"></i></i>Statistics</a>
        </li>

      </#if>
      <#if isAdmin>
        <li class="nav-item">
          <a class="nav-link" href="/user">Admin Dashboard</a>
        </li>
      </#if>
    </ul>
    <div class="navbar-text mr-3">
      <a class="nav-link" href="/user/profile"><i class="fas fa-running"></i><#if user??>${name}<#else></#if></a>
    </div>
    <@l.logout />
  </div>
</nav>