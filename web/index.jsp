<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Database</title>
</head>
<body>
    <h5>${group1.groupId}</h5>
    
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
<%@include file="footer.jsp" %>