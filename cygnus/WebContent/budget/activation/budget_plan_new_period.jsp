<%@ include file ="/main/taglib.jsp" %>

<!-- START BREADCRUMB -->
<jnds:breadcrumb list="Home > Budgeting > Aktivasi Budget" />
<jnds:pagetitle value="Aktivasi" />

<div class="row">
    <div class="col-md-12">
    	<s:if test="hasActionErrors()"><s:actionerror theme="jquery"/><br></s:if>
		<s:if test="hasActionMessages()"><s:actionmessage theme="jquery"/><br></s:if>
	</div>
</div>

<s:form id="frm" action="/cygnus/budget/activation/DoActivate.action" target="content" validate="true" method="post" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Ringkasan Budget Tercatat</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-12">
							<s:label label="Total Akun Budget" cssClass="control-label" value="%{activation.totalCoa}"/>
							<s:label label="Total Akun Di Anggarkan" cssClass="control-label" value="%{activation.totalCoaSet}"/>
							<s:label label="Total Akun BELUM Di Anggarkan" cssClass="control-label" value="%{activation.totalCoaNotSet}"/>
							<s:label label="Total Budget Tercatat" cssClass="control-label" value="Rp. %{activation.totalBudget}"></s:label>
							<s:select 
								name="year" 
								cssClass="form-control" 
								list="listYears"
								label="Tahun Budget"
								value="%{activation.yearOfActivation}"
							/>
						</div>   
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgridCancel" action="BudgetMainAction" namespace="cygnus/budget/plan/budget_plan/budget_plan"/>
					<sj:a id="btnCancel" href="%{urlgridCancel}" cssClass="btn btn-default" targets="content" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" button="false" cssClass="btn btn-info" targets="content" value="Aktifkan" onBeforeTopics="beforeSubmit" />
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>



<script type="text/javascript">
$("#btnSubmit").subscribe('beforeSubmit', function(e, data) {
	var result = confirm(' Apakah anda yakin akan mengaktifkan budget ini ?');
	if (result == false) {
		e.originalEvent.preventDefault();
	}	
});
</script>