<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
 

<t:navbar>
<jsp:body>
<div class="panel panel-default" id="custwrapp">
  <!-- Default panel contents -->
  <div class="panel-heading">
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
</jsp:body>
</t:navbar>