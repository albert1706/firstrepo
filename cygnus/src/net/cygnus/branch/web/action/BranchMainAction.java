package net.cygnus.branch.web.action;

import id.co.nds.AppConstants;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.branch.BranchFcd;

public class BranchMainAction extends JFXActionSupport {

	private List rsl = new ArrayList();
	
	public String getListForCombo() throws Exception {
		
		String heading = getRequest().getParameter("genericAcc");
		
		if (heading.equals(AppConstants.GENERIC_ACC_N)) {
			rsl = BranchFcd.getListBranches(null);
		}
		
		return SUCCESS;
	}

	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}
	
}
