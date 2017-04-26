<%-- Include tag is used to import header page --%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="test.DbManager"%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=${role} ${email}">Home</a></li>
            <li><a href="create_group.jsp?user=${role} ${email} ">Create Group</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
    <form action="GroupServlet" method="Post">
    <label>Search<label>
    <input type="text" name="search_group" >
    <input type="submit" value="Search" id="search_button" >
    </form>
    <br>
    <h5>${user.getUserEmail()}</h5>
    <h4><b>GROUPS</b></h4>
    <div id = "groupsManagement">
        <%
        String emailid = session.getAttribute("email").toString();
        DbManager db = new DbManager();
        java.sql.Connection conn = db.getConnection();
        Statement stmtForAdmin=conn.createStatement();
        Statement stmtForUser = conn.createStatement();
        String role = session.getAttribute("role").toString();
        System.out.println("role---------------- is "+role);
        if(conn == null)
        {
                out.print("Connection not established");
        }else
        {
            System.out.println("role is ADMIN");
            String queryForAdmin="SELECT * FROM groups";
            String queryForUser = "select g.g_name as group_name from groups g, group_user_relationship gu, users u where g.g_id = gu.g_id and gu.u_id = u.u_id and u.u_emailid = '"+emailid+"'";
            try
            {
                if (role.equals("admin")) 
                {
                   System.out.println("role is ADMIN");
                   ResultSet rs = stmtForAdmin.executeQuery(queryForAdmin);
                   while(rs.next())
                   {
                      %>
                      <table>
                    <tr>
                        <td><input type="button" value="<%=rs.getString("g_name")%>" id="<%=rs.getString("g_name")%>" onclick="getToGroup('<%=rs.getString("g_name")%>')"></td>
                        <td><input type="button" value="Delete" id="<%=rs.getString("g_id")%>" onclick="Delete_row('<%=rs.getString("g_id")%>')"></td>
                    </tr>
                      </table>
                      <% 
                   }
                }
                else
                {
                    System.out.println("User is arrived");
                    ResultSet rs1=stmtForUser.executeQuery(queryForUser);
                    while(rs1.next())
                    {
                       %>
                       <table>
                        <tr>
                            <td><input type="button" value="<%=rs1.getString("group_name")%>" id="<%=rs1.getString("group_name")%>" onclick="getToGroup('<%=rs1.getString("group_name")%>')"></td>
                        </tr>
                       </table>
                       <% 
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
                %>
        </div>

        <script>
        function getToGroup(group_name)
        {
            window.location.href = "GroupServlet?action="+group_name;
        }
               
        function Delete_row(group_id)
        {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function()
            {
                if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
                {
                    alert(xmlhttp.responseText);
                    location.reload(true);
                }
            };
            
            var params= "g_id="+group_id;
            xmlhttp.open("Post","/SSDI_Project/GroupServlet",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(params);
        }
    </script>
 </section>
</body>
</html>