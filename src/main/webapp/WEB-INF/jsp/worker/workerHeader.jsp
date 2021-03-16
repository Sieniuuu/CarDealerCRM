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
        <a href="http://localhost:8080/worker/${user.id}" class="navbar-brand mr-3"><b>Welcome - ${user.fullName}</b></a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav">
                <a href="http://localhost:8080/user/bonusCalculator/${user.id}" class="nav-item nav-link">CALCULATOR</a>
                <a href="http://localhost:8080/car/carsStock/${user.id}" class="nav-item nav-link">STOCK</a>
            </div>
            <div class="navbar-nav ml-auto">
                <a href="http://localhost:8080/user/editProfil/${user.id}" class="nav-item nav-link">CHANGE PASSWORD</a>
                <a href="http://localhost:8080/" class="nav-item nav-link">LOGOUT</a>
            </div>
        </div>
    </div>
</nav>
<div class="container" style="text-align: center">
    <div class="jumbotron">
