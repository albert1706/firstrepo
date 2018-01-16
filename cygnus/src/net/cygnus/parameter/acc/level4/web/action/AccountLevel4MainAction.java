package net.cygnus.parameter.acc.level4.web.action;

import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.List;

import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.level4.AccLevel4Fcd;

public class AccountLevel4MainAction extends JFXActionSupport {

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
		
		rsl = AccLevel4Fcd.searchAcc(searchFilter, getJfxGrid());
		setGridProperties(jfxGrid);
		saveToModuleSession("searchFilter", searchFilter);
				
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
