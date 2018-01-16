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
							<s:textfield id="userId" name="userId" cssClass="form-control" placeholder="Authentication ID" label="User ID" tooltip="fa fa-rss"></s:textfield>
							<s:textfield id="userName" name="userName" cssClass="form-control" placeholder="Name Of The User" label="Name" tooltip="fa fa-user"></s:textfield>
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
					<sj:a id="btnAdd" cssClass="btn btn-default" button="false">Add</sj:a>
            	</div>
        	</div>
    </div>
</div>
</s:form>

<div class="row">
    <div class="col-md-12">
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="id/co/nds/webapp/user/testJq"/>
					<sjg:grid id="gridtable" 
						caption="User" 
						dataType="json"
						href="%{remoteurl}" 
						pager="false" 
						gridModel="rsl"
						resizable="false"
						shrinkToFit="true" 
						onSelectRowTopics="getDetail"
						onCellSelectTopics="cellclicked"
						sortname="user_Name" 
						sortorder="asc" 
						autowidth="true"
						viewrecords="true">
						<sjg:gridColumn cssClass="thePointer" name="userId" index="user_id" title="User ID" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="userName" index="user_Name" title="User Name" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="groupId" index="group_id" title="Group ID" sortable="false" />
						<sjg:gridColumn cssClass="thePointer" name="groupName" index="group_Name" title="Group Name" sortable="false" />
						<sjg:gridColumn cssClass="thePointer" name="edit" title="" width="20" formatter="function(){ 
                          return test();}" />
					</sjg:grid>
			</div>
		</div>
		<!-- END SEARCH TABLE --> 
    </div>
</div>
                   	               
<!-- END MAIN CONTENT -->

<script type="text/javascript">
function test () {
	return "<span class='ui-icon ui-icon-pencil'></span>";
}
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
  $("#btnAdd").click(function() {
	  	var grid = $("#gridtable");
	  	grid.jqGrid('setGridParam', { search: true, postData:{
	  		userId:$("#userId").val(),
	  		groupId:$("#groupId").val(),
	  		userName:$("#userName").val(), 
	  		groupName:$("#groupId option:selected").text()
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
  });
  
  var flagSubmit = false;
  
  $("#gridtable").subscribe('cellclicked', function(rowid, e) {
	  alert(JSON.stringify($(e.target), null, 2));
	  var $self = $(this),
      $td = $(rowid.target).closest("td");
      iCol = $.jgrid.getCellIndex($(rowid.target).closest($td[0]));
	  
	  var grid = $("#gridtable");
	  var colModel = grid.jqGrid("getGridParam", "colModel");
      var columnName = colModel[1].name;
      alert(columnName);
	});
  
  
</script>