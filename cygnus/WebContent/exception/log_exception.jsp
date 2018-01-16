<%@ include file="/main/taglib.jsp" %>
<script type='text/javascript' src='<s:url value="/js/plugins/bootstrap/bootstrap-datepicker.js"/>' ></script>
<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Utilities > Exception Log" />

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
                	<h3 class="panel-title"><strong>Exception Log</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="form-group">
                    	<label class="col-md-3 col-xs-12 control-label">Error Code</label>
                        <div class="col-md-6 col-xs-12">                                            
                        	<div class="input-group">
                            	<span class="input-group-addon"><span class="fa fa-user"></span></span>
                                <input id="errorCode" name="errorCode" type="text" class="form-control"/>
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
                 
				</div>
                <div class="panel-footer">
                	<sj:submit cssClass="btn btn-primary" id="search_button" onClickTopics="reload" button="false" value="Search" />
            	</div>
        	</div>
        </s:form>
        <!-- START SEARCH TABLE -->
        	<div class="panel panel-default">
        		<div class="panel-body panel-body-table">
			        <s:url var="remoteurl" action="ExceptionSearch" namespace="id/co/nds/webapp/exception"/>
					<sjg:grid id="gridtable" 
							caption="Log Exception" 
							dataType="json" 
							href="%{remoteurl}" 
							pager="true"
							gridModel="rsl" 
							rowList="10,15,20" 
							rowNum="10" 
							rownumbers="true" 
							resizable="true" 
							shrinkToFit="true"
							onSelectRowTopics="getDetail" 
							sortname="create_date" 
							sortorder="desc" 
							autowidth="true" 
							viewrecords="true"
							onGridCompleteTopics="gridComplete" >
						<sjg:gridColumn cssClass="thePointer" name="errorCode" index="error_code"  width="100" title="Error Code" sortable="true"/>	
						<sjg:gridColumn cssClass="thePointer" name="createDate" index="create_Date" width="100" title="Exception Date" sortable="true" formatter="date" formatoptions="{srcformat:'Y-m-d H:i:s' ,newformat: 'd-m-Y   H:i:s'}"/>
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
	  		errorCode:$("#errorCode").val(),
	  		startDate:$("#startDate").val(),
	  		endDate:$("#endDate").val(),
	  		searchTrigger:true
	  	} });
	  	grid.trigger("reloadGrid",[{page: 1}]);
		event.preventDefault();
  });
  var flagSubmit = false;
  
  $("#gridtable").subscribe('getDetail', function(event, data) {
	   	var grid = event.originalEvent.grid;
	    var sel_id = grid.jqGrid('getGridParam','selrow');
	    var selectedId = grid.jqGrid('getCell', sel_id, 'errorCode'); 
	    var actUrl= "id/co/nds/webapp/exception/PrepareUpdateAct.action?selectedException="+selectedId;
	    var ajaxLoader = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    
	    if (flagSubmit == false) {
	    	$("#content").html(ajaxLoader).load(actUrl);
	    	flagSubmit= true;
	    }
	});
	
</script>