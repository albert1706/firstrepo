<!DOCTYPE html>
<%@ include file="/main/taglib.jsp" %>
<html lang="en">
    <head>      
        <!-- META SECTION -->
        <title>Joli Admin - Responsive Bootstrap Admin Template</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <link rel="icon" href="favicon.ico" type="image/x-icon" />
        <!-- END META SECTION -->
        
        <!-- CSS INCLUDE -->        
        <link rel="stylesheet" type="text/css" id="theme" href="css/theme-default.css"/>
        <!-- EOF CSS INCLUDE -->                                     
    </head>
    <body>
        <div class="error-container">
        	<div class="error-text">Error Code : </div>
            <div class="error-code"><s:property value="errorCode"/></div>
            <div class="error-subtext">Unfortunately we're having trouble loading the page you are looking for. <br> Please contact your administrator...</div>
            <div class="error-actions">                                
                <div class="row">
                    <div class="col-md-12">
                    	<s:property value="exception.message"/>
                        <!-- <button class="btn btn-info btn-block btn-lg" onClick="document.location.href = 'index.html';">Back to dashboard</button> -->
                    </div>
                </div>                                
            </div>
        </div>                 
    </body>
</html>






