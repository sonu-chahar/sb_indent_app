<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Detail</title>
<link rel="stylesheet" type="text/css"
	  href="${pageContext.request.contextPath}/resources/css/basic/styles.css">
</head>
<body>
   <div class="page-title">Order Details</div>
   <div class="container">
       <h3>Thank you for Order</h3>
       Your order number is: ${order.orderNum} <br>
       Name : ${order.product.name}<br>
       Price : ${order.product.price}<br>
       Quantity : ${order.quantity}<br>
       Total Amount : ${order.amount}<br> 
   </div>
</body>
</html>