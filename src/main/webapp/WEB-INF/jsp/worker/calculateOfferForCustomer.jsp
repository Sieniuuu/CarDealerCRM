<%@ include file="workerHeader.jsp" %>

<form:form method="post"
           modelAttribute="offer">


    <hr>
    <h2><b>FILL IN THE FORM FIELD:</b></h2>
    <hr>
    <form:hidden path="id"/>
    <b>TYPE:</b> <form:select path="type" items="${allOfferTypes}" class="form-control"/>
    <form:errors path="type" /><br>
    <h4><b>PRICE FOR CUSTOMER CAR: </b><i>(${car.model}) </i> ${car.price}<br></h4>
    <h4><b>PRICE FOR ALL ACCESORIES: </b> ${accesoriesPriceSum}<br></h4>
    <h4><b>TOTAL PRICE: </b> ${car.price + accesoriesPriceSum}<br></h4>
    <form:hidden path="amount" value="${car.price + accesoriesPriceSum}"/><br>
    <b>CONTRIBUTION:</b>
    <input type="number" id="contributionInput" name="contributionInput" class="form-control" min="0" max="${car.price * 0.5}" required/>
    <form:hidden path="contribution" value="0"/>
    <b>REPURCHASE:</b>
    <input type="number" id="repurchaseInput" name="repurchaseInput" class="form-control" min="1000" max="${car.price * 0.2}" required />
    <form:hidden path="repurchase" value="1000"/>
    <b>PEROID:</b>
    <input type="number" id="periodInput" name="periodInput" class="form-control" min="12" max="120" required />
    <form:hidden path="period" value="12"/>
    <form:hidden path="instalment"/>
    <br />
    <input type="submit" value="CALCULATE INSTALMENT" class="btn btn-primary btn-lg btn-block" />
</form:form>

</div>
</div>
</body>
</html>