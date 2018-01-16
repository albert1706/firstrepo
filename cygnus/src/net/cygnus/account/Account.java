package net.cygnus.account;

import id.co.nds.beans.BasicUser;

import java.io.Serializable;

import net.cygnus.parameter.acc.AccCommon;

public class Account extends AccCommon implements Serializable {

	private static final long serialVersionUID = 1428331856777852889L;
	public static final String TABLENAME = "MS_ACC_D";
	
	
	private String accNo;
	private String accName;
	private String owner;
	private String accNoHeading;
	private String level;
	private String toa;
	private String dc;
	
	public Account() {
		
	}
	
	public Account(Long id) {
		super.setId(id);
	}
	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAccNoHeading() {
		return accNoHeading;
	}
	public void setAccNoHeading(String accNoHeading) {
		this.accNoHeading = accNoHeading;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public String getToa() {
		return toa;
	}

	public void setToa(String toa) {
		this.toa = toa;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}
	
}
