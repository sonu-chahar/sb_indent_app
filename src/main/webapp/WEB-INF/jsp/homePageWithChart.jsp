<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<spring:message code="label.btn.login" var="btnLogin" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content='IE=edge' http-equiv='X-UA-Compatible' />
<title><spring:message code="label.homePageWithChart.title" /></title>
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<link href="${contextPath}/resources/images/favicon.ico" rel="icon">
<link href="${contextPath}/resources/css/nn/style.css" rel="stylesheet">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/dashboard-style.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/pgallery.css" rel="stylesheet">
<link href="${contextPath}/resources/css/homePageWithChart.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/loginStyle.css"
	rel="stylesheet">
<%-- <link rel="stylesheet" type="text/css"
     href="${pageContext.request.contextPath}/resources/css/basic/styles.css"> --%>
<style>
/* html {
  height: 100%;
}
body {
  min-height: 100%;
  display: grid;
  grid-template-rows: 1fr auto;
} */
/* .main-footer {
  grid-row-start: 2;
  grid-row-end: 3;
} */
</style>
</head>
<body>
	<%-- <header
		style="box-shadow: 0 10px 28px 0 rgba(0, 0, 0, 0.2), 0 10px 20px 0 rgba(0, 0, 0, 0.19)">
		<div id="en">
			<img class="header_img" alt="Header Image"
				src="${contextPath}/resources/images/SCDG_header.png" width="100%">
		</div>
	</header> --%>
	<%-- <jsp:include page="pages/_header.jsp" />
	<jsp:include page="pages/_menu.jsp" /> --%>
	
	<div id="main" style="position: relative;">
		<div style="text-align: center;">
			<div id="page-wrapper"
				style="background: -webkit-linear-gradient(-90deg, #f7b174, #88c181 100%); background-image: -webkit-linear-gradient(-90deg, rgb(247, 177, 116), rgb(136, 193, 129) 100%); background-position-x: initial; background-position-y: initial; background-size: initial; background-repeat-x: initial; background-repeat-y: initial; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial;">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="graphical-dashboard">
								<h2>
									<spring:message code="label.homePageWithChart.dashBoardHeading" />
								</h2>
							</div>
							<%-- <div class="btnn">
								<a href="#" class="btn" data-toggle="modal"
									data-target="#myModal1"><spring:message
										code="label.homePageWithChart.login" /></a>
							</div> --%>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="row wow fadeInDown" data-wow-duration="2s"
						id="mainCount">
						<div class="col-lg-3 col-md-6 dataBoxHover">
							<div class="panel orange">
								<em class="fa  fa-android fa-4x"></em>
								<div class="panel-heading">
									<div class="row">
										<div id="pendency" class="col-xs-9 text-left">
											<div>
												<a href="javascript:void(0);" style="text-decoration: none;">
													<h3 id="total_pending1" data-tag="">${userStats.organizations}</h3>
												</a>
											</div>
											<div style="margin-left: 50px; margin-top: -32px;">
												<strong><spring:message
														code="label.homePageWithChart.organizationsLabel"
														var="organizationsLabel" />${organizationsLabel}</strong>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="col-lg-3 col-md-6 dataBoxHover">
							<div class="panel pink">
								<em class="fa  fa-users fa-4x"></em>
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-9 text-left">
											<div>
												<a href="javascript:void(0);" style="text-decoration: none;">
													<h3 id="pendingcomplience2">${userStats.subOrganizations}</h3>
												</a>
											</div>
											<div style="margin-left: 50px; margin-top: -32px;">
												<strong><spring:message
														code="label.homePageWithChart.subOrganizationsLabel"
														var="subOrganizationsLabel" />${subOrganizationsLabel}</strong>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="col-lg-3 col-md-6 dataBoxHover">
							<div class="panel tar">
								<em class="fa  fa-user fa-4x"></em>
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-9 text-left">
											<div>
												<a href="javascript:void(0);" style="text-decoration: none;"><h3
														id="fInactiveUsers">${userStats.doctors}</h3></a>
											</div>
											<div style="margin-left: 50px; margin-top: -32px;">
												<strong><spring:message
														code="label.homePageWithChart.doctorsLabel"
														var="doctorsLabel" />${doctorsLabel}</strong>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6">
							<div class="panel yellow">
								<em class="fa  fa-calendar fa-4x"></em>
								<div class="panel-heading">
									<div class="row">
										<div class="col-xs-9 text-left">
											<div>
												<a href="javascript:void(0);" style="text-decoration: none;"><h3
														id="#">${userStats.mmus}</h3></a>
											</div>
											<div style="margin-left: 50px; margin-top: -32px;">
												<strong><spring:message
														code="label.homePageWithChart.mmusLabel" var="mmusLabel" />${mmusLabel}</strong>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="tab-content"
						style="border-radius: 10px; margin: 10px, 20px, 10px, 20px !important;">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="container-fluid" id="pending-div">
								<div class="col-md-12 wow slideInLeft graphWrapper"
									data-wow-duration="4s">
									<div class="row popupHeader">
										<div class="col-md-6">
											<div style="text-align: center"></div>
										</div>
										<div class="col-md-6">
											<div style="text-align: center"></div>
										</div>
									</div>
									<div class="col-md-6">
										<div id="drill" class="requirement "
											style="border: 4px solid #7e7575; padding: 4px 0px; background-size: cover; text-align: center; min-height:56vh;">
											<canvas id="myChart1" width="576" height="294"></canvas>
										</div>
									</div>
									<div class="col-md-6">
										<div id="drill" class="requirement "
											style="border: 4px solid #7e7575; padding: 4px 0px; background-size: cover; text-align: center; min-height:56vh;">
											<%-- <canvas id="myChart" width="576" height="314"
												style="width: 576px; height: 314px !important; margin-top: 14px;"></canvas> --%>
											<h2
												style="margin: 0 10px 10px 10px; padding: 0 0 5px 0; text-align: left; font-size: 22px; font-weight: bold; border-bottom: solid 1px #3c763dcf; color: #3c763d;">Log
												In</h2>
											<div id="kc-content">
												<div id="kc-content-wrapper">
													<div id="kc-form">
														<div id="kc-form-wrapper"${error != null ? 'has-error' : ''}">
															<span>${message}</span>
															<form id="kc-form-login" onsubmit="validateForm()"
																action="${contextPath}/login" method="post">
																<div class="">
																	<label for="username" class="">Username</label> <input
																		id="username" name="username" value="" type="text"
																		autofocus="true" autocomplete="off">
																</div>
																<div>
																	<label for="password" class="">Password</label> <input
																		id="password" class="" name="password" type="password"
																		autocomplete="off">
																</div>
																<span>${error}</span> <input type="hidden"
																	name="${_csrf.parameterName}" value="${_csrf.token}" />
																<div id="kc-form-buttons">
																	<input name="login" class="" id="kc-login"
																		type="submit" value="${btnLogin}">
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="position: inherit; bottom: -10px;">
			<footer class="main-footer text-center footer"
				style="text-align: center;">
				<spring:message code="label.homePageWithChart.footer" />
				<p style="margin: 0">
					<a href="#"><span data-toggle="modal" data-target="#myModal4"><spring:message
								code="label.homePageWithChart.helpdesk" /></span></a>&nbsp;&nbsp;|&nbsp; <span><spring:message
							code="label.homePageWithChart.faq" /></span>&nbsp;&nbsp;|&nbsp; <span><spring:message
							code="label.homePageWithChart.sitemap" /></span>
				</p>
			</footer>
		</div>
	</div>

	<!-- helpdesk -->
	<div class="modal fade" id="myModal4" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<small class="card-title" style="float: left; color: #ffffff;"><spring:message
							code="label.homePageWithChart.helpdeskHeading" /></small>

					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>

				</div>
				<div class="modal-body">
					<form class="example" action="">
						<!--Grid column-->
						<div class="col-md-12 text-center">
							<h3>
								<spring:message
									code="label.homePageWithChart.helpdeskModelHeading" />
							</h3>
							<ul class="list-unstyled mb-0">
								<li><em class="fa fa-map-marker-alt fa-2x"></em>
									<p>
										<spring:message code="label.homePageWithChart.helpdesk.li-p1" />
									</p></li>
								<li><em class="fa fa-phone mt-4 fa-2x"></em>
									<p>
										<spring:message code="label.homePageWithChart.helpdesk.li-p2" />
									</p></li>
								<li><em class="fa fa-phone mt-4 fa-2x"></em>
									<p>
										<spring:message code="label.homePageWithChart.helpdesk.li-p3" />
									</p></li>
							</ul>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="${contextPath}/resources/js/jquery-1.12.4.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui-1.12.1.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${contextPath}/resources/js/fusioncharts.js"></script>
	<script src="${contextPath}/resources/js/fusioncharts.theme.fint.js"></script>
	<script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>
	<script src="${contextPath}/resources/js/Chart.bundle.js"></script>
	<script>
		/* // Bar Chart
		var ctx = new Chart(document.getElementById("myChart"), {
			type : "bar",
			data : {
				datasets : [ {
					label : "${organizationsLabel}",
					backgroundColor : 'rgba(255,0,0)',
					borderColor : 'rgba(255,99,132,1)',
					borderWidth : 1,
					data : [ "1" ],
				}, {
					label : "${subOrganizationsLabel}",
					backgroundColor : 'rgba(40,167,69,1)',
					data : [ "3" ],
				}, {
					label : "${doctorsLabel}",
					backgroundColor : 'rgba(23, 162, 184, 1)',
					data : [ "6" ],
				}, {
					label : "${mmusLabel}",
					backgroundColor : 'rgba(255, 193, 7, 1)',
					data : [ "21" ],
				} ]
			},
			options : {
				legend : {
					position : 'top'
				},
				title : {
					display : true,
				},
			}
		}); */
		// Application Chart
		var ctx = document.getElementById("myChart1").getContext('2d');
		var myChart = new Chart(ctx, {
			type : 'pie',
			data : {
				labels : [ "${organizationsLabel}", "${subOrganizationsLabel}",
						"${doctorsLabel}", "${mmusLabel}" ],
				datasets : [ {
					backgroundColor : [ 'rgba(255,0,0)', 'rgba(40,167,69,1)',
							'rgba(23, 162, 184, 1)', 'rgba(255, 193, 7, 1)', ],
					data : [ "1", "3", "6", "21" ]
				} ]
			}
		});
	</script>
</body>
</html>


