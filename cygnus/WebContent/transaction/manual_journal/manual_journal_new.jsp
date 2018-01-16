<%@ include file ="/main/taglib.jsp" %>

<script type="text/javascript" src='<s:url value="/js/select2.min.js"/>' ></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/select2.min.css" />" />
<script type="text/javascript" src='<s:url value="/js/autoNumeric.js" />'></script>
<script type="text/javascript">
jQuery(function($) {
    $('.autonumeric').autoNumeric('init', {aSign: '', vMin: '0.00', vMax: '999999999999999999999999', mDec: '0'});
});
$("#account").select2();
</script>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Transaction</a></li>
    <li>Manual Journal</li>
    <li class="active">New</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Manual Journal</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/transaction/manual_journal/DoTransaction.action" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Informasi Transaksi</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Transaksi</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="trxName" name="trxName" type="text" placeholder="Nama Transaksi" class="form-control"/>		                        	                                            
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
		                    	<label class="col-md-3 col-xs-12 control-label">Keterangan</label>
		                        <div class="col-md-6 col-xs-12">
									<s:textarea name="description" label="Description" cssClass="form-control" placeholder="Keterangan"></s:textarea>		                        	                                            
		                        </div>
		                    </div>
						</div>   
                    </div>
				</div>
        	</div>
    </div>
    
    <div class="col-md-12">
		<div class="panel panel-default">
           	<div class="panel-heading">
               	<h3 class="panel-title"><strong>Debet/Kredit</strong></h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="panel-body">
						<div class="col-md-12">
							<div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Akun</label>
		                        <div class="col-md-6 col-xs-12">                                            
		                        	<s:select id="account" name="account" key="COA" list="listAcc" listKey="coaId" listValue="coaName" cssClass="form-control" />
									<s:hidden name="trxAmt" id="trxAmt"></s:hidden>
									<s:hidden name="trxType" id="trxType" value="00manual_journal"></s:hidden>
									                                    
		                        </div>
		                    </div>
		                    <div class="form-group">
		                    	<label class="col-md-3 col-xs-12 control-label">Amount</label>
		                        <div class="col-md-6 col-xs-12">
									<input id="accValueAmt" name="accValueAmt" type="text" class="form-control autonumeric"/>		                        	                                            
		                        </div>
		                    </div>
						</div>
					</div>
					<div class="panel-footer" style="background-color:#1caf9a">
							<button id="addDebet" name="addDebet" class="btn btn-info">DEBET</button>
							<button id="addKredit" name="addKredit" class="btn btn-info pull-right">KREDIT</button>
		           	</div>  
				</div>
				<br>
				<div class="row">  
					<div class="col-md-6">
				    	<div class="panel panel-warning">
				            <div class="panel-heading">
				                <h3 class="pull-left">Debet</h3>
				                <h2 class="pull-right">Total : <span id="spnDebet">Rp.0</span></h2>
				                <s:hidden id="selectedDebet" name="selectedDebet" />
				            </div>
				            <div class="panel-body">
				                <select id="debet" name="debet" multiple class="form-control" />
				            </div>     
				            <div class="panel-footer">
				                <button id="btnRemoveDebet" name="btnRemoveDebet" class="btn btn-danger button">Remove</button>
				            </div>                            
				        </div>  
				    </div>
				    <div class="col-md-6">
				    	<div class="panel panel-info">
				             <div class="panel-heading">
				                 <h3 class="pull-left">Kredit</h3>
				                <h2 class="pull-right">Total : <span id="spnCredit">Rp.0</span></h2>
				                <s:hidden id="selectedCredit" name="selectedCredit" />
				             </div>
				             <div class="panel-body">
				                 <select id="kredit" name="kredit" multiple class="form-control" />
				             </div>                            
				             <div class="panel-footer">
				                 <button id="btnRemoveKredit" name="btnRemoveKredit" class="btn btn-danger button">Remove</button>
				             </div>                              
				         </div>
				    </div> 
				    <h2 class="pull-left">Balance : <span id="spnBalance">Rp.0</span></h2>
                 </div>
			</div>
               <div class="panel-footer">
               	<s:token />
               	<s:url var="urlgrid" action="AccountMappingMainAction" namespace="cygnus/acc/mapping"/>
				<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
				<sj:submit id="btnSubmit" name="btnSubmit" button="false" cssClass="btn btn-info" targets="content" onBeforeTopics="beforeSubmit" value="Save" />			                	
           	</div>
       	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>

<style>
.button {
    display:block;
    margin: 0 auto;
}
</style>


<script type="text/javascript">

var debetAmount = 0;
var creditAmount = 0;
var balanceAmount = 0;

