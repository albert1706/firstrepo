package net.cygnus.transaction.expenses.web.action;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.parameter.Parameter;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import net.cygnus.transaction.mapping.TransactionMappingFcd;

public class ExpensesTrxInval extends ActionSupport {

	private ArrayList<Parameter> listTrx = new ArrayList<Parameter>();
	
	public String prepareCreate() throws GeneralException {
		return SUCCESS;
	}
	
}
