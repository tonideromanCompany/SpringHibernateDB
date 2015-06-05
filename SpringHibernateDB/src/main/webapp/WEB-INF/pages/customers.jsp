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
<div class="panel panel-default" id="custwrapp">
  <!-- Default panel contents -->
  <div class="panel-heading">
  <h5 class="page-right">Page <strong id="stronger">${firstpage}</strong> from ${npages} pages</h5>
  <strong>${currentemployee.name}</strong> <strong>${currentemployee.surname}</strong><strong>: </strong>${currentemployee.role} from ${currentemployee.corporation}</div>
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
 <c:forEach items="${listing}" var="customer">
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
  <t:paginator>
  </t:paginator>
<h5 id="wrapp">Showing <strong>${fn:length(listing)}</strong> from ${ncustomers} registers</h5>
</div>
</jsp:body>
</t:navbar>