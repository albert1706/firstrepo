<ul class="breadcrumb">
    <#if parameters.pages??>
    	${parameters.pages}
    </#if>
    <#if parameters.active??>
    	<li class="active">${parameters.active}</li>
    </#if>
</ul>