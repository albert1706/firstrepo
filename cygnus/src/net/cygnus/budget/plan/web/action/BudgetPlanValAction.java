package net.cygnus.budget.plan.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.standardization.IValAction;
import id.co.nds.webapp.GeneralConstants;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;

import java.sql.Timestamp;

import net.cygnus.budget.Budget;
import net.cygnus.budget.plan.BudgetPeriod;
import net.cygnus.budget.plan.BudgetPlanFcd;

public class BudgetPlanValAction extends JFXActionSupport implements IValAction {

	private static final long serialVersionUID = -790811927392450108L;
	private Budget budget = new Budget();
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		BudgetPlanFcd.registerBudget(budget, userSession.getUser());
			
//		addActionMessage("Akun baru ["+ acc.getAccLv1Name() +"] dengan ID "+acc.getAccLv1Id() +" telah berhasil di daftarkan");
		
		return SUCCESS;
	}
	
	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return budget;
	}

	@Override
	public String doDelete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
