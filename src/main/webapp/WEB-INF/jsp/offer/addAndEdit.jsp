<%@ include file="headerAdmin.jsp" %>

<form:form method="post"
           modelAttribute="offer">
    <hr>
    <h2><b>Fill in the form field:</b></h2>
    <hr>
    <form:hidden path="id"/>
    <b>TYPE:</b> <form:select path="type" items="${allOfferTypes}" class="form-control"/><form:errors path="type" /><br>
    <b>AMOUNT:</b> <form:input path="amount" class="form-control" /><form:errors path="amount" /><br>
    <b>CONTRIBUTION:</b> <form:input path="contribution" class="form-control" /><form:errors path="contribution" /><br>
    <b>REPURCHASE:</b> <form:input path="repurchase" class="form-control" /><form:errors path="repurchase" /><br>
    <b>PEROID:</b> <form:input path="period" class="form-control" /><form:errors path="period" /><br>
    <form:hidden path="instalment"/>
    <br />
    <input type="submit" value="CALCULATE INSTALMENT" class="btn btn-primary btn-lg btn-block" />
</form:form>
</div>
</div>
</body>
</html>