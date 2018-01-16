<%@ include file="/main/taglib.jsp" %>
<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Akunting > Pengaturan Akun > Akun Level 2" />
<jnds:pagetitle value="Account Level 2" />


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
					<s:textfield name="accLv2Name" cssClass="form-control" placeholder="Nama Akun" label="Nama Akun" tooltip="fa fa-pencil"></s:textfield>                                                                  
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="PrepareCreateAct" namespace="cygnus/acc/acc_level2"  />
					<sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" targets="content" indicator="indicator" button="false">New</sj:a>                                    
            	</div>
        	</div>
        </form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="AccountLevel2SearchAction" namespace="cygnus/acc/acc_level2"  />
					<sjg:grid
						id="gridtable"
						caption="Account Level 2"
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
						sortname="acc_lv1_id"
						sortorder="asc"
						autowidth="true"
						viewrecords="true"
						resizableMaxWidth="1110"
						resizableMinWidth="1110"
						groupField="['accLv1Name']"
						groupColumnShow="[true]"
						groupSummary="[true]"
						groupCollapse="false"
						>
						<sjg:gridColumn cssClass="thePointer" hidden="true" name="id" index="id" title="ID" width="70" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="accLv2Id" index="acc_lv2_id" title="Akun L2 ID" width="50" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="accLv2Name" index="acc_lv2_name" title="Akun L2 Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="heading" index="heading" width="40" title="Heading" sortable="true" />
						<sjg:gridColumn cssClass="thePointer" name="accLv1Name" index="acc_lv1_name" title="Akun L1 Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="createBy" index="create_By" width="60" title="Create By" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="createDate" index="create_date" width="60" title="Create Date" align="right" formatter="date" sortable="true"/>
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
	  		accLv2Name:$("#accLv2Name").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  
  var flagSubmit = false;
  
  $("#gridtable").subscribe('getDetail', function(event, data) {
	   	var grid = event.originalEvent.grid;
	    var sel_id = grid.jqGrid('getGridParam','selrow');
	    var selectedId = grid.jqGrid('getCell', sel_id, 'id'); 
	    var actUrl= "cygnus/acc/acc_level2/PrepareUpdateAct.action?id="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
</script>