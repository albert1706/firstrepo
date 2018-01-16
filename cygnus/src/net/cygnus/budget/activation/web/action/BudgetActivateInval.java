package net.cygnus.budget.activation.web.action;

import id.co.nds.util.Util;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.budget.activation.BudgetActivation;
import net.cygnus.budget.activation.BudgetActivationFcd;

import org.apache.struts2.components.ActionMessage;
import org.joda.time.DateTime;

public class BudgetActivateInval extends JFXActionSupport {

	BudgetActivation activation = null;
	List listYears = new ArrayList();
	
	public String prepareActivation() throws Exception {
		
		// get budgeting plan set info
		activation = BudgetActivationFcd.prepareActivation();
		
		// prepare list of years
		DateTime dt = new DateTime();
		Integer y = dt.getYear();
		listYears.add(y); 
		listYears.add(y+1);
		
		return SUCCESS;
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
