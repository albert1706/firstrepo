package net.cygnus.budget;

import id.co.nds.beans.Actor;

import java.io.Serializable;

public class Budget extends Actor implements Serializable {

	public static final String TABLE_PS_BUDGET = "ps_budget_plan";
	
	private Long budgetId;
	private String coaId;
	private Double budgetAmt;
	private Double prevBudgetAmt;
	
	public Long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}
	public Double getBudgetAmt() {
		return budgetAmt;
	}
	public void setBudgetAmt(Double budgetAmt) {
		this.budgetAmt = budgetAmt;
	}
	public Double getPrevBudgetAmt() {
		return prevBudgetAmt;
	}
	public void setPrevBudgetAmt(Double prevBudgetAmt) {
		this.prevBudgetAmt = prevBudgetAmt;
	}
	public String getCoaId() {
		return coaId;
	}
	public void setCoaId(String coaId) {
		this.coaId = coaId;
	}

}
