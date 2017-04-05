<%-- 
    Document   : updateAuthor
    Created on : Feb 20, 2017, 10:29:57 AM
    Author     : jstra
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${pageContext.request.locale}"
scope="session" />
<fmt:setBundle basename="my.package.prefix.i18n.messages" />
<!DOCTYPE html>
<html lang="${language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>update Author</title>
         <jsp:include page="templates/bundleLinks.jsp"/>
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        <h1>Author: ${author.authorName}</h1>
        

        <form class="form-horizontal" id="formAuthor" name="formAuthor" method="POST" action="<%= response.encodeURL("AuthorController?action=authorUpdate") %>">
             <input type="text" class="form-control hidden" id="${author.authorId}" name ="authorId" value="${author.authorId}">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="authorName">Author Name:</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" id="authorName" name ="authorName"  value="${author.authorName}" required>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="formGroupInputSmall">Date Added:</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" id="dateAdded" name="dateAdded" value="${author.dateAdded}" readOnly="true">
                </div>
            </div>
                

        </form>
                <div class="row justify-content-md-center">
                    <div class="col-sm-2"></div>
                <div class="col-sm-6">
                        
                    <form id="form1" name="formRect" method="POST" action="<%= response.encodeURL("BookController?action=bookEditDel") %>">        
<input type="text" class="form-control hidden" id="authorId" name ="authorId" value="${author.authorId}">
                <table id="table" class="table table-bordered table-striped table-condensed table-hover">
                    <thead class="">
                        <tr class="font-md"><th>Select</th><th>Book Title</th><th>ISBN</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="b" items="${author.bookSet}" varStatus="line">
                            <tr onclick="selectRow(this)">


                                <td>

                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="optionsCheckbox" id="${b.bookId}" value="${b.bookId}" >
                                        </label>    

                                    </div>

                                </td><td>${b.title}</td><td>${b.isbn}</td>

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
                <input class="btn btn-primary" name="edit" type="submit" value="Edit">
                <input class="btn btn-danger" name="del" type="submit" value="Delete">
                <span class="pull-right"><input class="btn btn-success" name="add" type="submit" value="Add" ></span>

              

            </form>
              
                    
                </div>
                </div>
                <div class="buttons">
                    <input class="btn btn-success" name="submit" type="Submit" value="Submit Author" form="formAuthor">
        <input class="btn btn-default" name="cancel" type="submit" value="Back to Lsit" form="formAuthor">
                </div>
                
                
                <jsp:include page="templates/footer.jsp"/>
    </body>
     <jsp:include page="templates/bundleScripts.jsp"/>
     
     <script>
            $(document).ready(function () {
                $('#table').DataTable();
            });
            function selectRow(row)
            {
                var firstInput = row.getElementsByTagName('input')[0];
                firstInput.checked = !firstInput.checked;
            }
    </script>
</html>
