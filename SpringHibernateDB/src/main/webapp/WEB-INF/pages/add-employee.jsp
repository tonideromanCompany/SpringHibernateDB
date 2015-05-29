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
 <title>Add Employee</title>
</head>
<body>

<form:form modelAttribute="employee" class="form-horizontal" method="post">  
<div class="col-md-6 col-md-offset-3">
<div class="panel panel-default" id="wrapper">
 <div class="panel-body">  
<div class="col-xs-8 col-xs-offset-2">  
<div class="form-group">        
<label class="control-label">Corporation Name:</label>                  
<form:input type="text" class="form-control" path="corporation" placeholder="Introduce the name of your company" /> 
<form:errors path="corporation" element="span" cssClass="error"/>     
</div>    
</div>
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Corporative Email:</label>                  
<form:input type="email" class="form-control" path="user" placeholder="Introduce your corporative email" /> 
<form:errors path="user" element="span" cssClass="error"/>
</div>    
</div>
<div class="col-xs-8 col-xs-offset-2">   
<div class="form-group">        
<label class="control-label">First Name:</label>                 
<form:input type="text" class="form-control" path="name" placeholder="Introduce your name" /> 
<form:errors path="name" element="span" cssClass="error"/>
</div>    
</div>  
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Second Name:</label>                   
<form:input type="text" class="form-control" path="surname" placeholder="Introduce your surname" /> 
<form:errors path="surname" element="span" cssClass="error"/>
</div>    
</div>
<div class="col-xs-4 col-xs-offset-2">  
<div class="form-group">        
<label class="control-label">Role:</label>                    
<form:select class="form-control" path="role">                
<form:option value="" label="Select Role" />
<form:option value="Secretary" label="Secretary" />  
<form:option value="Developer" label="Developer" />
<form:option value="Developer Pro" label="Developer Pro" />
<form:option value="Manager" label="Manager" /> 
<form:option value="Executive" label="Executive" />                           
<form:option value="CEO" label="CEO" />             
</form:select> 
<form:errors path="role" element="span" cssClass="error"/>       
</div>
</div> 
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Corporative Phone:</label>                 
<form:input type="telephone" class="form-control" path="phone" placeholder="Introduce your corporative phone" /> 
<form:errors path="phone" element="span" cssClass="error"/>
</div>    
</div> 
<div class="col-xs-8 col-xs-offset-2"> 
<div class="form-group">        
<label class="control-label">Password:</label>                  
<form:input type="password" class="form-control" path="password" placeholder="Introduce your password" />
<form:errors path="password" element="span" cssClass="error"/> 
<c:forEach items="${fail}" var="fail">${fail}</c:forEach>
</div>    
</div> 
<div class="col-xs-8 col-xs-offset-2">  
<div class="form-group">        
<label class="control-label">Password:</label>                
<form:input type="password" class="form-control" path="password2" placeholder="Repeat your password" /> 
<form:errors path="password2" element="span" cssClass="error"/>
<c:forEach items="${fail}" var="fail">${fail}</c:forEach>
</div>    
</div>
<p></p>
<div class="col-md-6 col-md-offset-4"> 
<input type="submit" class="btn btn-primary" value="Create Employee" />
</div>
</div>
</div> 
</div> 
</form:form>
</body>
</html>