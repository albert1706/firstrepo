package net.cygnus.transaction.mapping.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.standardization.IValAction;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.cygnus.transaction.mapping.TransactionMapping;
import net.cygnus.transaction.mapping.TransactionMappingFcd;

public class TransactionMappingValAction extends JFXActionSupport implements IValAction {

	private List debet = new ArrayList();
	private List kredit = new ArrayList();
	private TransactionMapping tm = new TransactionMapping();
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = getRequest().getSession();
		UserSession userSession = UserSessionManager.getFromSession(session);
		
		TransactionMappingFcd.saveMapping(tm, debet, kredit, userSession.getUser());
		
		addActionMessage("Data sudah berhasil di tambahkan (atau di ubah).");
		
		return SUCCESS;
	}

	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String doDelete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getModel() {
		return tm;
	}

	public List getDebet() {
		return debet;
	}

	public void setDebet(List debet) {
		this.debet = debet;
	}

	public List getKredit() {
		return kredit;
	}

	public void setKredit(List kredit) {
		this.kredit = kredit;
	}

	public TransactionMapping getTm() {
		return tm;
	}

	public void setTm(TransactionMapping tm) {
		this.tm = tm;
	};
	
	
}
