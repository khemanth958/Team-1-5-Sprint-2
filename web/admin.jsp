<%-- Include tag is used to import header page --%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="test.DbManager"%>
<%@ include file="header.jsp" %>
<%-- Code to display items in List --%>
<body background= campus_loginw.jpg; no-repeat x center center fixed>

</body>
<nav id="menu">
     <ul><%-- Added the EL tag ${email} to display the users email instead of static name--%>
            <li><a href="admin.jsp?user=${user.getRole()} ${user.getUserEmail()}">Home</a></li>
            <li><a href="create_group.jsp?user=${user.getRole()} ${user.getUserEmail()} ">Create Group</a></li>
     </ul>    <%--On clicking the Reported Question link it will be directed  to the reportques.jsp--%>
</nav>
<%-- Section tag is used to write description  --%>
<section class="main">
    <form action="GroupServlet" method="Post">
    <%--Search:<br>--%>
    <input type="text" name="search_group" >
    <input type="submit" value="Search" id="search_button" >
    </form>
    <br>
    <h5>${user.getUserEmail()}</h5>
    <h4><b>GROUPS</b></h4>
    <div id = "groupsManagement" style="width:1000000px">
        <table style="width: 970px">
         <th>Group_ID</th>
        <th>Group Name</th>
        <th>Group Description</th>
  <c:forEach var="groups" items="${sessionScope.groups}">
        <tr> 
            <td>${groups.groupID}</td>
            <td> <a href="GroupServlet?action=${groups.groupName}">${groups.groupName}</a></td>
            <td> ${groups.groupDescription} </td>
        </tr>  
    </c:forEach>
    
    
    
    
    </table>
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
            xmlhttp.open("Post","/FinalNBadProject/GroupServlet",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send(params);
        }
    </script>
 </section>
