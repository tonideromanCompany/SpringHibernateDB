<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <meta http-equiv="Content-Language" content="English"/>
 <link rel="stylesheet" media="all" href="<c:url value="/resources/site.css"/>">
 <title>Customers</title>
</head>
<body>
<h2>Customers</h2>
<table>
 <tr>
 <th>Title</th>
 <th>Author</th>
 </tr>
 <c:forEach items="${customers}" var="customer">
 <tr>
  <td>${customer.title}</td>
  <td>${customer.author}</td>
 </tr>
 </c:forEach>
</table>
<br/>
<a href="<c:url value="/add-customer"/>">Add Customer</a>
</body>
</html>