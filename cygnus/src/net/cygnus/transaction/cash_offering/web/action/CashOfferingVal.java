package net.cygnus.transaction.cash_offering.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;
import net.cygnus.transaction.TransactionForm;
import net.cygnus.transaction.cash_offering.OfferingTransactionFcd;

public class CashOfferingVal extends JFXActionSupport {

	private TransactionForm transaction;
	
	public String doTransaction() throws Exception {

		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		OfferingTransactionFcd.doTransaction(transaction, userSession.getUser());
		
		addActionMessage("Transaksi Berhasil disimpan.");
		
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		transaction = new TransactionForm();
		return transaction;
	}
	
}
