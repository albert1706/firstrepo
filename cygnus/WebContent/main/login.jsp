<!DOCTYPE html>
<html lang="en" class="body-full-height">
<head>

<title>GKI Samanhudi</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<%@ include file="taglib.jsp" %>
<%@ include file="meta-header.jsp"%>
</head>

<body>
	<div class="login-container lightmode">

		<div class="login-box animated fadeInDown">
			<div class="login-logo"></div>
			<div class="login-body">
				<div class="login-title">
					<strong>Log In</strong> to your account
				</div>
				<form action="LoginAction.action" class="form-horizontal" method="post">
				<s:if test="hasActionErrors()"><s:actionerror theme="jquery"/><br></s:if>
				<s:token/>

					<div class="form-group">
						<div class="col-md-12">
							<input class="form-control" placeholder="User ID" id="userId" name="userId" type="text">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<input class="form-control" placeholder="Password" id="userPassword" autocomplete="off" name="userPassword" value="" onblur="" type="password">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<button class="btn btn-info btn-block">Log In</button>
						</div>
					</div>
				</form>
			</div>
			<div class="login-footer">
				<div class="pull-left">&copy; 2015 Grace</div>
				<div class="pull-right"></div>
			</div>
		</div>

	</div>

</body>
</html>
