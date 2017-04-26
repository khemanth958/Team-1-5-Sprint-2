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

<!DOCTYPE html>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=Admin ${email}">Home</a></li>
            <li><a href="NewPostsServlet?action=${groupName}&email1=${email}">Show My Posts</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>

<section class="main">
    <%-- Img tag is used to import image --%>
             <center><h1>${groupName}</h1></center>
            <% 
            String p = session.getAttribute("role").toString();
            String group_name = request.getAttribute("groupName").toString();
            String email_id = session.getAttribute("email").toString();
            try
            {
                if (p.equals("admin")) 
                {
                %>
                <input type="hidden" value="Join" id="join_group_button" onClick="${groupName}">
                <br>
                <section class="login_form">
                    <form action="NewPostsServlet" method="Post">
                    <%--<Label type="hidden"> What's on your mind</label>--%>
                    <input type="hidden" value="${groupName}" name="group_name">
                    </form>
                </section>
                <%
                }
                else
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
                        //String p = session.getAttribute("role").toString();
                        int post_id = 0;
                        String queryForCheckJoin="select gu.u_id from group_user_relationship gu, users u where gu.u_id = u.u_id and u.u_emailid = '"+email_id+"' and gu.g_id = (select g_id from groups where g_name = '"+group_name+"')";
                        Statement stmtJoin = conn.createStatement();
                        ResultSet rscheck=stmtJoin.executeQuery(queryForCheckJoin);
                        if (rscheck.next()) 
                        {
                        %>
                            <input type="hidden" value="Join" id="join_group_button" onClick="Join('${groupName}','${email}')">
                            <br>
                            <section class="login_form">
                                <form action="NewPostsServlet" method="Post">
                                <Label> What's on your mind</label>
                                <input type="hidden" value="${groupName}" name="group_name">
                                <input type="text" id="text1" name="input1" required />    
                                <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
                                </form>
                            </section>
                        <%
                        }
                        else
                        {
                        %>
                        <input type="submit" value="Join" id="join_group_button" onClick="Join('${groupName}','${email}')">
                        <br>
                            <section class="login_form" id = "post_section" style="visibility: hidden">
                                <form action="NewPostsServlet" method="Post">
                                <Label> What's on your mind</label>
                                <input type="hidden" value="${groupName}" name="group_name">
                                <input type="text" id="text1" name="input1" required />    
                                <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
                                </form>
                            </section>
                        <%
                        }
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                %>
        
  <section class="main">
    <div id="main">
        <table>
        <%
        
        System.out.println("The group name is ---- "+group_name);
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        if(conn == null)
        {
            out.print("Connection not established");
        }else
        {
            //out.print("Connection Established");
            
            System.out.println("email Id is "+email_id);
            //String p = session.getAttribute("role").toString();
            int post_id = 0;
            String query1="select p.post as post_text, p.post_id as post_id,u.u_id as u_id, u.u_name as uname from posts p, users u, post_user_group_relationship pug, groups g where p.post_id = pug.p_id and pug.u_id = u.u_id and pug.g_id = g.g_id and g.g_name = '"+group_name+"'";
            Statement stmtForPost=conn.createStatement();
            Statement stmtForLikes=conn.createStatement();
            Statement stmtForDelete = conn.createStatement();
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
                        String queryForDelete = "Select u_id from users where u_emailid = '"+email_id+"'";
                        String query2 = "SELECT l.like_id as like_id from likes l, users u where u.u_id = l.u_id and u.u_emailid = '"+email_id+"' and l.post_id = "+post_id+"";
                        ResultSet rs1=stmtForLikes.executeQuery(query2);
                        ResultSet rs2 = stmtForDelete.executeQuery(queryForDelete);
                        if (rs1.next())
                        {
                        System.out.println("like_id is " + rs1.getString("like_id"));
                        %>
                            <tr>
                            <td><%=rs.getString("uname") %></td>
                            <td><%=rs.getString("post_text") %></td>
                            <td><input type="button" id="like_<%=i%>" value="Liked" ></td>
                            <td><a href ="comment1.jsp?post_id=<%=post_id %>">Comment</a></td>
                            <%
                                while(rs2.next())
                                {
                                    if (rs2.getInt("u_id") == rs.getInt("u_id")) 
                                    {
                                    %>
                                        <td><input type="button" id="delete_<%=i%>" value="Delete" onclick="Delete_User_Post('<%=rs.getInt("post_id") %>','delete_<%=i%>')"/></td>
                                    <%
                                    }
                                }
                            %>
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
                            <td><a href = "comment1.jsp?post_id=<%=post_id %>">Comment</a></td>
                            <%
                                while(rs2.next())
                                {
                                    if (rs2.getInt("u_id") == rs.getInt("u_id")) 
                                    {
                                    %>
                                        <td><input type="button" id="delete_<%=i%>" value="Delete" onclick="Delete_User_Post('<%=rs.getInt("post_id") %>','delete_<%=i%>')"/></td>
                                    <%
                                    }
                                }
                            %>
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

    function Join(group_name,email)
        {
            if (document.getElementById("join_group_button").value != "Joined") 
            {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function()
                {
                    if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        if((xmlhttp.responseText) == "Joined")
                        {
                            document.getElementById("join_group_button").value = "Joined";
                            document.getElementById("post_section").style.visibility = "visible";
                        }
                        else
                            alert("Member Limit reached. Cant Join this group");
                    }
                };

                var params= "group_name="+group_name+";email="+email+";";
                //var params= "g_id="+group_id;
                xmlhttp.open("Post","/SSDI_Project/JoinGroupServlet",true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
                //xmlhttp.send(param1);
                //xmlhttp.send(params2);
            }
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
    
    function Delete_User_Post(post_id,button_id)
    {
        var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    if((xmlhttp.responseText) == "Deleted")
                        document.getElementById(button_id).value = "Deleted";
                }
            };
            
            var params= "post_id="+post_id;
            //var params= "g_id="+group_id;
            xmlhttp.open("Post","/SSDI_Project/DeletePost",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(params);
    }
    
    function VisitPage(post_id)
    {
        var x = location.href = "/comment.jsp?post_id=" + post_id;
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
