<%@ include file="/static-resources/common/taglibs.jsp"%>
<%@page import="com.chahar.indent.model.UserMaster"%>
<spring:message code="label.btn.update" var="btnUpdate" />
<spring:message code="label.btn.reset" var="btnReset" />
<script src="<%=request.getContextPath()%>/resources/js/countries.js"></script>
<script
	src='<%=request.getContextPath()%>/dwr/interface/userMasterRemoteService.js'></script>

<title><spring:message code="label.profilePage.title" /></title>

<noscript class="noscript">
	<div id="div100">
		<h1>Please enable javascript in your browser ....</h1>
	</div>
</noscript>

<style>
body {
	position: relative;
}

.noscript {
	width: 100%;
	height: 100%;
	/* will cover the text displayed when javascript is enabled*/
	z-index: 100000; /* higher than other z-index */
	position: absolute;
}

.noscript #div100 {
	display: block;
	height: 100%;
	background-color: white;
	margin-top: -22px;
}
</style>
<%
	UserMaster user = (UserMaster) request.getSession(false).getAttribute("userMaster");

	if (user != null) {
		String imageUrl = "myProfile/getImage/" + user.getContactNumber() + "/" + user.getImageName() + "/"
				+ user.getFileExtension();
		request.setAttribute("imageUrl", imageUrl);
	}
%>

