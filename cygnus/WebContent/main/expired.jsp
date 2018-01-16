<!DOCTYPE html>
<html lang="en">
    <head>        
        <!-- META SECTION -->
        <title>NDS - Nusantara Duta Solusindo</title>            
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<META http-equiv="CACHE-CONTROL" content="max-age=86400">
		<META http-equiv="Expires" content="300">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" >
		<meta name="viewport" content="width=device-width, initial-scale=1" />
        
        <!-- END META SECTION -->
		<%@ include file ="taglib.jsp" %>
		<%@ include file="meta-header.jsp"%>
		
		                                  
    </head>
    <body>
        <div class="error-container">
            <div class="error-code">401</div>
            <div class="error-text">SESSION EXPIRED</div>
            <div class="error-subtext">Your session has been expired, please try to <a href="<s:url value='InitAction.action'  />">re-login</a> again.</div>            
        </div>                 
    </body>
</html>






