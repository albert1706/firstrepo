package net.cygnus.coa;

import java.sql.Timestamp;

public class CoaBalance extends Coa {

	private Timestamp lastUpdate;
	private Double prevBalBase;
	private Double prevBalCcy;
	private Integer ccy;
	private String dc;
	private Double debitBase;
	private Double debitCcy;
	private Double creditBase;
	private Double creditCcy;
	private Double lastMonthBalBase;
	private Double lastMonthBalCcy;
	private Double curMonthBalBase;
	private Double curMonthBalCcy;
	
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Double getPrevBalBase() {
		return prevBalBase;
	}
	public void setPrevBalBase(Double prevBalBase) {
		this.prevBalBase = prevBalBase;
	}
	public Double getPrevBalCcy() {
		return prevBalCcy;
	}
	public void setPrevBalCcy(Double prevBalCcy) {
		this.prevBalCcy = prevBalCcy;
	}
	public Integer getCcy() {
		return ccy;
	}
	public void setCcy(Integer ccy) {
		this.ccy = ccy;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public Double getDebitBase() {
		return debitBase;
	}
	public void setDebitBase(Double debitBase) {
		this.debitBase = debitBase;
	}
	public Double getDebitCcy() {
		return debitCcy;
	}
	public void setDebitCcy(Double debitCcy) {
		this.debitCcy = debitCcy;
	}
	public Double getCreditBase() {
		return creditBase;
	}
	public void setCreditBase(Double creditBase) {
		this.creditBase = creditBase;
	}
	public Double getCreditCcy() {
		return creditCcy;
	}
	public void setCreditCcy(Double creditCcy) {
		this.creditCcy = creditCcy;
	}
	public Double getLastMonthBalBase() {
		return lastMonthBalBase;
	}
	public void setLastMonthBalBase(Double lastMonthBalBase) {
		this.lastMonthBalBase = lastMonthBalBase;
	}
	public Double getLastMonthBalCcy() {
		return lastMonthBalCcy;
	}
	public void setLastMonthBalCcy(Double lastMonthBalCcy) {
		this.lastMonthBalCcy = lastMonthBalCcy;
	}
	public Double getCurMonthBalBase() {
		return curMonthBalBase;
	}
	public void setCurMonthBalBase(Double curMonthBalBase) {
		this.curMonthBalBase = curMonthBalBase;
	}
	public Double getCurMonthBalCcy() {
		return curMonthBalCcy;
	}
	public void setCurMonthBalCcy(Double curMonthBalCcy) {
		this.curMonthBalCcy = curMonthBalCcy;
	}
	
}