<div class="main-gap form-horizontal user-portal">
	<form:form modelAttribute="userMasterDTO" method="post"
		onsubmit="return validate(this);"
		action="${ctx}/myProfile/updateProfile" enctype="multipart/form-data"
		id="profileForm">

		<h1 class="heading">
			<em class="fa fa-edit"></em>
			<spring:message code="label.profilePage.fa-heading1" />
		</h1>

		<form:hidden path="id" />
		<input type="hidden" id="fGenerateOTPFlag" value="false" />
		<input type="hidden" id="fEmployeeCodeOTPFlag" value="false" />
		<input type="hidden" id="fRemoveImageFlag" name="removeImageFlag"
			value="false" />
		<input type="hidden" id="fGeoLocFlag" name="geoLocFlag" value="false" />
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.firstName" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fFirstName" path="firstName"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="firstName" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.profilePage.lastName" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fLastName" path="lastName"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="lastName" cssClass="error" />
			</div>

		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.profilePage.fatherName" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fFatherName" path="fatherName"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="fatherName" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.profilePage.dateOfBirth" /> </label>
			<div class="col-sm-3 required">
				<form:input id="dateOfBirth" style="width:120px" path="dateOfBirth"
					type="text" class="form-control" placeholder="DD/MM/YYYY" />
				*
				<form:errors path="dateOfBirth" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.profilePage.emailId" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="emailId" path="emailId" cssClass="form-control"
					maxlength="50" />
				*
				<form:errors path="emailId" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.profilePage.mobileNumber" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fMobileNumber" path="mobileNumber" maxlength="10"
					cssClass="form-control"
					onkeypress='return isNumberKeyMobileNumber(event)' />
				*
				<form:errors path="mobileNumber" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.profilePage.gender" />
			</label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fGender" path="gender">
					<form:option selected="selected" value=""> ---- <spring:message
							code="label.profilePage.gender" />
						<spring:message code="label.profilePage.dropdownSelect" /> ----</form:option>
					<form:option value="m">
						<spring:message code="label.profilePage.gender.male" />
					</form:option>
					<form:option value="f">
						<spring:message code="label.profilePage.gender.female" />
					</form:option>
					<form:option value="o">
						<spring:message code="label.profilePage.gender.other" />
					</form:option>
				</form:select>
				*
				<form:errors path="gender" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.profilePage.country" /> </label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fCountry" path="country">
					<option selected="selected" value=""><spring:message
							code="label.profilePage.lastName" /> ----
						<spring:message code="label.profilePage.country" />
						<spring:message code="label.profilePage.dropdownSelect" /> ----
					</option>
				</form:select>
				*
				<form:errors path="country" cssClass="error" />
			</div>

		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.state" /> </label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fState" path="state"
					onchange="getLocalityByState(this.value)">
					<option selected="selected" value="">----
						<spring:message code="label.profilePage.state" />
						<spring:message code="label.profilePage.dropdownSelect" /> ----
					</option>
				</form:select>
				*
				<form:errors path="state" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.profilePage.city" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fCity" path="city" maxlength="50"
					class="form-control denyNumber" />
				*
				<form:errors path="city" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.locality" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fLocality" path="locality" cssClass="form-control" />
				<form:select path="locality" id="fLocalitySelect"
					class="form-control">
					<form:option selected="selected" value="">---- <spring:message
							code="label.profilePage.locality" />
						<spring:message code="label.profilePage.dropdownSelect" />  ----</form:option>
					<c:forEach items="${localityList}" var="locality">
						<form:option value="${locality.localityName}">${locality.localityName}</form:option>
					</c:forEach>
				</form:select>
				*
				<form:errors path="locality" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.profilePage.pinCode" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fPinCode" path="pinCode" maxlength="6"
					cssClass="form-control"
					onkeypress='return isNumberKeyCPincode(event)' />
				*
				<form:errors path="pinCode" cssClass="error" />
			</div>
		</div>

		<h2 class="subheading">
			<em class="fa fa-edit"></em>
			<spring:message code="label.profilePage.fa-heading2" />
		</h2>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.profilePage.userType" />
			</label>
			<div class="col-sm-3 required">
				<form:select class="form-control" id="fUserType" path="userType"
					onchange="checkUserTypeDetails()">
					<form:option selected="selected" value="">---- <spring:message
							code="label.profilePage.userType" />
						<spring:message code="label.profilePage.dropdownSelect" /> ----</form:option>
					<form:option value="ndmcCitizen">
						<spring:message code="label.profilePage.userType.ndmcCitizen" />
					</form:option>
					<form:option value="nonNdmcCitizen">
						<spring:message code="label.profilePage.userType.nonNdmcCitizen" />
					</form:option>
					<form:option value="ndmcEmployee">
						<spring:message code="label.profilePage.userType.ndmcEmployee" />
					</form:option>
				</form:select>
				*
				<form:errors path="userType" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.profilePage.adharCardNumber" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fAdharCardNumber" path="adharCardNumber"
					maxlength="16" cssClass="form-control"
					onkeypress='return isNumberKeyAdharCardNumber(event)' />
				<form:errors path="adharCardNumber" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.voterIdNumber" /> </label>
			<div class="col-sm-3">
				<form:input id="fVoterIdNumber" path="voterIdNumber"
					class="form-control" />
				<form:errors path="voterIdNumber" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.profilePage.passportNumber" />
			</label>
			<div class="col-sm-3">
				<form:input id="fPassportNumber" path="passportNumber"
					class="form-control" />
				<form:errors path="passportNumber" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.waterConsumerNumber" /> </label>
			<div class="col-sm-3">
				<form:input id="fWaterConsumerNumber" path="waterConsumerNumber"
					class="form-control" />
				<form:errors path="waterConsumerNumber" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.profilePage.electricityConsumerNumber" />
			</label>
			<div class="col-sm-3">
				<form:input id="fElectricityConsumerNumber"
					path="electricityConsumerNumber" class="form-control" />
				<form:errors path="electricityConsumerNumber" cssClass="error" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.profilePage.propertyId" />
			</label>
			<div class="col-sm-3">
				<form:input id="fPropertyId" path="propertyId" class="form-control" />
				<form:errors path="propertyId" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.profilePage.estatePropertyId" />
			</label>
			<div class="col-sm-3">
				<form:input id="fEstatePropertyId" path="estatePropertyId"
					class="form-control" />
				<form:errors path="estatePropertyId" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.profilePage.uploadPhoto" /> </label>

			<div class="col-sm-3">
				<form:input path="imageFile" type="file" id="file" />
				<form:hidden path="imageName" />
				<form:hidden path="fileExtension" />
			</div>
		</div>

		<div id='fOtpPopupDiv' class='manage-otp-popup modal fade'
			tabindex='-1' role='dialog' aria-labelledby='myModalLabel'
			aria-hidden='true'>
			<div class='modal-dialog'>
				<div class='modal-content'>
					<div class='modal-header'>
						<button type='button' class='btn btn-warning close'
							data-dismiss='modal' onclick='return closeOpenPopup();'>&times;</button>
						<h4 class='modal-title'>
							<spring:message code="label.profilePage.mobile-modal-title" />
						</h4>
					</div>
					<div id='fOtpModelBody' class='modal-body'>
						<div class="form-group form-group-sm" id="fOtpDiv">
							<label class="col-sm-4 control-label label-inn"> <spring:message
									code="label.profilePage.mobile-otp" /></label>
							<div class="col-sm-6">
								<input id="fOtp" name="otp" maxlength="6" class="form-control" />
							</div>
						</div>
					</div>
					<div class='modal-footer'>
						<button id="fOtpResentBtn" type='button' class='btn btn-warning'
							onclick="resendOtp()">
							<spring:message code="label.profilePage.mobile-resend-otp" />
						</button>
						<button type='button' class='btn btn-warning' data-dismiss='modal'
							onclick='return closeOpenPopup();'>
							<spring:message code="label.profilePage.update" />
						</button>
					</div>
				</div>
			</div>
		</div>


		<div id='fEmployeeCodePopupDiv'
			class='manage-employeecode-popup modal fade' tabindex='-1'
			role='dialog' aria-labelledby='myEmployeeCodeModalLabel'
			aria-hidden='true'>
			<div class='modal-dialog'>
				<div class='modal-content'>
					<div class='modal-header'>
						<button id="fCloseBtn" type='button' class='btn btn-warning close'
							data-dismiss='modal'
							onclick='return closeOpenEmployeeCodePopup("false");'>&times;</button>
						<h4 class='modal-title'>
							<spring:message code="label.profilePage.employee-modal-title" />
						</h4>
					</div>
					<div id='fEmployeeCodeModelBody' class='modal-body'>
						<div class="form-group form-group-sm" id="fEmployeeCodeDiv">
							<label class="col-sm-4 control-label label-inn"> <spring:message
									code="label.profilePage.employeeCode" /></label>
							<div class="col-sm-6">
								<form:input id="fEmployeeCode" path="employeeCode"
									class="form-control" />
							</div>
						</div>
					</div>
					<div class='modal-footer'>
						<button type='button' class='btn btn-warning' data-dismiss='modal'
							onclick='return closeOpenEmployeeCodePopup("true");'>
							<spring:message code="label.profilePage.verify" />
						</button>
					</div>
				</div>
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn">&nbsp; </label> <input
				type="submit" name="" value="${btnUpdate}" class="btn btn-warning" />
			&nbsp;&nbsp; <input type="reset" name="reset" value="${btnReset}"
				id="btnReset" class="btn btn-warning"
				onclick="window.location.href='${ctx}/myProfile/showProfile'">
			&nbsp;&nbsp;
			<c:if test="${not empty message}">
				<span id="msg" class="message">${message}</span>
			</c:if>
			<input type="button" name="bHiddenBtnForBSModal"
				id="bHiddenBtnForBSModal" data-toggle="modal"
				data-target="#fOtpPopupDiv" /> <input type="button"
				name="bHiddenBtnForBSModal" id="bHiddenBtnForEmployeeCodeBSModal"
				data-toggle="modal" data-target="#fEmployeeCodePopupDiv" />
		</div>

	</form:form>
