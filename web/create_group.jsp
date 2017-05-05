

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<%-- Section to input login details --%>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section id="Create_group_form">
    <%-- Code to create Group form form--%>
    <form action="CreateGroupServlet" method="Post">
        <%--<input type="hidden" name="action" value="login">--%>
        <label >Group Name *</label>
        <input type="text" name="group_name" required/> <br>
        <label >Group Description *(150 words)</label>
        <input type="text" name="group_description" required/><br>
        <label>Group Members *</label>
        <input type="number" name="groupMembers" required/><br>
        <input type="submit" value="Create Group" id="Create_group_button" >
        <br>
    </form>
    
</section>
    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>