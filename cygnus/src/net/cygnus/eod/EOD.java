package net.cygnus.eod;

import java.sql.Timestamp;

public class EOD {

	private Integer eodId;
	private String branchId;
	private String eodStatus;
	private Timestamp postingDate;
	private String eodBy;
	private Double endBal;
	private Timestamp serverTimestamp;
	
	public Integer getEodId() {
		return eodId;
	}
	public void setEodId(Integer eodId) {
		this.eodId = eodId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getEodStatus() {
		return eodStatus;
	}
	public void setEodStatus(String eodStatus) {
		this.eodStatus = eodStatus;
	}
	public Timestamp getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Timestamp postingDate) {
		this.postingDate = postingDate;
	}
	public String getEodBy() {
		return eodBy;
	}
	public void setEodBy(String eodBy) {
		this.eodBy = eodBy;
	}
	public Double getEndBal() {
		return endBal;
	}
	public void setEndBal(Double endBal) {
		this.endBal = endBal;
	}
	public Timestamp getServerTimestamp() {
		return serverTimestamp;
	}
	public void setServerTimestamp(Timestamp serverTimestamp) {
		this.serverTimestamp = serverTimestamp;
	}
	
}
