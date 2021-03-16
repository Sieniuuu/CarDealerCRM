<%@ include file="headerAdmin.jsp" %>
<a href="http://localhost:8080/admin/"><button type="button" class="btn btn-secondary btn-lg btn-block">GO BACK TO MAIN PAGE</button></a>
<a href="http://localhost:8080/customer/add"><button type="button" class="btn btn-primary btn-lg btn-block">ADD NEW CUSTOMER</button></a><br>
<h2>All customers: </h2>
<hr>
<form method="get">
    <label>
        SEARCH BY:
        <select name="searchMode">
            <option value="firstName">FIRSTANEM</option>
            <option value="lastName">LASTNAME</option>
            <option value="type">TYPE</option>
            <option value="pesel">PESEL</option>
            <option value="nip">NIP</option>
            <option value="status">STATUS</option>
        </select>
    </label>
    <br/>
    <input type="text" name="query"/>
    <br/>
    <input type="submit" value="SEARCH">
    <input type="submit" value="CLEAR">
</form>
<hr>
<table class="table table-hover">
    <tr>
        <th><b>FIRSTNAME:</b></th>
        <th><b>LASTNAME:</b></th>
        <th><b>EMAIL:</b></th>
        <th><b>TYPE:</b></th>
        <th><b>NIP:</b></th>
        <th><b>PESEL:</b></th>
        <th><b>ADDRESS:</b></th>
        <th><b>STATUS:</b></th>
        <th><b>CREATED:</b></th>
        <th><b>UPDATE:</b></th>
        <th><b>CAR:</b></th>
        <th><b>OFFER:</b></th>
        <th><b>DEALER:</b></th>
        <th><b>ACTION:</b></th>
    </tr>
    <c:forEach items="${customer}" var="customer">
        <tr>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>${customer.type}</td>
            <td>${customer.NIP}</td>
            <td>${customer.PESEL}</td>
            <td>${customer.address}</td>
            <td>${customer.status}</td>
            <td>${customer.createdOn}</td>
            <td>${customer.updateOn}</td>
            <td>${empty customer.car.model ? "Undefined" : customer.car.model}</td>
            <td>${empty customer.offer.proposition ? "Undefined" : customer.offer.proposition}</td>
            <td>${empty customer.user.fullDescription ? "Undefined" : customer.user.fullDescription}</td>
            <td>
                <a href="http://localhost:8080/customer/edit/${customer.id}"><button type="button" class="btn btn-secondary">EDIT</button></a>
                <a href="http://localhost:8080/customer/delete/${customer.id}"><button type="button" class="btn btn-secondary"
                                                                                       onclick="return confirm('Are you sure you want delete this customer?')">DELETE</button></a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>