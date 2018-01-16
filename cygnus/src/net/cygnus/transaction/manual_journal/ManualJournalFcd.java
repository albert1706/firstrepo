package net.cygnus.transaction.manual_journal;

import id.co.nds.AppConstants;
import id.co.nds.beans.Activity;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.log.LogFcd;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.joda.time.DateTime;

import net.cygnus.coa.CoaFcd;
import net.cygnus.eod.EOD;
import net.cygnus.eod.EodFcd;
import net.cygnus.gl.GL;
import net.cygnus.gl.GLFcd;
import net.cygnus.transaction.Transaction;
import net.cygnus.transaction.TransactionFcd;

public class ManualJournalFcd {

	private static ArrayList<GL> parseToGLObj(String segmentId, String debetValues, String creditValues) {
		
		System.out.println("creditValues : " + creditValues);
		System.out.println("debetValues : " + debetValues);
		// parse to object
		ArrayList<GL> listGl = new ArrayList();
		String[] creditSplit = creditValues.split("\\|");
		String[] debetSplit = debetValues.split("\\|");
		
		// object credit 
		for (String c : creditSplit) {
			String[] s = c.split("-");
			GL glCredit = new GL();
			String coa = s[0];
			Double amt = Double.parseDouble(s[1]);
			glCredit.setAccNo(CoaFcd.getAccFromCoaId(coa));
//			glCredit.setGlId(GLFcd.getId());
			glCredit.setBranchId(AppConstants.BRANCH_HO);
			glCredit.setCoa(coa);
			glCredit.setTrxId(null); // set later after save trx
			glCredit.setDebitBase(0.0);
			glCredit.setDebitCcy(0.0);
			glCredit.setCreditBase(amt);
			glCredit.setCreditCcy(amt); // KEY ASSUMPTION : no foreign currency
			glCredit.setBalanceBase(0.0);
			glCredit.setBalanceCcy(0.0);
			glCredit.setGlDate(null); //set later after save trx
			
			listGl.add(glCredit);
		}
		
		for (String d : debetSplit) {
			String[] s = d.split("-");
			GL glDebet = new GL();
			String coa = s[0];
			Double amt = Double.parseDouble(s[1]);
			glDebet.setAccNo(CoaFcd.getAccFromCoaId(coa));
//			glDebet.setGlId(GLFcd.getId());
			glDebet.setBranchId(AppConstants.BRANCH_HO);
			glDebet.setCoa(coa);
			glDebet.setTrxId(null); // set later after save trx
			glDebet.setDebitBase(amt);
			glDebet.setDebitCcy(amt);
			glDebet.setCreditBase(0.0);
			glDebet.setCreditCcy(0.0); // KEY ASSUMPTION : no foreign currency
			glDebet.setBalanceBase(0.0);
			glDebet.setBalanceCcy(0.0);
			glDebet.setGlDate(null); //set later after save trx
			
			listGl.add(glDebet);
		}
		
		return listGl;
	}
	
	public static void doManualJournal(ManualJournalForm form, BasicUser actor) throws GeneralException {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String creditValues = form.getSelectedCredit();
		String debetValues = form.getSelectedDebet();
		ArrayList<GL> listGl = new ArrayList();
		listGl = parseToGLObj(form.getSegmentId(), debetValues, creditValues);
		
		// means this transaction will do auto-journal
		form.setPostingDate(now);
		
		DBConnection dbconn = null;
		
		try {
			dbconn = new DBConnection();
			dbconn.beginTransaction();
			
			// 1. saveTransaction
			Transaction trx = TransactionFcd.saveTransaction(form, actor, dbconn);
			
			// 2. do Journal
			for (GL gl : listGl) {
				gl.setGlDate(trx.getTrxDate());
				gl.setTrxId(trx.getTrxId());
				// is-backdated ?????
				DateTime glDateJoda = new DateTime(trx.getTrxDate());
				EOD eod = EodFcd.getLatestEod();
				if (glDateJoda.isAfter(eod.getPostingDate().getTime())) {
					gl.setBackdated(AppConstants.BACKDATED_NO);
				} else {
					gl.setBackdated(AppConstants.BACKDATED_YES);
				}
				
				GLFcd.saveGL(gl, dbconn);
			}
			
			// 3. log user activity
			Activity log = new Activity();
			log.setDescription("Melakukan Transaksi " + trx.getTrxName() + " dengan MANUAL JOURNAL");
			log.setLogBy(actor.getUserId());
			log.setLogDate(now);
			log.setModuleId(actor.getOnModule());
			log.setObjectId(String.valueOf(trx.getTrxId()));
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
		} catch (SQLException e) {
			if (dbconn != null) dbconn.rollback();
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
	}
	
}
