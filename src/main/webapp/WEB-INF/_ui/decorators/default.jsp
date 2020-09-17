<!DOCTYPE html>
<%@ include file="/static-resources/common/taglibs.jsp"%>
<html style="overflow-x: hidden;">
<head>
<title><decorator:title /> | Accuster</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content='IE=edge' http-equiv='X-UA-Compatible' />

<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/resources/images/favicon.ico" />

<script
	src="<%=request.getContextPath()%>/resources/js/jquery-1.12.4.js"></script>
<script src='<%=request.getContextPath()%>/dwr/engine.js'></script>

<script
	src="<%=request.getContextPath()%>/resources/js/jquery-ui-1.12.1.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/multiselect.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.scrollbar.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/common.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/inside-style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/scroll.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/style-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/basic/styles.css">
<decorator:head />
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<%-- <div class="sidebar-header">
				<h3>
					<img alt=""
						src="<%=request.getContextPath()%>/resources/images/logo1.png" />
				</h3>
			</div> --%>
			<ul class="list-unstyled components">
				<p class="centered user_image">
					<a href="#"> <c:choose>
							<c:when
								test="${userDtl.imageName ne null and userDtl.imageName ne '' and  userDtl.fileExtension ne null and userDtl.fileExtension ne ''}">
								<img border="0" width="65px"
									src="${baseURL}/myProfile/getImage/${userDtl.contactNumber}/${userDtl.imageName}/${userDtl.fileExtension}"
									alt="Profile Image" />
								<%-- <img border="0" class="img-circle" width="100" height="100"
									src="<%=request.getContextPath()%>/images/default-image.jpg" /> --%>
							</c:when>
							<c:otherwise>
								<img border="0" class="img-circle" width="100" height="100"
									src="<%=request.getContextPath()%>/resources/images/default-image.jpg"
									alt="Profile Image" />
							</c:otherwise>
						</c:choose>
					</a>
				</p>
				<h5 class="centered user_name">
					<spring:message code="label.homePage.nav.welcome" />
					${userDtl.name}
				</h5>
				<h5 class="centered user_name">${userDtl.username}</h5>
				<h5 class="centered logout_btn " style="font-weight: bold">
					<a href='<c:url value="/logout"/>'> <em class="fa fa-sign-out"></em>
						<spring:message code="label.homePage.nav.logout" />
					</a>
				</h5>
				<p class="nav-head">
					<spring:message code="label.homePage.nav.heading" />
				</p>

				<li><a href="<%=request.getContextPath()%>/welcome"
					class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-home"></em>
						<spring:message code="label.homePage.nav.home" /> </a></li>

				<%-- <li><a
					href="<%=request.getContextPath()%>/myProfile/showProfile"
					class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-user"></em>
						<spring:message code="label.homePage.nav.profile" /> </a></li> --%>
				<%-- <li><a
					href="<%=request.getContextPath()%>/myProfile/showChangePwdPage"
					class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-key"></em>
						<spring:message code="label.homePage.nav.changePassword" /> </a></li> --%>
				<security:authorize
					access="hasAnyRole('ROLE_ORGANIZATION','ROLE_ADMIN')">
					<li><a href="<%=request.getContextPath()%>/user"
						class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-cog"></em>
							<spring:message code="label.homePage.nav.user" /></a></li>
				</security:authorize>
				<li><a href="<%=request.getContextPath()%>/products"
					class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-cog"></em>
						<spring:message code="label.homePage.nav.products" /></a></li>
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="<%=request.getContextPath()%>/product"
						class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-cog"></em>
							<spring:message code="label.homePage.nav.product" /></a></li>
					<li><a href="<%=request.getContextPath()%>/orders"
						class="hvr-shutter-in-vertical"><em class="fa fa-fw fa-cog"></em>
							<spring:message code="label.homePage.nav.orders" /></a></li>
				</security:authorize>
			</ul>
		</nav>
		<!-- Page Content Holder -->
		<div id="content">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" id="sidebarCollapse"
							class="btn btn-info navbar-btn">
							<em class="glyphicon glyphicon-align-left"></em> <span></span>
						</button>
					</div>


					<button class="right-menu btn btn-info navbar-btn">
						<em class="fa fa-fw fa-bars"></em>
					</button>

					<div class="collapse navbar-collapse navbar-right-main"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">

							<li id="enLi" class="btn-custom"><a href="?lang=en"><em
									class="fa fa-fw fa-language"></em> <spring:message
										code="label.lang.en" /></a></li>
							<li id="indicLi" class="btn-custom"><a href="?lang=indic"><em
									class="fa fa-fw fa-language"></em> <spring:message
										code="label.lang.indic" /></a></li>
							<li class="btn-custom"><a
								href="<%=request.getContextPath()%>/myProfile/showChangePwdPage"><em
									class="fa fa-fw fa-key"></em> <spring:message
										code="label.homePage.nav.changePassword" /></a></li>
							<li class="btn-custom"><a href="${ctx}/logout"><em
									class="fa fa-fw fa-sign-out"></em> <spring:message
										code="label.homePage.nav.logout" /></a></li>

						</ul>
					</div>
				</div>
			</nav>

			<div class="content-wrapper">
				<decorator:body />
			</div>
		</div>
	</div>


	<div class="footer">
		<div class="col-lg-12 col-md-7 col-sm-7 col-xs-12 copyrightContainer">
			<p style="margin: 0">
				<spring:message code="label.homePage.footer-p1" />
			</p>
			<p style="margin: 0">
				<spring:message code="label.homePage.footer-p2" />
			</p>
		</div>
	</div>




	<script>
		$(document).ready(function() {
			if ($("#mainFormId").val() != "") {
				$("#cancel").show();
				$("#btnSave").val("UPDATE");
			} else {
				$("#cancel").hide();
				/* $("#btnSave").val("SAVE"); */
			}
		});
	</script>

	<script>
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
		});
	</script>
	<script>
		jQuery(document).ready(function() {
			jQuery('.scrollbar-macosx').scrollbar();
		});
	</script>

	<script>
		$(document).ready(function() {
			$('.right-menu').click(function() {
				$('.navbar-right-main').slideToggle();
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			if ('${currentLocale}' == 'en') {
				$("#enLi a").click(function() {
					return false;
				});
			} else {
				$("#indicLi a").click(function() {
					return false;
				});
			}
		});

		function deleteObject(objectName, id, url, ctx) {
			if (confirm("Are you sure you want to delete this " + objectName)) {
				window.location.href = ctx + url + id;
			} else {
				return false;
			}
		}
		jQuery('.denyNumber').keyup(function() {
			if (this.value !== "" && !isNaN(this.value)) {
				alert("Name can't be a number!")
			}
			this.value = this.value.replace(/[^a-zA-Z\s]/g, '');
		});
	</script>
</body>
</html>