<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Your Cart</title>
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/shared/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/shared/topbar.jsp"></jsp:include>

	${error}

	<c:if test="${cartItems.size() > 0}">
		<br>
		<br>Total Cart Value: ${cartValue }<br>

		<table border=1 cellspacing=2 cellpadding=4>
			<thead>
				<tr>
					<th>Product</th>
					<th>Qty</th>
					<th>Rate</th>
					<th>GrossTotal</th>
					<th>Add qty</th>
					<th>Dec qty</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cartItems}" var="item">
					<tr>
						<td>${item.getProduct().getName() }</td>
						<td>${item.getQty()}</td>
						<td>${item.getProduct().getPrice() }</td>
						<td>${item.getGrossTotal()}</td>
						<td><a href="addQty?id=${item.getProduct().getPoductId()}">+</a>
						</td>
						<td><a href="deductQty?id=${item.getProduct().getPoductId()}">-</a>
						</td>
						<td><a
							href="cartdeleteitem?id=${item.getProduct().getPoductId()}">X</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br>
		<c:if test="${cartItems.size() > 0}">
			<a href="checkout">Checkout Now</a>
		</c:if>
	</c:if>
	<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>