<%@ include file="/static-resources/common/taglibs.jsp"%>
<spring:message code="label.btn.save" var="btnSave" />
<spring:message code="label.btn.reset" var="btnReset" />
<title>Product Master</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/basic/styles.css">

<div class="main-gap form-horizontal user-portal">
	<form:form modelAttribute="product" method="post"
		onsubmit="return validate(this);" action="${ctx}/product"
		enctype="multipart/form-data" id="productForm">

		<input type="hidden" name="isEdit" id="fEdit" value="${isEdit}" />

		<h1 class="heading">
			<em class="fa fa-edit"></em>
			<spring:message code="label.productPage.fa-heading" />
		</h1>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.productPage.name" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fName" path="name"
					cssClass="form-control denyNumber" />
				*
				<form:errors path="name" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"> <spring:message
					code="label.productPage.code" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fCode" path="code"
					cssClass="form-control" />
				*
				<form:errors id="fErrorCode" path="code" cssClass="error" />
			</div>

		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"> <spring:message
					code="label.productPage.price" />
			</label>
			<div class="col-sm-3 required">
				<form:input id="fPrice" path="price"
					cssClass="form-control" />
				*
				<form:errors path="price" cssClass="error" />
			</div>
			<label class="col-sm-2 control-label label-inn"><spring:message
					code="label.productPage.quantity" /> </label>
			<div class="col-sm-3 required">
				<form:input id="fQuantity" path="quantity"
					cssClass="form-control" />
				*
				<form:errors path="quantity" cssClass="error" />
			</div>
		</div>
		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn"><spring:message
					code="label.productPage.photo" /> </label>
			<div class="col-sm-3">
				<form:input path="imageFile" type="file" id="file" />
			</div>
		</div>

		<div class="form-group form-group-sm">
			<label class="col-sm-3 control-label label-inn">&nbsp; </label> <input
				type="submit" name="" value="${btnSave}" class="btn btn-warning" />
			&nbsp;&nbsp; <input type="reset" name="reset" value="${btnReset}"
				id="btnReset" class="btn btn-warning"
				onclick="window.location.href='${ctx}/product'">
			&nbsp;&nbsp;
			<c:if test="${not empty message}">
				<span id="msg" class="message">${message}</span>
			</c:if>
		</div>
	</form:form>
	<br> <br>
	<c:if test="${not empty  productList}">
		<div class="form-block">
			<h1 class="heading">
				<em class="fa fa-list"></em> List of Products
			</h1>
			<div class="form-bg">
				<div class="table-responsive">
					<div style="width: 100%; height: 100%; padding: 0px 0px 10px 10px;">
						<display:table name="productList" class="list grid data-grid"
							id="list" requestURI="" excludedParams="*"
							style="width: 100%; border: 1px solid #D4D4D4; border-collapse: collapse;"
							decorator="com.chahar.indent.decorator.ProductMasterDecorator"
							sort="list" pagesize="5">
							<display:column property="srNo" title="S.No."
								style="width:50px;text-align:center;" />
							<display:column property="name" title="Name" escapeXml="true" />
							<display:column property="code" title="Code" escapeXml="true" />
							<display:column property="price" title="Price" escapeXml="true" />
							<display:column property="quantity" title="Quantity"
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
	function validate() {
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
	}

	function deleteObject(objectName, id, url, ctx) {
		if (confirm("Are you sure you want to delete this " + objectName)) {
			window.location.href = ctx + url + id;
		} else {
			return false;
		}
	}

	$(document).ready(function() {
		if ("${isEdit}" == "true") {
			$("#fErrorCode").hide();
		}
	});
</script>