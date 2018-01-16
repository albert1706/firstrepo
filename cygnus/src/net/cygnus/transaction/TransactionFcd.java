package net.cygnus.transaction;

import id.co.nds.AppConstants;
import id.co.nds.beans.Activity;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.core.exception.ValidationException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.log.LogFcd;
import id.co.nds.webapp.GeneralConstants;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.cygnus.gl.GL;
import net.cygnus.gl.GLFcd;

public class TransactionFcd {

	public static synchronized Long getId() {
		return System.currentTimeMillis();
	}
	
	public static TransactionForm doGenericTransaction(TransactionForm trxFrm, BasicUser actor) throws Exception, ValidationException {
		TransactionForm out = null;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		// means this transaction will do auto-journal
		trxFrm.setPostingDate(now);
		
		DBConnection dbconn = null;
		
		try {
			dbconn = new DBConnection();
			dbconn.beginTransaction();
			
			// 1. saveTransaction
			Transaction t = TransactionFcd.saveTransaction(trxFrm, actor, dbconn);
			trxFrm.setTrxId(t.getTrxId());
			
			// 2. do Journal
			ArrayList<GL> listTrxGl = GLFcd.doJournal(trxFrm, actor, dbconn);
			
			// 3. update coa balance
			
			// 4. log user activity
			Activity log = new Activity();
			log.setDescription("Melakukan Transaksi " + t.getTrxName());
			log.setLogBy(actor.getUserId());
			log.setLogDate(now);
			log.setModuleId(actor.getOnModule());
			log.setObjectId(String.valueOf(t.getTrxId()));
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
			
		} catch (Exception e) {
			if (dbconn != null) dbconn.rollback();
			throw e;
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return out;
	}
	
	public static Transaction saveTransaction(Transaction trx, BasicUser actor, DBConnection dbconn) throws GeneralException {
		Transaction t = trx;
		Long id = getId();
		Timestamp now = new Timestamp(id);
		
		t.setTrxId(id);
		t.setBranchId(AppConstants.BRANCH_HO);
		t.setRefId(AppConstants.REF_ID);
		t.setTrxCcy(AppConstants.HOME_BASE_CCY);
		t.setEodStatus(AppConstants.EOD_STATUS_NO);
//		t.setPostingDate(now);
		t.setCreateDate(now);
		t.setCreateBy(actor.getUserId());
		t.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);
		
		try {
			SQLStandard sql = new SQLStandard(dbconn);
			
			sql.executeInsert("TX_TRANSACTION", t, Transaction.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
		
		return t;
	}
	
}
