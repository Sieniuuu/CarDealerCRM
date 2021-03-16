<%@ include file="workerHeader.jsp" %>
<hr>
<h2>Your calculation: </h2>
<hr>
<b>TYPE: </b> ${calculatedOffer.type} <br>
<b>AMOUNT:</b> ${calculatedOffer.amount}<br>
<b>CONTRIBUTION:</b> ${calculatedOffer.contribution}<br>
<b>REPURCHASE:</b> ${calculatedOffer.repurchase}<br>
<b>PEROID:</b> ${calculatedOffer.period}<br>
<b><u>INSTALMENT: ${calculatedOffer.instalment}</u></b><br>
<hr>
<h2>Current customer offer: </h2>
<hr>
<b>TYPE: </b> ${offer.type} <br>
<b>AMOUNT:</b> ${offer.amount}<br>
<b>CONTRIBUTION:</b> ${offer.contribution}<br>
<b>REPURCHASE:</b> ${offer.repurchase}<br>
<b>PEROID:</b> ${offer.period}<br>
<b>INSTALMENT:</b> ${offer.instalment}<br>
<hr>
<h2> Do you want to change offer? </h2>
<form method="post">
    <button type="submit" value="yes" name="confirmed" class="btn btn-success">YES</button>
    <button type="submit" value="no" name="confirmed" class="btn btn-warning">NO</button>
</form>

</div>
</div>
</html>