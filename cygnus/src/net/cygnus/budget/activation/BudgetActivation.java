package net.cygnus.budget.activation;


public class BudgetActivation {

	private Integer totalCoa;
	private Integer totalCoaSet;
	private Integer totalCoaNotSet;
	private Double totalBudget;
	private Integer yearOfActivation;
	
	public BudgetActivation() {
		totalCoa = 0;
		totalCoaSet = 0;
		totalCoaNotSet = 0;
		totalBudget = 0D;
	}
	
	public Integer getTotalCoa() {
		return totalCoa;
	}
	public void setTotalCoa(Integer totalCoa) {
		this.totalCoa = totalCoa;
	}
	public Integer getTotalCoaSet() {
		return totalCoaSet;
	}
	public void setTotalCoaSet(Integer totalCoaSet) {
		this.totalCoaSet = totalCoaSet;
	}
	public Integer getTotalCoaNotSet() {
		return totalCoaNotSet;
	}
	public void setTotalCoaNotSet(Integer totalCoaNotSet) {
		this.totalCoaNotSet = totalCoaNotSet;
	}
	public Double getTotalBudget() {
		return totalBudget;
	}
	public void setTotalBudget(Double totalBudget) {
		this.totalBudget = totalBudget;
	}
	public Integer getYearOfActivation() {
		return yearOfActivation;
	}
	public void setYearOfActivation(Integer yearOfActivation) {
		this.yearOfActivation = yearOfActivation;
	}
	
	
}