</div>


<script>
function getLocalityByState(stateValue){
	
	if(stateValue == 'Delhi'){
		$("#fLocalitySelect").val($("#fLocality").val());
		$("#fLocalitySelect").show();
		$("#fLocalitySelect").prop('disabled', false);
		$("#fLocality").prop('disabled', true);
		$("#fLocality").hide();
	}else{
		$("#fLocalitySelect").hide();
		$("#fLocalitySelect").prop('disabled', true);
		$("#fLocality").show();
		$("#fLocality").prop('disabled', false);
		$("#fLocality").val($("#fLocalitySelect").val());
	}
}
//disable enter button
$("#fEmployeeCodePopupDiv").keypress(
  function(event){
    if (event.which == '13') {
      event.preventDefault();
    }
}); 
function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(
			/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	return pattern.test(emailAddress);
};

		$(document).ready(function() {
			//$("#fLoaderGif").hide();
			$("#bHiddenBtnForBSModal").hide();
			
			$("#bHiddenBtnForEmployeeCodeBSModal").hide();
			datePick(new Array("dateOfBirth"));
		});

		function datePick(elemIds) {
			for (var i = 0; i < elemIds.length; i++) {
				$("#" + elemIds[i]).attr("readonly", true);
				$("#" + elemIds[i]).datepicker(
						{
							yearRange : "-72:+30",
							dateFormat : 'dd/mm/yy',
							showOn : "button",
							changeMonth : true,
							changeYear : true,
							buttonImage : "${appctx}"
									+ "/resources/images/index.jpeg",
							buttonImageOnly : true,
							buttonText : "",
							autoSize : true,
							showButtonPanel : true,
							closeText : 'Clear',
							beforeShow : function(input) {
								setTimeout(function() {
									var clearButton = $(input).datepicker(
											"widget").find(
											".ui-datepicker-close");
									clearButton.unbind("click").bind("click",
											function() {
												$.datepicker._clearDate(input);
											});
								}, 1);
							}
						});
			}
		}
	</script>
