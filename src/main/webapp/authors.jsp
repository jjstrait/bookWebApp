<%-- 
    Document   : authors
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
        <title>Author Page</title>
        <jsp:include page="templates/bundleLinks.jsp"/>
        
        
    </head>
    <body>
         <jsp:include page="templates/navbar.jsp"/>
        <h1><fmt:message key="page.authors.list"/>${errMsg}</h1>
        <div>
            <form id="form1" name="formRect" method="POST" action="<%= response.encodeURL("AuthorController?action=authorEditDel") %>">        

                <table id="table" class="table table-bordered table-striped table-condensed table-hover">
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

                <span class="pull-right"><input class="btn btn-success" name="Add" type="button" value="Add" onclick="window.location = 'addAuthor.jsp';"></span>

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
