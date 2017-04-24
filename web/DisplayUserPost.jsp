<%-- 
    Document   : DisplayUserPost
    Created on : Apr 23, 2017, 9:08:03 PM
    Author     : Akshay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          
   
          <h2>${PostError}</h2>
  
   <table>
         <th>Post_ID</th>
        <th>Post</th>
        
    <c:forEach var="postsList" items="${sessionScope.postList}">
       
        <tr> 
            <td>${postsList.postId}</td>
            <td> <a href="GroupServlet?action=${postsList.userPosts}">${postsList.userPosts}</a></td>
            <td> <a href="UpdatePostServlets?showpost=${postsList.userPosts}&action=${groupName}">Update Post</a></td>  
        </tr>  
    </c:forEach>   
    
    </table>
     
    </body>        

</html>