<script>
var isMobileNumberExist=false;
//for hiding message
function hideMsg(){
	$("#msg").hide("slow");
}

function clearImage(){
	$("#imgcontent").html("");
	$("#imageName").val('');
	$("#fileExtension").val('');
}

//validation script
function validate(){
	//for First Name
	if ($("#fFirstName").val() == "") {
		alert("First Name is required !");
		$("#fFirstName").focus();
		return false;
	}

	//for Last Name
	if ($("#fLastName").val() == "") {
		alert("Last Name is required !");
		$("#fLastName").focus();
		return false;
	}

	//for Father Name
	if ($("#fFatherName").val() == "") {
		alert("Father Name is required !");
		$("#fFatherName").focus();
		return false;
	}

	//for dob
	if ($("#dateOfBirth").val() == "") {
		alert("Date of Birth is required !");
		$("#dateOfBirth").focus();
		return false;
	}

	var dobval = $("#dateOfBirth").val();
	var dob11 = dobval.split("/");
	var dobDate = dob11[0];
	var dobMonth = dob11[1];
	var dobYear = dob11[2];
	var dobdate = new Date();
	dobdate.setFullYear(dobYear, dobMonth - 1, dobDate);
	var today = new Date();
	var currentDate_Year = new Date().getFullYear();
	var ageyeardifference = currentDate_Year - dobYear;
	if (dobdate > today) {
		alert("Date of Birth can not be greater than the Today's Date !");
		$("#dateOfBirth").focus();
		return false;
	}

	//for emailId
	if ($("#emailId").val() == "") {
		alert("Email id is required !");
		$("#emailId").focus();
		return false;
	}
	//for checkung emailaddress
	var emailaddress = $("#emailId").val();
	if (emailaddress != "") {
		if (!isValidEmailAddress(emailaddress)) {
			alert("Please enter a valid Email Id !");
			$("#emailId").focus();
			$("#emailId").select();
			return false;
		}
	}

	//for MobileNumber
	if ($("#fMobileNumber").val() == "") {
		alert("Mobile Number is required !");
		$("#fMobileNumber").focus();
		return false;
	}

	if ($("#fMobileNumber").val().length != 10) {
		alert("Please enter a valid Mobile Number !");
		$("#fMobileNumber").focus();
		return false;
	}
	
	if(("${userMaster.mobileNumber}"=="" || "${userMaster.mobileNumber}" != $("#fMobileNumber").val()) && isMobileNumberExists()){
		alert("Mobile number is already used. Please enter different mobile number!!");
		return false;
	};  
	
	//for Geneder
	if ($("#fGender").val() == "") {
		alert("Geneder is required !");
		$("#fGender").focus();
		return false;
	}
	//for Locality
	if ($("#fLocality").val() == "") {
		alert("Locality is required !");
		$("#fLocality").focus();
		return false;
	}

	//for City
	if ($("#fCity").val() == "") {
		alert("City is required !");
		$("#fCity").focus();
		return false;
	}
	//for State
	if ($("#fState").val() == "") {
		alert("State is required !");
		$("#fState").focus();
		return false;
	}

	//for Country
	if ($("#fCountry").val() == "") {
		alert("Country is required !");
		$("#fCountry").focus();
		return false;
	}
	//for pincode 
	if ($("#fPinCode").val() == "") {
		alert("PinCode is required !");
		$("#fPinCode").focus();
		return false;
	}
	//for pincode
	if ($("#fPinCode").val().length != 6) {
		alert("Please enter a valid PinCode!");
		$("#fPinCode").focus();
		return false;
	}
	//for UserType
	if ($("#fUserType").val() == "") {
		alert("UserType is required !");
		$("#fUserType").focus();
		return false;
	}
/* 	//for AdharCardNumber
	if ($("#fAdharCardNumber").val() == "") {
		alert("AdharCardNumber is required !");
		$("#fAdharCardNumber").focus();
		return false;
	}*/
	
	//for AdharCardNumber
	if ($("#fAdharCardNumber").val() != ""&& $("#fAdharCardNumber").val().length<12) {
		alert("Please enter a valid AdharCardNumber!");
		$("#fAdharCardNumber").focus();
		return false;
	}
	

	if($("#fGeoLocFlag").val() == "true" || (("${userMaster.mobileNumber}" != $("#fMobileNumber").val()) && $("#fEmployeeCodeOTPFlag").val()!="true" )){
		$( "#bHiddenBtnForBSModal" ).trigger( "click" );
	}
	//if( $("#fGeoLocFlag").val()=="true" )
	if($("#fGenerateOTPFlag").val()=="false" && ($("#fGeoLocFlag").val() == "true" || ("${userMaster.mobileNumber}" != $("#fMobileNumber").val()) && $("#fEmployeeCodeOTPFlag").val()!="true")){
		//alert("abcd validate sendOtp")
		return sendOtp();
	} 

	if($("#fGenerateOTPFlag").val()=="true" && $("#fOtp").val().length != 6){
		alert("Please enter OTP..");
		return false;
	}

	let file=$('#file')[0].files[0];
	if(file!= null){
		file_size=(file.size/1024).toFixed(2);
		if(file_size>3072) {
			alert("File size should not be greater than 3MB!");
			return false;
		} 
	}
}

