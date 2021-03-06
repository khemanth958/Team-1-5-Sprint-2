<%@ page import="java.sql.DriverManager" %>



<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users Database</title>
</head>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="LoginValidation">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section class="main">
    <h5>${group1.groupId}</h5>
    
      <table>
         <th>Group_ID</th>
        <th>Group Name</th>
        <th>Group Description</th>
        <th>Numbers of Members</th>
    <c:forEach var="groups" items="${requestScope.groups}">
        
        <tr> 
            <td>${groups.groupID}</td>
            <td> <a href="GroupServlet?action=${groups.groupName}">${groups.groupName}</a></td>
            <td> ${groups.groupDescription} </td>
            <td>${groups.numberOfGroupMembers} </td>
        </tr>  
    </c:forEach>
            
      </table>  
</section>>
<%@include file="footer.jsp" %>