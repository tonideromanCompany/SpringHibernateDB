<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form2" uri="http://www.springframework.org/tags/form" %>
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
 <title>Add Customer</title>
</head>
<body>
<div class="navbar navbar-default" align="left" id="degrace"> <h2><a href="<c:url value="customers"/>" class="btn btn-primary glyphicon glyphicon-arrow-left" id="addcustbut"></a><strong> Add New Customer</strong></h2>
</div>

<form:form modelAttribute="customer" class="form-horizontal" method="post">  
<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
 <div class="panel-body">  
<div class="col-xs-8 col-xs-offset-2">  
<div class="form-group">        
<label class="control-label">Customer Name:</label>                  
<form:input type="text" class="form-control" path="name" placeholder="Introduce your customer's name" /> 
<form:errors path="name" element="span" cssClass="error"/>     
</div>    
</div>
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Customer Email:</label>                  
<form:input type="email" class="form-control" path="email" placeholder="Introduce your customer's email" /> 
<form:errors path="email" element="span" cssClass="error"/>
</div>    
</div>
<div class="col-xs-8 col-xs-offset-2">   
<div class="form-group">        
<label class="control-label">Customer Age:</label>                 
<form:input class="form-control" path="age" placeholder="Introduce your customer's age" /> 
<form:errors path="age" element="span" cssClass="error"/>
</div>    
</div>  
<div class="col-xs-4 col-xs-offset-2">  
<div class="form-group">        
<label class="control-label">Customer Gender:</label>                    
<form:select class="form-control" path="gender">                
<form:option value="" label="Select Gender" />  
<form:option value="MALE" label="Male" />
<form:option value="FEMALE" label="Female" />                                       
</form:select> 
<form:errors path="gender" element="span" cssClass="error"/>       
</div>
</div> 

<div class="col-xs-8 col-xs-offset-2">   
<div class="form-group">        
<label class="control-label">Customer Birthday:</label>                 
<form:input class="form-control" path="birthday" placeholder="MM/dd/yyyy" /> 
<form:errors path="birthday" element="span" cssClass="error"/>
</div>    
</div> 
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Customer Phone:</label>                 
<form:input type="telephone" class="form-control" path="phone" placeholder="Introduce your customer's phone" /> 
<form:errors path="phone" element="span" cssClass="error"/>
</div>    
</div> 
<p></p>
<div class="col-md-6 col-md-offset-4"> 
<input type="submit" class="btn btn-primary" value="Create Customer" />
</div>
</div>
</div> 
</div> 
</form:form>
</body>
</html>