<%-- 
    Document   : bookList
    Created on : Nov 16, 2017, 9:50:00 AM
    Author     : Rasika
--%>



<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class ="container">
            <h2>Book List</h2>
            <div class="row col-md-6">
                <table  class ="table table-striped table-bordered table-condensed">            
                    <th> Book Title</th>
                    <th> ISBN</th>  
                    <th> Author Name</th>            
                    <th colspan="2"> Delete/Edit</th></tr>
                        <c:forEach var="b" items="${bookList}">
                        <tr>

                            <td class="span6">${b.title}</td>
                            <td class="span2"> ${b.isbn}</td>
                            <td class="span2"> ${b.getAuthor().authorName}</td>
                            <td class="span1"><button class="btn btn-danger" type="submit" value="Delete" onclick="location.href = 'bookController?action=delete&bookId=${b.bookId}'"> Delete</button></td>
                            <td class="span1"><button class="btn btn-warning" type="submit" value="Edit" onclick="location.href = 'bookController?action=edit&bookId=${b.bookId}'">Edit</button></td>
                        </tr>

                    </c:forEach>
                </table> 

                <button class="btn btn-success" type="submit" value="Add" onclick="location.href = 'bookController?action=add'">Add</button>
                <button class="btn btn-success" type="submit" value="list" onclick="location.href = 'authorController?action=add'">Add Author</button>
            </div>
        </div>
    </body>
</html>
