package net.cygnus.report.journal;

import java.sql.Timestamp;

public class SearchFilter {

	private Timestamp periodStart;
	private Timestamp periodEnd;
	private String[] transaction;
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
	public String[] getTransaction() {
		return transaction;
	}
	public void setTransaction(String[] transaction) {
		this.transaction = transaction;
	}

}
