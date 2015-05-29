<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 <title>Customers</title>
</head>
<body>
<c:forEach items="${EmployeeName}" var="EmployeeName">
<div class="navbar navbar-default" align="left" id="degrace"> <h2><a href="<c:url value="employee"/>" class="btn btn-primary glyphicon glyphicon-off" id="addcustbut"></a><strong> Customers from ${EmployeeName}</strong></h2>
</div>
</c:forEach>
<div class="panel panel-default" id="wrapper">
  <!-- Default panel contents -->
  <div class="panel-heading"><a href="<c:url value="add-customer"/>" class="btn btn-default glyphicon glyphicon-plus" id="addcustbut"></a>
  <c:forEach items="${EmployeeName}" var="EmployeeName"><strong>${EmployeeName}</strong></c:forEach><c:forEach items="${EmployeeRole}" var="EmployeeRole"><strong>: </strong>${EmployeeRole}</c:forEach><c:forEach items="${EmployeeCorporation}" var="EmployeeCorporation"> from ${EmployeeCorporation}</c:forEach></div>
  
  <!-- Table -->
  <table class="table">
     <tr>
 <th>Name</th>
 <th>Email</th>
 <th>Age</th>
 <th>Gender</th>
 <th>Birthday</th>
 <th>Phone</th>
 </tr>
 <c:forEach items="${customers}" var="customer">
 <tr>
  <td>${customer.name}</td>
  <td>${customer.email}</td>
  <td>${customer.age}</td>
  <td>${customer.gender}</td>
  <td><fmt:formatDate value="${customer.birthday}" type="date" /></td>
  <td>${customer.phone}</td>
 </tr>
 </c:forEach>
  </table>
  <p></p>
   
 <p></p>
</div>
</body>
</html>