

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section class="main">
     <h1><%= request.getAttribute("Result") %></h1>
     <p> click <a href ="admin.jsp?user=Admin ${email}">here</a> to go to Home
        </p>
       </section>
    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>