package net.cygnus.gl;

import java.sql.Timestamp;

public class GL {

	private String accNo;
	private Long glId;
	private String branchId;
	private String coa;
	private Long trxId;
	private Double debitBase;
	private Double creditBase;
	private Double debitCcy;
	private Double creditCcy;
	private Double balanceBase;
	private Double balanceCcy;
	private String backdated;
	private Timestamp postingDate;
	private Double excRate;
	private Integer targetCcy;
	private Timestamp glDate;
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public Long getGlId() {
		return glId;
	}
	public void setGlId(Long glId) {
		this.glId = glId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getCoa() {
		return coa;
	}
	public void setCoa(String coa) {
		this.coa = coa;
	}
	public Long getTrxId() {
		return trxId;
	}
	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}
	public Double getDebitBase() {
		return debitBase;
	}
	public void setDebitBase(Double debitBase) {
		this.debitBase = debitBase;
	}
	public Double getCreditBase() {
		return creditBase;
	}
	public void setCreditBase(Double creditBase) {
		this.creditBase = creditBase;
	}
	public Double getDebitCcy() {
		return debitCcy;
	}
	public void setDebitCcy(Double debitCcy) {
		this.debitCcy = debitCcy;
	}
	public Double getCreditCcy() {
		return creditCcy;
	}
	public void setCreditCcy(Double creditCcy) {
		this.creditCcy = creditCcy;
	}
	public Double getBalanceBase() {
		return balanceBase;
	}
	public void setBalanceBase(Double balanceBase) {
		this.balanceBase = balanceBase;
	}
	public Double getBalanceCcy() {
		return balanceCcy;
	}
	public void setBalanceCcy(Double balanceCcy) {
		this.balanceCcy = balanceCcy;
	}
	public String getBackdated() {
		return backdated;
	}
	public void setBackdated(String backdated) {
		this.backdated = backdated;
	}
	public Timestamp getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Timestamp postingDate) {
		this.postingDate = postingDate;
	}
	public Double getExcRate() {
		return excRate;
	}
	public void setExcRate(Double excRate) {
		this.excRate = excRate;
	}
	public Integer getTargetCcy() {
		return targetCcy;
	}
	public void setTargetCcy(Integer targetCcy) {
		this.targetCcy = targetCcy;
	}
	public Timestamp getGlDate() {
		return glDate;
	}
	public void setGlDate(Timestamp glDate) {
		this.glDate = glDate;
	}
	
}
