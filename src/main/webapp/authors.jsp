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
        <h1>List of Authors</h1>
        <table class="table table-bordered">
            <thead class="">
            <tr class="font-md"><th>Id</th><th>Author Name</th><th>Date Added</th></tr>
            </thead>
            <tbody>
            <c:forEach var="a" items="${authorList}" varStatus="line">
                <c:choose>
                    <c:when test="${line.count%2!=0}">
                        <tr style="background-color: #ccffcc">
                        </c:when>
                        <c:otherwise>
                        <tr>
                        </c:otherwise>
                    </c:choose>


                    <td>${a.authorId}</td><td>${a.authorName}</td><td>${a.dateAdded}</td>

                </tr>

            </c:forEach>
                </tbody>
        </table>
    </body>




    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</html>
