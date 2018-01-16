<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<ul class="breadcrumb">
    <li><a>Home</a></li>
    <li><a>Akunting</a></li>
    <li><a>Pengaturan Akun</a></li>
    <li>Akun Level 1</li>
    <li class="active">New</li>
</ul>

<div class="page-title">                    
	<h2><span class="fa fa-arrow-circle-o-left"></span> Account Level 1</h2>
</div>
<!-- END BREADCRUMB -->       
<s:form id="frm" action="cygnus/acc/acc_level1/DoCreateAct.action" target="content" validate="true" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>New Akun Level 1</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:textfield name="accLv1Id" maxlength="1" cssClass="form-control" placeholder="Kode Akun" label="Kode Akun Level 1" tooltip="fa fa-pencil"></s:textfield>
							<s:textfield name="accLv1Name" cssClass="form-control" placeholder="Nama Akun" label="Nama Akun" tooltip="fa fa-pencil"></s:textfield>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgrid" action="cygnus/acc/acc_level1/AccountLevel1MainAction"/>
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