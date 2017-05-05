

<%@page import="Model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Posts"%>
<%@page import="Model.Users"%>
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
            <li><a href="admin.jsp?user=Admin ${user.getUserEmail()}">Home</a></li>
                        <%--<li><a href="GroupServlet?action=${groupName}&email=${user.getUserEmail()}">Back</a></li>--%>

     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
        <section class="main">
    <div id="main">
        <label style="font-size: xx-large;font-weight: bold;width:auto"> Post</label>&nbsp;&nbsp;&nbsp;&nbsp;
        
        <%--<h3><label>${msgForComment}</label></h3>--%>
        <%
        Users user = (Users)session.getAttribute("user");
        Posts thePost = (Posts)request.getAttribute("thePost");
        ArrayList<Comment> commentList = (ArrayList<Comment>)request.getAttribute("commentList"); 
        String email_id = user.getUserEmail();
        //String post_id = request.getParameter("post_id").toString();
        System.out.println("email Id is "+email_id);
        try
        {
            %>
            <label style="font-size: x-large;font-weight: bold;width:auto"><%=thePost.getuName() %>: </label>
            <label style="font-size: x-large;font-weight: bold;width:auto" readonly>
                    <%=thePost.getUserPosts() %>
            </label>        
            <%   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        %>
    </div>
    <hr>
    <%--<h3 style="color: blue">${msgOfComment}</h3>
    <hr>--%>
    <div>
        <table readonly>
            <th>From</th>
            <th>Comment</th>
        <%       
        try
        {
            for (int i = 0; i < commentList.size(); i++)
            {
            %>
            <tr>
                <td><%=commentList.get(i).getU_name() %></td>
                <td><%=commentList.get(i).getComment_text() %></td>
            </tr>
            <%
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
         %>
        </table>
    </div>
        <hr>
    <form action="CommentServlet" method="Post"> 
        <label >Comment Here</label>
        <input type="text" name="comment" required/> <br>
        <input type ="hidden" value="<%=thePost.getPostId() %>" name ="post_id">
        <input type="submit" value="Submit Comment" id="comment_button" >
        <br>
    </form>
</section>
         
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>
