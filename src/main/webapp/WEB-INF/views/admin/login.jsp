<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  --%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/admin-header.jsp" ></jsp:include>

${error}<br>
<h1>${pageTitle }</h1>

<form  name="frmLogin" action="loginAction" method="post">
 <table border=1 cellspacing=2 cellpadding=4>
 	<tr>
 		<td width=25%>Admin id*</td>
 		<td><input name="admin_name" path="admin_name" maxlength="20"/></td>
 	</tr>
 	<tr>
 		<td width=25%>Admin Password*</td>
 		<td><input name="password" path="password" maxlength="10" type="password"/></td>
 	</tr>
 	<tr>
 		<td colspan=2>
 			<button>Login</button>
 		</td>
 	</tr>
 </table>
</form>

<jsp:include page="/WEB-INF/views/shared/admin-footer.jsp"></jsp:include>
</body>
</html>