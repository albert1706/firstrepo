<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Akunting</a></li>
    <li><a>Pengaturan Akun</a></li>
    <li>Akun Level 1</li>
    <li class="active">Modify</li>
</ul>
<!-- END BREADCRUMB -->       

<div class="row">
    <div class="col-md-12">
    	<s:actionerror theme="jquery" />
	</div>
</div>
<s:form id="frm" action="cygnus/acc/acc_level1/DoUpdateAct.action" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title">Akun Level 1 Maintenance <strong>New</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:label label="Kode Akun" cssClass="control-label" value="%{acc.accLv1Id}"/>
							<s:hidden name="accLv1Id" value="%{acc.accLv1Id}" />
							<s:hidden name="id" value="%{acc.id}" />
							<s:textfield name="accLv1Name" cssClass="form-control" value="%{acc.accLv1Name}" placeholder="Nama Akun" label="Nama Akun" tooltip="fa fa-pencil"></s:textfield>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgrid" action="AccountLevel1MainAction"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" value="Save" onBeforeTopics="beforeSubmit" />
                	
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
	var result = confirm(' Apakah anda yakin akan menyimpan akun ini?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}	
});
</script>