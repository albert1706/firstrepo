<%@ include file ="/main/taglib.jsp" %>
<%@ taglib prefix="sjm" uri="/struts-jquery-mobile-tags"%>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Report</a></li>
    <li class="active">Journal</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Report Journal</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/report/journal/DoDownloadReport.action" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Filter Pencarian</strong></h3>
				</div>				
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Periode</label>
		                        <div class="col-md-3 col-xs-12 ">                                            
		                        	<div class="input-group">
		                            	<sj:datepicker id="periodStart" name="periodStart"
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
		                                id="periodEnd" name="periodEnd" showAnim="fadeIn" buttonImageOnly="false" changeMonth="true" changeYear="true"  />
		                                <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Jenis Transaksi</label>
		                        <div class="col-md-6 col-xs-12">  
		                        	<sj:checkboxlist list="listTrx" name="transaction" listKey="value1Param" listValue="value2Param" />
		                        </div>
		                    </div>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
					<s:submit id="btnSubmit" button="false" cssClass="btn btn-info" value="Tampilkan Report" />			                	
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">

$("#reset").click(function() {
	frm.reset();
});

</script>