<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="workerHeader.jsp" %>
    <h2>SELECT OPTION: </h2>
    <label>
        <select name="form" onchange="location = this.value;" class="form-control form-control-lg">
            <option value="----" selected="selected"> ---- </option>
            <option value="http://localhost:8080/worker/byCash/${user.id}/${customer.id}" > CASH </option>
            <option value="http://localhost:8080/worker/customerOffer/${user.id}/${customer.id}" > FINANCING </option>
        </select>
    </label>

</div>
</div>
</body>
</html>