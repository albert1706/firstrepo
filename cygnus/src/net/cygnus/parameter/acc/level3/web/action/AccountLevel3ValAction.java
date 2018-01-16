package net.cygnus.parameter.acc.level3.web.action;

import id.co.nds.AppConstants;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.session.UserSession;
import id.co.nds.standardization.IValAction;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.helper.HelperUtil;
import id.co.nds.webapp.session.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.account.Account;
import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;
import net.cygnus.parameter.acc.level3.AccLevel3Fcd;
import net.cygnus.segment.Segment;
import net.cygnus.segment.SegmentFcd;

public class AccountLevel3ValAction extends JFXActionSupport implements IValAction {

	private static final long serialVersionUID = -790811927392450108L;
	private Account acc = new Account();
	private List listSegment = new ArrayList();
	private List leftSegment = new ArrayList();
	private String branchId = null;
	private List rsl = new ArrayList();
	private List rsl2 = new ArrayList();
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if (HelperUtil.isEmpty(acc.getAccLv1Id())) addActionError(getText("error.acc.level1.required"));
		if (HelperUtil.isEmpty(acc.getParentId())) addActionError(getText("error.acc.level2.required"));
		if (HelperUtil.isEmpty(acc.getAccLv3Id())) addActionError(getText("error.acc.code.level3.required"));
		if (HelperUtil.isEmpty(acc.getAccLv3Name())) addActionError(getText("error.acc.name.level3.required"));
		if (HelperUtil.isEmpty(acc.getHeading())) addActionError(getText("error.heading.required"));
		if (acc.getHeading() != null && acc.getHeading().equalsIgnoreCase(AppConstants.HEADING_NO)) {
			if (listSegment == null || listSegment.size() < 1) addActionError(getText("error.segments.required"));
			
			ArrayList<Segment> allSegments;
			ArrayList<Segment> leftSegments = new ArrayList<Segment>();
			ArrayList<Segment> rightSegments = new ArrayList<Segment>();
			try {
				allSegments = SegmentFcd.getListSegments(null);
				for (Segment s : allSegments) {
					for (Object leftItem : leftSegment) {
						if (s.getSegmentId().equals(leftItem)) {
							leftSegments.add(s);
							break;
						}
					}
					for (Object rightItem : listSegment) {
						if (s.getSegmentId().equals(rightItem)) {
							rightSegments.add(s);
							break;
						}
					}
				}
				
				rsl2 = rightSegments;
				rsl = leftSegments;
				acc.setAccLv2Id(String.valueOf(acc.getParentId()));
				acc.setAccLv1Id(acc.getAccLv1Id());
			} catch (GeneralException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (HelperUtil.isEmpty(acc.getToa())) addActionError(getText("error.toa.required"));
			if (HelperUtil.isEmpty(acc.getDc())) addActionError(getText("error.jenis.akun.required"));
		}
	}
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// Call Main process
		AccLevel3Fcd.create(acc, getBranchId(), getListSegment(), userSession.getUser(), ACCLEVEL.Lv3);
		
		addActionMessage("Akun baru ["+ acc.getAccLv3Name() +"] dengan ID "+acc.getAccLv3Id() +" telah berhasil di daftarkan");
		
		return SUCCESS;
	}
	
	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		String activityDesc = "Pembaharuan Data Akun ID "+acc.getAccLv2Id()+" pada kelompok akun [L1-L2] => ["+acc.getAccLv1Name()+"("+acc.getAccLv1Id()+")-"+acc.getAccLv2Name()+"("+acc.getAccLv2Id()+"]";
		AccCommon oldAcc = (AccCommon) getFromModuleSession("currentAcc");
		AccCommonFcd.update(oldAcc, acc, getBranchId(), getListSegment(), userSession.getUser(), activityDesc, ACCLEVEL.Lv3);
		
		addActionMessage("Akun baru ["+ acc.getAccLv3Name() +"] dengan ID "+acc.getAccLv3Id() +" telah berhasil di perbaharui");
		
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return acc;
	}

	@Override
	public String doDelete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Account getAcc() {
		return acc;
	}

	public void setAcc(Account acc) {
		this.acc = acc;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List getListSegment() {
		return listSegment;
	}

	public void setListSegment(List listSegment) {
		this.listSegment = listSegment;
	}

	public List getLeftSegment() {
		return leftSegment;
	}

	public void setLeftSegment(List leftSegment) {
		this.leftSegment = leftSegment;
	}

	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}

	public List getRsl2() {
		return rsl2;
	}

	public void setRsl2(List rsl2) {
		this.rsl2 = rsl2;
	}

}