function sendOtp(){
	let effUrl=getUrlForOtp();
	$.get(effUrl, function(data, status,xhr){
		  $("#fGenerateOTPFlag").val("true");
		  $("#fOtpResentBtn").prop('disabled', false);
		  if(data.response.responsecode!=1){
			  alert(data.response.responsemessage);
			  return false;
		  };
	});

	if($("#fGenerateOTPFlag").val()=="false" && $("#fOtp").val().length != 6){
		alert("Please wait otp is being sent to your mobile...");
		return false;
	}
	return false;
}

var otpCounter=1;
function resendOtp(){
	if(otpCounter==4){
		alert("can't send further otp");
		 $("#fOtpResentBtn").prop('disabled', true);
	}else{
		sendOtp();
	}
	otpCounter++;
}

function getUrlForOtp(){
	return "${baseURL}"+"/myProfile/generateOtp"+"/"+$("#fMobileNumber").val();
}

function checkUserTypeDetails(){
	//alert("abcd: "+$("#fEmployeeCode").val()+" :: "+ '${userMaster.employeeCode}');
	if($("#fUserType").val()=="ndmcEmployee"){
		$( "#bHiddenBtnForEmployeeCodeBSModal" ).trigger( "click" );
	}
}

function closeOpenEmployeeCodePopup(isButtonClicked){
	
	if(isButtonClicked=='true' && $(".manage-employeecode-popup").hasClass("in")){
		if($("#fEmployeeCode").val()!=""){
			
			//$("#fLoaderGif").show();
			
			userMasterRemoteService.checkEmployeeDetails(
					$("#fEmployeeCode").val(), {
					async : false,
					callback : fetchEmployeeDetailsCallback
				}
			);
			$(".manage-employeecode-popup").css("display", "none");
		}
	}
}
function isMobileNumberExists(){
	userMasterRemoteService.isMobileNumberExist(
			$("#fMobileNumber").val(), {
			async : false,
			callback : isMobileNumberExistCallback
		}
	);
	return isMobileNumberExist;
}
function isMobileNumberExistCallback(isMobileNumberExistFlag){
	isMobileNumberExist=isMobileNumberExistFlag;
}
function fetchEmployeeDetailsCallback(employeeVO){
	
	//$("#fLoaderGif").hide();
	
	if(employeeVO==null){
		alert("Employee Code is not found ..")
		$("#fUserType").val("");
		$("#fEmployeeCode").val("")
	}else if(employeeVO.mobileNumber !=null && employeeVO.mobileNumber !="" && $("#fEmployeeCode").val()!='${userMaster.employeeCode}'){
		// alert(employeeVO.mobileNumber);
		$("#fMobileNumber").val(employeeVO.mobileNumber);
		//$("#fMobileNumber").val("7428058043");
		//alert("fetchEmployeeDetailsCallback sendOtp")
		sendOtp();
		$("#bHiddenBtnForBSModal").trigger( "click" );
	}else{
		alert("Employee Code is already verified..")
	}
}

