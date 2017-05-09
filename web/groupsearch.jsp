<%@include file="header.jsp" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>
<%--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
<center><h3>${TheResultMessage}</h3></center>
<table>
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
        <th>Member Limit</th>
        <th>Join</th>
        <%
        while(rs.next())
	{

	%>
	    <tr>
            <td><%=rs.getInt("g_id") %></td>
	    <td><a href="<%=rs.getString("g_name")%>.jsp"><%=rs.getString("g_name") %></a></td>
	    <td><%=rs.getString("g_description") %></td>
            <td><%=rs.getString("g_group_members")%></td>
            <td><a href="<%=rs.getString("g_name")%>.jsp">Join</a></td></tr>
            
	 <%}
}
        %>
</table>
<%@include file="footer.jsp" %>