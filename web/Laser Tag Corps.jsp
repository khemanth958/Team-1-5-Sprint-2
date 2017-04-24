<%-- 
    Document   : 49_uncc
    Created on : 26 Mar, 2017, 5:09:40 PM
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
    <%-- Img tag is used to import image --%>
             <center><h1>Laser Tag Corps</h1><center>
             <hr>
        <input type="submit" value="Join" id="join_group_button" onClick="Join('Laser Tag Corps')">
        <br>
        
        

<section class="login_form">
    <form action="NewPostsServlet" method="Post">
    <Label> What's on your mind</label>
    <input type="hidden" value="Laser Tag Corps" name="group_name">
    <input type="text" id="text1" name="input1" required />    
    <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
    </form>
</section>
        
<section class="main">
    <div id="main">
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
            String email_id = session.getAttribute("email").toString();
            System.out.println("email Id is "+email_id);
            String p = session.getAttribute("role").toString();
            int post_id = 0;
            String query1="select p.post as post_text, p.post_id as post_id, u.u_name as uname from posts p, users u, post_user_group_relationship pug, groups g where p.post_id = pug.p_id and pug.u_id = u.u_id and pug.g_id = g.g_id and g.g_name = 'Laser Tag Corps'";
            Statement stmtForPost=conn.createStatement();
            Statement stmtForLikes=conn.createStatement();
            ResultSet rs=stmtForPost.executeQuery(query1);
            System.out.println(p);
            int i = 0;
        %>
       
        <th>Posts</th>
        <%
            try
            {
                if(p.equals("admin"))
                {    
                    while(rs.next())
                    {
                        i++;
                        %>
                            <tr>
                            <td><%=rs.getString("uname") %></td>
                            <td><%=rs.getString("post_text") %></td>
                            <td><input type="button" id="delete_<%=i%>" value="Delete" onclick="Insert_or_Delete('<%=rs.getInt("post_id") %>','<%=p %>','<%=email_id %>','delete_<%=i%>')"/></td>
                            </tr>
                        <%
                    }
                }
                else
                {
                    while(rs.next())
                    {
                        i++;
                        post_id = rs.getInt("post_id");
                        System.out.println("post id is " + post_id);
                        String query2 = "SELECT l.like_id as like_id from likes l, users u where u.u_id = l.u_id and u.u_emailid = '"+email_id+"' and l.post_id = "+post_id+"";
                        ResultSet rs1=stmtForLikes.executeQuery(query2);
                        if (rs1.next())
                        {
                        System.out.println("like_id is " + rs1.getString("like_id"));
                        %>
                            <tr>
                            <td><%=rs.getString("uname") %></td>
                            <td><%=rs.getString("post_text") %></td>
                            <td><input type="button" id="like_<%=i%>" value="Liked" ></td>
                            </tr>
                        <%
                        }
                        else
                        {
                        %>
                            <tr>
                            <td><%=rs.getString("uname") %></td>
                            <td><%=rs.getString("post_text") %></td>
                            <td><input type="button" id="like_<%=i%>" value="Like" onclick="Insert_or_Delete('<%=rs.getInt("post_id") %>','<%=p %>','<%=email_id %>','like_<%=i%>')"/></td>
                            </tr>
                        <%
                        }
                    }
                }
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
        }   
                %>
        </table>
    </div>
</section>
</section>
    
             <script>

    function Join(group_name)
        {
            alert("Your request has been sent");
            document.getElementById("join_group_button").value = "Request Sent";
        }
    
    function Insert_or_Delete(post_id,role,user_id,button_id)
    {
        if(document.getElementById(button_id).value == "Like" || document.getElementById(button_id).value == "Delete")
        {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    if((xmlhttp.responseText) == "Liked")
                    {
                        document.getElementById(button_id).value = "Liked";
                    }
                    else if((xmlhttp.responseText) == "Deleted")
                        document.getElementById(button_id).value = "Deleted";
                }
            };
            
            var params= "post_id="+post_id+";role="+role+";user_id="+user_id+";";
            //var params= "g_id="+group_id;
            xmlhttp.open("Post","/SSDI_Project/InserOrDeleteServlet",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(params);
            //xmlhttp.send(param1);
            //xmlhttp.send(params2);
        }
    }
    
    function ShowText()
    {
        //var post_group_button = document.getElementById("post_group_button").value;
        //var h = document.input1;
        //h.style.visibility="visible";
        document.getElementById("text1").style.visibility="visible";
        //document.getElementsByName("input1").style.visibility="visible";
        //document.getElementsByName("input1").style.display="block";
        //var h = document.input1;
          //h.style.visibility="visible";


//        document.getElementById("input1").style.visibility = "visible";
    }
        </script>
<%-- Include tag is used to import footer page --%>
<%@ include file="footer.jsp" %>

