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
    </head>
    <body>
        <h1>Hello World!</h1>
        <form id="form1" name="formRect" method="POST" action="AuthorController?action=authorAdd">
            <label >Author ID:</label><input type="text" name="authorName" value=""><br>
            <label >Author Name:</label><input type="text" name="authorName" value=""><br>
            <label >Date Added:</label><input type="text" name="authorName" value=""><br>
            <input class="btn btn-default" type="submit" value="Submit">
            
            
            
        </form>
    </body>
</html>
