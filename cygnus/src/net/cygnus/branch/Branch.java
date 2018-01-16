package net.cygnus.branch;

import id.co.nds.beans.Actor;

public class Branch extends Actor {

	public static final String TABLENAME = "MS_BRANCH";
	
	private String branchId;
	private String branchName;
	
	public Branch() {
		
	}
	public Branch(String branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
}
