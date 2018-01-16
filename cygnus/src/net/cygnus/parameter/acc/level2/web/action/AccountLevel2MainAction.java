package net.cygnus.parameter.acc.level2.web.action;

import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.List;

import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;
import net.cygnus.parameter.acc.level2.AccLevel2Fcd;

public class AccountLevel2MainAction extends JFXActionSupport {

	private List rsl;
	private AccCommon searchFilter = null;
	private Boolean searchTrigger;
	private String accId = null;
	
	@Override
	public Object getModel() {
		searchFilter = new AccCommon();
		return searchFilter;
	}
	
	public String doSearch () throws Exception {
		
		if (HelperUtil.isNothing(searchFilter)) {
			if (HelperUtil.isNothing(getFromModuleSession("searchFilter"))) searchFilter = (AccCommon) getFromModuleSession("searchFilter");
		}
		
		rsl = AccLevel2Fcd.searchAcc(searchFilter, getJfxGrid());
		setGridProperties(jfxGrid);
		saveToModuleSession("searchFilter", searchFilter);
				
		return SUCCESS;
	}
	
	public String prepareCombo() throws Exception {
		rsl = AccLevel2Fcd.searchForCombo(searchFilter);
		return SUCCESS;
	}
	
	public String getListAkunLv1() throws Exception {
		
		return SUCCESS;
	}
	
	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}

	public AccCommon getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(AccCommon searchFilter) {
		this.searchFilter = searchFilter;
	}

	public Boolean getSearchTrigger() {
		return searchTrigger;
	}

	public void setSearchTrigger(Boolean searchTrigger) {
		this.searchTrigger = searchTrigger;
	}

	public String getAccId() {
		return accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}

}
