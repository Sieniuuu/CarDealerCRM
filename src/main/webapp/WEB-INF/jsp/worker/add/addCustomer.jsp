<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="workerHeader.jsp" %>

    <h2>CHOOSE TYPE OF CUSTOMER</h2>
    <label>
        <select name="form" onchange="location = this.value;" class="form-control form-control-lg">
            <option value="----" selected="selected"> ---- </option>
            <option value="http://localhost:8080/worker/addCustomerConsumer/${user.id}" > CONSUMER </option>
            <option value="http://localhost:8080/worker/addCustomerCompany/${user.id}" > COMPANY </option>
        </select>
    </label>

</div>
</div>
</body>
</html>