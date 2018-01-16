package net.cygnus.gl;

import id.co.nds.AppConstants;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import net.cygnus.eod.EOD;
import net.cygnus.eod.EodFcd;
import net.cygnus.transaction.TransactionForm;
import net.cygnus.transaction.mapping.TransactionMapping;
import net.cygnus.transaction.mapping.TransactionMappingFcd;

import org.joda.time.DateTime;

public class GLFcd {

	public static synchronized Long getId() {
		return System.currentTimeMillis();
	}
	
	public static ArrayList<GL> doJournal(TransactionForm trxFrm, BasicUser actor, DBConnection dbconn) throws GeneralException {

		ArrayList<GL> out = new ArrayList<GL>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		// get mapping data
		Map<String, TransactionMapping> pMapping = TransactionMappingFcd.prepareTransaction(trxFrm);
		
		for (Map.Entry<String, TransactionMapping> e : pMapping.entrySet()) {
			  String key = e.getKey();
			  TransactionMapping value = e.getValue();
			  
			  GL ledger = new GL();
			  ledger.setAccNo(value.getAccNo());
//			  ledger.setGlId(getId());
			  ledger.setBranchId(AppConstants.BRANCH_HO);
			  if (trxFrm.getCoa1().startsWith(value.getAccNo())) {
				  ledger.setCoa(trxFrm.getCoa1());  
			  } else {
				  ledger.setCoa(trxFrm.getCoa2());
			  }
			  ledger.setTrxId(trxFrm.getTrxId());
			  if (value.getDc().equalsIgnoreCase(AppConstants.DEBET)) {
				  ledger.setDebitBase(trxFrm.getTrxAmt());
				  ledger.setDebitCcy(trxFrm.getTrxAmt()); // KEY ASSUMPTION : no foreign currency
				  ledger.setCreditBase(0.0);
				  ledger.setCreditCcy(0.0);
			  } else {
				  ledger.setDebitBase(0.0);
				  ledger.setDebitCcy(0.0);
				  ledger.setCreditBase(trxFrm.getTrxAmt());
				  ledger.setCreditCcy(trxFrm.getTrxAmt()); // KEY ASSUMPTION : no foreign currency
			  }
			  ledger.setBalanceBase(0.0);
			  ledger.setBalanceCcy(0.0);
			  ledger.setGlDate(now);
			  
			  // is-backdated ?????
			  DateTime glDateJoda = new DateTime(ledger.getGlDate());
			  EOD eod = EodFcd.getLatestEod();
			  if (glDateJoda.isAfter(eod.getPostingDate().getTime())) {
				  ledger.setBackdated(AppConstants.BACKDATED_NO);
			  } else {
				  ledger.setBackdated(AppConstants.BACKDATED_YES);
			  }
			  
			  ledger.setExcRate(null);
			  ledger.setTargetCcy(null);
			  saveGL(ledger, dbconn);
			  out.add(ledger);
		}
		
		return out;
	}
	
	public static void saveGL(GL gl, DBConnection dbconn) throws GeneralException  {
		
		try {
			SQLStandard sql = new SQLStandard(dbconn);
			gl.setGlId(getId());
			sql.executeInsert("tx_gl", gl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
}
