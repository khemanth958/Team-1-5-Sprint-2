

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
            <li><a href="NewPostsServlet?action=${groupName}&email1=${email}">Back</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section class="main">
<table class="table.center">

  Group :  <h3>${groupName}</h3>
  Post :  <h4>${showpost}</h4>
  
 <form action="UpdatePostServlet" method="Post">
  Enter Post: <input type="text" name="fname"><br>
  <input type="hidden" name="groupname" value="${groupName}">
  <input type="hidden" name="oldpost" value="${showpost}">
  <input type="submit" value="Submit">
</form> 
</table>
  </section>
  
  <%@ include file="footer.jsp" %>
