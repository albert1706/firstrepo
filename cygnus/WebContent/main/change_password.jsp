<%@ include file="/main/taglib.jsp"%>

<jnds:breadcrumb list="Home > Administration > User Management > Change Password" />

<div class="row">
	<div class="col-md-12">
		<s:if test="hasActionErrors()"><s:actionerror theme="jquery" /> <br></s:if>
		<s:if test="hasActionMessages()"><s:actionerror theme="jquery" /><br></s:if>
	</div>
</div>

<s:form id="frm" action="id/co/nds/webapp/password/DoChangeAct.action" target="content" cssClass="form-horizontal">
	<!-- START MAIN CONTENT -->
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><strong>Change Password</strong></h3>
				</div>
				<div class="panel-body">
				
					<s:textfield name="userId" cssClass="form-control" label="User ID" tooltip="fa fa-user"/>
					<s:textfield name="userName" cssClass="form-control" label="Name" tooltip="fa fa-user"/>
					<s:textfield name="oldPassword" cssClass="form-control" label="Password" type="password" tooltip="fa fa-unlock-alt"/>
					<s:textfield name="newPassword" cssClass="form-control" label="New Password" type="password" tooltip="fa fa-unlock-alt"/>
					<s:textfield name="newPasswordConfirm" cssClass="form-control" label="New Password Confirmation" type="password" tooltip="fa fa-unlock-alt"/>

				</div>
				<div class="panel-footer">
					<sj:submit id="btnSubmit" cssClass="btn btn-info" button="false" targets="content" value="Save" onBeforeTopics="beforeSubmit" />
				</div>
			</div>
		</div>
	</div>
	<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
	var result = confirm(' Are you sure want to save this password?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}	
});
</script>