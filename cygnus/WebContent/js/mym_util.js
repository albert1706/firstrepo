/**
 * 
 * @author 	: MeetYourMaker
 * @Date	: May'1 2016
 * 
 * this is a repository for helper function used in forms
 * 
 */

String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};

function formatMoney(value, sign, decimalLength) {
	if (decimalLength == null) decimalLength = 0;
	var num = sign + value.toFixed(decimalLength).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
	return num;
}

function pushToCombo(jsonObject, targetCombo) {
	
	// should add a logic to handle duplicate push to the same combo; 
	var $select = $('#'+targetCombo);
     $.each(jsonObject, function(key, value) {
     	$('<option>').val(key).text(value).appendTo($select);
  	});
}

function populateToCombo(jsonObject, targetCombo) {
	var $select = $('#'+targetCombo);
     $select.find('option').remove();
     $('<option>').val("-1").text("Select").appendTo($select);
     $.each(jsonObject, function(key, value) {
     	$('<option>').val(key).text(value).appendTo($select);
  	});
}
function doAjax(theUrl, params, callback) {
	var mymurl = null;
	if (params === "" || params===null || params===undefined) {
		mymurl = theUrl;
	} else {
		mymurl= theUrl+"?"+params;
	}
	
	request = $.ajax({
  	  	async : true,
        datatype:"json",
        type: "GET",
        contentType: "application/json",
        url: mymurl,
        timeout:5000,
        success: function(response)
        {
			 if(typeof response==='object') 
			 {
			    return callback(response);
			 }
        },
        complete: function()
        {
            timeout = request = null;
        },
        error: function(request, status, error)
        {
            if(status!=="timeout"&&status!=="abort")
            {
                alert(status+" : "+error);
            }
        }
    });
    /*timeout = setTimeout(function() {
    	alert("setTimeout");
        if(request)
        {
        	alert("abort request");
            request.abort();
            alert("The request has been timed out.");
        }
    	}, 60000); //1 minutes  
*/    
}
function loadAnotherSelect(theUrl, params, targetSelectElement) {
	doAjax(theUrl, params, function(jsonO) {
		populateToCombo(jsonO, targetSelectElement);
	});
}