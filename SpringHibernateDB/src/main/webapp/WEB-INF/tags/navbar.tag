<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%> 
<%@attribute name="EmployeeName" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <meta http-equiv="Content-Language" content="English"/>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>">
</head>
<body>
<nav class="navbar navbar-default" id="degrace">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand"><strong><font color="black">Corporative DB 1.0</font></strong></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav nav-pills nav-justified">
        <li class="${stateH}"><a href="<c:url value="personalpage"/>">Home <span class="sr-only">(current)</span></a></li>
        <li class="${stateAC}"><a href="<c:url value="add-customer"/>">Add Customer</a></li>
        <li class="${stateC}"><a href="<c:url value="customers?page=1"/>">My Customers</a></li>
        <li class="${stateS}"><a href="<c:url value="search"/>">Search</a></li>
        <li class="disabled">Add Employee</li>
        <li><a href="<c:url value="employees"/>">Shut Down</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<jsp:doBody/>
</body>
</html>