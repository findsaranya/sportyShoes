<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Purchase Confirmation</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/topbar.jsp" ></jsp:include>

<h1> ${pageTitle} </h1>
<br><br>
Your Order worth ${cartValue} has been completed successfully.<br><br>
Check your <A href="memberpurchases">Order History</A>
 
<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>