<%@ include file ="/main/taglib.jsp" %>

<jnds:breadcrumb list="Home > User Management > Individual User > Modify" />
<jnds:pagetitle value="Individual  User" />

<s:form id="frm" action="id/co/nds/webapp/user/DoUpdateAct.action" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Modify Individual User</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:textfield name="userId" readonly="true" cssClass="form-control" placeholder="Authentication ID" label="User ID" tooltip="fa fa-user" value="%{user.userId}"></s:textfield>
							<s:textfield name="userName" cssClass="form-control" placeholder="Name Of The User" label="Name" tooltip="fa fa-user" value="%{user.userName}"></s:textfield>
							<s:url id="remoteurlcombobox" action="UserGroupSearch" namespace="id/co/nds/webapp/usergroup"/>
							<sj:select 
								href="%{remoteurlcombobox}" 
								id="groupId" 
								name="groupId" 
								list="rsl" 
								listKey="groupId" 
								listValue="groupName" 
								emptyOption="true"
								cssClass="form-control" 
								label="Group"
								value="%{user.groupId}"
							/>
                             <s:label label="Password" cssStyle="color: red;" cssClass="control-label" value="**********"/>
                             <s:textarea name="userDesc" label="Description" cssClass="form-control" placeholder="Keterangan" value="%{user.userDesc}"></s:textarea>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	
                	<s:url var="urlgrid" action="UserMainAction" namespace="/id/co/nds/webapp/user"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" targets="content" cssClass="btn btn-info" value="Save" onBeforeTopics="beforeSubmit" />
					<s:url var="urlDelete" action="DoDeleteAct" namespace="/id/co/nds/webapp/user"/>
					<sj:a id="btnDelete" formIds="frm" onClickTopics="before" cssClass="btn btn-danger" targets="content" button="false">Delete</sj:a>
                	
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
	var result = confirm(' Are you sure want to save this user?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}	
});

$("#btnDelete").subscribe('before', function(event, data) {
	var result = confirm(' Are you sure want to delete this user?');
	if (result == true ) {
		$('#frm').attr('action', 'id/co/nds/webapp/user/DoDeleteAct.action');	
	}
	
	return result; 		
});
</script>