<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="header-container">
 
    <div class="site-name">Indent Shop App</div>
 
    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
           <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </c:if>
    </div>
</div>