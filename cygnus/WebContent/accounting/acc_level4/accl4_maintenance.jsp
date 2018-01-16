<%@ include file="/main/taglib.jsp" %>
<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a href="#">Home</a></li>
    <li><a href="#">Akunting</a></li>
    <li><a href="#">Pengaturan Akun</a></li>
    <li class="active">Akun Level 4</li>
</ul>
<!-- END BREADCRUMB -->

<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Account Level 4</h2>
</div>

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
					<s:textfield name="accLv4Name" cssClass="form-control" placeholder="Nama Akun" label="Nama Akun" tooltip="fa fa-pencil"></s:textfield>                                                                  
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" value="Search" />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
                	<s:url var="urlgrid" action="PrepareCreateAct" namespace="cygnus/acc/acc_level4"  />
					<sj:a id="new_button" href="%{urlgrid}" cssClass="btn btn-info" targets="content" indicator="indicator" button="false">New</sj:a>                                    
            	</div>
        	</div>
        </form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
                	<s:url var="remoteurl" action="AccountLevel4SearchAction" namespace="cygnus/acc/acc_level4"  />
					<sjg:grid
						id="gridtable"
						caption="Account Level 4"
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
						sortname="acc_lv4_id"
						sortorder="asc"
						autowidth="true"
						viewrecords="true"
						resizableMaxWidth="1110"
						resizableMinWidth="1110"
						groupField="['accLv1Name', 'accLv2Name', 'accLv3Name']"
						groupColumnShow="[true, true, true]"
						groupSummary="[true, true, true]"
						groupCollapse="false"
						>
						<sjg:gridColumn cssClass="thePointer" hidden="true" name="id" index="id" title="ID" width="100" sortable="true"/>
						<sjg:gridColumn cssClass="thePointer" name="accLv4Id" index="acc_lv3_id" title="Akun L4 ID" width="100" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="accLv4Name" index="acc_lv3_name" title="Akun L4 Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="accLv3Id" index="acc_lv3_id" title="Akun L3 ID" width="100" hidden="true" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="accLv3Name" index="acc_lv3_name" title="Akun L3 Name" width="100" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="accLv2Id" index="acc_lv2_id" title="Akun L2 Name" width="100" hidden="true" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="accLv1Id" index="acc_lv1_id" title="Akun L1 Name" width="100" hidden="true" sortable="true" search="true" />
						<sjg:gridColumn cssClass="thePointer" name="accLv2Name" index="acc_lv2_name" title="Akun L2 Name" width="100" sortable="true" search="true" />
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
	  		accLv4Name:$("#accLv4Name").val(),
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
	    var actUrl= "cygnus/acc/acc_level4/PrepareUpdateAct.action?id="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
</script>