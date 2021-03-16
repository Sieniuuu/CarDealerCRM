<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="workerHeader.jsp" %>

<form:form method="post"
           modelAttribute="customer">

    <h2><b>Choose accessories for Customer:</b></h2>
    <hr>
    <form:hidden path="id"/>
    <form:hidden path="firstName"/>
    <form:hidden path="lastName"/>
    <form:hidden path="type"/>
    <form:hidden path="email"/>
    <form:hidden path="NIP"/>
    <form:hidden path="PESEL"/>
    <form:hidden path="address"/>
    <form:hidden path="status"/>
    <form:hidden path="createdOn"/>
    <b>ADD ACCESSORIES: </b><br>
    <c:forEach items="${accessories}" var="accessories">
        <form:checkbox path="accessories" value="${accessories}"/> <b> ${accessories.fullName} </b><br>
    </c:forEach>
    <form:hidden path="car"/>
    <form:hidden path="offer"/>
    <form:hidden path="user"/>
    <form:hidden path="notes"/>
    <br>
    <input type="submit" value="ADD" class="btn btn-primary btn-lg btn-block"/>
</form:form>

</div>
</div>
</body>
</html>