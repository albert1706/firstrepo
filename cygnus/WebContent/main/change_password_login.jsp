<!DOCTYPE html>
<html lang="en">
    <head>        
        <!-- META SECTION -->
        <title>NDS - Nusantara Duta Solusindo</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <!-- END META SECTION -->

		<%@ include file ="taglib.jsp" %>
		<sj:head />
		<%@ include file="meta-header.jsp"%>
        
    </head>
    <body>
        <div class=" _mCS_1 mCS-autoHide mCS_no_scrollbar">
                <!-- START X-NAVIGATION -->
                <ul class="x-navigation">
                    <li class="xn-logo">
                        <a href="#">NDS - Nusantara Duta Solusindo</a>
                    </li>
                </ul>
                <!-- END X-NAVIGATION -->
            </div>
            	<s:actionmessage />
				<s:actionerror  />
        		<div class="page-content-wrap">
                	<!-- MAIN FORM -->
                    <div class="row">
                        <div class="col-md-12">
                            <s:form id="validate" name="validate" action="DoChangeLoginAct" cssClass="form-horizontal">
                            <s:hidden name="fromLogin" />
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title"><strong>Change Password</strong></h3>
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
                                        <label class="col-md-3 col-xs-12 control-label">Old Password</label>
                                        <div class="col-md-6 col-xs-12">
                                            <div class="input-group">
                                                <span class="input-group-addon"><span class="fa fa-unlock-alt"></span></span>
                                                <input name="oldPassword" type="password" class="form-control"/>                        
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
									<sj:submit id="btnSubmit" button="true" cssClass="btn btn-info" value="Save" onBeforeTopics="beforeSubmit" />         
                                </div>
                            </div>
                            </s:form>
                            
                        </div>
                    </div>                    
                    <!-- END MAIN FORM -->
                </div>
        
    </body>
    
<script type="text/javascript">
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
	var result = confirm(' Are you sure want to save this password?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}	
});
</script>    
    
</html>

