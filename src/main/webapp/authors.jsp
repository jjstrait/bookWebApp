<%-- 
    Document   : authors
    Created on : Feb 8, 2017, 12:05:11 AM
    Author     : Joshua
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author Page</title>
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
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        <h1>List of Authors ${errMsg}</h1>
        <div>
        <form id="form1" name="formRect" method="POST" action="AuthorController?action=authorEditDel">        

            <table class="table table-bordered table-striped table-condensed table-hover">
                <thead class="">
                    <tr class="font-md"><th>Select</th><th>Author Name</th><th>Date Added</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${authorList}" varStatus="line">
                        <tr onclick="selectRow(this)">


                            <td>

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="optionsCheckbox" id="${a.authorId}" value="${a.authorId}" >
                                    </label>    

                                </div>

                            </td><td>${a.authorName}</td><td>${a.dateAdded}</td>

                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <input class="btn btn-primary" name="edit" type="submit" value="Edit">
        <input class="btn btn-danger" name="del" type="submit" value="Delete">
         
        <span class="pull-right"><input class="btn btn-success" name="Add" type="button" value="Add" onclick="window.location='addAuthor.jsp';"></span>
          
              </form>
           
        </div>
        
      
                   
        
                    
                

    </body>



    <script   src="https://code.jquery.com/jquery-3.1.1.slim.min.js"   integrity="sha256-/SIrNqv8h6QGKDuNoLGA4iret+kyesCkHGzVUUV0shc="   crossorigin="anonymous"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script>
                            function selectRow(row)
                            {
                                var firstInput = row.getElementsByTagName('input')[0];
                                firstInput.checked = !firstInput.checked;
                            }
    </script>


</html>
