<%@ include file="headerAdmin.jsp" %>
<a href="http://localhost:8080/admin/"><button type="button" class="btn btn-secondary btn-lg btn-block">GO BACK TO MAIN PAGE</button></a>
<a href="http://localhost:8080/car/add"><button type="button" class="btn btn-primary btn-lg btn-block">ADD NEW CAR</button></a><br>

<form method="get">
    <label>
        SEARCH BY:
        <select name="searchMode">
            <option value="body">BODY</option>
            <option value="engine">ENGINE</option>
            <option value="gearbox">GEARBOX</option>
            <option value="price">PRICE</option>
            <option value="version">VERSION</option>
            <option value="name">NAME</option>
        </select>
    </label>
    <br/>
    <input type="text" name="query"/>
    <br/>
    <input type="submit" value="SEARCH">
    <input type="submit" value="CLEAR">
</form>



<table class="table table-hover">
    <h2>All cars: </h2>
    <tr>
        <th><b>NAME:</b></th>
        <th><b>BODY:</b></th>
        <th><b>ENGINE:</b></th>
        <th><b>GEARBOX:</b></th>
        <th><b>PRICE:</b></th>
        <th><b>VERSION:</b></th>
        <th><b>QUANTITY:</b></th>
        <th><b>ACTION:</b></th>
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
            <td>
                <a href="http://localhost:8080/car/edit/${car.id}"><button type="button" class="btn btn-secondary">EDIT</button></a>
                <a href="http://localhost:8080/car/delete/${car.id}"><button type="button" class="btn btn-secondary"
                                                                             onclick="return confirm('Are you sure you want delete this car?')">DELETE</button></a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>