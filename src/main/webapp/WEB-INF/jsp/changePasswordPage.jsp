<%@ include file="/static-resources/common/taglibs.jsp"%>
<spring:message code="label.btn.update" var="btnUpdate" />
<spring:message code="label.btn.reset" var="btnReset" />

<div class="main-gap form-horizontal user-portal">
	<form:form modelAttribute="userMasterDTO" method="post"
		id="updatePasswordForm" onsubmit="return validate(this);"
		action="${ctx}/myProfile/updatePassword">

		<h1 class="heading">
			<em class="fa fa-edit"></em>
			<spring:message code="label.passwordPage.heading" />
		</h1>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.passwordPage.newPassword" />
			</label>
			<div class="col-sm-3 required ${status.error ? 'has-error' : ''}">
				<form:input type="password" id="fPassword" path="password"
					class="form-control" />
				*
				<form:errors path="password" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.passwordPage.confirmPassword" />
			</label>
			<div class="col-sm-3 required ${status.error ? 'has-error' : ''}">
				<form:input type="password" id="fConfirmPassword"
					path="confirmPassword" class="form-control" />
				*
				<form:errors path="password" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn">&nbsp; </label>
			&nbsp;&nbsp; <input type="submit" name="" value="${btnUpdate}"
				class="btn btn-warning" /> &nbsp;&nbsp; <input type="reset"
				name="reset" value="${btnReset}" id="btnReset"
				class="btn btn-warning"
				onclick="window.location.href='${ctx}/myProfile/showChangePwdPage'">
			&nbsp;&nbsp;
			<c:if test="${not empty message}">
				<span id="msg" class="message">${message}</span>
			</c:if>
		</div>
	</form:form>
</div>
<script>
	//validation script
	function validate() {
		//for Password
		if ($("#fPassword").val() == "") {
			alert("Password is required !");
			$("#fPassword").focus();
			return false;
		}
		//for ConfirmPassword
		if ($("#fConfirmPassword").val() == "") {
			alert("ConfirmPassword is required !");
			$("#ConfirmPassword").focus();
			return false;
		}

		if ($("#fPassword").val() != $("#fConfirmPassword").val()) {
			alert("Password doesn't match !");
			$("#fUsername").focus();
			return false;
		}
	}

	$(document).ready(function() {
		//$("#updatePasswordForm").trigger('reset');
		$("#fPassword").val("");
		$("#fConfirmPassword").val("");

		//document.getElementById('updatePasswordForm').reset();
	});
</script>


