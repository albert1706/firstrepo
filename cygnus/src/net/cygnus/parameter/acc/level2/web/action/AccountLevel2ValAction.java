package net.cygnus.parameter.acc.level2.web.action;

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
import net.cygnus.parameter.acc.level2.AccLevel2Fcd;
import net.cygnus.segment.Segment;
import net.cygnus.segment.SegmentFcd;

public class AccountLevel2ValAction extends JFXActionSupport implements IValAction {

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
//		if (HelperUtil.isEmpty(user.getUserId())) addActionError(getText("error.user.id.cannot.empty"));
		if (HelperUtil.isEmpty(acc.getParentId())) addActionError(getText("error.acc.level1.required"));
		if (HelperUtil.isEmpty(acc.getAccLv2Id())) addActionError(getText("error.acc.code.level2.required"));
		if (HelperUtil.isEmpty(acc.getAccLv2Name())) addActionError(getText("error.acc.name.level2.required"));
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
			} catch (GeneralException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		AccLevel2Fcd.create(acc, branchId, listSegment, userSession.getUser(), ACCLEVEL.Lv2);
			
		addActionMessage("Akun baru ["+ acc.getAccLv2Name() +"] dengan ID "+acc.getAccLv2Id() +" telah berhasil di daftarkan");
		
		return SUCCESS;
	}
	
	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		AccCommon oldAcc = (AccCommon) getFromModuleSession("currentAcc");
		AccCommonFcd.update(oldAcc, acc, branchId, listSegment, userSession.getUser(), "Pembaharuan Data Akun ID "+acc.getAccLv2Id()+" pada kelompok akun [L1] => ["+acc.getAccLv1Name()+"("+acc.getAccLv1Id()+")]", ACCLEVEL.Lv2);
		
		addActionMessage("Akun baru ["+ acc.getAccLv2Name() +"] dengan ID "+acc.getAccLv2Id() +" telah berhasil di perbaharui");
		
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

	public List getListSegment() {
		return listSegment;
	}

	public void setListSegment(List listSegment) {
		this.listSegment = listSegment;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
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

	public List getLeftSegment() {
		return leftSegment;
	}

	public void setLeftSegment(List leftSegment) {
		this.leftSegment = leftSegment;
	}

}
