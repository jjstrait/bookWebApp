<%-- 
    Document   : index
    Created on : Feb 8, 2017, 12:04:45 AM
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
        <title>Home Page</title>
       
             <jsp:include page="templates/bundleLinks.jsp"/>
             
    </head>
    <body>
        <jsp:include page="templates/navbar.jsp"/>
        
        <div class="jumbotron">
            <h2>Authors Data View:</h2>
            ${errMsg}
            <form class="form-group form-group-lg" id="form1" name="formRect" method="POST" action="<%= response.encodeURL("AuthorController?action=authorList") %>">
                <div class="col-md-offset-1">
            <input class="btn btn-default" type="submit" value="List of Authors">
                </div>
        </form>
                 <form class="form-group form-group-lg" id="form1" name="formRect" method="POST" action="<%= response.encodeURL("BookController?action=bookList") %>">
                <div class="col-md-offset-1">
            <input class="btn btn-default" type="submit" value="List of Books">
                </div>
        </form>
        </div>
        
        <jsp:include page="templates/footer.jsp"/>
    </body>
    
  
</html>
