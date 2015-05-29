<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
 
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
 <title>Employees</title>
</head>
<body>
<form:form class="form-horizontal" modelAttribute="employeelog" method="post" action="employees">
<p></p>
<div class="container" align="center">
<p></p>
<div class="row">
<p></p>
<div class="col-xs-4 col-xs-offset-4">
<p></p>
<div id="wrapper" class="panel panel-primary">
<!-- Default panel contents -->
<div class="panel-heading" align="center"><h4><strong>Corporative Access</strong></h4></div>
<p></p>
<div class="row">
<div class="col-xs-8 col-xs-offset-2">
<p></p>
<div class="form-group-center">
    <div class="input-group">
  	<span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>
  	<form:input type="text" class="form-control" path="corporation" />
	</div>
	</div>
	</div>
	</div>
	<p></p>
	<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
<div class="form-group-center">
	<div class="input-group">
  	<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
  	<form:input type="text" class="form-control" path="user" />
	</div>
	</div>
	</div>
	</div>
	<p></p>
	<div class="row">
	<div class="col-xs-8 col-xs-offset-2">
<div class="form-group-center">
	<div class="input-group">
  	<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
  	<form:input type="password" class="form-control" path="password" />
    </div>
    </div>
    </div>
    </div>
    <p></p>
    <div class="row">
	<div class="col-xs-8 col-xs-offset-2">
<div class="btn-group-sm">
	<input type="submit" class="btn btn-primary" value="Login" id="addcustbut"/>
	<a href="<c:url value="add-employee"/>" class="btn btn-default" id="addcustbut">Add Employee</a>
	<p></p>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</form:form>
</body>
</html>