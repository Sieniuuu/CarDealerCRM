<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CrmDealer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-3">
    <div class="container">
        <a href="#" class="navbar-brand mr-3"><h3>Welcome at CRM-Dealer</h3>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button></a></div>
</nav>

<div class="container" style="text-align: center">
    <div class="jumbotron">
        <h1><b>Please login: </b></h1>
        <hr>
        <form method="post">
            <input type="text" name="userName" placeholder="LOGIN" class="form-control mx-sm-3">
            <input type="password" name="password" placeholder="PASSWORD" class="form-control mx-sm-3"><br>
            <span style="color:Red; font-size:1em"><b>${empty errorLogin ? " " : errorLogin }</b></span>
            <hr>
            <button type="submit" value="SUBMIT" class="btn btn-primary">LOGIN</button>
        </form>
    </div>
</div>

</body>
</html>
