<%-- 
    Document   : books
    Created on : Feb 8, 2017, 12:05:11 AM
    Author     : Joshua
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
        <title>Books Page</title>
        <jsp:include page="templates/bundleLinks.jsp"/>
        
        
    </head>
    <body>
         <jsp:include page="templates/navbar.jsp"/>
        <h1>Books</h1>
        <div>
            <form id="form1" name="formRect" method="POST" action="<%= response.encodeURL("BookController?action=bookEditDel") %>">        

                <table id="table" class="table table-bordered table-striped table-condensed table-hover">
                    <thead class="">
                        <tr class="font-md"><th>Select</th><th>Book Title</th><th>ISBN</th><th>Author</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="b" items="${bookList}" varStatus="line">
                            <tr onclick="selectRow(this)">


                                <td>

                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="optionsCheckbox" id="${b.bookId}" value="${b.bookId}" >
                                        </label>    

                                    </div>

                                </td><td>${b.title}</td><td>${b.isbn}</td><td>${b.authorId.authorName}</td>

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
                <input class="btn btn-primary" name="edit" type="submit" value="Edit">
                <input class="btn btn-danger" name="del" type="submit" value="Delete">

              

            </form>
              <form class="form-group form-group-lg" id="form1" name="formRect" method="POST" action="<%= response.encodeURL("AuthorController?action=autohrsForBook") %>">
                <div class="col-md-offset-1">
            <input class="btn btn-default" type="submit" value="Add Book">
                </div>
        </form>

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
