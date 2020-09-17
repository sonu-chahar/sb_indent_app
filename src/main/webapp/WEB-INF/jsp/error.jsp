<%-- SiteMesh has a bug where error pages aren't decorated - hence the full HTML --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/static-resources/common/taglibs.jsp"%><%@ page
	isErrorPage="true" import="java.io.*"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>Internal server error</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/styles/base.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<a name="top"></a>
	<div id="container">
		<div id="intro">
			<div id="pagetitle">
				<h3>
					<span>Server encountered some internal error.</span>
				</h3>
				<div id="logo" onclick="location.href='<c:url value="/"/>'"
					onkeypress="location.href='<c:url value="/"/>'"></div>
			</div>

			<div id="body">
				<p>
					Server encountered some error in displaying the requested page.<br />
					<br /> You might try returning to the <a href="<c:url value="/"/>">welcome
						page</a>.
				</p>
				<hr></hr>
				<font color="red"> <%
									 	try(PrintWriter printWriter=new PrintWriter(out)) {
									 		if (exception != null) {
									 			//	print the stack trace hidden in the HTML source code for debug
									 			exception.printStackTrace(printWriter);
									 		}
									 	} catch (Exception e) {
											e.printStackTrace();
									 	}
									 %>
				</font>

			</div>
		</div>

	</div>

</body>
</html>