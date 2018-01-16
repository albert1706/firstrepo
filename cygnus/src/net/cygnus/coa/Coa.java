package net.cygnus.coa;

import id.co.nds.beans.Actor;

public class Coa extends Actor {

	public static final String TABLENAME = "MS_COA";
	public static final String COA_TYPE_PL = "PL";
	public static final String COA_TYPE_BS = "BS";
	public static final String COA_BUDGET_Y = "Y";
	public static final String COA_BUDGET_N = "N";
	public static final Integer IDR = 360;
	
	private String coaId;
	private String coaName;
	private String branchId;
	private String segmentId;
	private String accNo;
	private String coaType;
	private String coaBudget;
	private Integer ccy;
	
	public Coa() {
		
	}
	
	public Coa(String accNo) {
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
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getCoaBudget() {
		return coaBudget;
	}
	public void setCoaBudget(String coaBudget) {
		this.coaBudget = coaBudget;
	}
	public String getCoaType() {
		return coaType;
	}
	public void setCoaType(String coaType) {
		this.coaType = coaType;
	}

	public Integer getCcy() {
		return ccy;
	}

	public void setCcy(Integer ccy) {
		this.ccy = ccy;
	}
	
	
}
