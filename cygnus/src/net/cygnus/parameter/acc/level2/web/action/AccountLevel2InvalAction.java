package net.cygnus.parameter.acc.level2.web.action;

import id.co.nds.AppConstants;
import id.co.nds.standardization.IInvalAction;
import id.co.nds.webapp.GeneralConstants;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.account.Account;
import net.cygnus.account.AccountFcd;
import net.cygnus.coa.Coa;
import net.cygnus.coa.CoaFcd;
import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;
import net.cygnus.parameter.acc.level2.AccLevel2Fcd;
import net.cygnus.segment.Segment;
import net.cygnus.segment.SegmentFcd;

public class AccountLevel2InvalAction extends JFXActionSupport implements IInvalAction {

	private List rsl = new ArrayList();
	private List rsl2 = new ArrayList();
	private AccCommon acc = null;
	private String toa;
	private String dc;
	
	@Override
	public Object getModel() {
		acc = new AccCommon();
		return acc;
	}
	
	@Override
	public String prepareCreate() throws Exception {
		// TODO Auto-generated method stub
		
		rsl = SegmentFcd.getListSegments(null);
		
		return SUCCESS;
	}

	@Override
	public String prepareUpdate() throws Exception {
		
		// Preparing Pre-Loaded Form
		acc = AccLevel2Fcd.getDetail(acc, ACCLEVEL.Lv2);
		Coa searchCoa = new Coa();
		ArrayList<Coa> listCoa = new ArrayList<Coa>();
		ArrayList<Segment> leftSegments = SegmentFcd.getListSegments(null);
		if (acc.getHeading().equalsIgnoreCase(AppConstants.HEADING_NO)) {
			Account account = AccountFcd.getDetailAcc(new Account(acc.getId()));
			toa = account.getToa();
			dc = account.getDc();
			searchCoa.setAccNo(account.getAccNo());
			listCoa = CoaFcd.getListCoa(searchCoa);
		}
		for (Segment s : leftSegments) {
			Boolean notSelected = true;
			for (Coa coa : listCoa) {
				if (s.getSegmentId().equals(coa.getSegmentId())) {
					rsl2.add(s);
					notSelected = false;
					break;
				}
			}
			if (notSelected) rsl.add(s);
		}
		
		// Cache for update process
		saveToModuleSession("currentAcc", acc);
		
		return SUCCESS;
	}


	public AccCommon getAcc() {
		return acc;
	}

	public void setAcc(AccCommon acc) {
		this.acc = acc;
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
