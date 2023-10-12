<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Setup Products</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/admin-header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/admin-topbar.jsp" ></jsp:include>

Total Products: ${list.size()}  &nbsp;&nbsp; <a href="admineditproduct?id=0">Add Product</a><br>
<table border=1 cellspacing=2 cellpadding=4>
 	<thead>
 	<tr>
 		<th>Product</th>
 		<th>Price</th>
 		<th>Added On</th>
 		<th>Category</th>
 		<th>Action</th>
 	</tr>
 	</thead>
 	<tbody>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.getName() }</td>
 			<td>${item.getPrice() }</td>
 			<td>${item.getCreatedAt()}</td>
 			<td>${item.getCategory().getName()}</td>
 	  		<td>
 	  			<a href="admineditproduct?id=${item.getPoductId()}">Edit</a> | <a href="admindeleteproduct?id=${item.getPoductId()}">Delete</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
 	  </tbody>
</table> 	
<jsp:include page="/WEB-INF/views/shared/admin-footer.jsp"></jsp:include>
</body>
</html>