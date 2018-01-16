package net.cygnus.transaction.manual_journal.web.action;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.account.Account;
import net.cygnus.account.AccountFcd;
import net.cygnus.coa.Coa;
import net.cygnus.coa.CoaFcd;
import net.cygnus.segment.SegmentFcd;
import id.co.nds.webapp.JFXActionSupport;

public class ManualJournalInvalAction extends JFXActionSupport {

	List<Coa> listAcc = new ArrayList();
	List rsl = new ArrayList();
	
	public String prepareCreate() throws Exception {
		listAcc = CoaFcd.getListCoa(null);
		rsl = SegmentFcd.getListSegments(null);
		return SUCCESS;
	}

	public List<Coa> getListAcc() {
		return listAcc;
	}
	public void setListAcc(List<Coa> listAcc) {
		this.listAcc = listAcc;
	}

	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}
	
}
