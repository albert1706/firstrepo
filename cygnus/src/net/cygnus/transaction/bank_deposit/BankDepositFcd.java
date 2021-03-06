package net.cygnus.transaction.bank_deposit;

import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.ValidationException;
import id.co.nds.parameter.Parameter;
import id.co.nds.parameter.ParameterFcd;
import net.cygnus.transaction.TransactionFcd;
import net.cygnus.transaction.TransactionForm;

public class BankDepositFcd {

	public static TransactionForm doTransaction(TransactionForm trxFrm, BasicUser actor) throws Exception, ValidationException {
		
		// get transaction nname : 
		Parameter p = ParameterFcd.getParameter(Parameter.KEY_PARAM_TRX_TYPE, "03bank_deposit");
		trxFrm.setTrxName(p.getValue2Param());
		
		return TransactionFcd.doGenericTransaction(trxFrm, actor);
		
	}
	
}
