<%-- 
    Document   : updatepost
    Created on : Apr 23, 2017, 5:00:41 PM
    Author     : Akshay
--%>

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
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section class="main">
<table class="table.center">

    <h1>${groupName}</h1>
    <h1>${showpost}</h1>
  
 <form action="UpdatePostServlets" method="Post">
  Enter Post: <input type="text" name="fname"><br>
  <input type="hidden" name="groupname" value="${groupName}">
  <input type="hidden" name="oldpost" value="${showpost}">
  <input type="submit" value="Submit">
</form> 
</table>
  </section>
  
  <%@ include file="footer.jsp" %>
