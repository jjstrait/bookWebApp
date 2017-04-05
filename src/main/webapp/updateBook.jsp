<%-- 
    Document   : updateBook
    Created on : Apr 4, 2017, 10:22:59 PM
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
        <title>Edit Book</title>
        <jsp:include page="templates/bundleLinks.jsp"/>

    </head>
    <body>

        <jsp:include page="templates/navbar.jsp"/>
        <h1>Edit Book</h1>

        <form class="form-horizontal" id="form1" name="formRect" method="POST" action="<%= response.encodeURL("BookController?action=bookEdit")%>">
           <input type="text" class="form-control hidden" id="authorId" name ="authorId" value="${book.authorId.authorId}">
                <input type="text" class="form-control hidden" id="bookId" name ="bookId" value="${book.bookId}">
            <div class="form-group form-group-lg">
                
                <label class="col-sm-2 control-label" for="bookTitle">Book Title:</label>
                
                <div class="col-sm-8">
                    <input class="form-control" type="text" id="bookTitle" name ="bookTitle"  value="${book.title}" required>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="isbn">ISBN:</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" id="isbn" name ="isbn"  value="${book.isbn}" required>
                </div>
            </div>
           

            <input class="btn btn-success" type="submit" value="Submit">
            <input class="btn btn-default" name="cancel" type="button" value="Cancel" onclick="window.location = 'BookController?action=bookList';">
        </form>

        <jsp:include page="templates/footer.jsp"/>
    </body>
    <jsp:include page="templates/bundleScripts.jsp"/>
    
</html>
