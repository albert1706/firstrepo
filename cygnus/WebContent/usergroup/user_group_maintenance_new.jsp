<%@page import="id.co.nds.session.JndsSession"%>
<%@ include file ="/main/taglib.jsp" %>
<%@page import="java.util.*"%>
<%@page import="id.co.nds.webapp.usergroup.matrix.IUserAccessRight"%>
<%@page import="id.co.nds.plugin.user_matrix.TreeUserMatrixImpl"%>

<style type="text/css">

.ygtvcheck0 { background: url(<s:url value="/assets/images/jnds/check0.gif" />) 0 0 no-repeat; width:16px; cursor:pointer }
.ygtvcheck1 { background: url(<s:url value="/assets/images/jnds/check1.gif" />) 0 0 no-repeat; width:16px; cursor:pointer }
.ygtvcheck2 { background: url(<s:url value="/assets/images/jnds/check2.gif" />) 0 0 no-repeat; width:16px; cursor:pointer }

#expandcontractdiv {border:1px solid #336600; background-color:#FFFFCC; margin:0 0 .5em 0; padding:0.2em;}
#treeDiv1 { background: #fff }

</style>


<jnds:breadcrumb list="Home > User Management > User Group > New" />
<jnds:pagetitle value="User Group" />

<s:form id="frm" action="id/co/nds/webapp/usergroup/DoCreateAct.action" target="content" theme="simple" cssClass="form-horizontal">
<!-- START MAIN CONTENT -->
<div class="row">
    <div class="col-md-12">
    		<s:actionerror theme="jquery" />
			<div class="panel panel-default">
            	<div class="panel-heading">
                	<h3 class="panel-title"><strong>New Group</strong></h3>
				</div>
				<div class="panel-body">                                                                        
					<div class="row">  
						<div class="col-md-6">
                             <div class="form-group">
                                 <label class="col-md-3 control-label">Group Name</label>
                                 <div class="col-md-9">                                            
                                     <div class="input-group">
                                         <span class="input-group-addon"><span class="fa fa-pencil"></span></span>
                                         <input id="groupName" name="groupName" type="text" class="form-control" placeholder="Nama Group" />
                                     </div>                                            
                                     <span class="help-block"></span>
                                 </div>
                             </div>
                             
                             <div class="form-group">
                                 <label class="col-md-3 control-label">Description</label>
                                 <div class="col-md-9">                                            
                                     <textarea id="groupDesc" name="groupDesc" class="form-control" rows="5" placeholder="Keterangan" ></textarea>
                                     <span class="help-block"></span>
                                 </div>
                             </div>
						</div>   
						<div class="col-md-6">
                                 <div class="form-group">   
                                 		<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->
										<!-- Some style for the expand/contract section-->
										<div id="expandcontractdiv" class="col-md-9">
													<a id="expand" href="#">Expand all</a>
													<a id="collapse" href="#">Collapse all</a>
													<a id="check" href="#">Check all</a>
													<a id="uncheck" href="#">Uncheck all</a>
													<!-- <a id="getchecked" href="#">Log array of checked nodes</a>  -->
												</div>
												<div id="treeDiv1" class="col-md-9"></div>
										<!--END SOURCE CODE FOR EXAMPLE =============================== -->                                     
                                 </div>
							</div>               
                    </div>
				</div>
                <div class="panel-footer">
                	<s:token />
					<s:iterator value="rsl" status="status">
						<input style="display: none" type="checkbox" id="chk_<s:property value="moduleId"/>" name="chk_<s:property value="moduleId"/>"   />			
					</s:iterator>
                	<s:url var="urlgrid" action="id/co/nds/webapp/usergroup/UserGroupMainAction"/>
					<sj:a id="btnCancel" href="%{urlgrid}" cssClass="btn btn-default" targets="content" indicator="indicator" button="false">Cancel</sj:a>
					<sj:submit id="btnSubmit" cssClass="btn btn-info" button="false" targets="content"  value="Save" />
            	</div>
        	</div>
    </div>
