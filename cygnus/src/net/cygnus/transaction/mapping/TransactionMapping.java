package net.cygnus.transaction.mapping;

import id.co.nds.beans.Actor;

public class TransactionMapping extends Actor {

	public static final String TABLE_NAME = "mp_acc_trx";
	
	private Long mpId;
	private String trx;
	private String accNo;
	private String coaId;
	private String coaName;
	private String dc;
	private String trxName;
	private String classCode;
	
	public TransactionMapping copy() {
		TransactionMapping m = new TransactionMapping();
		m.setMpId(mpId);
		m.setTrx(trx);
		m.setAccNo(accNo);
		m.setCoaId(coaId);
		m.setCoaName(coaName);
		m.setDc(dc);
		m.setTrxName(trxName);
		m.setClassCode(classCode);
		return m;
	}
	
	public Long getMpId() {
		return mpId;
	}
	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}
	public String getTrx() {
		return trx;
	}
	public void setTrx(String trx) {
		this.trx = trx;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getCoaId() {
		return coaId;
	}
	public void setCoaId(String coaId) {
		this.coaId = coaId;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getTrxName() {
		return trxName;
	}

	public void setTrxName(String trxName) {
		this.trxName = trxName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
}
