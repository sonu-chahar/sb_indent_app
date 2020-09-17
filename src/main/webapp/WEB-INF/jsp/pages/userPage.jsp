<%@ include file="/static-resources/common/taglibs.jsp"%>
<spring:message code="label.btn.save" var="btnSave" />
<spring:message code="label.btn.reset" var="btnReset" />
<title>User Master</title>
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>
<div class="main-gap form-horizontal user-portal">
	<form:form modelAttribute="user" method="post"
		onsubmit="return validate(this);" action="${ctx}/user"
		enctype="multipart/form-data" id="userForm">

		<input type="hidden" name="isEdit" id="fEdit" value="${isEdit}" />

		<h1 class="heading">
			<em class="fa fa-edit"></em>
			<spring:message code="label.userPage.fa-heading" />
		</h1>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.userPage.name" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fName" path="name"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="name" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.userPage.contactNumber" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fContactNumber" path="contactNumber"
					cssClass="form-control" />
				*
				<form:errors path="contactNumber" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.userPage.userType" />
			</label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fUserType" path="userType"
					onchange="checkUserTypeDetails()">
					<form:option selected="selected" value="">---- <spring:message
							code="label.userPage.userType" /> ----</form:option>
					<form:option value="ADMIN">
						<spring:message code="label.userPage.userType.admin" />
					</form:option>
					<form:option value="ORGANIZATION">
						<spring:message code="label.userPage.userType.organization" />
					</form:option>
					<form:option value="SUB_ORGNIZATION">
						<spring:message code="label.userPage.userType.subOrganization" />
					</form:option>
					<form:option value="DOCTOR">
						<spring:message code="label.userPage.userType.doctor" />
					</form:option>
					<form:option value="MMU">
						<spring:message code="label.userPage.userType.mmu" />
					</form:option>
				</form:select>
				*
				<form:errors path="userType" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn owner-name"><spring:message
					code="label.userPage.ownerName" /> </label>
			<div class="col-sm-3 required owner-name">
				<form:input id="fOwnerName" path="ownerName"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="ownerName" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.userPage.countryName" /> </label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fCountryName"
					path="countryName">
					<option selected="selected" value="">----
						<spring:message code="label.dropdownSelect" />
						<spring:message code="label.userPage.countryName" /> ----
					</option>
				</form:select>
				*
				<form:errors path="countryName" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.userPage.stateName" />
			</label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fStateName" path="stateName">
					<option selected="selected" value="">----
						<spring:message code="label.dropdownSelect" />
						<spring:message code="label.userPage.stateName" /> ----
					</option>
				</form:select>
				*
				<form:errors path="stateName" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.userPage.cityName" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fCityName" path="cityName"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="cityName" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.userPage.streetAddress" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fStreetAddress" path="streetAddress"
					cssClass="form-control" />
				*
				<form:errors path="streetAddress" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.userPage.pincode" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fPincode" path="pincode" cssClass="form-control" />
				*
				<form:errors path="pincode" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.isActive" />
			</label>
			<div class="col-sm-3 required">
				<input id="fContactNumber" name="isActive" cssClass="form-control"
					type="checkbox" /> *
				<form:errors path="contactNumber" cssClass="error" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.userPage.username" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fUsername" path="username" cssClass="form-control" />
				*
				<form:errors path="username" id="fErrorUsername" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.userPage.password" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fPassword" path="password" type="password"
					cssClass="form-control" />
				*
				<form:errors path="password" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.photo" /> </label>
			<div class="col-sm-3">
				<form:input path="imageFile" type="file" id="file" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn">&nbsp; </label> <input
				type="submit" name="" value="${btnSave}" class="btn btn-warning" />
			&nbsp;&nbsp; <input type="reset" name="reset" value="${btnReset}"
				id="btnReset" class="btn btn-warning"
				onclick="window.location.href='${ctx}/user'"> &nbsp;&nbsp;
			<c:if test="${not empty message}">
				<span id="msg" class="message">${message}</span>
			</c:if>
		</div>


	</form:form>
	<br> <br>
	<c:if test="${not empty  userList}">
		<div class="form-block">
			<h1 class="heading">
				<em class="fa fa-list"></em> User List
			</h1>
			<div class="form-bg">
				<div class="table-responsive">
					<div style="width: 100%; height: 100%; padding: 0px 0px 10px 10px;">
						<display:table name="userList" class="list grid data-grid"
							id="list" requestURI="" excludedParams="*"
							style="width: 100%; border: 1px solid #D4D4D4; border-collapse: collapse;"
							decorator="com.chahar.indent.decorator.UserMasterDecorator"
							sort="list" pagesize="5">
							<display:column property="srNo" title="S.No."
								style="width:50px;text-align:center;" />
							<display:column property="name" title="Name" escapeXml="true" />
							<display:column property="contactNumber" title="Contact Number"
								escapeXml="true" />
							<display:column property="username" title="Username"
								escapeXml="true" />
							<display:column property="userType" title="User Type"
								escapeXml="true" />
							<display:column property="actionLink" title="EDIT/DELETE"
								media="html" style="width:100px;text-align:center;" />
						</display:table>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>

<script>
	/* function validate() {
		if ($("#fName").val() == "") {
			alert("Name is required !");
			$("#fName").focus();
			return false;
		}

		if ($("#fCode").val() == "") {
			alert("Code is required !");
			$("#fCode").focus();
			return false;
		}

		if ($("#fPrice").val() == "") {
			alert("Price is required !");
			$("#fPrice").focus();
			return false;
		}

		if ($("#fQuantity").val() == "") {
			alert("Quantity is required !");
			$("#fQuantity").focus();
			return false;
		}

		let file = $('#file')[0].files[0];
		if (file != null) {
			file_size = (file.size / 1024).toFixed(2);
			if (file_size > 512) {
				alert("File size should not be greater than 512KB!");
				return false;
			}
		}
	} */

	$(document).ready(function() {
		$('.owner-name').hide();
		populateCountries("fCountryName", "fStateName");
		if ("${isEdit}" == "true") {
			$("#fErrorUsername").hide();
		}
	});
	function checkUserTypeDetails() {
		//alert("abcd: "+$("#fEmployeeCode").val()+" :: "+ '${userMaster.employeeCode}');
		if ($("#fUserType").val() == "ADMIN"
				|| $("#fUserType").val() == "ORGANIZATION") {
			$('.owner-name').show();
		} else {
			$('.owner-name').hide();
		}
	}
</script>