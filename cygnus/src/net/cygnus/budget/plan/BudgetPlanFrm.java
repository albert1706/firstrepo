package net.cygnus.budget.plan;

import net.cygnus.coa.Coa;

public class BudgetPlanFrm extends Coa {
	private String segmentName;
	private Double budgetAmt;
	
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	public Double getBudgetAmt() {
		return budgetAmt;
	}
	public void setBudgetAmt(Double budgetAmt) {
		this.budgetAmt = budgetAmt;
	}
	
}
