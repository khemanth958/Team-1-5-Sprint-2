<%-- 
    Document   : UpdatePost
    Created on : 22 Apr, 2017, 8:25:34 PM
    Author     : Viranchi
--%>

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<%-- Section to input login details --%>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=${role} ${email}">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<section id="UpdatePost">
    <%-- Code to create Group form form--%>
    <form action="UpdatePost" method="processRequest">
        <%--<input type="hidden" name="action" value="login">--%>
        <label >Post</label>
        <h3><label> ${messageForPost} </label></h3>
        <input type="text" value="${post_text}" name="group_name" required/> <br>
        <input type="hidden" value="${group_name}" name="group_name">
        <input type="hidden" value="${post_id}" name="post_id">
        <input type="submit" value="Create Group" id="Create_group_button" >
        <br>
    </form>
    
</section>
    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>
