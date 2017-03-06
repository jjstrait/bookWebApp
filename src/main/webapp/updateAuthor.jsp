<%-- 
    Document   : updateAuthor
    Created on : Feb 20, 2017, 10:29:57 AM
    Author     : jstra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>update Author</title>
        <link href="mainApp.css" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    </head>
    <body>
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
        <li class="active"><a href="AuthorController?action=authorList">View Records<span class="sr-only">(current)</span></a></li>
        
      <form class="navbar-form navbar-right" method="POST" action="AuthorController?action=search">
        <div class="form-group">
          <input type="text" class="form-control" name ="search" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        <h1>Author: ${author.authorName}</h1>
        

        <form class="form-horizontal" id="form1" name="formRect" method="POST" action="AuthorController?action=authorUpdate">
             <input type="text" class="form-control hidden" id="authorId" name ="authorId" value="${author.authorId}">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="authorName">Author Name:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="authorName" name ="authorName"  value="${author.authorName}" required>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="formGroupInputSmall">Date Added:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="dateAdded" name="dateAdded" value="${author.dateAdded}" readOnly="true">
                </div>
            </div>
                <input class="btn btn-default" name="submit" type="submit" value="Submit">
        <input class="btn btn-default" name="cancel" type="submit" value="Cancel">

        </form>
    </body>
    <script   src="https://code.jquery.com/jquery-3.1.1.slim.min.js"   integrity="sha256-/SIrNqv8h6QGKDuNoLGA4iret+kyesCkHGzVUUV0shc="   crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</html>
