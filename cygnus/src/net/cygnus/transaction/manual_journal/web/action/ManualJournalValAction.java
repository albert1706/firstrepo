package net.cygnus.transaction.manual_journal.web.action;

import java.util.ArrayList;
import java.util.List;

import id.co.nds.session.UserSession;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;
import net.cygnus.account.Account;
import net.cygnus.transaction.manual_journal.ManualJournalFcd;
import net.cygnus.transaction.manual_journal.ManualJournalForm;

public class ManualJournalValAction extends JFXActionSupport {

	public ManualJournalForm form = new ManualJournalForm();
	List<Account> listAcc = new ArrayList();
	List rsl = new ArrayList();
	
	public String doTransaction() throws Exception 
	{
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		ManualJournalFcd.doManualJournal(form, userSession.getUser());

		addActionMessage("Transaksi Berhasil disimpan.");
		
		return SUCCESS;
	}

	public ManualJournalForm getForm() {
		return form;
	}

	public void setForm(ManualJournalForm form) {
		this.form = form;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}

	public List<Account> getListAcc() {
		return listAcc;
	}

	public void setListAcc(List<Account> listAcc) {
		this.listAcc = listAcc;
	}

	public List getRsl() {
		return rsl;
	}

	public void setRsl(List rsl) {
		this.rsl = rsl;
	}
}
