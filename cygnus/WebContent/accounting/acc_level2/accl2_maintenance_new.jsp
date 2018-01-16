<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Akunting</a></li>
    <li><a>Pengaturan Akun</a></li>
    <li>Akun Level 2</li>
    <li class="active">New</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Account Level 2</h2>
</div>
<s:form id="frm" name="frm" action="cygnus/acc/acc_level2/DoCreateAct.action" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
    <s:fielderror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>New Akun Level 2</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:textfield id="accLv2Id" name="accLv2Id" cssClass="form-control" maxlength="5" placeholder="Kode Akun" label="Kode Akun Level 2" requiredLabel="true"></s:textfield>
							<s:textfield name="accLv2Name" cssClass="form-control" placeholder="Nama Akun" label="Nama Akun" requiredLabel="true"></s:textfield>
							<s:url var="remoteurlcombobox" namespace="cygnus/acc/acc_level1" action="AccountLevel1SearchAction" />
							<sj:select 
								href="%{remoteurlcombobox}" 
								id="parentId" 
								name="parentId" 
								list="rsl" 
								listKey="id" 
								listValue="accLv1Name" 
								emptyOption="true" 
								cssClass="form-control"
								label="Akun Level 1"
							/>
							<s:select 
								list="#{'Y':'Yes', 'N':'No'}"
								id="heading"
								name="heading"
								emptyOption="true"
								cssClass="form-control"
								onchange="headingCheck(this.value)"
								label="Heading"  />
							<s:select 
								id="toa" 
								name="toa" 
								cssClass="form-control" 
								list="#{'-':'N/A', 'BS':'Balance Sheet', 'PL':'P/L'}"
								label="Type of Account"
								value="%{toa}"
							/>
							<s:select 
								id="dc" 
								name="dc" 
								cssClass="form-control" 
								list="#{'-':'N/A', 'd':'Debet', 'c':'Credit'}"
								label="Jenis Akun"
								value="%{dc}"
							/>
							<s:optiontransferselect
								id="leftSegment"
								name="leftSegment"
								label="Komisi"
								list="rsl"
								listKey="segmentId"
								listValue="segmentName"
								cssClass="form-control"
								doubleCssClass="form-control" 
								doubleName="listSegment"
								doubleId="listSegment" 
								doubleList="rsl2"
								doubleListKey="segmentId"
								doubleListValue="segmentName"
								size="10"
								doubleSize="10"
								allowSelectAll="false"
								allowUpDownOnLeft="false"
								allowUpDownOnRight="false"
								rightTitle="Komisi Terpilih"
								leftTitle="List Komisi"
								addToRightLabel="ADD >"
								addAllToRightLabel="ADD ALL >>"
								addToLeftLabel="< REMOVE"
								addAllToLeftLabel="<< REMOVE ALL"								
							/>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgrid" action="AccountLevel2MainAction" namespace="cygnus/acc/acc_level2"/>
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

function headingCheck(heading) {
	if (heading === 'Y') {
		 toaTemp = $("#toa").val(); 
		 $("#toa").val("-");
		 $("#toa").prop("disabled", true);
		 
		 dcTemp = $("#dc").val();
		 $("#dc").val("-");
		 $("#dc").prop("disabled", true);
	} else {
		$("#toa").prop("disabled", false);
		$("#toa").val(toaTemp);
		$("#dc").prop("disabled", false);
		$("#dc").val(dcTemp);
	}
}

$( "#btnSubmit" ).mousedown(function(e) {
	selectAllOptions(document.getElementById('leftSegment'));
	selectAllOptions(document.getElementById('listSegment'));
});
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
//$('#btnSubmit2').click(function(e) {
	var result = confirm(' Are you sure want to save this data?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}
	
})
</script>