package net.cygnus.transaction.bank_deposit.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;
import net.cygnus.transaction.TransactionForm;
import net.cygnus.transaction.bank_deposit.BankDepositFcd;

public class BankDepositVal extends JFXActionSupport {

	private TransactionForm transaction;
	
	public String doTransaction() throws Exception {

		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		BankDepositFcd.doTransaction(transaction, userSession.getUser());
		
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
