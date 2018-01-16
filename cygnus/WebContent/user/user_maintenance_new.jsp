<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > User Management > Individual User > New" />
<jnds:pagetitle value="Individual User" />

<s:form id="frm" action="id/co/nds/webapp/user/DoCreateAct" target="content" validate="true" method="post" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>New Individual User</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">
						<div class="col-md-12">
							<s:textfield name="userId" cssClass="form-control" placeholder="Authentication ID" label="User ID" tooltip="fa fa-rss"></s:textfield>
							<s:textfield name="userName" cssClass="form-control" placeholder="Name Of The User" label="Name" tooltip="fa fa-user"></s:textfield>
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
							/>
                             <s:label label="Password" cssStyle="color: red;" cssClass="control-label" value="Default password sama dengan User ID"/>
                             <s:textarea name="userDesc" label="Description" cssClass="form-control" placeholder="Keterangan"></s:textarea>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	
                	<s:url var="urlgrid" action="id/co/nds/webapp/user/UserMainAction"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" value="Save" onBeforeTopics="beforeSubmit" />
                	
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
</script>