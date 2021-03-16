<%@ include file="headerAdmin.jsp" %>

<form:form method="post"
           modelAttribute="accessories">

    <hr>
    </div>
    <hr>
    <form:hidden path="id"/>
    <b>NAME:</b><form:input path="name" class="form-control" />
    <form:errors path="name" /><br>
    <b>PRICE:</b><form:input path="price" class="form-control"  />
    <form:errors path="price" /><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>

</form:form>

</div>
</div>
</body>
</html>