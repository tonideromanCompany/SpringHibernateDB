<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
 

<t:navbar>
<jsp:body>
<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h3 class="panel-tittle">
			<span class="glyphicon glyphicon-user"></span>
			<strong>Personal Info</strong>
			<a href="<c:url value="personalpage-editinfo"/>"  class="panel-right btn bnt-default glyphicon glyphicon-pencil"></a>
		</h3> 
	</div>
		<table class="table table-bordered">
			<tr>
				<th>Company</th>
				<td>${currentemployee.corporation}</td>
			</tr>
			<tr>
				<th>Name</th>
				<td>${currentemployee.name}</td>
			</tr>
			<tr>
				<th>Surname</th>
				<td>${currentemployee.surname}</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>${currentemployee.user}</td>
			</tr>
			<tr>
				<th>Role</th>
				<td>${currentemployee.role}</td>
			</tr>
			<tr>
				<th>Phone</th>
				<td>${currentemployee.phone}</td>
			</tr>
		</table>
</div>
</div>

<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h3 class="panel-tittle">
			<span class="glyphicon glyphicon-home"></span>
			<strong>Personal Adress</strong>
			<a href="<c:url value="personalpage-edit"/>"  class="panel-right btn bnt-default glyphicon glyphicon-pencil"></a>
		</h3> 
	</div>
		<table class="table table-bordered">
			<tr>
				<th>Address</th>
				<td>${employeeadress.adress}</td>
			</tr>
			<tr>
				<th>City</th>
				<td>${employeeadress.city}</td>
			</tr>
			<tr>
				<th>Postal Code</th>
				<td>${employeeadress.postalcode}</td>
			</tr>
			<tr>
				<th>Country</th>
				<td>${employeeadress.country}</td>
			</tr>
		</table>
</div>
</div>

<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
	<!-- Default panel contents -->
	<div class="panel-heading">
		<h3 class="panel-tittle">
			<span class="glyphicon glyphicon-briefcase"></span>
			<strong>Corporation Address</strong>
		</h3> 
	</div>
		<table class="table table-bordered">
			<tr>
				<th>Corporation</th>
				<td>${corporationadress.corporation}</td>
			</tr>
			<tr>
				<th>Office</th>
				<td>${corporationadress.office}</td>
			</tr>
			<tr>
				<th>Department</th>
				<td>${corporationadress.department}</td>
			</tr>
			<tr>
				<th>Address</th>
				<td>${corporationadress.adress}</td>
			</tr>
			<tr>
				<th>City</th>
				<td>${corporationadress.city}</td>
			</tr>
			<tr>
				<th>Postal Code</th>
				<td>${corporationadress.postalcode}</td>
			</tr>
			<tr>
				<th>Country</th>
				<td>${corporationadress.country}</td>
			</tr>
		</table>
</div>
</div>
</jsp:body>
</t:navbar>