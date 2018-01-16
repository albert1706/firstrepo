package net.cygnus.transaction;

import id.co.nds.beans.Actor;

import java.sql.Timestamp;

public class Transaction extends Actor {

	private Long trxId;
	private String branchId;
	private String refId;
	private Timestamp trxDate;
	private Double trxAmt;
	private Integer trxCcy;
	private String eodStatus;
	private Timestamp postingDate;
	private String trxName;
	private String trxType;
	private Timestamp warkatDate;
	private String beneBankName;
	private String beneAccNo;
	private String beneName;
	private String description;
	
	
	public Long getTrxId() {
		return trxId;
	}
	public void setTrxId(Long trxId) {
		this.trxId = trxId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public Timestamp getTrxDate() {
		return trxDate;
	}
	public void setTrxDate(Timestamp trxDate) {
		this.trxDate = trxDate;
	}
	public Double getTrxAmt() {
		return trxAmt;
	}
	public void setTrxAmt(Double trxAmt) {
		this.trxAmt = trxAmt;
	}
	public Integer getTrxCcy() {
		return trxCcy;
	}
	public void setTrxCcy(Integer trxCcy) {
		this.trxCcy = trxCcy;
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
	public String getTrxName() {
		return trxName;
	}
	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}
	public String getTrxType() {
		return trxType;
	}
	public void setTrxType(String trxType) {
		this.trxType = trxType;
	}
	public Timestamp getWarkatDate() {
		return warkatDate;
	}
	public void setWarkatDate(Timestamp warkatDate) {
		this.warkatDate = warkatDate;
	}
	public String getBeneBankName() {
		return beneBankName;
	}
	public void setBeneBankName(String beneBankName) {
		this.beneBankName = beneBankName;
	}
	public String getBeneAccNo() {
		return beneAccNo;
	}
	public void setBeneAccNo(String beneAccNo) {
		this.beneAccNo = beneAccNo;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