function isNumberKeyMobileNumber(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode< 48 || charCode >57)) {
		alert("Phone Number must be a number !");
		return false;
	}
	return true;
}

function isNumberKeyOTP(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode< 48 || charCode >57)) {
		alert("OTP must be a number !");
		return false;
	}
	return true;
}

function isNumberKeyCPincode(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode< 48 || charCode >57)) {
		alert("Pincode must be a number !");
		return false;
	}
	return true;
}

function isNumberKeyAdharCardNumber(evt) {
	var charCode = (evt.which) ? evt.which : event.keyCode;
	if (charCode > 31 && (charCode< 48 || charCode >57)) {
		alert("AdharCardNumber must be a number !");
		return false;
	}
	return true;
}
</script>
<script>

function closeOpenPopup(){
	if($(".manage-otp-popup").hasClass("in")){
		if($("#fOtp").val() != "" && $("#fOtp").val().length < 6 ){
			alert("Please enter Valid OTP")
		}
		if($("#fOtp").val() != "" && $("#fOtp").val().length == 6){
			if( $("#fEmployeeCode").val()!=""){
				$("#fEmployeeCodeOTPFlag").val("true");
			}
			$(".manage-otp-popup").removeClass("in");
			$(".manage-otp-popup").css("display", "none");
			$("#profileForm").submit();
		}
	}else{
		$(".manage-otp-popup").addClass("in");
		$(".manage-otp-popup").css("display", "block");
		$("#fOtp").focus();
	}
}

$(document).ready(function() {
	if("${verifyMobileMsg}"!=""){
		$("#fGeoLocFlag").val("true");
		alert("${verifyMobileMsg}");
	}
	$(".manage-otp-popup").css("display", "none");
	
	populateCountries("fCountry", "fState");
	
	if("${userMaster.state}"=="Delhi"){
		$("#fLocality").prop('disabled', true);
		$("#fLocality").hide();
	}else{
		$("#fLocalitySelect").prop('disabled', true);
		$("#fLocalitySelect").hide();
	}
	
	if("${userMaster.imageName}"==""){
		alert("Kindly complete your profile to proceed further");
		$("#fCountry").val("India");
		$("#fCountry").trigger("change");
	}
	$("#fOtpPopupDiv").hide();
	$("#fEmployeeCodePopupDiv").hide();
	
	$("#fOtpResentBtn").prop('disabled', true);

}); 

jQuery('.denyNumber').keyup(function () { 
	if(this.value!=="" && !isNaN(this.value)){
    	alert("Name can't be a number!")
    }
    this.value = this.value.replace(/[^a-zA-Z\s]/g,'');
});
function isMobileNubmerExist(){
	let effUrl="${baseURL}"+"/myProfile/checkMobileUniqueness"+"/"+$("#fMobileNumber").val();
	$.get(effUrl, function(data, status,xhr){
		  if(data.response.responsecode!=1){
			  alert(data.response.responsemessage);
			  return false;
		  };
	});
}
</script>


