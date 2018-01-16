package net.cygnus.budget.activation.web.action;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.session.UserSession;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.cygnus.budget.activation.BudgetActivation;
import net.cygnus.budget.activation.BudgetActivationFcd;
import net.cygnus.budget.plan.BudgetPeriod;

import org.joda.time.DateTime;

public class BudgetActivateVal extends JFXActionSupport {

	private Integer year;
	BudgetActivation activation = null;
	List listYears = new ArrayList();
	
	public String doActivate() throws GeneralException {
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());

		DateTime start = new DateTime(year,1,1,00,01);
		DateTime end = new DateTime(year,12,31,23,59);
		
		BudgetPeriod bp = new BudgetPeriod();
		bp.setPeriodStart(new Timestamp(start.getMillis()));
		bp.setPeriodEnd(new Timestamp(end.getMillis()));

		BudgetActivationFcd.ActivateBudget(bp, userSession.getUser());

		// prepare for form :
		activation = BudgetActivationFcd.prepareActivation();
		activation.setYearOfActivation(year);
		DateTime dt = new DateTime();
		Integer y = dt.getYear();
		listYears.add(y); 
		listYears.add(y+1);
		
		String actionMessage = "Budget telah diaktifkan. Tahun Budget saat ini : " + year;
		addActionMessage(actionMessage);
		
		return SUCCESS;
		
	}

	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public BudgetActivation getActivation() {
		return activation;
	}

	public void setActivation(BudgetActivation activation) {
		this.activation = activation;
	}

	public List getListYears() {
		return listYears;
	}

	public void setListYears(List listYears) {
		this.listYears = listYears;
	}
	
}
