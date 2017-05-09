<%-- 
    Document   : Comment
    Created on : Apr 23, 2017, 11:15:36 PM
    Author     : Akshay
--%>
<%@include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#submitBtn').click(function() {
        var cmt = $('#cmt').val();
        $.ajax({
            type : 'POST',
            data : {
                cmt : cmt,
                action : 'EnterMsg'
            },
            url : 'SubmitComment',
            var html='';
   $.ajax({
    dataType: "json",
    url: "SubmitComment",
    error: function () {
          alert('error occured');
    },
    success: function (result) {
    for(var key in result) {
    var value = result[key];
        html+='<div>'+key+':'+value+'</div>'
    }
    $("#view2").append(html);

    }
});
        });
    });
});

</script>
    <fieldset>
        <legend>Enter Message</legend>
    <form>
        Ques.<input type="text" id="cmt"> <input type="button"
            value="Send" id="submitBtn"><br> <span id="post1"></span>
    </form>
</fieldset>
<fieldset>
    <legend>View Message</legend>
    <form>
        <div id='view2'></div>
        <br>
    </form>
</fieldset>
</html>
<%@include file="footer.jsp" %>