<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:set var="appctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content='IE=edge' http-equiv='X-UA-Compatible' />
<title><spring:message code="label.homePageWithChart.title" /></title>
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/resources/chartTheme/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css_nn/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css/dashboard-style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css-new/pgallery.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css/homePageWithChart.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/chartTheme/css-new/jquery.dataTables.min.css" />
<style>
.more-less {
	float: right;
	font-weight: bold !important;
	font-size: 22px;
	padding-right: 12px;
}
</style>
</head>


<body class="dashboard-page sb-l-o sb-r-c">
	<div id="google_translate_element"></div>
	<div id="main">
		<div class="container no-padding">
			<div class="row wow fadeInDown animated animated"
				data-wow-duration="2s"></div>
		</div>
	</div>

	<header
		style="box-shadow: 0 10px 28px 0 rgba(0, 0, 0, 0.2), 0 10px 20px 0 rgba(0, 0, 0, 0.19)">
		<img class="header_img" alt="Header Image"
			src="<%=request.getContextPath()%>/resources/chartTheme/SCDG_header.png"
			width="100%">
	</header>

	<div style="text-align: center">
		<div id="page-wrapper"
			style="background: -webkit-linear-gradient(-90deg, #f7b174, #88c181 100%); background-image: -webkit-linear-gradient(-90deg, rgb(247, 177, 116), rgb(136, 193, 129) 100%); background-position-x: initial; background-position-y: initial; background-size: initial; background-repeat-x: initial; background-repeat-y: initial; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial;">
			<div class="row">
				<a href="#"><p
						style="margin-top: -66px; margin-left: 318px; font-weight: bold; font-size: 16px; color: #000000;">
					</p></a>
			</div>
			<div class="row">
				<a href="#">
					<p
						style="margin-top: -70px; margin-left: 375px; font-weight: bold; font-size: 16px; color: #000000;">
						<sub data-toggle="modal" data-target="#myModal3"> </sub>
					</p>
				</a>
				<div class="languageButton-header"
					style="margin-top: 0px; margin-right: 5px;">
					<span id="rbtnListLanguage" class="radio-list"> <a
						id="rbtnListLanguage_0"
						href="?lang=en"
						class="lang-en lang-select" style="color: #333"> <label
							for="rbtnListLanguage_0" style="background-image: none;"><spring:message
									code="label.lang.en" /></label>
					</a> <a id="rbtnListLanguage_1"
						href="?lang=indic"
						class="lang-en lang-select" style="color: #333"> <label
							for="rbtnListLanguage_1"><spring:message
									code="label.lang.indic" /></label></a></span>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="card">
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
															<spring:message
																code="label.homePageWithChart.helpdesk.li-p1" />
														</p></li>
													<li><em class="fa fa-phone mt-4 fa-2x"></em>
														<p>
															<spring:message
																code="label.homePageWithChart.helpdesk.li-p2" />
														</p></li>
													<li><em class="fa fa-phone mt-4 fa-2x"></em>
														<p>
															<spring:message
																code="label.homePageWithChart.helpdesk.li-p3" />
														</p></li>
												</ul>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</br>
			<div class="row">
				<div class="col-md-12">
					<div class="container-fluid" id="pending-div">
						<div class="col-md-12 wow slideInLeft graphWrapper"
							data-wow-duration="4s" style="min-height: auto">
							<div class="card card-info">
								<div class="card-header p-0" style="background-color: #17a2b8;">
									<div class="text-white text-center py-2"
										style="margin-bottom: 20px;">
										<h3 class="m-0" style="margin: 10px; padding: 5px;">
											<spring:message code="label.passwordPolicy.heading" />
										</h3>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="panel-group" id="accordion" role="tablist"
											aria-multiselectable="true">

											<div class="panel-default">
												<div class="panel-heading" role="tab" id="headingOne">
													<h3 class="panel-title" style="color: #e03545;">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseOne" aria-expanded="true"
															aria-controls="collapseOne" style="margin-right: 90%;">
															<em class="more-less glyphicon glyphicon-minus"></em> <spring:message
																code="label.passwordPolicy.policyHeading" />
														</a>
													</h3>

												</div>
												<div id="collapseOne" class="panel-collapse collapse in"
													role="tabpanel" aria-labelledby="headingOne">
													<div class="panel-body" style="text-align: justify;">
														<ul>
															<strong><spring:message
																	code="label.passwordPolicy.policy-b1-heading" /></strong>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li1" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li2" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li3" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li4" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li5" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li6" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b1-li7" />
																</p></li>

															<strong><spring:message
																	code="label.passwordPolicy.policy-b2-heading" /></strong>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b2-li1" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b2-li2" />
																</p></li>

															<strong><spring:message
																	code="label.passwordPolicy.policy-b3-heading" /></strong>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li1" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li2" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li3" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li4" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li5" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li6" />
																</p></li>
															<li><p>
																	<spring:message
																		code="label.passwordPolicy.policy-b3-li7" />
																</p></li>
														</ul>
													</div>
												</div>
											</div>
											<br>
											<div class="panel-default">
												<div class="panel-heading" role="tab" id="headingTwo">
													<h3 class="panel-title" style="color: #e03545;">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#collapseTwo"
															aria-expanded="false" aria-controls="collapseTwo"
															style="margin-right: 90%;"> <spring:message
																code="label.passwordPolicy.purposeHeading" /> <em
															class="more-less glyphicon glyphicon-plus"></em>
														</a>
													</h3>

												</div>
												<div id="collapseTwo" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="headingTwo">
													<div class="panel-body" style="text-align: justify;">
														<spring:message code="label.passwordPolicy.purposeBody" />
													</div>
												</div>
											</div>
											<br>
											<div class=" panel-default">
												<div class="panel-heading" role="tab" id="headingThree">
													<h3 class="panel-title" style="color: #e03545;">
														<a class="collapsed" data-toggle="collapse"
															data-parent="#accordion" href="#collapseThree"
															aria-expanded="false" aria-controls="collapseThree"
															style="margin-right: 90%;"> <spring:message
																code="label.passwordPolicy.scopeHeading" /> <em
															class="more-less glyphicon glyphicon-plus"></em>
														</a>
													</h3>

												</div>
												<div id="collapseThree" class="panel-collapse collapse"
													role="tabpanel" aria-labelledby="headingThree">
													<div class="panel-body" style="text-align: justify;">
														<spring:message code="label.passwordPolicy.scopeBody" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
					</div>
					<!--/. container-fluid -->
				</div>
			</div>
		</div>
		<div class="row">
			<div class="row wow fadeInDown" data-wow-duration="2s" id="mainCount"></div>

			<div class="tab-content"
				style="border-radius: 10px; margin: 10px, 20px, 10px, 20px !important;">

				<div role="tabpanel" class="tab-pane active" id="home">

					<footer class="main-footer text-center"
						style="padding: 0px; margin-left: 0px; color: #fff; background-color: #343A40; font-size: 12px;">
						<spring:message code="label.homePageWithChart.footer" />
						<p style="margin: 0">
							<a href="#"><span data-toggle="modal" data-target="#myModal4"><spring:message
										code="label.homePageWithChart.helpdesk" /></span></a>&nbsp;&nbsp;|&nbsp;
							<a href="websitePolicy.html"> <span><spring:message
										code="label.homePageWithChart.websitePolicy" /></span></a>&nbsp;&nbsp;|&nbsp;
							<a href="passwordPolicy.html"> <span><spring:message
										code="label.homePageWithChart.passwordPolicy" /></span></a>&nbsp;&nbsp;|&nbsp;
							<span><spring:message code="label.homePageWithChart.faq" /></span>&nbsp;&nbsp;|&nbsp;
							<span><spring:message
									code="label.homePageWithChart.sitemap" /></span>
						</p>
					</footer>
				</div>
			</div>
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/jquery-1.11.1.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/jquery-ui.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/fusioncharts.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/themes/fusioncharts.theme.fint.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/chartTheme/js/Chart.bundle.js"></script>
	<script>
		$(document).ready(function() {
			$('.panel-group').on('hidden.bs.collapse', toggleIcon);
			$('.panel-group').on('shown.bs.collapse', toggleIcon);
		});
		function toggleIcon(e) {
			$(e.target).prev('.panel-heading').find(".more-less").toggleClass(
					'glyphicon-plus glyphicon-minus');
		}
	</script>
	<script>
		$(document).ready(function() {
			var urlParams = new URLSearchParams(window.location.search);
			if (urlParams.has('lang')) {
				if (urlParams.get('lang') == 'en') {
					$("#rbtnListLanguage_0").click(function() {
						return false;
					});
				} else {
					$("#rbtnListLanguage_1").click(function() {
						return false;
					});
				}
			} else {
				$("#rbtnListLanguage_0").click(function() {
					return false;
				});
			}
		});
	</script>
</body>


</html>
