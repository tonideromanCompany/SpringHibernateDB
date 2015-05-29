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
	<div class="panel-heading"><strong>Personal Page</strong></div>
		<table class="table">
			<tr>
				<th>Company</th>
				<td><c:forEach items="${currentemployeeC}" var="currentemployeeC">${currentemployeeC}</c:forEach></td>
			</tr>
			<tr>
				<th>Name</th>
				<td><c:forEach items="${currentemployeeN}" var="currentemployeeN">${currentemployeeN}</c:forEach></td>
			</tr>
			<tr>
				<th>Surname</th>
				<td><c:forEach items="${currentemployeeS}" var="currentemployeeS">${currentemployeeS}</c:forEach></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><c:forEach items="${currentemployeeE}" var="currentemployeeE">${currentemployeeE}</c:forEach></td>
			</tr>
			<tr>
				<th>Role</th>
				<td><c:forEach items="${currentemployeeR}" var="currentemployeeR">${currentemployeeR}</c:forEach></td>
			</tr>
			<tr>
				<th>Phone</th>
				<td><c:forEach items="${currentemployeeP}" var="currentemployeeP">${currentemployeeP}</c:forEach></td>
			</tr>
		</table>
</div>
</div>
</jsp:body>
</t:navbar>