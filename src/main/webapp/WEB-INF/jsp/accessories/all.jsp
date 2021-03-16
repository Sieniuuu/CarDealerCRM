<%@ include file="headerAdmin.jsp" %>

<a href="http://localhost:8080/admin/"><button type="button" class="btn btn-secondary btn-lg btn-block">GO BACK TO MAIN PAGE</button></a>
<a href="http://localhost:8080/accessories/add"><button type="button" class="btn btn-primary btn-lg btn-block">ADD NEW ACCESSORY</button></a><br>


<table class="table table-striped">
    <h2>All accessories: </h2>
    <hr>
    <tr>
        <th><b>NAME:</b></th>
        <th><b>PRICE:</b></th>
        <th><b>ACTION:</b></th>
    </tr>
    <c:forEach items="${accessories}" var="accessories">
        <tr>
            <td>${accessories.name}</td>
            <td>${accessories.price}</td>
            <td>
                <a href="http://localhost:8080/accessories/edit/${accessories.id}"><button type="button" class="btn btn-primary">EDIT</button></a>
                <a href="http://localhost:8080/accessories/delete/${accessories.id}">
                    <button type="button" class="btn btn-secondary" onclick="return confirm('Are you sure you want delete this accessory?')">DELETE</button></a>
            </td>
        </tr>
    </c:forEach>
</table>

</div>
</div>
</body>
</html>