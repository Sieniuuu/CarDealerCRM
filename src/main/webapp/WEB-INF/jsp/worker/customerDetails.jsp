<%@ include file="workerHeader.jsp" %>
<h2>Details about: ${customer.fullName} </h2>

<hr>
<b>FIRSTNAME: </b><i>${customer.firstName}</i><br>
<b>LASTNAME: </b><i>${customer.lastName}</i><br>
<b>TYPE: </b><i>${customer.email}</i><br>
<b>TYPE: </b><i>${customer.type}</i><br>
<b>NIP: </b><i>${empty customer.NIP ? "----------" : customer.NIP}</i><br>
<b>PESEL: </b><i>${customer.PESEL}</i><br>
<b>ADDRESS: </b><i>${customer.address}</i><br>
<b>STATUS: </b><i>${customer.status}</i><br>
<b>CREATED: </b><i>${customer.createdOn}</i><br>
<b>UPDATE: </b><i>${customer.updateOn}</i><br>
<b>CAR: </b><b>${empty customer.car.model ? "Undefined!" : " "}</b><br>
name: <i>${customer.car.name}</i><br>
body: <i>${customer.car.body}</i><br>
engine: <i>${customer.car.engine}</i><br>
gearbox: <i>${customer.car.gearbox}</i><br>
version: <i>${customer.car.version}</i><br>
price: <i>${customer.car.price}</i><br>
<b>ACCESSORIES: </b><b>${empty customer.accessories ? "Undefined!" : " "}<br></b>
<c:forEach items="${customer.accessories}" var="accesories">
    <li><i>${accesories.fullName}</i><br></li>
</c:forEach>
<b>OFFER: </b><b>${empty customer.offer.proposition ? "Uncalculated!" : " "}<br></b>
type: <i>${customer.offer.type}</i><br>
amount: <i>${customer.offer.amount}</i><br>
contribution: <i>${customer.offer.contribution}</i><br>
repurchase: <i>${customer.offer.repurchase}</i><br>
period: <i>${customer.offer.period}</i><br>
instalment: <i>${customer.offer.instalment}</i><br>
<b>NOTES:</b><br>
<i>${customer.notes}</i><br>

</div>
</div>
</body>
</html>