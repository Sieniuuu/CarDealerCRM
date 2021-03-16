<%@ include file="headerAdmin.jsp" %>

<form:form method="post"
           modelAttribute="car">

    <hr>
    <h2><b>Fill in the form field:</b></h2>
    <hr>
    <form:hidden path="id"/>
    <b>NAME: </b><form:input path="name" class="form-control" placeholder="NAME"/><form:errors path="name"/><br>
    <b>BODY: </b><form:input path="body" class="form-control"  placeholder="BODY"/><form:errors path="body"/><br>
    <b>ENGINE: </b><form:input path="engine" class="form-control"  placeholder="ENGINE"/><form:errors path="engine"/><br>
    <b>GEARBOX: </b><form:input path="gearbox" class="form-control"  placeholder="GEARBOX"/><form:errors path="gearbox"/><br>
    <b>VERSION: </b><form:input path="version" class="form-control"  placeholder="VERSION"/><form:errors path="version"/><br>
    <b>PRICE: </b><form:input path="price" class="form-control"  placeholder="PRICE"/><form:errors path="price"/><br><br>
    <b>QUANTITY: </b><form:input path="quantity" class="form-control"  placeholder="QUANTITY"/><form:errors path="quantity"/><br><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>

</form:form>
</div>
</div>
</body>
</html>