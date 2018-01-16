package net.cygnus.transaction.manual_journal;

import net.cygnus.transaction.Transaction;
import net.cygnus.transaction.TransactionForm;

public class ManualJournalForm extends TransactionForm {

	private String segmentId;
	private String selectedDebet;
	private String selectedCredit;
	
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getSelectedDebet() {
		return selectedDebet;
	}
	public void setSelectedDebet(String selectedDebet) {
		this.selectedDebet = selectedDebet;
	}
	public String getSelectedCredit() {
		return selectedCredit;
	}
	public void setSelectedCredit(String selectedCredit) {
		this.selectedCredit = selectedCredit;
	}
	
}
