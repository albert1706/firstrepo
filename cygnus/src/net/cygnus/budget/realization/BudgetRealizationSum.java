package net.cygnus.budget.realization;

import java.io.Serializable;
import java.sql.Timestamp;

public class BudgetRealizationSum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 882367894937621729L;

	private Long budgetPeriodId;
	private Long budgetId;
	private Double totalOutstanding;
	private Double totalReal;
	private String status;
	private Timestamp recapDate;
	
	public Long getBudgetPeriodId() {
		return budgetPeriodId;
	}
	public void setBudgetPeriodId(Long budgetPeriodId) {
		this.budgetPeriodId = budgetPeriodId;
	}
	public Long getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}
	public Double getTotalOutstanding() {
		return totalOutstanding;
	}
	public void setTotalOutstanding(Double totalOutstanding) {
		this.totalOutstanding = totalOutstanding;
	}
	public Double getTotalReal() {
		return totalReal;
	}
	public void setTotalReal(Double totalReal) {
		this.totalReal = totalReal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getRecapDate() {
		return recapDate;
	}
	public void setRecapDate(Timestamp recapDate) {
		this.recapDate = recapDate;
	}
	
	
}
