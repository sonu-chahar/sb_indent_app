<%@ include file="/static-resources/common/taglibs.jsp"%>

<title>Products</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/basic/styles.css">

<div class="main-gap form-horizontal user-portal">
	<h1 class="heading">
		<em class="fa fa-list"></em> Product List
	</h1>
	<c:forEach items="${productList}" var="prodInfo">
		<div class="product-preview-container">
			<ul>
				<li>Name: ${prodInfo.name}</li>
				<li>Price: <fmt:formatNumber value="${prodInfo.price}"
						type="currency" /></li>
				<security:authorize
					access="hasAnyRole('ROLE_ORGANIZATION','ROLE_ADMIN')">
					<li><a
						href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
							Buy Now</a></li>
				</security:authorize>
			</ul>
		</div>
	</c:forEach>
</div>

