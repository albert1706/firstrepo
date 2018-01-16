package net.cygnus.parameter.acc.level4.web.action;

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
import net.cygnus.parameter.acc.level4.AccLevel4Fcd;
import net.cygnus.segment.Segment;
import net.cygnus.segment.SegmentFcd;

public class AccountLevel4ValAction extends JFXActionSupport implements IValAction {

	private static final long serialVersionUID = -790811927392450108L;
	private Account acc = new Account();
	private String branchId = null;
	private List listSegment = new ArrayList();
	private List leftSegment = new ArrayList();
	private List rsl = new ArrayList();
	private List rsl2 = new ArrayList();
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		if (HelperUtil.isEmpty(acc.getAccLv1Id())) addActionError(getText("error.acc.level1.required"));
		if (HelperUtil.isEmpty(acc.getAccLv2Id())) addActionError(getText("error.acc.level2.required"));
		if (HelperUtil.isEmpty(acc.getParentId())) addActionError(getText("error.acc.level3.required"));
		if (HelperUtil.isEmpty(acc.getAccLv4Id())) addActionError(getText("error.acc.code.level4.required"));
		if (HelperUtil.isEmpty(acc.getAccLv4Name())) addActionError(getText("error.acc.name.level4.required"));
		if (HelperUtil.isEmpty(acc.getHeading())) addActionError(getText("error.heading.required"));
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
			acc.setAccLv3Id(String.valueOf(acc.getParentId()));
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// Call Main process
		AccLevel4Fcd.create(acc, getBranchId(), getListSegment(), userSession.getUser(), ACCLEVEL.Lv4);
		
		addActionMessage("Akun baru ["+ acc.getAccLv4Name() +"] dengan ID "+acc.getAccLv4Id() +" telah berhasil di daftarkan");
		
		return SUCCESS;
	}
	
	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		String activityDesc = "Pembaharuan Data Akun ID "+acc.getAccLv2Id()+" pada kelompok akun [L1-L2-L3] => ["+acc.getAccLv1Name()+"("+acc.getAccLv1Id()+")-"+acc.getAccLv2Name()+"("+acc.getAccLv2Id()+")-"+acc.getAccLv3Name()+"("+acc.getAccLv3Id()+"]";
		AccCommon oldAcc = (AccCommon) getFromModuleSession("currentAcc");
		AccCommonFcd.update(oldAcc, acc, getBranchId(), listSegment, userSession.getUser(), activityDesc, ACCLEVEL.Lv4);
		
		addActionMessage("Akun baru ["+ acc.getAccLv4Name() +"] dengan ID "+acc.getAccLv4Id() +" telah berhasil di perbaharui");
		
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
