<%@ include file="workerHeader.jsp" %>
<h2><b>All cars:</b> </h2>
<hr>
<form method="get">
    <label>
        SEARCH BY:
        <select name="searchMode">
            <option value="name">NAME</option>
            <option value="engine">ENGINE</option>
            <option value="gearbox">GEARBOX</option>
            <option value="price">PRICE</option>
            <option value="version">VERSION</option>
            <option value="body">BODY</option>
            <option value="quantity">QUANTITY <i>(more than)</i></option>
        </select>
    </label>
    <br/>
    <input type="text" name="query"/>
    <br/>
    <input type="submit" value="SEARCH">
    <input type="submit" value="CLEAR">
</form>
<hr>
<table class="table table-striped">
    <tr>
        <th><b>NAME:</b></th>
        <th><b>BODY:</b></th>
        <th><b>ENGINE:</b></th>
        <th><b>GEARBOX:</b></th>
        <th><b>PRICE:</b></th>
        <th><b>VERSION:</b></th>
        <th><b>QUANTITY:</b></th>
    </tr>
    <c:forEach items="${car}" var="car">
        <tr>
            <td>${car.name}</td>
            <td>${car.body}</td>
            <td>${car.engine}</td>
            <td>${car.gearbox}</td>
            <td>${car.price}</td>
            <td>${car.version}</td>
            <td>${car.quantity}</td>
        </tr>
    </c:forEach>
</table>

</div>
</div>
</body>
</html>