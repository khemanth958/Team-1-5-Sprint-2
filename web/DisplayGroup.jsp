<%-- 
    Document   : DisplayGroup
    Created on : Apr 22, 2017, 10:38:57 PM
    Author     : Akshay
--%>

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
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
            <li><a href="NewPostsServlet?action=${groupName}">Show Posts</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>

<section class="main">
    <%-- Img tag is used to import image --%>
             <center><h1>${groupName}</h1><center>
             <hr>
        <input type="submit" value="Join" id="join_group_button" onClick="Join('Uncc_49ers')">
        <br>
        
        

<section class="login_form">
    <form action="NewPostsServlet" method="Post">
    <Label> What's on your mind</label>
    <input type="hidden" value="Uncc_49ers" name="group_name">
    <input type="text" id="text1" name="input1" required />    
    <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
    </form>
</section>   
        
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