</div>
<!-- END MAIN CONTENT -->
</s:form>
 	


	
<script type="text/javascript">
(function() {
	var tree;
	var nodes = [];
	var nodeIndex;
	
	var Dom = YAHOO.util.Dom,
    	submit = Dom.get('btnSubmit');

	function treeInit() {
		buildRandomTaskNodeTree();
	}

	//handler for expanding all nodes
	YAHOO.util.Event.on("expand", "click", function(e) {
		tree.expandAll();
		YAHOO.util.Event.preventDefault(e);
	});

	//handler for collapsing all nodes
	YAHOO.util.Event.on("collapse", "click", function(e) {
		tree.collapseAll();
		YAHOO.util.Event.preventDefault(e);
	});

	//handler for checking all nodes
	YAHOO.util.Event.on("check", "click", function(e) {
		checkAll();
		YAHOO.util.Event.preventDefault(e);
	});

	//handler for unchecking all nodes
	YAHOO.util.Event.on("uncheck", "click", function(e) {
		uncheckAll();
		YAHOO.util.Event.preventDefault(e);
	});

	YAHOO.util.Event.on("btnSubmit", "mousedown", function(e) {
		YAHOO.util.Event.preventDefault(e);
		var result = confirm('Arey you sure want to save this form?');
		if (result == true)
		{
			//YAHOO.util.Event.preventDefault(e);
			//alert(document.getElementById("frm").target);
			getCheckedNodes();
			document.getElementById('btnSubmit').click();
		}
	});

	//Function  creates the tree and
	//builds between 3 and 7 children of the root node:
    function buildRandomTaskNodeTree() {

		//instantiate the tree:
        tree = new YAHOO.widget.TreeView("treeDiv1");

        <%
        	Hashtable moduleSession = (Hashtable) request.getSession().getAttribute(JndsSession.MODULE_SESSION);
        	String menu = String.valueOf(moduleSession.get("USERMENU"));
        %>
        <%=menu%>

       // Expand and collapse happen prior to the actual expand/collapse,
       // and can be used to cancel the operation
       tree.subscribe("expand", function(node) {
              // return false; // return false to cancel the expand
       });

       tree.subscribe("collapse", function(node) {
              
       });

       // Trees with TextNodes will fire an event for when the label is clicked:
       tree.subscribe("labelClick", function(node) {
        
       });

       // Trees with TaskNodes will fire an event for when a check box is clicked
       tree.subscribe("checkClick", function(node) {
           
       });

		//The tree is not created in the DOM until this method is called:
        tree.draw();
    }

	var callback = null;

    function onCheckClick(node) {

    }
    
    function checkAll() {
        var topNodes = tree.getRoot().children;
        for(var i=0; i<topNodes.length; ++i) {
            topNodes[i].check();
        }
    }

    function uncheckAll() {
        var topNodes = tree.getRoot().children;
        for(var i=0; i<topNodes.length; ++i) {
            topNodes[i].uncheck();
        }
    }

   function onLabelClick(node) {
       node.refresh();
       return false;
   }


   // Gets the labels of all of the fully checked nodes
   // Could be updated to only return checked leaf nodes by evaluating
   // the children collection first.
    function getCheckedNodes(nodes) {
	   nodes = nodes || tree.getRoot().children;
        checkedNodes = [];
        for(var i=0, l=nodes.length; i<l; i=i+1) {
            var n = nodes[i];
            //if (n.checkState > 0) { // if we were interested in the nodes that have some but not all children checked
            if (n.checkState === 2) {
                checkedNodes.push(n.label); // just using label for simplicity
                if(document.getElementById('chk_' + n.data) != null) {
                	document.getElementById('chk_' + n.data).checked = true;
                }
            }
            if (n.hasChildren()) {
				checkedNodes = checkedNodes.concat(getCheckedNodes(n.children));
            }
        }

        return checkedNodes;
    }


	YAHOO.util.Event.onDOMReady(treeInit);
})();

</script>

