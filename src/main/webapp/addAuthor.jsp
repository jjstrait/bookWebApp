<%-- 
    Document   : addAuthor
    Created on : Feb 20, 2017, 10:03:29 AM
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
        <title>Add Author</title>
         <jsp:include page="templates/bundleLinks.jsp"/>
        
    </head>
    <body>
        
       <jsp:include page="templates/navbar.jsp"/>
        <h1>Add Author</h1>
        
        <form class="form-horizontal" id="form1" name="formRect" method="POST" action="<%= response.encodeURL("AuthorController?action=authorAdd") %>">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="authorName">Author Name:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="authorName" name ="authorName"  value="${author.authorName}" required>
                </div>
            </div>

            <input class="btn btn-primary" type="submit" value="Submit">
            <input class="btn btn-default" name="cancel" type="button" value="Cancel" onclick="window.location='AuthorController?action=authorList';">
        </form>
      
     <jsp:include page="templates/footer.jsp"/>
    </body>
    <jsp:include page="templates/bundleScripts.jsp"/>
</html>
