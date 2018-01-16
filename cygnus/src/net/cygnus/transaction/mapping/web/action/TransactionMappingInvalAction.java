package net.cygnus.transaction.mapping.web.action;

import id.co.nds.standardization.IInvalAction;
import id.co.nds.webapp.GeneralConstants;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

import net.cygnus.account.Account;
import net.cygnus.account.AccountFcd;
import net.cygnus.transaction.mapping.TransactionMapping;
import net.cygnus.transaction.mapping.TransactionMappingFcd;

public class TransactionMappingInvalAction extends JFXActionSupport implements IInvalAction {

	String trx = "";
	List listTrx = new ArrayList();
	List<Account> listAcc = new ArrayList();
	List listDebet = new ArrayList();
	List listCredit = new ArrayList();
	TransactionMapping tm = new TransactionMapping();
	
	@Override
	public String prepareCreate() throws Exception {
		// TODO Auto-generated method stub
		
		listTrx = TransactionMappingFcd.getTransactionType();
		listAcc = AccountFcd.getListAcc(null);
		
		return SUCCESS;
	}

	@Override
	public String prepareUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		// prepare the combo
		listTrx = TransactionMappingFcd.getTransactionType();
		listAcc = AccountFcd.getListAcc(null);
		
		// prepare the value 
		ArrayList<TransactionMapping> listofmap = TransactionMappingFcd.getMappingAcc(getTrx());
		int c = 0;
		for (TransactionMapping t : listofmap) {
			if (c==0) tm = t;
			
			for (Account acc : listAcc) {
				if (acc.getAccNo().equals(t.getAccNo())) {
					if (t.getDc().equalsIgnoreCase(GeneralConstants.CREDIT)) {
						listCredit.add(acc);
					} else {
						listDebet.add(acc);
					}
				}
			}
			c++;
		}
		
		return SUCCESS;
	}

	public List getListTrx() {
		return listTrx;
	}
	public void setListTrx(List listTrx) {
		this.listTrx = listTrx;
	}

	public List getListAcc() {
		return listAcc;
	}

	public void setListAcc(List listAcc) {
		this.listAcc = listAcc;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	public List getListDebet() {
		return listDebet;
	}

	public void setListDebet(List listDebet) {
		this.listDebet = listDebet;
	}

	public List<Account> getListCredit() {
		return listCredit;
	}

	public void setListCredit(List<Account> listCredit) {
		this.listCredit = listCredit;
	}

	public TransactionMapping getTm() {
		return tm;
	}

	public void setTm(TransactionMapping tm) {
		this.tm = tm;
	}
	
}
