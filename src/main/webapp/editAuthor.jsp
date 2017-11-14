<%-- 
    Document   : authorEdit
    Created on : Oct 13, 2017, 9:52:10 AM
    Author     : Rasika
--%>

<%@page import="edu.wctc.distjava.jgl.bookwebapp.model.Author"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Author Information</h1>

        <form id="edit" name="formType "value="" method = "POST" action ="authorController?action=update">
            <table border="1">
                <tr>
                    <td>Author Name</td>
                    <td> <input type ="text" name="authorName" value="${authorRec.getAuthorName()}"</td>
                </tr>
                
                <tr>
                    <td>Date Added</td>
                    <td> <input type ="text" name="dateAdded"  value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${authorRec.getDateAdded()}" />"readonly=""</td>
                </tr>                       
                       <input type ="hidden" name="authorId" value="${authorRec.getAuthorId()}">                          
                                     
            </table>
            
            
            <button type ="submit" name ="buttonAction" value="Save">Save</button>
            <button type ="submit" name ="buttonAction" value="Cancel">Cancel</button>
        </form>
    </body>
</html>
