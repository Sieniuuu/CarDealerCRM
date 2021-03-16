<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="workerHeader.jsp" %>

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
    <form:hidden path="type" value="CONSUMER"/>
    <b>EMAIL:</b> <form:input path="email" class="form-control" />
    <form:errors path="email" /><br>
    <form:hidden path="NIP"/>
    <b>PESEL:</b> <form:input path="PESEL" class="form-control" />
    <form:errors path="PESEL" /><br>
    <b>ADDRESS:</b> <form:input path="address" class="form-control"/>
    <form:errors path="address" /><br>
    <b>STATUS:</b> <form:select path="status" items="${allStatus}" class="form-control" />
    <form:errors path="status" /><br>
    <form:hidden path="createdOn"/>
    <form:hidden path="car"/>
    <form:hidden path="offer"/>
    <form:hidden path="user"/>
    <form:hidden path="accessories"/>
    <b>NOTES:</b> <form:textarea path="notes" class="form-control" />
    <form:errors path="notes" /><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>
</form:form>

</div>
</div>
</body>
</html>