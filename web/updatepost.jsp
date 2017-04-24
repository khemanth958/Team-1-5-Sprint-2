<%-- 
    Document   : updatepost
    Created on : Apr 23, 2017, 5:00:41 PM
    Author     : Akshay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
  <body>
    <h1>${groupName}</h1>
    <h1>${showpost}</h1>
  
 <form action="UpdatePostServlet" method="Post">
  Enter Post: <input type="text" name="fname"><br>
  <input type="hidden" name="groupname" value="${groupName}">
  <input type="hidden" name="oldpost" value="${showpost}">
  <input type="submit" value="Submit">
</form> 


        <h1>Hello World!</h1>
    </body>
</html>
