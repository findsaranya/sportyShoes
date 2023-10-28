<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Purchases Report</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/admin-header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/admin-topbar.jsp" ></jsp:include>

<form action="searchPO">
<input name="poDate" type="date" placeholder="search by Date"/>
<select name="category">
 				<option value="0">Select category</option>
 				 <c:forEach items="${catList}" var="item">
 				<option value="${item.getCatId()}">${item.getName()} </option>
 				 </c:forEach>
 			</select>
<input type="submit" value="search"/>
</form>
<br><br>Total ${list.size()} Orders: ${totalAmount }<br>

<%-- <table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td><b>Order ID</b></td>
 		<td><b>User</b>
 		<td><b>Date</b></td>
 		<td><b>Total</b></td>
 		<td><b>Items</b></td>
 	</tr>
 	<c:forEach items="${list}" var="item">
 	  	<tr>
	 		<td>${item.ID }</td>
 			<td>
 				${mapUsers.get(item.ID)}
 			</td>
 			<td>${item.date }</td>
 			<td>${item.total}</td>
 			<td>
 				${mapItems.get(item.ID)}
 			</td>
 	  	</tr>
 	  </c:forEach>
</table> --%> 	

<table border=1 cellspacing=2 cellpadding=4>
		<thead>
			<tr>
				<th rowspan="2">Order ID</th>
				<th rowspan="2">User Name</th>
				<th rowspan="2">UserId</th>
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
	 		<td>${item.getUser().getFname()}&nbsp; ${ item.getUser().getLname()} </td>
	 		<td>${item.getUser().getUserId() }</td>
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
	

<jsp:include page="/WEB-INF/views/shared/admin-footer.jsp"></jsp:include>
</body>
</html>