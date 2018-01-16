<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Akunting</a></li>
    <li><a>Pengaturan Akun</a></li>
    <li>Akun Level 4</li>
    <li class="active">New</li>
</ul>
<!-- END BREADCRUMB -->       
<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Account Level 4</h2>
</div>
<s:form id="frm" action="cygnus/acc/acc_level4/DoCreateAct" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    <s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>New Akun Level 4</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:hidden name="heading" value="N" />
							<s:textfield id="accLv4Id" name="accLv4Id" maxlength="5" cssClass="form-control" placeholder="Kode Akun" label="Kode Akun Level 4" tooltip="fa fa-pencil"></s:textfield>
							<s:textfield name="accLv4Name" cssClass="form-control" placeholder="Nama Akun" label="Nama Akun Level 4"></s:textfield>
							<s:url var="remoteurlcombobox" namespace="cygnus/acc/acc_level1" action="AccountLevel1SearchAction" />
							<sj:select 
								href="%{remoteurlcombobox}" 
								id="accLv1Id" 
								name="accLv1Id" 
								list="rsl" 
								listKey="id" 
								listValue="accLv1Name" 
								emptyOption="true" 
								cssClass="form-control"
								onChangeTopics="reloadAccL2"
								label="Akun Level 1"
								value="%{accLv1Id}"
							/>
							<s:url var="remoteurlcombobox2" namespace="cygnus/acc/acc_level2" action="AccountLevel2SearchAction" />
							<sj:select 
								reloadTopics="reloadAccL2"
								href="%{remoteurlcombobox2}" 
								id="accLv2Id" 
								name="accLv2Id" 
								list="rsl" 
								listKey="id" 
								listValue="accLv2Name" 
								emptyOption="true" 
								cssClass="form-control"
								onChangeTopics="reloadAccL3"
								label="Akun Level 2"
								value="%{accLv2Id}"
							/>
							<s:url var="remoteurlcombobox3" namespace="cygnus/acc/acc_level3" action="AccountLevel3SearchAction" />
							<sj:select 
								reloadTopics="reloadAccL3"
								href="%{remoteurlcombobox3}" 
								id="parentId" 
								name="parentId" 
								list="rsl" 
								listKey="id" 
								listValue="accLv3Name" 
								emptyOption="true" 
								cssClass="form-control"
								label="Akun Level 3"
								value="%{accLv3Id}"
							/>
							<s:select 
								id="toa" 
								name="toa" 
								cssClass="form-control" 
								list="#{'-':'N/A', 'BS':'Balance Sheet', 'PL':'P/L'}"
								label="Type of Account"
								value="%{toa}"
							/>
							<s:select id="dc" name="dc" cssClass="form-control"
								list="#{'-':'N/A', 'd':'Debet', 'c':'Credit'}"
								label="Jenis Akun" value="%{dc}" />
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
                	<s:url var="urlgrid" action="AccountLevel3MainAction" namespace="cygnus/acc/acc_level4"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" value="Save" onBeforeTopics="beforeSubmit" />
                	
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">
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