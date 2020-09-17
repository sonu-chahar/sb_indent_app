<%@ include file="/static-resources/common/taglibs.jsp"%>

<style>
#myInput {
	background-position: 10px 12px; /* Position the search icon */
	background-repeat: no-repeat; /* Do not repeat the icon image */
	width: 100%; /* Full-width */
	font-size: 10px; /* Increase font-size */
	padding: 6px 20px 6px 20px; /* Add some padding */
	border: 1px solid #ddd; /* Add a grey border */
	margin-bottom: 12px; /* Add some space below the input */
}

#myUL {
	/* Remove default list styling */
	list-style-type: none;
	padding: 0;
	margin: 0;
}
</style>


<div class="col-sm-12 col-md-12 col-lg-12">
	<div class="row">
		<div class="main-gap keycloak-portal">
			<h1 class="dashbord-heading">
				<spring:message code="label.homePage.dashboard-heading" />
			</h1>
			<div class="row">
				<div class="col-sm-3">
					<input type="text" id="myInput" onkeyup="myFunction()"
						placeholder="Search for Application">
				</div>
			</div>
			<div class="row">
				<div class="main-gap keycloak-portal">
					<div class="row">
						<c:forEach items="${nonNdmcEmployeeApplicationList}"
							var="application" varStatus="counter">
							<c:if test="${counter.index == 0}">
								<div class="row">
									<div class="col-md-12">
							</c:if>
							<c:if test="${counter.index != 0  && counter.index%6 eq 0 }">
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						</c:if>

						<div class="col-sm-2">
							<div class="base hvr-float-shadow">
								<div class="circle bg${counter.index%8+1}-circle">
									<a href='<c:url value="${application.applicationUrl}"/>'> <img
										alt="applicationLogo"
										src="<%=request.getContextPath()%>/images/icons/${application.logoImageName}.${application.logoImageExtension}"
										width="90" height="90" />
									</a>
								</div>
								<h2>
									<a href='<c:url value="${application.applicationUrl}"/>'>
										${application.applicationDisplayName}</a>
								</h2>
							</div>
						</div>
						<c:if
							test="${fn:length(nonNdmcEmployeeApplicationList)==(counter.index+1)}">
					</div>
				</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<script>
	$(document)
			.ready(
					function() {
						const urlParams = new URLSearchParams(
								window.location.search);
						const status = urlParams.get('status');
						if (status == "updateSuccess") {
							if ("${applicationList}" == "") {
								alert("User Updated Successfully.Now please add application first")
							} else {
								alert("User Updated Successfully");
							}
						} else if ("${applicationList}" == "") {
							alert("Please add application first")
						}
					});

	function myFunction() {
		// Declare variables
		var input, filter, ul, li, a, i, txtValue;
		input = document.getElementById('myInput');
		filter = input.value.toUpperCase();
		// ul = document.getElementById("myUL");
		li = document.getElementsByClassName('col-sm-2');

		// Loop through all list items, and hide those who don't match the search query
		for (i = 0; i < li.length; i++) {
			a = li[i].getElementsByTagName("a")[1];
			txtValue = a.textContent || a.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				li[i].style.display = "";
			} else {
				li[i].style.display = "none";
			}
		}
	}
</script>