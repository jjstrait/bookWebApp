<%-- 
    Document   : navbar
    Created on : Mar 6, 2017, 11:58:11 AM
    Author     : jstra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="index.jsp">Book Web Application</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="<%= response.encodeURL("AuthorController?action=authorList") %>">View Records<span class="sr-only">(current)</span></a></li>
        
      <form class="navbar-form navbar-right" method="POST" action="<%= response.encodeURL("AuthorController?action=search") %>">'
        <div class="form-group">
          <input type="text" class="form-control" name ="search" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>