<%@ include file="headerAdmin.jsp" %>
<a href="http://localhost:8080/admin/"><button type="button" class="btn btn-secondary btn-lg btn-block">GO BACK TO MAIN PAGE</button></a>
<a href="http://localhost:8080/user/add"><button type="button" class="btn btn-primary btn-lg btn-block">ADD NEW USER</button></a><br>

<form method="get">
    <label>
        SEARCH BY:
        <select name="searchMode">
            <option value="firstName">FIRSTNAME</option>
            <option value="lastName">LASTNAME</option>
            <option value="type">TYPE <i>("DORADCA", "STARSZY DORADCA", "EKSPERT")</i></option>
            <option value="pesel">PESEL</option>
            <option value="branch">BRANCH</option>
        </select>
    </label>
    <br/>
    <input type="text" name="query"/>
    <br/>
    <input type="submit" value="SEARCH">
    <input type="submit" value="CLEAR">
</form>

<table class="table table-hover">
    <h2>All users: </h2>
    <hr>
    <tr>
        <th><b>LOGIN:</b></th>
        <th><b>PASSWORD:</b></th>
        <th><b>FIRSTNAME:</b></th>
        <th><b>LASTNAME:</b></th>
        <th><b>EMAIL::</b></th>
        <th><b>PESEL:</b></th>
        <th><b>BRANCH:</b></th>
        <th><b>TYPE:</b></th>
        <th><b>ACTION:</b></th>
    </tr>
    <c:forEach items="${user}" var="user">
        <tr>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.PESEL}</td>
            <td>${user.branch}</td>
            <td>${user.type}</td>
            <td>
                <a href="http://localhost:8080/user/edit/${user.id}"><button type="button" class="btn btn-secondary">EDIT</button></a>
                <a href="http://localhost:8080/user/delete/${user.id}"><button type="button" class="btn btn-secondary"
                                                                               onclick="return confirm('Are you sure you want delete this user?')">DELETE</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</div>
</body>
</html>