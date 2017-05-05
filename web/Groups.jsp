<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>

<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>

<section class="main">
<table class="table.center">
<%
DbManager db = new DbManager();
java.sql.Connection conn = db.getConnection();
if(conn == null)
{
	out.print("Connection not established");
}else
{
	//out.print("Connection Established");
	String query="SELECT * FROM groups WHERE g_name like '%" + request.getAttribute("group_name") + "%'";
	Statement stmt=conn.createStatement();
	ResultSet rs=stmt.executeQuery(query);  
	%>
        <th>Group_ID</th>
        <th>Group Name</th>
        <th>Group Description</th>
        <th>Join</th>
        <%
        while(rs.next())
	{

	%>
	    <tr>
            <td><%=rs.getInt("g_id") %></td>
	    <td><a href="<%=rs.getString("g_name")%>.jsp"><%=rs.getString("g_name") %></a></td>
	    <td><%=rs.getString("g_description") %></td>
            <td><a href="<%=rs.getString("g_name")%>.jsp">Join</a></td></tr>
            
	 <%}
}
        %>
</table>
</section>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>