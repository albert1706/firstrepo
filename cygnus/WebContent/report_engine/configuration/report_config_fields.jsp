<%@ include file ="/main/taglib.jsp" %>

<table width="100%" class="tableBorder" cellpadding="1" cellspacing="1" >
	<tr class="tablerowheader" align="left">
		<td >Column Name</td>
		<td >Alias</td>
		<td >Data Type</td>
		<td >Sequence</td>
		<td >Show</td>
		<td >Filter</td>
	</tr>
	<s:iterator value="fields" status="fieldsStatus">
		<s:if test="#fieldsStatus.odd == true">
			<tr class="tablerowbody1" align="left">
		</s:if>
		<s:else>
			<tr class="tablerowbody2" align="left">
		</s:else>
			<td width="30%"><s:hidden name="fields[%{#fieldsStatus.index}].columnName" value="%{columnName}" /><s:property value="columnName" /></td>
			<td width="30%"><input name="fields[<s:property value="#fieldsStatus.index" />].columnAlias" type="text" class="formInputBox" /></td>
			<td width="10%"><s:hidden name="fields[%{#fieldsStatus.index}].dataType" /><s:property value="dataType" /></td>
			<td width="10%"><input name="fields[<s:property value="#fieldsStatus.index" />].sequence" type="text" class="formInputBox" /></td>
			<td width="10%"><input name="fields[<s:property value="#fieldsStatus.index" />].show" type="checkbox" /> </td>
			<td width="10%"><input name="fields[<s:property value="#fieldsStatus.index" />].filter" type="checkbox" /></td>
		</tr>
	</s:iterator>
</table>

 
