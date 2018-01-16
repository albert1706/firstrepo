<%@ include file="/main/taglib.jsp" %>

<jnds:breadcrumb list="Home > User Management > Individual User" />
<jnds:pagetitle value="Individual User" />

<div class="row">
    <div class="col-md-12">
    	<s:if test="hasActionErrors()"><s:actionerror theme="jquery"/><br></s:if>
		<s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
	</div>
</div>

<!-- END BREADCRUMB -->
       
<!-- START MAIN CONTENT -->
<div class="row">	
    <div class="col-md-12">
    	<s:form id="frm" target="content" cssClass="form-horizontal">
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Search Filter</strong></h3>
				</div>
				<div class="panel-body">
					<s:textfield id="userId" name="userId" cssClass="form-control" label="User ID" tooltip="fa fa-check"></s:textfield>
					<s:textfield id="userName" name="userName" cssClass="form-control" label="Name" tooltip="fa fa-user"></s:textfield>
                    <s:url id="remoteurlcombobox" action="UserGroupSearch" namespace="id/co/nds/webapp/usergroup"/>
					<sj:select href="%{remoteurlcombobox}" id="groupId" name="groupId"
						list="rsl" listKey="groupId" listValue="groupName"
						emptyOption="true" cssClass="form-control" label="Group" />
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" onClickTopics="reload" button="false" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="id/co/nds/webapp/user/PrepareCreateAct"/>
					<sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" targets="content" indicator="indicator" button="false">New</sj:a>                                    
            	</div>
        	</div>
        </s:form>
	</div>
</div>
<div class="row">
    <div class="col-md-12">
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="id/co/nds/webapp/user/UserSearch"/>
					<sjg:grid id="gridtable" 
						caption="User" 
						dataType="json"
						href="%{remoteurl}" 
						pager="true" 
						gridModel="rsl"
						rowList="10,15,20" 
						rowNum="10" 
						rownumbers="true" 
						resizable="false"
						shrinkToFit="false" 
						onSelectRowTopics="getDetail"
						sortname="user_Name" 
						sortorder="asc" 
						autowidth="true"
						viewrecords="true">
						<sjg:gridColumn cssClass="thePointer" name="userId" index="user_id" title="User ID" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="userName" index="user_Name" title="User Name" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="groupName" index="group_Name" title="Group" sortable="false" />
						<sjg:gridColumn cssClass="thePointer" name="createBy" index="create_By" title="Create By" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="createDate" index="create_date" title="Create Date" align="right" formatter="date" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="updateBy" index="update_By" title="Update By" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="updateDate" index="update_date" title="Update Date" align="right" formatter="date" sortable="true" />
					</sjg:grid>
			</div>
		</div>
		<!-- END SEARCH TABLE --> 
    </div>
</div>
                   	               
<!-- END MAIN CONTENT -->

<script type="text/javascript">
 $( document ).ready( 
		function() {
			$("#userId").val("");
			$("#userName").val("");		
			$("#groupId").val("");
			
		}
 )
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
	    var actUrl= "id/co/nds/webapp/user/PrepareUpdateAct.action?selectedUser="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
  
</script>