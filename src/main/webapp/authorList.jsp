

<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
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
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class ="container">
        <h2>Author List</h2>
        <div class="row col-md-6">
        <table  class ="table table-striped table-bordered table-condensed">            
            <th> Author Name</th>
            <th> Date</th>
            <th colspan="2"> Delete/Edit</th></tr>
            <c:forEach var="a" items="${authorList}">
                <tr>
                                       
                    <td class="span6">${a.authorName}</td>
                    <td class="span2"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${a.dateAdded}" /></td>
                    <td class="span1"><button class="btn btn-danger" type="submit" value="Delete" onclick="location.href = 'authorController?action=delete&authorId=${a.authorId}'"> Delete</button></td>
                    <td class="span1"><button class="btn btn-warning" type="submit" value="Edit" onclick="location.href = 'authorController?action=edit&authorId=${a.authorId}'">Edit</button></td>
                </tr>

            </c:forEach>
        </table> 
        
        <button class="btn btn-success" type="submit" value="Add" onclick="location.href = 'authorController?action=add'">Add</button>
        <button class="btn btn-success" type="submit" value="list" onclick="location.href = 'bookController?action=list'">Book List</button>

        </div>
    </div>
</body>
</html>
