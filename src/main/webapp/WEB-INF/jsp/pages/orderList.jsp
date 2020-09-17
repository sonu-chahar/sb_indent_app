<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/basic/styles.css">
</head>
<body>
	<div class="main-gap form-horizontal user-portal">

		<div class="form-block">
			<h1 class="heading">
				<em class="fa fa-list"></em> Order List
			</h1>
			<div class="form-bg">
				<div class="table-responsive">
					<div style="width: 100%; height: 100%; padding: 0px 20px 0px 20px;">
						<table class="list grid data-grid"
							style="width: 100%;border: 1px solid #D4D4D4; border-collapse: collapse;"
							id="list">
							<thead>
								<tr>
									<th>Order Num</th>
									<th>Order Name</th>
									<th>Order Price</th>
									<th>Order Date</th>
									<th>Order Quantity</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orderList}" var="order">
									<tr>
										<td style="text-align:center;">${order.orderNum}</td>
										<td style="text-align:center;">${order.product.name}</td>
										<td style="text-align:center;"><fmt:formatNumber value="${order.product.price}"
												type="currency" /></td>
										<td style="text-align:center;"><fmt:formatDate value="${order.orderDate}"
												pattern="dd-MM-yyyy HH:mm" /></td>
										<td style="text-align:center;">${order.quantity}</td>
										<td style="color: red;text-align:center;"><fmt:formatNumber
												value="${order.amount}" type="currency" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>