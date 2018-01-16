<!DOCTYPE html>
<html amp lang="en">
    <head>        
        <!-- META SECTION -->
        <title>GKI Samanhudi - Cygnus</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<META http-equiv="CACHE-CONTROL" content="max-age=86400">
		<META http-equiv="Expires" content="300">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" >
		<meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <!-- END META SECTION -->
		<%@ include file ="taglib.jsp" %>
		<%@ include file="meta-header.jsp"%>
		
		
		<style>
<!--
.ui-jqgrid .ui-jqgrid-bdiv {
  position: relative; 
  margin: 0em; 
  padding:0; 
  /*overflow: auto;*/ 
  overflow-x:hidden; 
  overflow-y:hidden; 
  text-align:left;
} 
-->
</style>
		
		
    </head>
    <body>
        <!-- START PAGE CONTAINER -->
        <div class="page-container">
            
            <!-- START PAGE SIDEBAR -->
            <div class="page-sidebar">
                <!-- START X-NAVIGATION -->
                <ul class="x-navigation">
                    <li class="xn-logo">
                        <a href="index.html">GKI Samanhudi</a>
                        <a href="#" class="x-navigation-control">-Cygnus-</a>
                    </li>
                    
                    <li class="xn-profile">
                        <div class="profile">
                            <div class="profile-data">
                                <div class="profile-data-name"><s:property value="userName" /></div>
                                <div class="profile-data-title"></div>
                            </div>
                        </div>                                                                        
                    </li>
                    <li class="xn-title">Menus</li>
					<%=request.getAttribute("menu")%> 
                </ul>
                <!-- END X-NAVIGATION -->
            </div>
            <!-- END PAGE SIDEBAR -->
            
            <!-- PAGE CONTENT -->
            <div  class="page-content">
                
                <!-- START X-NAVIGATION VERTICAL -->
                <ul class="x-navigation x-navigation-horizontal x-navigation-panel">
                    <!-- SIGN OUT -->
                    <li class="pull-right">
                        <a href="#" class="mb-control" data-box="#mb-signout"><span class="fa fa-sign-out"></span>Log Out</a>                        
                    </li> 
                    <!-- END SIGN OUT -->
                </ul>
                <!-- END X-NAVIGATION VERTICAL -->                     

                <!-- PAGE CONTENT WRAPPER -->
                <div id="content" class="page-content-wrap">
                    <!-- START BREADCRUMB -->
                    <jnds:breadcrumb list="Home > Dashboard"/>
	                <!-- END BREADCRUMB -->    
                    <!-- START WIDGETS -->                    
                    <div class="row">
	                    <div class="col-md-6"></div>
	                    <div class="col-md-3"></div>
                        <div class="col-md-3">
                            <!-- START WIDGET CLOCK -->
                            <div class="widget widget-info widget-padding-sm ">
                                <div class="widget-big-int plugin-clock">00:00</div>                            
                                <div class="widget-subtitle plugin-date">Loading...</div>
                                <div class="widget-controls">                                
                                    <a href="#" class="widget-control-right widget-remove" data-toggle="tooltip" data-placement="left" title="Remove Widget"><span class="fa fa-times"></span></a>
                                </div>                            
                                <div class="widget-buttons widget-c3">
                                    <div class="col">
                                        <a href="#"><span class="fa fa-clock-o"></span></a>
                                    </div>
                                    <div class="col">
                                        <a href="#"><span class="fa fa-bell"></span></a>
                                    </div>
                                    <div class="col">
                                        <a href="#"><span class="fa fa-calendar"></span></a>
                                    </div>
                                </div>                            
                            </div>                        
                            <!-- END WIDGET CLOCK -->
                            
                        </div>
                    </div>
                    <!-- END WIDGETS -->                    
                    
                    
                    
                    <!-- START DASHBOARD CHART -->
					<div class="chart-holder" id="dashboard-area-1" style="height: 200px;"></div>
					<div class="block-full-width">
                                                                       
                    </div>                    
                    <!-- END DASHBOARD CHART -->
                    
                </div>
                <!-- END PAGE CONTENT WRAPPER -->                                
            </div>            
            <!-- END PAGE CONTENT -->
        </div>
        <!-- END PAGE CONTAINER -->

        <!-- MESSAGE BOX-->
        <div class="message-box animated fadeIn" data-sound="alert" id="mb-signout">
            <div class="mb-container">
                <div class="mb-middle">
                    <div class="mb-title"><span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?</div>
                    <div class="mb-content">
                        <p>Are you sure you want to log out?</p>                    
                        <p>Press No if youwant to continue work. Press Yes to logout current user.</p>
                    </div>
                    <div class="mb-footer">
                        <div class="pull-right">
                            <a href="LogoutAction.action" class="btn btn-success btn-lg">Yes</a>
                            <button class="btn btn-default btn-lg mb-control-close">No</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MESSAGE BOX-->
<img alt="" style="vertical-align: middle; " align="middle" src="">
    </body>
</html>



<script type="text/javascript">

$.ajaxSetup ({  
    cache: false  	
});  

function showPage(modulId, loadUrl, elem){
	
	if (modulId != null) $("#" + modulId).parents('li').addClass("active");	
	if (elem == null) elem = "#content";
	
	var ajax_load = "<div id='ajaxLoad'> <img src='assets/images/jnds/ajax-loader.gif' align='middle' class='ajax-loader' alt='loading...' /></div>";  
	$(elem).html(ajax_load).load(loadUrl);
}

</script>


