<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Your Purchases</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/shared/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/shared/topbar.jsp"></jsp:include>

	<h2>${pageTitle}</h2>
	<br>
	<br>Total ${list.size()} Orders: ${totalAmount }
	<br>

	<table border=1 cellspacing=2 cellpadding=4>
		<thead>
			<tr>
				<th rowspan="2">Order ID</th>
				<th rowspan="2">Date</th>
				<th rowspan="2">Total</th>
				<th colspan="4">Items</th>
			</tr>
			<tr>
				<th>Product Name</th>
				<th>Qty</th>
				<th>Rate</th>
				<th>Gross Total</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.getPoId() }</td>
 			<td>${item.getPurchaseDate() }</td>
 			<td>${item.getGrossTotal()}</td>
 			<td colspan="4">
 			
 			<table width="100%" border="1">
 			<c:forEach items="${item.getItems()}" var="prod">
 			<tr>
 			 <td>${prod.getProduct().getName() }</td>
 			 <td>${prod.getQty() }</td>
 			 <td>${prod.getProduct().getPrice() }</td>
 			 <td>${prod.getTotalPrice()}</td>
 			 </tr>
 			</c:forEach>
 			</table>
 			</td>
 	  	</tr>
 	  </c:forEach>
		</tbody>

	</table>

	<br>
	<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>