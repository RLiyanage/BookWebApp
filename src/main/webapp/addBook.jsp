<%-- 
    Document   : addBook
    Created on : Nov 25, 2017, 12:00:42 AM
    Author     : Rasika
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Book"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book Information</title>
    </head>
    <body>
        <h1>Add Book Information</h1>
        <form id="add" name="formType" value="" method = "POST" action ="bookController?action=insertData">
            <table border="1">
                <tr>
                    <td>Book Title</td>
                    <td> <input type ="text" name="title" value=""</td>
                </tr>
                <tr>
                    <td>Book ISBN</td>
                    <td> <input type ="text" name="isbn" value=""</td>
                </tr>
                <tr>
                    <td>Author Name</td>
                    <td> 
                        <select name="authorId" size="1" width="25">
                            <c:forEach var="a" items="${authorList}">
                                <option value="${a.authorId}">
                                    ${a.authorName}
                                </option> 
                            </c:forEach>
                        </select>
                    </td>
                </tr>

            </table>

            <input type ="hidden" name="bookId" value="">

            <button type ="submit" name ="buttonAction" value="Save">Save</button>
            <button type ="submit" name ="buttonAction" value="Cancel">Cancel</button>
        </form>
    </body>
</html>
