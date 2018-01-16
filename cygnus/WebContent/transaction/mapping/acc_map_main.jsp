<%@ include file="/main/taglib.jsp" %>
<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Akunting > Akun Mapping" />
<jnds:pagetitle value="Akun Mapping" />


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
					<s:textfield name="classCode" cssClass="form-control" placeholder="Class Code" label="Class Code" tooltip="fa fa-pencil"></s:textfield>      
					<s:textfield name="trxName" cssClass="form-control" placeholder="Nama Mapping" label="Nama Mapping" tooltip="fa fa-pencil"></s:textfield>                                                                  
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="PrepareCreateAct" namespace="cygnus/transaction/trx"  />
					<sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" targets="content" indicator="indicator" button="false">New</sj:a>                                    
            	</div>
        	</div>
        </form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="TransactionMappingSearchAction" namespace="cygnus/transaction/trx"  />
					<sjg:grid
						id="gridtable"
						caption="Mapping Akun - Transaksi"
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
						onSelectRowTopics="getDetail"
						sortname="trx_name"
						sortorder="asc"
						autowidth="true"
						viewrecords="true"
						resizableMaxWidth="1110"
						resizableMinWidth="1110"
						>
						<sjg:gridColumn cssClass="thePointer" hidden="true" name="trx" index="trx" title="ID" width="100" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="classCode" index="class_code" title="Class Code" width="50" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="trxName" index="trx_name" title="Mapping Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="updateBy" index="update_By" width="60" title="Update By" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="updateDate" index="update_date" width="60" title="Update Date" align="right" formatter="date" sortable="true"/>
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
	  		classCode:$("#classCode").val(),
	  		trxName:$("#trxName").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  
  var flagSubmit = false;
  
  $("#gridtable").subscribe('getDetail', function(event, data) {
	   	var grid = event.originalEvent.grid;
	    var sel_id = grid.jqGrid('getGridParam','selrow');
	    var selectedId = grid.jqGrid('getCell', sel_id, 'trx'); 
	    var actUrl= "cygnus/transaction/trx/PrepareUpdateAct.action?trx="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
</script>