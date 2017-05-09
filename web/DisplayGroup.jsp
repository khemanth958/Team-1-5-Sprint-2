

<%@page import="Model.Posts"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Group"%>
<%@page import="Model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.PreparedStatement" %> 
<%@ page import="test.DbManager" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%-- Include tag is used to import header page --%>
<%@ include file="header.jsp" %>

<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="LoginValidation">Home</a></li>
            <li><a href="NewPostsServlet?action=${group.getGroupName()}&email1=${user.getUserEmail()}">Show My Posts</a></li>
     </ul>    
</nav>

<section class="main">
    <%-- Img tag is used to import image --%>
             <center><h1>${group.getGroupName()}</h1></center>
            <% 
            Users user = (Users)session.getAttribute("user");
            Group group = (Group)request.getAttribute("group");
            ArrayList<Posts> newArrayList = (ArrayList<Posts>)request.getAttribute("posts");
            int u_id = user.getUserId();
            String group_name = group.getGroupName();
            String role = user.getRole();
            String email_id = user.getUserEmail();
            boolean isJoined = (Boolean)request.getAttribute("isJoined");
            try
            {
                if (role.equals("admin")) 
                {
                %>
             
             <input type="hidden" value="Join" id="join_group_button" onClick="${group.getGroupName()}">
             
             <br>
                <section class="login_form">
                    <form action="NewPostsServlet" method="Post">
                    <%--<Label type="hidden"> What's on your mind</label>--%>
                    <input type="hidden" value="${group.getGroupName()}" name="group_name">
                    </form>
                </section>
                <%
                }
                else
                {
                    if (isJoined) 
                    {
                    %>
                        <input type="hidden" value="Join" id="join_group_button" onClick="Join('${group.getGroupName()}','${user.getUserEmail()}')">
                        <br>
                        <section class="login_form">
                            <form action="NewPostsServlet" method="Post">
                            <Label> What's on your mind </label>
                            <input type="hidden" value="${group.getGroupName()}" name="group_name">
                            <input type="text" id="text1" name="input1" required />    
                            <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
                            </form>
                        </section>
                    <%
                    }
                    else
                    {
                    %>
                    <input type="submit" value="Join" id="join_group_button" onClick="Join('${group.getGroupName()}','${user.getUserEmail()}')">
                    <br>
                        <section class="login_form" id = "post_section" style="visibility: hidden">
                            <form action="NewPostsServlet" method="Post">
                            <Label> What's on your mind</label>
                            <input type="hidden" value="${group.getGroupName()}" name="group_name">
                            <input type="text" id="text1" name="input1" required />    
                            <input type="submit" value="Post" id="post_group_button" onClick="ShowText()">
                            </form>
                        </section>
                    <%
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
        <th>Posts</th>
        <%
            try
            {
                if(role.equals("admin"))
                {    
                    //while(rs.next())
                    for (int i = 0; i < newArrayList.size(); i++)
                    {
                        //i++;
                        %>
                            <tr>
                                <td><%=newArrayList.get(i).getuName() %></td>
                                <td><%=newArrayList.get(i).getUserPosts()  %></td>
                                <td><input type="button" id="delete_<%=i%>" value="Delete" style="width:auto" onclick="Insert_or_Delete(<%=newArrayList.get(i).getPostId() %>,'<%=role %>','<%=email_id %>','delete_<%=i%>')"/></td>
                            </tr>
                        <%
                    }
                }
                else
                {
                    System.out.println("The arraySize is-----------"+newArrayList.size());
                    for (int i = 0; i < newArrayList.size(); i++)
                    {
                        boolean check = false;
                        System.out.println("The arraySize of likes is-----------"+newArrayList.get(i).getListOfLikes().size());
                        for (int j = 0; j < newArrayList.get(i).getListOfLikes().size(); j++) 
                        {
                            int user_id = newArrayList.get(i).getListOfLikes().get(j).getU_id();
                            if (user_id == u_id) 
                            {
                                check = true;
                                break;
                            }
                        }
                        if (check)
                        {
                        //System.out.println("like_id is " + rs1.getString("like_id"));
                        %>
                            <tr>
                                <td><%=newArrayList.get(i).getuName()%></td>
                                <td><%=newArrayList.get(i).getUserPosts() %></td>
                                <td><input type="button" id="like_<%=i%>" style="width:auto" value="Liked" ></td>
                                <td><a href ="CommentServlet?post_id=<%=newArrayList.get(i).getPostId() %>&group_name=<%=group.getGroupName() %>">Show Comments</a></td>
                            <%
                                if (newArrayList.get(i).getuId() == u_id) 
                                {
                                %>
                                <td><input type="button" id="delete_<%=i%>" value="Delete" style="width:auto" onclick="Delete_User_Post(<%=newArrayList.get(i).getPostId() %>,'delete_<%=i%>')"/></td>
                                <%
                                }
                                %>
                            </tr>
                        <%
                        }
                        else
                        {
                        %>
                            <tr>
                                <td><%=newArrayList.get(i).getuName()%></td>
                                <td><%=newArrayList.get(i).getUserPosts() %></td>
                                <td><input type="button" id="like_<%=i%>" value="Like" style="width:auto" onclick="Insert_or_Delete(<%=newArrayList.get(i).getPostId() %>,'<%=role %>','<%=email_id %>','like_<%=i%>')"/></td>
                                <td><a href = "CommentServlet?post_id=<%=newArrayList.get(i).getPostId() %>&group_name=<%=group.getGroupName() %>">Show Comments</a></td>
                            <%
                                if (newArrayList.get(i).getuId() == u_id) 
                                {
                                %>
                                <td><input type="button" id="delete_<%=i%>" value="Delete" style="width:auto" onclick="Delete_User_Post(<%=newArrayList.get(i).getPostId() %>,'delete_<%=i%>')"/></td>
                                <%
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
                xmlhttp.open("Post","/FinalNBadProject/JoinGroupServlet",true);
                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                xmlhttp.send(params);
                //xmlhttp.send(param1);
                //xmlhttp.send(params2);
            }
        }
    
    function Insert_or_Delete(post_id,role,user_id,button_id)//like or delete
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
                        alert("the post is liked");
                    }
                    else if((xmlhttp.responseText) == "Deleted")
                    {
                        document.getElementById(button_id).value = "Deleted";
                        alert("The post is deleted");
                    }
                }
            };
            
            var params= "post_id="+post_id+";role="+role+";user_id="+user_id+";";
            //var params= "g_id="+group_id;
            xmlhttp.open("Post","/FinalNBadProject/InserOrDeleteServlet",true);
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
            xmlhttp.open("Post","/FinalNBadProject/DeletePost",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(params);
    }
    
    function VisitPage(post_id)
    {
        var x = location.href = "/comment1.jsp?post_id=" + post_id;
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
