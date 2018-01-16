package net.cygnus.coa.web.action;

import id.co.nds.AppConstants;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.List;

import net.cygnus.coa.Coa;
import net.cygnus.coa.CoaFcd;

public class CoaMainAction extends JFXActionSupport {

	private Coa search = null;
	private List rsl = null;
	
	public String doSearch() throws GeneralException, Exception
	{
		if (HelperUtil.isNothing(search)) {
			if (HelperUtil.isNothing(getFromModuleSession("search"))) search = (Coa) getFromModuleSession("search");
		}
		
		search.setCoaType(Coa.COA_TYPE_PL);
		rsl = CoaFcd.getListCoa(search);
		
		setGridProperties(jfxGrid);
		saveToModuleSession("search", search);
		return SUCCESS;
	}

	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}

	@Override
	public Object getModel() {
		search = new Coa();
		return search;
	}
	
}



