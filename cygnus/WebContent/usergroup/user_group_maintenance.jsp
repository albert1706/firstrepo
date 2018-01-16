<%@ include file="/main/taglib.jsp" %>

<jnds:breadcrumb list="Home > User Management > User Group" />
<jnds:pagetitle value="User Group" />

<div class="row">
	<div class="col-md-12">
		<s:if test="hasActionErrors()"><s:actionerror theme="jquery" /> <br></s:if>
		<s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
	</div>
</div>

<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
		<s:form id="frm" name="frm" target="content" cssClass="form-horizontal">
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Search Filters</strong></h3>

				</div>
				<div class="panel-body">        
					<s:textfield name="searchGroupName" id="searchGroupName" key="text.user.id" cssClass="form-control" label="Group Name" ></s:textfield>                                                                
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" onClickTopics="reload" button="false" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="id/co/nds/webapp/usergroup/PrepareCreateAct" />
					<sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" onCompleteTopics="complete" targets="content" indicator="indicator" button="false">New</sj:a>                                    
            	</div>
        	</div>
        </s:form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="UserGroupSearch" namespace="id/co/nds/webapp/usergroup"/>
					<sjg:grid
							id="gridtable"
							caption="User Groups Roles"
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
							sortname="group_Name"
							sortorder="asc"
							autowidth="true"
							viewrecords="true"
							>
						<sjg:gridColumn name="groupId" hidden="true" cssClass="thePointer" index="group_id" title="ID" sortable="true"/>
						<sjg:gridColumn name="groupName" index="group_Name" cssClass="thePointer" title="Name"  sortable="true" search="true" />
						<sjg:gridColumn name="groupDesc" index="group_Desc" cssClass="thePointer" title="Description" sortable="false"/>
						<sjg:gridColumn name="createBy" index="create_By" cssClass="thePointer" title="Create By" sortable="true"/>
						<sjg:gridColumn name="createDate" index="create_date" cssClass="thePointer" title="Create Date" align="right" formatter="date" sortable="true"/>
						<sjg:gridColumn name="updateBy" index="update_By" cssClass="thePointer" title="Update By" sortable="true"/>
						<sjg:gridColumn name="updateDate" index="update_date" cssClass="thePointer" title="Update Date" align="right" formatter="date" sortable="true"/>
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
  
  $("#frm").submit(function(event) {
	  $('.actionMessage').remove();
	  	var grid = $("#gridtable");
	  	grid.jqGrid('setGridParam', { search: true, postData:{searchGroupName:$("#searchGroupName").val()} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  
  var flagSubmit = false;
  
  $("#gridtable").subscribe('getDetail', function(event, data) {
	   	var grid = event.originalEvent.grid;
	    var sel_id = grid.jqGrid('getGridParam','selrow');
	    var groupId = grid.jqGrid('getCell', sel_id, 'groupId'); 
	    var actUrl= "id/co/nds/webapp/usergroup/PrepareUpdateAct.action?selectedGroup="+groupId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	    
	});
  
  
</script>