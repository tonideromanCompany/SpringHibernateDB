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
  <form:form modelAttribute="ArrayID" name="form" action="delete" method="post">
  <form:input type="hidden" path="customers" />
  </form:form>
  <table class="table table-bordered table-hover table-condensed">
     <tr>
     
 <th><input type="checkbox" id="SelectAll" aria-label="..." onclick="AllCheck()"></th>
 <th>Name</th>
 <th>Email</th>
 <th>Age</th>
 <th>Gender</th>
 <th>Birthday</th>
 <th>Phone</th>
 </tr>
 <c:forEach items="${listing}" var="customer">
 <tr>
  <td><input type="checkbox" value="${customer.id}" name="checkbox" onclick="showblocked()"></td>
  <td>${customer.name}</td>
  <td>${customer.email}</td>
  <td>${customer.age}</td>
  <td>${customer.gender}</td>
  <td><fmt:formatDate value="${customer.birthday}" type="date" /></td>
  <td>${customer.phone}</td>
 </tr>
  </c:forEach>
  </table>
  <div class="panel-footer">
  <div class="rows row">
  <div class="col-md-4">
  <button class="addbut btn btn-primary glyphicon glyphicon-plus" id="addCustomer"></button>
  <button class="trashbut btn btn-danger glyphicon glyphicon-trash" id="deleteCustomer" style="display:none"></button>
  <button class="modifybut btn btn-info glyphicon glyphicon-user" id="modifyCustomer" style="display:none"></button>
  </div>
  <div class="col-md-4"></div>
  <div class="col-md-4" align="right">
  <t:paginator></t:paginator>
  </div>
  </div>
  <h5 id="wrapp">Showing <strong>${fn:length(listing)}</strong> from ${ncustomers} registers</h5>
  </div>
  </div>
  <t:modal></t:modal>
</jsp:body>
</t:navbar>
<!-- JavaScript Functions -->
<script type="text/javascript">
$(document).ready(function(){
    $(".trashbut").click(function(){
    	   checkboxes = document.getElementsByName("checkbox");
    	   
    	   var cont = 0;
    	   for (var i = 0; i < checkboxes.length; i++) {
    	   	var checkbox = checkboxes[i];
    	   	if(checkbox.checked) {
    	   		cont++;
    	   	}
    	   }
    	   $("#modalCustomersNumber").html(cont);
        $("#myModal").modal('show');
    });
});

//Show the trash & modify buttons in each case
function showblocked() {
	checkboxes = document.getElementsByName("checkbox");
	trash = document.getElementById("deleteCustomer");
	modify = document.getElementById("modifyCustomer");
	var cont = 0;
	for (var i = 0; i < checkboxes.length; i++) {
		var checkbox = checkboxes[i];
		if(checkbox.checked) {
			cont++;
		}
	}
	if(cont != 0)
		trash.style.display='';
	if(cont == 1) 
		modify.style.display='';
	if(cont == 0) {
		modify.style.display='none';
		trash.style.display='none';
	}
	if(cont > 1)
		modify.style.display='none';
}

//Show how much selected checkboxes there is and keep the asociated customers for delete/modify them
function check() {
checkboxes = document.getElementsByName("checkbox");
var selected = new Array();
for (var i = 0; i < checkboxes.length; i++) {
	var checkbox = checkboxes[i];
	if(checkbox.checked) {
		selected.push(checkbox.value);
	}
}
//alert("delete: "+ selected +" customers?");
document.form.customers.value=selected;
document.form.submit();

}

//Select/Deselect all checkboxes
function AllCheck() {
	checkboxes = document.getElementsByName("checkbox");
	trash = document.getElementById("deleteCustomer");
	modify = document.getElementById("modifyCustomer");
	var checkbox = document.getElementById("SelectAll");
	if(checkbox.checked){
		for(var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = true;
			trash.style.display='';
			modify.style.display='none';
		}
	}
	else {
		for(var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = false;
			modify.style.display='none';
			trash.style.display='none';
		}	
	}
	}
</script>