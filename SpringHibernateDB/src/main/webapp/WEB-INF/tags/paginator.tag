<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%> 
<%@attribute name="EmployeeName" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav>
  <ul class="pagination pagination-default">
    <li>
      <a href="?page=1" aria-label="Fisrt">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <c:choose>
    	<c:when test="${npages==1}">
    		<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
    	</c:when>
    	<c:when test="${npages==2}">
    		<c:choose>
    			<c:when test="${firstpage == 1}">
    				<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
    				<li class="inactive"><a href="?page=${firstpage+1}">${firstpage+1}</a></li>
    			</c:when>
    			<c:otherwise>
    				<li class="inactive"><a href="?page=${firstpage-1}">${firstpage-1}</a></li>
    				<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:when>
    	<c:when test="${npages>=3}">
    		<c:choose>
    			<c:when test="${firstpage == 1}">
    				<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
   					<li class="inactive"><a href="?page=${firstpage+1}">${firstpage+1}</a></li>
    				<li class="inactive"><a href="?page=${firstpage+2}">${firstpage+2}</a></li>
    			</c:when>
    			<c:when test="${firstpage == npages}">
    				<li class="inactive"><a href="?page=${firstpage-2}">${firstpage-2}</a></li>
    				<li class="inactive"><a href="?page=${firstpage-1}">${firstpage-1}</a></li>
    				<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
    			</c:when>
    			<c:otherwise>
    				<li class="inactive"><a href="?page=${firstpage-1}">${firstpage-1}</a></li>
   					<li class="active"><a href="?page=${firstpage}">${firstpage}</a></li>
    				<li class="inactive"><a href="?page=${firstpage+1}">${firstpage+1}</a></li>
    			</c:otherwise>
    		</c:choose>
    	</c:when>
    </c:choose>
    <li>
      <a href="?page=${npages}" aria-label="Last">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>