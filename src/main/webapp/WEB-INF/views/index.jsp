<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/topbar.jsp" ></jsp:include>
<table border=1 cellspacing=2 cellpadding=4>
<thead>
 	<tr>
 		<th>Product</th>
 		<th>Price</th>
 		<th>Category</th> 
 		<th>Action</th>
 	</tr>
 	</thead>
 	<tbody>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.getName() }</td>
 			<td>${item.getPrice() }</td>
 			<td>${item.getCategory().getName()}</td>
 	  		<td>
 	  			<a href="addTocart?id=${item.getPoductId()}">Add To Cart</a>
 	  		</td>
 	  	</tr>
 	  </c:forEach>
 	  </tbody>
</table>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>