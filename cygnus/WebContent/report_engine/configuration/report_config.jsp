<%@ include file ="/main/taglib.jsp" %>


<div class="wizard">

                                    <ul>
                                        <li>
                                            <a href="#step-1">
                                                <span class="stepNumber">1</span>
                                                <span class="stepDesc">Step 1<br /><small>Step 1 description</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-2">
                                                <span class="stepNumber">2</span>
                                                <span class="stepDesc">Step 2<br /><small>Step 2 description</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-3">
                                                <span class="stepNumber">3</span>
                                                <span class="stepDesc">Step 3<br /><small>Step 3 description</small></span>                   
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-4">
                                                <span class="stepNumber">4</span>
                                                <span class="stepDesc">Step 4<br /><small>Step 4 description</small></span>                   
                                            </a>
                                        </li>
                                    </ul>
                                    <div id="step-1">   
                                        <h4>Step 1 Content</h4>
                                        <p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam id dolor id nibh ultricies vehicula.</p>
                                    </div>
                                    <div id="step-2">
                                        <h4>Step 2 Content</h4>
                                        <p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam id dolor id nibh ultricies vehicula.</p>
                                    </div>                      
                                    <div id="step-3">
                                        <h4>Step 3 Content</h4>
                                        <p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam id dolor id nibh ultricies vehicula.</p>
                                    </div>
                                    <div id="step-4">
                                        <h4>Step 4 Content</h4>
                                        <p>Nullam quis risus eget urna mollis ornare vel eu leo. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam id dolor id nibh ultricies vehicula.</p>
                                    </div>                           

                                </div>




































<h2>Report Engine </h2>
<p class="text">
	Configuration
</p>
<s:actionerror theme="jquery" />
<s:actionmessage theme="jquery"/>
<br>
<s:url id="listOfColumnPage" action="/jfx/reportengine/listOfColumnPage" escapeAmp="false">
    <s:param name="sourceName" ></s:param>
</s:url> 

<script type="text/javascript">
$.ajaxSetup ({  
    cache: false  
});

// all this flag bellow are used to handle double ajax request
var tab1FlagSubmit = false;
var tab2FlagSubmit = false;
var tab3FlagSubmit = false;
$.subscribe('tabchange', function(event, data) {
	
	var fromTab = event.originalEvent.ui.oldTab.attr("id");
	if (fromTab==="tab1") {
		
	    if (!tab1FlagSubmit) {
	    	var source = encodeURIComponent($("#reportSource").val());
	    	var reportName = encodeURIComponent($("#reportName").val());
	    	var description = encodeURIComponent($("#description").val());
	    	
	    	var param = "sourceName=" + source + "&reportName=\"" + reportName + "\"'&description=" + description;
		    var actUrl= "jfx/reportengine/listOfColumnPage.action?"+param;
		    var ajaxLoader = "<div id='ajaxLoad'> <img src='images/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";
	    	$("#page2Content").html(ajaxLoader).load(actUrl);	
	    }
	    tab1FlagSubmit = true;
	    tab2FlagSubmit = false;
	    tab3FlagSubmit = false;
	    
	} else if (fromTab === "tab2") {
		
	    if (!tab2FlagSubmit) {
	    	var actUrl= "jfx/reportengine/saveFieldsInfo.action";
		    var formData = $("#colConfigurationForm").serialize();
	    	//alert(formData);
		    $.ajax({
		        url: actUrl,
		        data: formData,
		        success: function(data){
		          //work with returned data from requested file
		        }
		     });
		    
	    }

	    tab1FlagSubmit = false;
	    tab2FlagSubmit = true;
	    tab3FlagSubmit = false;
		
	} else if (fromTab === "tab3") {
		
		if (!tab3FlagSubmit) {
			
		}
		
		tab1FlagSubmit = false;
		tab2FlagSubmit = false;
		tab3FlagSubmit = true;
		
	}
    
});
	
$("#colConfigurationForm").submit(function(event) {
	
	event.stopImmediatePropagation();
	
});

</script>

<sj:tabbedpanel id="localtabs"  onChangeTopics="tabchange">
      <sj:tab id="tab1" target="page1" label="Main Information"/>
      <sj:tab id="tab2" target="page2" label="Column Configuration"/>
      <sj:tab id="tab3" target="tthree" label="Show Example"/>
      <div id="page2">
	      <s:form id="colConfigurationForm" name="colConfigurationForm" action="jfx/reportengine/saveFieldsInfo.action">
	      	<div id="page2Content"></div>
	      </s:form>
      </div>
      <div id="page1">
		      
      		<table width="100%" class="tableBorder" cellpadding="0" cellspacing="0" >
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" style="padding: 1px">
						<tr class="tablerowbody1" align="left">
							<td width="20%">Report Name</td>
							<td width="1%" align="center">:</td>
							<td><input id="reportName" name="reportName" type="text" class="formInputBox" size="30px" /> </td>
						</tr>
						
						<tr class="tablerowbody2" align="left">
							<td >Report Source</td>
							<td width="1%" align="center">:</td>
							<td>
							<sj:autocompleter
								id="reportSource"
								name="reportSource"
								list="reportSources"
								selectBox="true"
								selectBoxIcon="true"
								/>
							</td>
						</tr>
						
						<tr class="tablerowbody1" align="left">
							<td >Description</td>
							<td width="1%" align="center">:</td>
							<td><textarea id="description" name="description" rows="4" cols="50"></textarea> </td>
						</tr>
						</table>
					</td>
				</tr>
			</table>
	  </div>
	  
    </sj:tabbedpanel>
    <div id="accordionlist_div_1" align="center" class="tablerow-action" aria-labelledby="accordionlist_header_1" role="tabpanel" aria-expanded="true" aria-hidden="false">
		<s:url var="urlgrid" action="UserGroupMainAction" namespace="/id/co/nds/webapp/usergroup"/>
		<sj:a id="btnCancel" href="%{urlgrid}" targets="content" indicator="indicator" button="true">Cancel</sj:a>
		<sj:submit id="btnSubmit" button="true" targets="content" value="Save" />
	</div>

	