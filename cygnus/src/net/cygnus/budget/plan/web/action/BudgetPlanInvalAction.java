package net.cygnus.budget.plan.web.action;

import id.co.nds.webapp.JFXActionSupport;
import net.cygnus.budget.plan.BudgetPeriod;
import net.cygnus.budget.plan.BudgetPlanFcd;

public class BudgetPlanInvalAction extends JFXActionSupport {

	BudgetPeriod period = null;
	
	public String prepareSetPeriod() throws Exception {
		
		// get existing period
		BudgetPeriod bp = new BudgetPeriod();
		bp.setStatus(BudgetPeriod.STATUS_WAITING);
		bp = BudgetPlanFcd.getBudgetPeriod(bp);
		
		period = bp;
		
		return SUCCESS;
	}

	public BudgetPeriod getPeriod() {
		return period;
	}

	public void setPeriod(BudgetPeriod period) {
		this.period = period;
	}

}
