<%@ include file="workerHeader.jsp" %>

<table class="table table-striped">
    <h2><b>All sold customers: </b></h2>
    <hr>
    <tr>
        <th><b>FIRSTNAME:</b></th>
        <th><b>LASTNAME:</b></th>
        <th><b>TYPE:</b></th>
        <th><b>CREATED:</b></th>
        <th><b>UPDATE:</b></th>
        <th><b>CAR:</b></th>
        <th><b>CAR PRICE:</b></th>
        <th><b>ACCESSORIES PRICE:</b></th>
    </tr>
    <c:forEach items="${customer}" var="customer">
        <tr>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.type}</td>
            <td>${customer.createdOn}</td>
            <td>${customer.updateOn}</td>
            <td>${customer.car.model}</td>
            <td>${customer.car.price}</td>
            <td>${empty customer.accessoriesPrice ? "0" : customer.accessoriesPrice}</td>
            <td>
        </tr>
    </c:forEach>
</table>
<hr>
<h2><b>Calculated bonus: ${bonus}</b></h2>

</div>
</div>
</body>
</html>