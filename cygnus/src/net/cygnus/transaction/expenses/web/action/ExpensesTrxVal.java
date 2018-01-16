package net.cygnus.transaction.expenses.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;
import net.cygnus.transaction.TransactionFcd;
import net.cygnus.transaction.TransactionForm;
import net.cygnus.transaction.cash_offering.OfferingTransactionFcd;

public class ExpensesTrxVal extends JFXActionSupport {

	private TransactionForm transaction;
	
	public String doTransaction() throws Exception {

		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		TransactionFcd.doGenericTransaction(transaction, userSession.getUser());
		
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
