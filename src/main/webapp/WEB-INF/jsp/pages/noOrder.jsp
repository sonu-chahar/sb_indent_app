<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>No Order</title>
<link rel="stylesheet" type="text/css"
	  href="${pageContext.request.contextPath}/resources/css/basic/styles.css">
</head>
<body>
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
   <h2>404</h2>
   <h3 style="color:red;">You did not order anything</h3>
</body>
</html>