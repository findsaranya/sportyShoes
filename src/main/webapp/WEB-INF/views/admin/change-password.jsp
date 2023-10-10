<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Change Password</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/admin-header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/admin-topbar.jsp" ></jsp:include>

${error}
<h1>${pageTitle}</h1>
<form method="post" action="adminchangepwdaction">
<table border="1" cellspacing="2" cellpadding="4">
 	<tr>
 		<td width=25%>Enter new password*</td>
 		<td><input name="pwd" maxlength="10" type="password"></td>
 	</tr>
 	<tr>
 		<td width=25%>Confirm new Password*</td>
 		<td><input name="confirm" maxlength="10" type="password"></td>
 	</tr>
 	<tr>
 		<td colspan=2>
 			<button>Save</button>
 		</td>
 	</tr>
 </table>
</form>

<jsp:include page="/WEB-INF/views/shared/admin-footer.jsp"></jsp:include>
</body>
</html>