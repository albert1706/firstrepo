<%@ include file ="/main/taglib.jsp" %>

<jnds:breadcrumb list="Home > Utility > Exception Log > Detail" />

<s:form id="frm" target="content" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    	<s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>Exception Log</strong></h3>
				</div>
				<div class="panel-body">   
					<div class="row">  
						<div class="col-md-12">
							<s:label label="Error Code" cssClass="control-label" value="%{lgException.errorCode}"/>
							<s:label label="Exception Date" cssClass="control-label" value="%{lgException.createDate}" formatter="date" formatoptions="{srcformat:'Y-m-d H:i:s' ,newformat: 'd-m-Y   H:i:s'}"/>
							<div class="form-group">
                                 <label class="col-md-3 control-label">Stacktrace</label>
                                 <div class="col-md-9">                                            
                                     <div class="input-group">
                                         <label class="control-label" style="text-align:left"><s:property value="lgException.stacktrace" /></label>
                                     </div>                                            
                                     <span class="help-block"></span>
                                 </div>
                             </div>
						</div>
					</div>                                                                     
				</div>
                <div class="panel-footer">
                	<s:token />
                	<s:url var="urlgrid" action="ExceptionMainAction" namespace="/id/co/nds/webapp/exception"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" button="false">Back</sj:a>
				</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>
