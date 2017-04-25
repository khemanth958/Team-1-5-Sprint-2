<%-- 
    Document   : comment1
    Created on : 24 Apr, 2017, 12:10:40 AM
    Author     : Viranchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>

<!DOCTYPE html>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
        <section class="main">
    <div id="main">
        <%--<h3><label>${msgForComment}</label></h3>--%>
        <%
        String email_id = session.getAttribute("email").toString();
        String post_id = request.getParameter("post_id").toString();
        System.out.println("email Id is "+email_id);
        try
        {
            DbManager db = new DbManager();
            java.sql.Connection conn = db.getConnection();
            if(conn == null)
            {
                out.print("Connection not established");
            }else
            {
                //out.print("Connection Established");
                System.out.println("email Id is "+email_id);
                String query1="select p.post as post_text, u.u_name as uname from posts p, users u, post_user_relationship pu where p.post_id = pu.post_id and pu.u_id = u.u_id and p.post_id="+post_id;
                Statement stmtForPost=conn.createStatement();
                ResultSet rs=stmtForPost.executeQuery(query1);
                    while(rs.next())
                    {
                    %>
                    <label><%=rs.getString("uname")%></label>
                        <textarea rows="5" cols="100" readonly>
                            <%=rs.getString("post_text")%>

                        </textarea>        
                    <%
                    }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        %>
    </div>
    <div>
        <table readonly>
            <th>From</th>
            <th>Comment</th>
        <%       
        try
        {
            DbManager db1 = new DbManager();
            java.sql.Connection conn1 = db1.getConnection();
            Statement stmtForComments = conn1.createStatement();
            if(conn1 == null)
            {
                out.print("Connection not established");
            }else
            {
                //out.print("Connection Established");
                String query2="select comment_t as comment, u.u_name as uname from comments c, users u where u.u_id = c.u_id and c.post_id="+post_id;
                ResultSet rs1 = stmtForComments.executeQuery(query2);
                while(rs1.next())
                {
                %>
                <tr>
                <td><%=rs1.getString("uname") %></td>
                <td><%=rs1.getString("comment") %></td>
                </tr>
                <%
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         %>
        </table>
    </div>
    <form action="CommentServlet" method="Post"> 
        <label >Comment Here</label>
        <input type="text" name="comment" required/> <br>
        <input type ="hidden" value="<%=post_id %>" name ="post_id">
        <input type="submit" value="Submit Comment" id="comment_button" >
        <br>
    </form>
</section>
         
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
