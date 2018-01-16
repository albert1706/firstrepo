<%@ include file ="/main/taglib.jsp" %>

<script type="text/javascript" src='<s:url value="/js/select2.min.js"/>' ></script>
<link rel="stylesheet" type="text/css" href="<s:url value="/css/select2.min.css" />" />

<script type="text/javascript">
$("#account").select2();
</script>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Akunting</a></li>
    <li>Akun Mapping</li>
    <li class="active">New</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Akun Mapping</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/transaction/trx/DoCreateAct.action" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Akun Mapping</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:textfield id="classCode" name="classCode" cssClass="form-control" maxlength="5" placeholder="Class Code" label="Kode Mapping" requiredLabel="true"></s:textfield>
							<s:textfield name="trxName" cssClass="form-control" placeholder="Mapping Name" label="Nama Mapping" requiredLabel="true"></s:textfield>
							<s:select id="trx" name="trx" key="Transaction Type" list="listTrx" listKey="value1Param" listValue="value2Param" cssClass="form-control" />
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
							<s:select id="account" name="account" key="Akun" list="listAcc" listKey="accNo" listValue="accName" cssClass="form-control" />
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
				                <h3 class="panel-title">Debet</h3>
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
				                 <h3 class="panel-title">Kredit</h3>
				             </div>
				             <div class="panel-body">
				                 <select id="kredit" name="kredit" multiple class="form-control" />
				             </div>                            
				             <div class="panel-footer">
				                 <button id="btnRemoveKredit" name="btnRemoveKredit" class="btn btn-danger button">Remove</button>
				             </div>                              
				         </div>
				    </div> 
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
$( "#addKredit" ).click(function() {
	var selectedVal = $("#account").val();
	var selectedText = $("#account option:selected").text();
	var existsCredit = $("#kredit option[value='" + selectedVal + "']").length > 0;
	var existsDebet = $("#debet option[value='" + selectedVal + "']").length > 0;
	if(existsDebet) {
		alert('Akun sudah terpilih di kolom Debet, mohon untuk menghapus dari kolom Debet terlebih dahulu')
	} else {
		if (!existsCredit) {
			var pair = {};
			pair[selectedVal] = selectedText;
			pushToCombo(pair, "kredit");	
		}
	}
});
$( "#addDebet" ).click(function() {
	var selectedVal = $("#account").val();
	var selectedText = $("#account option:selected").text();
	
	var existsCredit = $("#kredit option[value='" + selectedVal + "']").length > 0;
	var existsDebet = $("#debet option[value='" + selectedVal + "']").length > 0;
	if(existsCredit) {
		alert('Akun sudah terpilih di kolom Kredit, mohon untuk menghapus dari kolom Kredit terlebih dahulu')
	} else {
		if (!existsDebet) {
			var pair = {};
			pair[selectedVal] = selectedText;
			pushToCombo(pair, "debet");
		}
	}
	
});
$( "#btnRemoveKredit" ).click(function() {
	$('option:selected', $("#kredit")).remove();
})

$( "#btnRemoveDebet" ).click(function() {
	$('option:selected', $("#debet")).remove();
})
/* $('option:selected', this).remove(); */

$( "#btnSubmit" ).mousedown(function(e) {
	$('#debet option').prop('selected', true);
	$('#kredit option').prop('selected', true);
});
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
//$('#btnSubmit2').click(function(e) {
	var result = confirm(' Are you sure want to save this data?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}
})
</script>