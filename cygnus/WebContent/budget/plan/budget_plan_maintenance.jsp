<%@ include file="/main/taglib.jsp" %>
<jnds:breadcrumb list="Home > Budgeting > Pendaftaran Budget" />
<jnds:pagetitle value="Pendaftaran Budget" />

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
                	<h3 class="panel-title"><strong>Search Filter</strong></h3>

				</div>
				<div class="panel-body">
					<s:textfield name="coaName" cssClass="form-control" placeholder="Nama COA" label="Nama COA" tooltip="fa fa-pencil"></s:textfield>
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="PrepareCreateAct" namespace="cygnus/budget/plan/budget_plan/budget_plan"/>
                	<%-- <sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" targets="content" indicator="indicator" button="false">New Bdugeting Period</sj:a> --%>
            	</div>
        	</div>
        </form>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" namespace="cygnus/budget/plan/budget_plan" action="getListCoa" />
                	<s:url var="editUrl" action="DoCreate" namespace="cygnus/budget/plan/budget_plan"/>
					<sjg:grid
						id="gridtable"
						caption="List Budget"
						dataType="json"
						href="%{remoteurl}"
						pager="true"
						toppager="true"
						gridModel="rsl"
						rowList="10,15,20"
						rowNum="10"
						rownumbers="true"
						resizable="false"
						shrinkToFit="true"
						onSelectRowTopics="fireEditNav"
						sortname="coa_id"
						sortorder="asc"
						autowidth="true"
						viewrecords="true"
						resizableMaxWidth="1000"
						resizableMinWidth="1000"
						editurl="%{editUrl}"
						navigatorEdit="true"
						>
						<sjg:gridColumn cssClass="thePointer" name="coaId" index="coa_id" title="COA ID" width="50" sortable="true"  editable="true" edittype="text" editoptions="{readonly:true,size:40}"/>
						<sjg:gridColumn cssClass="thePointer" name="coaName" index="coa_name" title="COA Name" width="100" sortable="true"  editable="true" edittype="text" editoptions="{readonly:true,size:40}"/>	
						<sjg:gridColumn cssClass="thePointer" name="segmentName" index="segment_name" title="Komisi" width="60" sortable="true" search="true"  editable="true" edittype="text" editoptions="{readonly:true,size:40}" />
						<sjg:gridColumn cssClass="thePointer" name="budgetAmt" index="budget_amt" title="Budget" width="50" align="right" sortable="false" formatter="currency" editable="true" editoptions="{size:40}" />
						<sjg:gridColumn cssClass="thePointer" name="createBy" index="create_By" width="60" title="Create By" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="createDate" index="create_date" width="60" title="Create Date" align="right" formatter="date" sortable="true"/>
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
	  		coaName:$("#coaName").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  
  var flagSubmit = false;
  $("#gridtable").subscribe('fireEditNav', function(event, data) {
	  	var grid = event.originalEvent.grid;
	    var rowid = grid.jqGrid('getGridParam','selrow');
	    grid.jqGrid('editGridRow', rowid, {
	    	beforeShowForm: 
	    	function (formid) {
            	$("#pData").hide();
            	$("#nData").hide();}, 
            recreateForm: true, 
            navkeys:false,
            height:175,
            width:330,
            reloadAfterSubmit:false,
            closeAfterEdit:true 
         });
  });
  
</script>