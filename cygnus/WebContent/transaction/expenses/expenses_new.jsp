<%@ include file ="/main/taglib.jsp" %>

<script type="text/javascript" src='<s:url value="/js/autoNumeric.js" />'></script>
<script type="text/javascript">
jQuery(function($) {
    $('.autonumeric').autoNumeric('init', {aSign: 'Rp.', vMin: '0.00', vMax: '999999999999999999999999', mDec: '0'});
});
</script>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Transaksi</a></li>
    <li class="active">Pengeluaran</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Input Pengeluaran</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/transaction/expenses/DoTransaction.action" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Pengeluaran</strong></h3>
				</div>				
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Jenis Pengeluaran</label>
		                        <div class="col-md-6 col-xs-12">                                            
		                        	<s:select 
										list="#{'04cash_disburse':'Pengeluaran Permintaan Dana', 
												'06salary_allowance':'Pengeluaran Gaji & Tunjangan', 
												'07regular_cost':'Pengeluaran Biaya Rutin',
												'08overhead_cost':'Pengeluaran Biaya Overhead'}"
										onchange="loadSelect(this.value);"
										emptyOption="false"
										id="trxType" 
										name="trxType" 
										cssClass="form-control"
										value="%{trxType}" />
									<s:hidden name="trxName" id="trxName"></s:hidden>                                           
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Peruntukan</label>
		                        <div class="col-md-6 col-xs-12">  
		                        	<select id="coa1" name="coa1" class="form-control" />
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Sumber Dana</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<select id="coa2" name="coa2" class="form-control" />                                            
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
		                    	<label class="col-md-3 col-xs-12 control-label">Tanggal Efektif Warkat/Cek/BG</label>
		                        <div class="col-md-6 col-xs-12">
		                        	<div class="input-group">
			                           	<sj:datepicker id="warkatDate" name="warkatDate"
			                               cssClass="form-control"
			                               showOn="focus"
			                               buttonImageOnly="true" changeMonth="true" changeYear="true" />
			                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
									</div>                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Nama Bank Penerima</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="beneBankName" name="beneBankName" type="text" class="form-control"/>		                        	                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">No Rekening Penerima</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="beneAccNo" name="beneAccNo" type="text" class="form-control"/>		                        	                                            
		                        </div>
		                    </div>
		                     <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Nama Penerima</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="beneName" name="beneName" type="text" class="form-control"/>		                        	                                            
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Nominal</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="trxAmt" name="trxAmt" type="text" class="form-control autonumeric"/>		                        	                                            
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
                	<sj:a id="reset" button="false" cssClass="btn btn-default" >Reset</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" onBeforeTopics="beforeSubmit" value="Save" />			                	
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

  var caraBayar = $("#trxType").val();
  loadSelect(caraBayar);
  
  function loadSelect(type)
  {
	  $("#trxName").val($("#trxType option:selected").text());
	  
      if(type===null || type===""||type===undefined)
      {
          alert("Silahkan mengisi informasi Cara Pembayaran Persembahan.");
          return;
      }
      
      var url = "cygnus/transaction/trx/getMapping.action";
      var params = "trxType="+type;
      doAjax(url, params, function(jsonObject) {
    	  
    	  var jsonDebet = {};
          var jsonCredit = {};
          $.each(jsonObject, function(key, value) {
    		if (value.dc == 'd') {
    			jsonDebet[key] = value.coaName;
        	} else {
        		jsonCredit[key] = value.coaName;
        	}
          }); 
          // var print = JSON.stringify(jsonObject, null, 2);
          // alert (print);
          populateToCombo(jsonDebet, "coa1");
          populateToCombo(jsonCredit, "coa2");
      });
  }
  
  
  $("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
		var result = confirm(' Apakah anda yakin dengan data transaksi ini?');
		if (result == false) {
			e.originalEvent.preventDefault();
		}
	})
</script>