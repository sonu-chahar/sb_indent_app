<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div class="menu-container">

	<a href="${pageContext.request.contextPath}/productList"> Product
		List </a> |
	<security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
		<a href="${pageContext.request.contextPath}/orderList"> Order List
		</a>
	</security:authorize>
</div>