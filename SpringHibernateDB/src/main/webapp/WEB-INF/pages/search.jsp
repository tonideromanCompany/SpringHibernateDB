<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
 

<t:navbar>
<jsp:body>
<form:form modelAttribute="Searchfields" class="form-horizontal" method="post"> 
<div class="panel panel-default" id="custwrapp">
	<div class="panel heading" id="wrapp">
		<h5>Hi <strong>${currentemployee.name}</strong> <s></s> <strong>${currentemployee.surname}</strong> set your filters</h5>
	</div>
	<div class="form-group" id="wrapp">
		<span class="form-group-addon">
    		<input type="checkbox" name="checkname" aria-label="..." onclick="byname.disabled = !this.checked">
    	</span>
    	<label class="control-label">Search by name:</label>                 
		<form:input name="byname" type="text" class="formlarge form-control" path="byname" placeholder="Introduce your customer's name"  disabled="true"/> 
    	<span class="form-group-addon">
    		<input type="checkbox" name="checkage" aria-label="..." onclick="byage.disabled = !this.checked">
    	</span>
    	<label class="control-label">Search by age:</label>                 
		<form:input name="byage" type="integer" class="formlarge form-control" path="byage" placeholder="Introduce your customer's age"  disabled="true"/>
    </div>
    <input type="submit" class="btn btn-primary" value="Search" id="searchbut"/>
</div>
</form:form>
<div class="panel panel-default" id="custwrapp">
  <!-- Default panel contents -->
  <div class="panel-heading">
  <h5 class="page-right">Page <strong id="stronger">${firstpage}</strong> from ${npages} pages</h5>
  <a href="search" class="btn btn-primary glyphicon glyphicon-refresh" id="refreshbut"></a> ${currentemployee.role} from ${currentemployee.corporation}</div>
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
 <c:forEach items="${searching}" var="customer">
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
<h5 id="wrapp">Showing <strong>${fn:length(searching)}</strong> from X registers</h5>
</div>
</jsp:body>
</t:navbar>