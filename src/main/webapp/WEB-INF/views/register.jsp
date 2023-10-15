<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sporty Shoes - Register</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/shared/header.jsp" ></jsp:include>
<jsp:include page="/WEB-INF/views/shared/topbar.jsp" ></jsp:include>
<h3> ${pageTitle} </h3>
${error}

<form name="frmReg" action="signupaction?id=${user.getUserId()}" method="post">
 <table border="1" cellspacing="2" cellpadding="4">
 	<tr>
 		<td width=25%>Email id <span style="color:red;">*</span></td>
 		<td><input name="emailId" type="email" maxlength="50"
 		value="${user.getEmailId()}"
 		></td>
 	</tr>
 	<tr>
 		<td width=25%>Password <span style="color:red;">*</span> </td>
 		<td><input name="pwd" maxlength="10" type="password"
 		value="${user.getPassword()}"
 		></td>
 	</tr>
 	<tr>
 		<td width=25%>Confirm Password <span style="color:red;">*</span></td>
 		<td><input name="pwd2" maxlength="10" type="password"
 		value="${user.getPassword()}"
 		></td>
 	</tr>
 	<tr>
 		<td width=25%>First name <span style="color:red;">*</span></td>
 		<td><input name="fname" maxlength="25"
 		value="${user.getFname()}"
 		 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Last name <span style="color:red;">*</span></td>
 		<td><input name="lname" maxlength="25"
 		value="${user.getLname()}"
 		 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Age <span style="color:red;">*</span></td>
 		<td><input name="age" maxlength="3" min="1" type="number"
 		value="${user.getAge()== 0 ? 1 : user.getAge() }"
 		 ></td>
 	</tr>
 	<tr>
 		<td width=25%>Address <span style="color:red;">*</span></td>
 		<td><input name="address" maxlength="100" 
 		value="${user.getAddress()}"
 		></td>
 	</tr>
 	
 	<tr>
 		<td colspan=2>
 			<button>
 			<c:choose>
 			<c:when test="${user.getUserId() == 0 }">
 			Signup
 			</c:when>
 			<c:otherwise>
 			Update
 			</c:otherwise>
 			</c:choose>
 			</button><br>
 			<c:if test="${user.getUserId() == 0 }">
 			<a href="userLogin">Already a member? Login</a>
 			</c:if>
 			
 		</td>
 	</tr>
 </table>
</form>
<jsp:include page="/WEB-INF/views/shared/footer.jsp"></jsp:include>
</body>
</html>