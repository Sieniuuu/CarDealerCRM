<%@ include file="headerAdmin.jsp" %>

<form:form method="post"
           modelAttribute="customer">

    <hr>
    <h2><b>Fill in the form field:</b></h2>
    <hr>
    <form:hidden path="id"/>
    <b>FIRSTNAME:</b> <form:input path="firstName" class="form-control"/>
    <form:errors path="firstName" /><br>
    <b>LASTNAME:</b> <form:input path="lastName" class="form-control" />
    <form:errors path="lastName" /><br>
    <b>TYPE:</b> <form:select path="type" items="${allTypes}" class="form-control" />
    <form:errors path="type" /><br>
    <b>EMAIL:</b> <form:input path="email" class="form-control" />
    <b>NIP:</b> <form:input path="NIP" class="form-control" />
    <form:errors path="NIP" /><br>
    <b>PESEL:</b> <form:input path="PESEL" class="form-control" />
    <form:errors path="PESEL" /><br>
    <b>ADDRESS:</b> <form:input path="address" class="form-control"/>
    <form:errors path="address" /><br>
    <b>STATUS:</b> <form:select path="status" items="${allStatus}" class="form-control" />
    <form:errors path="status" />
    <form:hidden path="createdOn"/>
    <b>CAR:</b> <form:select path="car.id" items="${allCars}" class="form-control"
                          itemLabel="model" itemValue="id" />
    <form:errors path="car" /><br>
    <b>OFFER:</b> <form:select path="offer.id" items="${allOffers}" class="form-control"
                          itemLabel="proposition" itemValue="id" />
    <form:errors path="offer" /><br>
    <b>DEALER:</b> <form:select path="user.id" items="${allUsers}" class="form-control"
                        itemLabel="fullDescription" itemValue="id" />
    <form:errors path="user" /><br>
    <b>NOTES:</b> <form:input path="notes" class="form-control" />
    <form:errors path="notes" /><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>
</form:form>
</div>
</div>
</body>
</html>