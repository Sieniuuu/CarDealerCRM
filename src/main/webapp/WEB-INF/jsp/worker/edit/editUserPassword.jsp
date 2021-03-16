<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="workerHeader.jsp" %>

<h2><b>Fill in the form field:</b></h2><br>
<hr>
<form method="post">
    <b>Old password:</b> <br>
    <hr>
    <input type="password" name="password" class="form-control"><br/>
    <hr>
    <h3>Password requirements:</h3><br>
    <i>At least one upper case English letter</i><br>
    <i> At least one lower case English letter</i><br>
    <i> At least one digit</i><br>
    <i> At least one special character</i><br>
    <i>Minimum eight in length<br>

    <hr>
    <b>New password: </b><br>
    <input type="password" name="firstNewPassword" class="form-control"><br>
    <hr>
    <b>New password: </b> <br>
    <input type="password" name="secondNewPassword" class="form-control"><br>
    <hr>
    <span style="color:Red; font-size:2em"><b>${empty error ? " " : error }</b></span><br>
    <hr>

    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>

</form>

</div>
</div>
</body>
</html>