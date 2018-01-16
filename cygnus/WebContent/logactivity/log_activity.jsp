<%@ include file="/main/taglib.jsp" %>
<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Utilities > Activity Log" />

<!-- END BREADCRUMB -->
<div class="row">
	<div class="col-md-12">
		<s:if test="hasActionErrors()"><s:actionerror theme="jquery" /> <br></s:if>
		<s:if test="hasActionMessages()"><s:actionerror theme="jquery" /><br></s:if>
	</div>
</div>
       
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<s:form id="frm" target="content" cssClass="form-horizontal" theme="simple" >
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Activity Log</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">User ID</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-user"></span></span>
                                <input id="logBy" name="logBy" type="text" class="form-control"/>
							</div>                                            
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">Module</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-cogs"></span></span>
                                <s:url id="remoteurlcombobox" action="MenuList" namespace="id/co/nds/webapp/log"/>
								<sj:select 
									href="%{remoteurlcombobox}" 
									id="moduleId" 
									name="moduleId" 
									list="menu" 
									listKey="moduleId" 
									listValue="moduleName" 
									emptyOption="true" 
									cssClass="form-control"
								/>
							</div>                                            
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">Log Date</label>
                        <div class="col-md-3 col-xs-12 ">                                            
                        	<div class="input-group">
                            	<sj:datepicker id="startDate" name="startDate"
                                cssClass="form-control"
                                showOn="focus"
                                buttonImageOnly="true" changeMonth="true" changeYear="true" />
                                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
                        </div>
                        <div class="col-md-3 col-xs-12 ">
                        	<div class="input-group">
								<sj:datepicker cssClass="form-control"
                                showOn="focus"
                                id="endDate" name="endDate" showAnim="fadeIn" buttonImageOnly="false" changeMonth="true" changeYear="true"  />
                                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">Object Id</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-leaf"></span></span>
                            	<input id="objectId" name="objectId" type="text" class="form-control"/>
							</div>                                            
                        </div>
                    </div>
                    
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" onClickTopics="reload" button="false" value="Search" />
            	</div>
        	</div>
        </s:form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
			        <s:url var="remoteurl" action="ActivitySearch" namespace="id/co/nds/webapp/log"/>
					<sjg:grid
							id="gridtable"
							caption="Log Activity"
							dataType="json"
							href="%{remoteurl}"
							pager="true"
							gridModel="rsl"
							rowList="10,15,20"
							rowNum="10"
							rownumbers="true"
							resizable="true"
							shrinkToFit="true"
							sortname="log_date"
							sortorder="desc"
							autowidth="true"
							viewrecords="true"
							onGridCompleteTopics="gridComplete"
							>
						<sjg:gridColumn name="logBy" 		index="log_by" title="Log By" width="100" sortable="true"/>	
						<sjg:gridColumn name="moduleId" 	index="module_id" title="Module" width="100" sortable="true" search="true" />
						<sjg:gridColumn name="logDate" 		index="log_date" width="100" title="Log Date" sortable="true" formatter="date" formatoptions="{srcformat:'Y-m-d H:i:s' ,newformat: 'd-m-Y   H:i:s'}"/>
						<sjg:gridColumn name="description" 	index="description" width="100" title="Description" sortable="false"/>
						<sjg:gridColumn name="objectId" 	index="object_id" width="60" title="Object" sortable="true"/>
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
  
  $("#gridtable").subscribe('gridComplete', function(event, data) {
	  
  })
  
  $("#reset").click(function() {
		frm.reset();
});
  
  // since <sjg has its form, then we have to hack it.
  $("#frm").submit(function(event) {
	  $('.actionMessage').remove();
	  	var grid = $("#gridtable");
	  	grid.jqGrid('setGridParam', { search: true, postData:{
	  		logBy:$("#logBy").val(),
	  		moduleId:$("#moduleId").val(),
	  		objectId:$("#objectId").val(),
	  		startDate:$("#startDate").val(),
	  		endDate:$("#endDate").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
</script>