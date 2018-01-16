<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Administration > User Management > Reset Password > Modify"/>
<!-- END BREADCRUMB -->       

<div class="row">
    <div class="col-md-12">
    	<s:if test="hasActionErrors()"><s:actionerror theme="jquery"/><br></s:if>
		<s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
	</div>
</div>

<s:form id="frm" action="DoResetAct" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
   <div class="col-md-12">
       <div class="panel panel-default">
           <div class="panel-heading">
               <h3 class="panel-title"><strong>Reset Password</strong></h3>
               <ul class="panel-controls">
                   <li></li>
               </ul>
           </div>
           <div class="panel-body">                                                                        
               <div class="form-group">
                   <label class="col-md-3 col-xs-12 control-label">User ID</label>
                   <div class="col-md-6 col-xs-12">                                            
                       <div class="input-group">
                           <span class="input-group-addon"><span class="fa fa-user"></span></span>
                           <input name="userId" type="text" class="form-control" readonly value="<s:property value="userId" />"/>
                          <s:hidden name="oldPassword" value="DUMMY" /> 
                       </div>                                            
                   </div>
               </div>
               
               <div class="form-group">                                        
                   <label class="col-md-3 col-xs-12 control-label">Name</label>
                   <div class="col-md-6 col-xs-12">
                       <div class="input-group">
                           <span class="input-group-addon"><span class="fa fa-user"></span></span>
                           <input name="userName" type="text" class="form-control" readonly value="<s:property value="userName" />"/>
                       </div>            
                   </div>
               </div>
               
               <div class="form-group">
                   <label class="col-md-3 col-xs-12 control-label">New Password</label>
                   <div class="col-md-6 col-xs-12">                                            
                       <div class="input-group">
                           <span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span>
                           <input name="newPassword" type="password" class="form-control"/>                        
                       </div>
                   </div>
               </div>
               
               <div class="form-group">
                   <label class="col-md-3 col-xs-12 control-label">New Password Confirmation</label>
                   <div class="col-md-6 col-xs-12">                                                                                            
                       <div class="input-group">
                           <span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span>
                           <input name="newPasswordConfirm" type="password" class="form-control"/>                        
                       </div>
                   </div>
               </div>
           </div>
           <div class="panel-footer">
           		<s:url var="urlgrid" action="ResetPasswordMainAction" namespace="/id/co/nds/webapp/password"/>
           		<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
           		<sj:submit id="btnSubmit" cssClass="btn btn-info" button="false" targets="content"  value="Save" onBeforeTopics="beforeSubmit" />
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