$( "#addKredit" ).click(function() {

	// get the value 
	var amount = $("#accValueAmt").val().replaceAll(",", "");
	if (amount === null || amount === "") amount = 0;
	var selectedVal = $("#account").val();
	var selectedText = $("#account option:selected").text();

	// validate selected
	var existsCredit = $("#kredit option[value='" + selectedVal + "']").length > 0;
	var existsDebet = $("#debet option[value='" + selectedVal + "']").length > 0;
	if(existsDebet) {
		alert('Akun sudah terpilih di kolom Debet, mohon untuk menghapus dari kolom Debet terlebih dahulu')
	} else {
		if (!existsCredit) {
			// push it to combo
			var pair = {};
			pair[selectedVal] = selectedText + " ( " + formatMoney(+amount, "Rp.", 0) + " )";
			pushToCombo(pair, "kredit");
			
			// modify span amount
			creditAmount = +creditAmount + +amount;
			balanceAmount =+debetAmount - +creditAmount; 
			$("#spnCredit").text(formatMoney(creditAmount, "Rp.", 0));
			$("#spnBalance").text(formatMoney(balanceAmount, "Rp.", 0));
		}
	}
});


$( "#addDebet" ).click(function() {
	// get the value 
	var amount = $("#accValueAmt").val().replaceAll(",", "");
	if (amount === null || amount === "") amount = 0;
	var selectedVal = $("#account").val();
	var selectedText = $("#account option:selected").text();
	
	// validate selected
	var existsCredit = $("#kredit option[value='" + selectedVal + "']").length > 0;
	var existsDebet = $("#debet option[value='" + selectedVal + "']").length > 0;
	if(existsCredit) {
		alert('Akun sudah terpilih di kolom Kredit, mohon untuk menghapus dari kolom Kredit terlebih dahulu')
	} else {
		if (!existsDebet) {
			// push it to combo
			var pair = {};
			pair[selectedVal] = selectedText + " ( " + formatMoney(+amount, "Rp.", 0) + " )";
			pushToCombo(pair, "debet");
			
			// modify span amount
			debetAmount = +debetAmount + +amount;
			balanceAmount =+debetAmount - +creditAmount; 
			$("#spnDebet").text(formatMoney(debetAmount, "Rp.", 0));
			$("#spnBalance").text(formatMoney(balanceAmount, "Rp.", 0));
		} else {
			alert("Akun sudah ada di dalam bucket Debet, mohon untuk menghapus terlebih dahulu");
		}
	}
	
});
$( "#btnRemoveKredit" ).click(function() {
	
	// get Amount in plain format
	// fmt for selectedText : AccountName (Rp. Amount) 
	var selectedText = $("#kredit option:selected").text();
	var lastIndexOfSign = selectedText.lastIndexOf("Rp.");
	var t = selectedText.replaceAll(",", "");
	t = t.substring(lastIndexOfSign + 3, t.length-1).trim();
	var amount = t;
	
	
	// modify span amount
	creditAmount = +creditAmount - +amount;
	balanceAmount =+debetAmount - +creditAmount; 
	$("#spnCredit").text(formatMoney(creditAmount, "Rp.", 0));
	$("#spnBalance").text(formatMoney(balanceAmount, "Rp.", 0));
	
	$('option:selected', $("#kredit")).remove(); 
})

$( "#btnRemoveDebet" ).click(function() {
	
	// get Amount in plain format
	// fmt for selectedText : AccountName (Rp. Amount) 
	var selectedText = $("#debet option:selected").text();
	var lastIndexOfSign = selectedText.lastIndexOf("Rp.");
	var t = selectedText.replaceAll(",", "");
	t = t.substring(lastIndexOfSign + 3, t.length-1).trim();
	var amount = t;
	
	
	// modify span amount
	debetAmount = +debetAmount + +amount;
	balanceAmount =+debetAmount - +creditAmount; 
	$("#spnDebet").text(formatMoney(debetAmount, "Rp.", 0));
	$("#spnBalance").text(formatMoney(balanceAmount, "Rp.", 0));
	
	$('option:selected', $("#debet")).remove(); 
	
})
/* $('option:selected', this).remove(); */

$( "#btnSubmit" ).mousedown(function(e) {
	$('#debet option').prop('selected', true);
	$('#kredit option').prop('selected', true);
	
	// TO DO : get all item listed in debet credit and post to server thru hidden variables
	var debetValues = "";
	$("#debet option").each(function()
	{
		var text = $(this).text(); 
		var lastIndexOfSign = text.lastIndexOf("Rp.");
		var t = text.replaceAll(",", "");
		t = t.substring(lastIndexOfSign + 3, t.length-1).trim();
		var amount = t;
		
	    // Add $(this).val() to your hidden
	    debetValues = debetValues + "|" + $(this).val() + "-" + amount  
	});
	debetValues = debetValues.substring(1);
	
	var creditValues = "";
	$("#debet option").each(function()
	{
		var text = $(this).text(); 
		var lastIndexOfSign = text.lastIndexOf("Rp.");
		var t = text.replaceAll(",", "");
		t = t.substring(lastIndexOfSign + 3, t.length-1).trim();
		var amount = t;
		
	    // Add $(this).val() to your hidden
	    creditValues = creditValues + "|" + $(this).val() + "-" + amount
	});
	creditValues = creditValues.substring(1);
	
	$("#trxAmt").val(debetAmount);
	$("#selectedDebet").val(debetValues);
	$("#selectedCredit").val(creditValues);
});


$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
//$('#btnSubmit2').click(function(e) {
	var result = confirm(' Are you sure want to save this data?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}
})
</script>