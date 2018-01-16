<%@ include file="/main/taglib.jsp" %>
<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Administration > User Management > Reset Password"/>
<!-- END BREADCRUMB -->

<div class="row">
    <div class="col-md-12">
    	<s:if test="hasActionErrors()"><s:actionerror theme="jquery"/><br></s:if>
		<s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
	</div>
</div>
       
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
		<form id="frm" name="frm" class="form-horizontal">
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Reset Password</strong></h3>

				</div>
				<div class="panel-body">                                                                        
					<div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">User ID</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-user"></span></span>
                                <input id="userId" name="userId" type="text" class="form-control"/>
							</div>                                            
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">User Name</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-user"></span></span>
                                <input id="userName" name="searchUser.userName" type="text" class="form-control"/>
							</div>                                            
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">User Group</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                        		<span class="input-group-addon"><span class="fa fa-users"></span></span>
                            	<s:url id="remoteurlcombobox" action="UserGroupSearch" namespace="id/co/nds/webapp/usergroup"/>
								<sj:select 
									href="%{remoteurlcombobox}" 
									id="groupId" 
									name="echo" 
									list="rsl" 
									listKey="groupId" 
									listValue="groupName" 
									emptyOption="true" 
									cssClass="form-control"
								/>
							</div>                                            
                        </div>
                    </div>
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" onClickTopics="reload" button="false" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
            	</div>
        	</div>
        </form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="UserSearch" namespace="id/co/nds/webapp/user"/>
					<sjg:grid
							id="gridtable"
							caption="User"
							dataType="json"
							href="%{remoteurl}"
							pager="true"
							gridModel="rsl"
							rowList="10,15,20"
							rowNum="10"
							rownumbers="true"
							resizable="false"
							shrinkToFit="true"
							onSelectRowTopics="getDetail"
							sortname="user_Name"
							sortorder="asc"
							autowidth="true"
							viewrecords="true"
							resizableMaxWidth="1110"
							resizableMinWidth="1110"
							>
						<sjg:gridColumn cssClass="thePointer" name="userId" index="user_id" title="User ID" width="100" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="userName" index="user_Name" title="User Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="userDesc" index="user_Desc" width="100" title="Description" sortable="false"/>
						<sjg:gridColumn cssClass="thePointer" name="groupName" index="group_Name" width="100" title="Group" sortable="false"/>
					</sjg:grid>
	            </div>
			</div>
		<!-- END SEARCH TABLE --> 
    </div>
</div>
                   	               
<!-- END MAIN CONTENT -->

<script type="text/javascript">
  $.ajaxSetup ({  
	    cache: false  
	});
  
  $("#reset").click(function() {
		frm.reset();
});
  // since <sjg has its form, then we have to hack it.
  $("#frm").submit(function(event) {
	  $('.actionMessage').remove();
	  	var grid = $("#gridtable");
	  	grid.jqGrid('setGridParam', { search: true, postData:{
	  		userId:$("#userId").val(),
	  		groupId:$("#groupId").val(),
	  		userName:$("#userName").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  
  var flagSubmit = false;
  
  $("#gridtable").subscribe('getDetail', function(event, data) {
	   	var grid = event.originalEvent.grid;
	    var sel_id = grid.jqGrid('getGridParam','selrow');
	    var selectedId = grid.jqGrid('getCell', sel_id, 'userId'); 
	    var actUrl= "id/co/nds/webapp/password/PrepareResetAct.action?selectedUser="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
</script>