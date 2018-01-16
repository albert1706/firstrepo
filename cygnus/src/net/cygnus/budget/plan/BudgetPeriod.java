package net.cygnus.budget.plan;

import java.sql.Timestamp;

public class BudgetPeriod {

	public static final String STATUS_ACTIVE = "ACTIVE";
	public static final String STATUS_WAITING = "WAITING";
	public static final String STATUS_EXPIRED = "EXPIRED";
	
	private Long budgetPeriodId;
	private Timestamp periodStart;
	private Timestamp periodEnd;
	private String activateBy;
	private Timestamp activateDate;
	private String status;
	
	public Long getBudgetPeriodId() {
		return budgetPeriodId;
	}
	public void setBudgetPeriodId(Long budgetPeriodId) {
		this.budgetPeriodId = budgetPeriodId;
	}
	public Timestamp getPeriodStart() {
		return periodStart;
	}
	public void setPeriodStart(Timestamp periodStart) {
		this.periodStart = periodStart;
	}
	public Timestamp getPeriodEnd() {
		return periodEnd;
	}
	public void setPeriodEnd(Timestamp periodEnd) {
		this.periodEnd = periodEnd;
	}
	public String getActivateBy() {
		return activateBy;
	}
	public void setActivateBy(String activateBy) {
		this.activateBy = activateBy;
	}
	public Timestamp getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Timestamp activateDate) {
		this.activateDate = activateDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
