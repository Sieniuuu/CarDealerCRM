<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="workerHeader.jsp" %>
<a href="http://localhost:8080/worker/addCustomer/${user.id}">
    <button type="button" class="btn btn-secondary btn-lg btn-block">Add new Customer</button>
</a>
<hr>
<form method="get">
    <label>
        SEARCH BY:
        <select name="searchMode">
            <option value="firstName">FIRSTNAME</option>
            <option value="lastName">LASTNAME</option>
            <option value="type">TYPE</option>
            <option value="nip">NIP</option>
            <option value="pesel">PESEL</option>
            <option value="status">STATUS</option>
        </select>
    </label>
    <br/>
    <input type="text"  name="query"/>
    <br/>
    <input type="submit" value="SEARCH">
    <input type="submit" value="CLEAR">
</form>
<hr>
<table class="table table-striped">
    <h2>All Clients: </h2>
    <hr>
    <tr>
        <th><b>Fullname:</b></th>
        <th><b>Email:</b></th>
        <th><b>Type:</b></th>
        <th><b>Created:</b></th>
        <th><b>Action:</b></th>
    </tr>
    <c:forEach items="${customer}" var="customer">
        <tr>
            <td>${customer.fullName}</td>
            <td>${customer.email}</td>
            <td>${customer.type}</td>
            <td>${customer.createdOn}</td>
            <td>
                <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                    <a href="http://localhost:8080/worker/customerDetails/${user.id}/${customer.id}">
                        <button type="button" class="btn btn-info">DETAILS</button>
                    </a>
                    <div class="btn-group" role="group">
                        <button id="btnGroupDrop1" type="button" class="btn btn-warning dropdown-toggle"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            EDIT
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item"
                               href="http://localhost:8080/worker/editCustomer/${user.id}/${customer.id}">EDIT
                                CUSTOMER DETAILS</a>
                            <a class="dropdown-item"
                               href="http://localhost:8080/worker/editCustomerEmail/${user.id}/${customer.id}">EDIT
                                CUSTOMER EMAIL</a>
                            <a class="dropdown-item"
                               href="http://localhost:8080/accessories/add/${user.id}/${customer.id}">ADD
                                ACCESSORIES</a>
                            <a class="dropdown-item"
                               href="http://localhost:8080/car/setCar/${user.id}/${customer.id}">SET
                                CAR</a>
                        </div>
                    </div>
                    <div class="btn-group" role="group">
                        <button id="btnGroupDrop2" type="button" class="btn btn-primary dropdown-toggle"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            OFFER
                        </button>
                        <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                            <a class="dropdown-item"
                               href="http://localhost:8080/worker/chooseOfferType/${user.id}/${customer.id}">CALCULATTE
                                OFFER</a>
                            <a class="dropdown-item"
                               href="http://localhost:8080/email/sendOfferDetail/${user.id}/${customer.id}">SEND
                                OFFER</a>
                        </div>
                    </div>

                    <a href="http://localhost:8080/worker/sell/${user.id}/${customer.id}">
                        <button type="button" class="btn btn-success">SELL</button>
                    </a>
                    <a href="http://localhost:8080/worker/out/${user.id}/${customer.id}">
                        <button type="button" class="btn btn-danger"
                                onclick="return confirm('Are you sure you want out this customer')">OUT</button>
                    </a>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

</div>
</div>
</body>
</html>