package net.cygnus.transaction.mapping.web.action;

import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cygnus.transaction.mapping.TransactionMapping;
import net.cygnus.transaction.mapping.TransactionMappingFcd;

public class TransactionMappingMainAction extends JFXActionSupport {

	private String trxType = "";
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private List rsl;
	private TransactionMapping searchFilter = null;
	private Boolean searchTrigger;
	
	@Override
	public Object getModel() {
		searchFilter = new TransactionMapping();
		return searchFilter;
	}
	
	public String doSearch () throws Exception {
		
		if (HelperUtil.isNothing(searchFilter)) {
			if (HelperUtil.isNothing(getFromModuleSession("searchFilter"))) searchFilter = (TransactionMapping) getFromModuleSession("searchFilter");
		}
		try {
			rsl = TransactionMappingFcd.doSearch(searchFilter, getJfxGrid());
			
			setGridProperties(jfxGrid);
			saveToModuleSession("searchFilter", searchFilter);	
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
				
		return SUCCESS;
	}
	
	public String getListMap() throws Exception {
		
		try {
			rsl = TransactionMappingFcd.doSearch(searchFilter, getJfxGrid());
			
			setGridProperties(jfxGrid);
			saveToModuleSession("searchFilter", searchFilter);	
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String getMapping() throws Exception {
		
		List<TransactionMapping> l = TransactionMappingFcd.getMappingCoa(trxType);
		for (TransactionMapping mapping : l) {
			map.put(mapping.getCoaId(), mapping);
		}
		return SUCCESS;
	}

	public List getRsl() {
		return rsl;
	}
	public void setRsl(List rsl) {
		this.rsl = rsl;
	}

	public TransactionMapping getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(TransactionMapping searchFilter) {
		this.searchFilter = searchFilter;
	}

	public Boolean getSearchTrigger() {
		return searchTrigger;
	}

	public void setSearchTrigger(Boolean searchTrigger) {
		this.searchTrigger = searchTrigger;
	}

	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
