<%@ include file ="/main/taglib.jsp" %>


<script type="text/javascript" src='<s:url value="/js/autoNumeric.js" />'></script>
<script type="text/javascript">
jQuery(function($) {
    $('.autonumeric').autoNumeric('init', {aSign: 'Rp.', vMin: '0.00', vMax: '999999999999999999999999', mDec: '0'});
});

jQuery(function($) {
    $('.autonumeric2').autoNumeric('init', {aSign: '', vMin: '0.00', vMax: '999', mDec: '0'});
});
</script>
<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li>Aset Tetap</li>
    <li class="active">New</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Pengaturan Aset</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/asset/DoCreateAct.action" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Aset Tetap</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Nama Aset</label>
		                        <div class="col-md-6 col-xs-12">  
		                        	<input id="trxAmt" name="trxAmt" type="text" class="form-control"/>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Usia Aset</label>
		                        <div class="col-md-2 col-xs-2">
		                        	<div class="input-group">
		                        		<input id="trxAmt" name="trxAmt" type="text" maxlength="3" class="form-control autonumeric2"/>
		                        		<span class="input-group-addon add-on">Bulan</span>
		                        	</div>
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Uang Muka (Rp.)</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<input id="trxAmt" name="trxAmt" type="text" class="form-control autonumeric"/>                                 
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Tanggal Transaksi</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<div class="input-group">
			                           	<sj:datepicker id="trxDate" name="trxDate"
			                               cssClass="form-control"
			                               showOn="focus"
			                               buttonImageOnly="true" changeMonth="true" changeYear="true" />
			                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Kode Akun Uang Muka</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<s:url var="remoteurlcombobox" action="getMapping" namespace="cygnus/transaction/trx"  />
									<sj:select 
										href="%{remoteurlcombobox}" 
										id="accLv1Id" 
										name="accLv1Id" 
										list="map" 
										emptyOption="true" 
										cssClass="form-control"
										value="%{coaId}"
									/>                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Kode Akun Penyusutan</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<select id="coa2" name="coa2" class="form-control" />                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Keterangan</label>
		                        <div class="col-md-6 col-xs-12">
									<s:textarea name="description" label="Description" cssClass="form-control" placeholder="Keterangan"></s:textarea>		                        	                                            
		                        </div>
		                    </div>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgrid" action="AssetMainAction" namespace="cygnus/asset"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" onBeforeTopics="beforeSubmit" value="Save" />			                	
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">

var toaTemp = "";
var dcTemp = "";

$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
//$('#btnSubmit2').click(function(e) {
	var result = confirm(' Are you sure want to save this data?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}
	
})
</script>