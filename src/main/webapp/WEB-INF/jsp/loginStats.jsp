<%@ include file="/static-resources/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Login History</title>


<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/resources/images/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/inside-style.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style-responsive.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/scroll.css">

</head>
<body>
	<div class="main-gap form-horizontal user-portal">
		<c:if test="${not empty  userLoginStatList}">
			<div class="form-block">
				<h1 class="heading" style="font-size: 24px; font-weight: 600;">Login History</h1>
				<div class="form-bg">
					<div class="table-responsive">
						<div style="width: 100%; height: 90%; overflow-y: scroll;text-align: center;">
							<display:table name="userLoginStatList" class="list grid data-grid"
								id="list" requestURI="" excludedParams="pageId"
								style="width: 100%;"
								sort="list" export="false">
								<display:column property="username" title="User name"
									escapeXml="true" style="text-align:center;"/>
								<display:column property="ipAddress" title="IP Address"
									escapeXml="true" style="text-align:center;" />
								<display:column property="insDate" title="Logged In Date"
									escapeXml="true" style="text-align:center;" />
							</display:table>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>
