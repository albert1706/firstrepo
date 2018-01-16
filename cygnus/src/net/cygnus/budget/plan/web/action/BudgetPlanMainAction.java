package net.cygnus.budget.plan.web.action;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;

import java.util.List;

import net.cygnus.budget.plan.BudgetPlanFcd;
import net.cygnus.budget.plan.BudgetPlanFrm;
import net.cygnus.coa.Coa;

public class BudgetPlanMainAction extends JFXActionSupport {

	private BudgetPlanFrm search = null;
	private List rsl = null;
	
	public String doSearch() throws GeneralException, Exception
	{
		if (HelperUtil.isNothing(search)) {
			if (HelperUtil.isNothing(getFromModuleSession("search"))) search = (BudgetPlanFrm) getFromModuleSession("search");
		}
		
		search.setCoaType(Coa.COA_TYPE_PL);
		rsl = BudgetPlanFcd.searchCoaForPsBudget(search, getJfxGrid());
		
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
		search = new BudgetPlanFrm();
		return search;
	}
	
}



