<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 1:12:54 PM
    Author     : Rasika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
             <table border ="1">
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List Page</title>
    </head>
    <body>
        <h1>Author List</h1>
        <body>
                <tr>
                    <td>Id</td>
                    <td>Name</td>
                    <td>Date</td>
                </tr>
            <c:forEach var ="a"  items = "${authorList}">
                <p>Author Name:${a.authorName}</p><br>
            </c:foreach>
        
            </table>
    </body>
</html>
