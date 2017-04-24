

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%-- title of the Page--%>
        <title>Click &AMP; Connect</title>
        <%-- importing CSS stylesheet --%>
        <link rel="stylesheet" href="main.css">
    </head>
    <body>
        <%-- Code to specify Header section of the page--%>
        <div id="header">
            <nav id="header_menu">
                <ul  class="left" >
                    <li>Click &AMP; Connect</li>
                </ul>
                <ul class="right">
                    <c:if test="${role == null}">
                        <li><a href="about.jsp">About Us</a></li>
                        <li><a href="login.jsp">Login</a></li>
                        <li><a href="signup.jsp">Sign Up</a></li>
                    </c:if>
                        <c:if test="${user.getUserEmail() != null}">
                        <li><a href="aboutl.jsp?user=Hello,${user.getUserName()}">About Us</a></li>
                        <li><a href="<%=request.getContextPath()%>/LogOutServlet">Log Out</a></li>
                        <li>Hello, ${user.getUserName()}</li>
                        </c:if>
                </ul>

            </nav>



        </div>

