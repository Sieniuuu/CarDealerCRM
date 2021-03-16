<%@ include file="headerAdmin.jsp" %>

<h2>YOUR CALCULATION: </h2>

<b>TYPE: </b> ${offer.type} <br>
<b>AMOUNT:</b> ${offer.amount}<br>
<b>CONTRIBUTION:</b> ${offer.contribution}<br>
<b>REPURCHASE:</b> ${offer.repurchase}<br>
<b>PEROID:</b> ${offer.period}<br>
<h4><u>YOUR INSTALMENT: ${calculatedOffer.instalment}</u></h4>
<br>

<b>ADD OFFER TO CUSTOMER:</b><br>
<form method="post">
    <select name="id">
        <option value="0"> -- select customer or leave this field</option>
        <c:forEach items="${customer}" var="customer">
            <option value="${customer.id}"> ${customer.fullNameWithPesel} </option>
        </c:forEach>
    </select>
    <button type="submit" value="yes" name="confirmed" class="btn btn-success">SUBMIT</button>
</form>
</div>
</html>