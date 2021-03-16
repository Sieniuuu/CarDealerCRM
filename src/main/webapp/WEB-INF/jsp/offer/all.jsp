<%@ include file="headerAdmin.jsp" %>
<a href="http://localhost:8080/admin/"><button type="button" class="btn btn-secondary btn-lg btn-block">GO BACK TO MAIN PAGE</button></a>
<a href="http://localhost:8080/offer/add"><button type="button" class="btn btn-primary btn-lg btn-block">ADD NEW OFFER</button></a><br>

<table class="table table-hover">
    <h2>All offers: </h2>
    <hr>
    <tr>
        <th><b>TYPE:</b></th>
        <th><b>AMOUNT:</b></th>
        <th><b>CONTRIBUTION:</b></th>
        <th><b>REPURCHASE:</b></th>
        <th><b>PEROID:</b></th>
        <th><b>INSTALMENT:</b></th>
        <th><b>ACTION:</b></th>
    </tr>
    <c:forEach items="${offer}" var="offer">
        <tr>
            <td>${offer.type}</td>
            <td>${offer.amount}</td>
            <td>${offer.contribution}</td>
            <td>${offer.repurchase}</td>
            <td>${offer.period}</td>
            <td>${offer.instalment}</td>
            <td>
                <a href="http://localhost:8080/offer/edit/${offer.id}"><button type="button" class="btn btn-secondary">EDIT</button></a>
                <a href="http://localhost:8080/offer/delete/${offer.id}"><button type="button" class="btn btn-secondary"
                                                                                 onclick="return confirm('Are you sure you want delete this offer?')">DELETE</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
</body>
</html>