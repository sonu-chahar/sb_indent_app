<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Sign Up</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/basic/styles.css">

</head>
<body>


	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />



	<div class="page-title">Sign Up</div>

	<div class="login-container">

		<h3>Enter Details</h3>
		<br>
		<!-- /login?error=true -->
		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<form:form action="${pageContext.request.contextPath}/signUp"
			method="POST" modelAttribute="account">
			<table>
				<tr>
					<td><form:label path="username">User Name *</form:label></td>
					<td><form:input path="username" required="required" /></td>
					<td style="color: red;"><form:errors path="username"></form:errors>
					</td>
				</tr>

				<tr>
					<td><form:label path="password">Password *</form:label></td>
					<td><form:password path="password" required="required" /></td>
					<td style="color: red;"><form:errors path="password"></form:errors>
					</td>
				</tr>

				<tr>
					<td><form:label path="passwordConfirm">Confirm Password *</form:label></td>
					<td><form:password path="passwordConfirm" required="required" /></td>
					<td style="color: red;"><form:errors path="passwordConfirm"></form:errors>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Sign Up" /> <input
						type="reset" value="Reset" /></td>
				</tr>
			</table>
		</form:form>

		<span class="error-message">${error }</span>

	</div>
</body>
</html>