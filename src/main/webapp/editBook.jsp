
<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Book</title>
    </head>
    <body>
        <h1>Edit Book Information</h1>

        <form id="edit" name="formType "value="" method = "POST" action ="bookController?action=update">
            <table border="1">
                <tr>
                    <td>Book Title</td>
                    <td> <input type ="text" name="title" value="${bookRec.title}"</td>
                </tr>
                
                <tr>
                    <td>Book Isbn</td>
                    <td> <input type ="text" name="isbn" Value= "${bookRec.isbn}"</td>
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
                
                       <input type ="hidden" name="bookId" value="${bookRec.bookId}">                          
                                     
            </table>
            
            
            <button type ="submit" name ="buttonAction" value="Save">Save</button>
            <button type ="submit" name ="buttonAction" value="Cancel">Cancel</button>
        </form>
    </body>
</html>
