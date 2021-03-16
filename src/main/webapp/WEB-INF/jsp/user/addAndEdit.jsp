<%@ include file="headerAdmin.jsp" %>

<form:form method="post"
           modelAttribute="user">

    <hr>
    </div>
    <hr>
    <form:hidden path="id"/>
    <b>LOGIN:</b><form:input path="userName" class="form-control" placeholder="LOGIN"/><form:errors path="userName" /><br>
    <b>PASSWORD:</b><form:input path="password" class="form-control" placeholder="PASSWORD" /><form:errors path="password" /><br>
    <b>EMAIL:</b><form:input path="email" class="form-control" placeholder="EMAIL" /><form:errors path="email" /><br>
    <b>FIRSTNAME:</b><form:input path="firstName" class="form-control" placeholder="FIRSTNAME" /><form:errors path="firstName" /><br>
    <b>LASTNAME:</b><form:input path="lastName" class="form-control" placeholder="LASTNAME" /><form:errors path="lastName" /><br>
    <b>PESEL:</b><form:input path="PESEL" class="form-control" placeholder="PESEL" /><form:errors path="PESEL" /><br>
    <b>BRANCH:</b> <form:select path="branch" items="${allBranch}" class="form-control"/><form:errors path="branch" /><br>
    <b>TYPE:</b><form:select path="type" items="${allTypes}" class="form-control"/><form:errors path="type" /><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>

</form:form>
</div>
</div>
</body>
</html>