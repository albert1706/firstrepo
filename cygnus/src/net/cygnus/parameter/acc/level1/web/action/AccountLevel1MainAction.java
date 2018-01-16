package net.cygnus.parameter.acc.level1.web.action;

import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.List;

import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.level1.AccLevel1Fcd;
import net.cygnus.parameter.acc.level2.AccLevel2Fcd;

public class AccountLevel1MainAction extends JFXActionSupport {

	private List rsl;
	private AccCommon searchFilter = null;
	private Boolean searchTrigger;
	
	@Override
	public Object getModel() {
		searchFilter = new AccCommon();
		return searchFilter;
	}
	
	public String doSearch () throws Exception {
		
		if (HelperUtil.isNothing(searchFilter)) {
			if (HelperUtil.isNothing(getFromModuleSession("searchFilter"))) searchFilter = (AccCommon) getFromModuleSession("searchFilter");
		}
//		System.out.println("DCH in L1 : " + searchFilter.getAccLv1Id());
		try {
			rsl = AccLevel1Fcd.searchAcc(searchFilter, getJfxGrid());
			
			setGridProperties(jfxGrid);
			saveToModuleSession("searchFilter", searchFilter);	
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
				
		return SUCCESS;
	}
	
	public String prepareCombo() throws Exception {
		rsl = AccLevel1Fcd.searchForCombo(searchFilter);
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

}
