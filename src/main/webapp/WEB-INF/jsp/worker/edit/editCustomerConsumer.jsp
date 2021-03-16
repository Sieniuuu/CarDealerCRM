<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="workerHeader.jsp" %>

<form:form method="post"
           modelAttribute="customer">
    <hr>
    <h2><b>Fill in the form field:</b></h2>
    <hr>
    <form:hidden path="PESEL"/>
    <form:hidden path="NIP"/>
    <form:hidden path="email"/>
    <form:hidden path="id"/>
    <b>FIRSTNAME:</b> <form:input path="firstName" class="form-control" />
    <form:errors path="firstName"/>
    <b>LASTNAME:</b> <form:input path="lastName" class="form-control" />
    <form:errors path="lastName"/>
    <form:hidden path="type"/>
    <b>PESEL:</b> <i>(sesitive data, to change contact the administrator)</i>
    <input type="text" class="form-control" disabled="true" placeholder="${customer.PESEL}" />
    <b>ADDRESS:</b> <form:input path="address" class="form-control"/>
    <form:errors path="address"/>
    <b>STATUS:</b> <form:select path="status" items="${allStatus}" class="form-control"/>
    <form:errors path="status"/>
    <form:hidden path="createdOn"/>
    <form:hidden path="car"/>
    <form:hidden path="offer"/>
    <form:hidden path="user"/>
    <form:hidden path="accessories"/>
    <b>NOTES:</b> <form:textarea path="notes" class="form-control"/>
    <form:errors path="notes"/><br>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>
</form:form>

</div>
</div>
</body>
</html>