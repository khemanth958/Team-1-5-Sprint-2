

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin${email}">Home</a></li>
            <li><a href="GroupServlet?action=${groupName}&email=${email}">Back</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
   
          <h2>${PostError}</h2>
          
 
      <table>
         <th>Post_ID</th>
        <th>Post</th>
        
    <c:forEach var="postsList" items="${requestScope.postList}">
       
        <tr> 
            <td>${postsList.postId}</td>
            <td>${postsList.userPosts}</td>
            <td> <a href="UpdatePostServlet?showpost=${postsList.userPosts}&action=${groupName}">Update Post</a></td>  
        </tr>  
    </c:forEach>   
    
    </table>
 
    </body>        

</html>
<%@include file="footer.jsp" %>
