<%-- Include tag is used to import header page --%>
<%@include file="header.jsp" %>
<%-- Section to input login details --%>

<section id="login_form">
    <%-- Code to create login form--%>
    <center><h3 style="color: red">${msgForNotLogin}</h3></center>
    <form action="LoginValidation" method="Post"><%--Sending the login username and password to the LoginValidationServlet 
                                                         for validation Admin username : admin@gmail.com and Password : admin
                                                            user username:aks@gmail.com  Password : aks --%> 
        <input type="hidden" name="action" value="login">
        <label >Email Address *</label>
        <input type="email" name="email" required/> <br>
        <label >Password *</label>
        <input type="password" name="password" required/><br>
        <label>&nbsp;</label>
        <input type="submit" value="Log in" id="login_button" >
        <br>
    </form>
    <%-- Code to go to Sign up for a new account  --%>
    <a href="signup.jsp" id="sign_up_link">Sign up for a new account</a>

</section>
    <%-- Include tag is used to import footer page --%>
<%@include file="footer.jsp" %>