<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Login</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/topbar.jsp" ></jsp:include>

${error}

<form name="frmLogin" action="loginaction" method="post">
	
 <table border="1" cellspacing="2" cellpadding="4">
 	<tr>
 		<td width=25%>Email id*</td>
 		<td><input name="emailId" maxlength=50></td>
 	</tr>
 	<tr>
 		<td width=25%>Password*</td>
 		<td><input name="pwd" maxlength="10" type="password"></td>
 	</tr>
 	<tr>
 		<td colspan=2>
 			<button>Login</button><br>
 			<a href="signup">Not a member? Signup</a>
 		</td>
 	</tr>
 </table>
</form>
<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>