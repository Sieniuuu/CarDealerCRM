<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="workerHeader.jsp" %>

<form:form method="post"
           modelAttribute="customer">
    <hr>
    <h2><b>Fill in the form field:</b></h2>
    <hr>

    <form:hidden path="id"/>
    <form:hidden path="firstName"/>
    <form:hidden path="lastName"/>
    <b>EMAIL:</b> <form:input path="email" class="form-control"/>
    <form:errors path="email"/>
    <form:hidden path="type"/>
    <form:hidden path="PESEL"/>
    <form:hidden path="address"/>
    <form:hidden path="status"/>
    <form:hidden path="createdOn"/>
    <form:hidden path="car"/>
    <form:hidden path="offer"/>
    <form:hidden path="user"/>
    <form:hidden path="notes"/><br>
    <form:hidden path="accessories"/>
    <input type="submit" value="SUBMIT" class="btn btn-primary btn-lg btn-block"/>
</form:form>

</div>
</div>
</body>
</html>