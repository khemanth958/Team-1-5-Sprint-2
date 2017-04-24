<%-- 
    Document   : groups
    Created on : Apr 22, 2017, 7:33:05 PM
    Author     : Akshay
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <table>
         <th>Group_ID</th>
        <th>Group Name</th>
        <th>Group Description</th>
  <c:forEach var="groups" items="${requestScope.groups}">
        
        <tr> 
            <td>${groups.groupID}</td>
            <td> <a href="GroupServlet?action=${groups.groupName}">${groups.groupName}</a></td>
            <td> ${groups.groupDescription} </td>
            <td>${groups.numberOfGroupMembers} </td>
        </tr>  
    </c:forEach>
    
    
    
    
    </table>
     </body>
</html>
