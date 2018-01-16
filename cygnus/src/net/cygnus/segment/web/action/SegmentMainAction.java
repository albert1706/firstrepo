package net.cygnus.segment.web.action;

import id.co.nds.AppConstants;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.branch.BranchFcd;
import net.cygnus.segment.SegmentFcd;

public class SegmentMainAction extends JFXActionSupport {

	private List rsl = new ArrayList();
	
	public String getListForCombo() throws Exception {
		
		String heading = getRequest().getParameter("genericAcc");
		
		if (heading.equals(AppConstants.GENERIC_ACC_N)) {
			rsl = SegmentFcd.getListSegments(null);
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
