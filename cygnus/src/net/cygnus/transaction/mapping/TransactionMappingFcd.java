package net.cygnus.transaction.mapping;

import id.co.nds.beans.BasicUser;
import id.co.nds.beans.JfxGrid;
import id.co.nds.core.exception.AppException;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.parameter.Parameter;
import id.co.nds.parameter.ParameterFcd;
import id.co.nds.util.AppUtil;
import id.co.nds.util.Util;
import id.co.nds.webapp.GeneralConstants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.cygnus.transaction.TransactionForm;

public class TransactionMappingFcd {

	public static synchronized Long getId() {
		return System.currentTimeMillis();
	}
	
	public static ArrayList getMappingList(TransactionMapping filter) throws GeneralException {
		ArrayList rsl = new ArrayList();
		
		DBConnection dbconn = null;
		SQLStandard sql;
		ArrayList fields = new ArrayList();
		String tableName = TransactionMapping.TABLE_NAME;
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			// called for table search
			fields.add("distinct trx");
			fields.add("trx_name");
			fields.add("class_code");
			fields.add("update_by");
			fields.add("update_date");
			criterias = CriteriasDB.buildCriteriaFromObj(filter, null, MODE.EQUAL);
			rsl = sql.executeQueryList(tableName, fields, criterias, null, TransactionMapping.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return rsl;
	}
	
	public static ArrayList doSearch(TransactionMapping filter, JfxGrid page) throws GeneralException {
		ArrayList rsl = new ArrayList();
		
		DBConnection dbconn = null;
		SQLStandard sql;
		ArrayList fields = new ArrayList();
		String tableName = TransactionMapping.TABLE_NAME;
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			// called for table search
			fields.add("distinct trx");
			fields.add("trx_name");
			fields.add("class_code");
			fields.add("update_by");
			fields.add("update_date");
			criterias = CriteriasDB.buildCriteriaFromObj(filter, null, MODE.LIKE);
			ArrayList order = new ArrayList();
			order.add(page.getSortIdx() + " " + page.getSortMode());
			rsl = sql.executeQueryPaging(tableName, fields, criterias, order, page, TransactionMapping.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return rsl;
	}
	
	public static List getTransactionType() throws Exception {
		return ParameterFcd.getListParameter(Parameter.KEY_PARAM_TRX_TYPE);
	}
	
	public static void saveMapping(TransactionMapping tm, List<String> debet, List<String> credit, BasicUser actor) throws AppException {
		
		Timestamp today = new Timestamp (System.currentTimeMillis());
		ArrayList saveIt = new ArrayList();
		for (String d : debet) {
			TransactionMapping m = tm.copy();
			m.setDc(GeneralConstants.DEBET);
			m.setAccNo(d);
			m.setUpdateBy(actor.getUserId());
			m.setUpdateDate(today);
			saveIt.add(m);
		}
		for (String c: credit) {
			TransactionMapping m = tm.copy();
			m.setDc(GeneralConstants.CREDIT);
			m.setAccNo(c);
			m.setUpdateBy(actor.getUserId());
			m.setUpdateDate(today);
			saveIt.add(m);
		}
		
		DBConnection dbconn = null;
		SQLStandard sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			dbconn.beginTransaction();
			
			Properties criterias = new Properties();
			criterias.put("trx", "='"+tm.getTrx()+"'");
			sql.executeDelete(TransactionMapping.TABLE_NAME, criterias);
			
			for (Object object : saveIt) {
				TransactionMapping m= (TransactionMapping) object;
				m.setMpId(getId());
				sql.executeInsert(TransactionMapping.TABLE_NAME, object, TransactionMapping.class);	
			}
			
			dbconn.commitTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (dbconn != null) dbconn.rollback();
			throw new AppException(e);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			if (dbconn != null) dbconn.rollback();
			throw new AppException(e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
	}
	
public static ArrayList<TransactionMapping> getMappingAcc(String trx) throws GeneralException {
		
		ArrayList<TransactionMapping> out = new ArrayList<TransactionMapping>(); 
		DBConnection dbconn = null;
		SQLCustomStatement sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLCustomStatement(dbconn);
			
			Properties criterias = new Properties();
			criterias.put("criterias", "trx='" + trx + "'");
			
			out = sql.executeQueryList("sql-transaction", "getClassCodeAccMap", criterias, TransactionMapping.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally  {
			if (dbconn != null) dbconn.close();
		}
		
		return out;
		
	}
	
	public static ArrayList<TransactionMapping> getMappingCoa(String trx) throws GeneralException {
		
		ArrayList<TransactionMapping> out = new ArrayList<TransactionMapping>(); 
		DBConnection dbconn = null;
		SQLCustomStatement sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLCustomStatement(dbconn);
			
			Properties criterias = new Properties();
			criterias.put("criterias", "trx='" + trx + "'");
			
			out = sql.executeQueryList("sql-transaction", "getClassCodeCoaMap", criterias, TransactionMapping.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally  {
			if (dbconn != null) dbconn.close();
		}
		
		return out;
		
	}
	
	/**
	 * Prepare GL data based on user input in transaction form
	 * 
	 * @param trxFrm
	 * @return
	 * @throws GeneralException 
	 */
	public static Map<String, TransactionMapping> prepareTransaction(TransactionForm trxFrm) throws GeneralException {
		Map<String, TransactionMapping> out = new HashMap<String, TransactionMapping>();
		
		DBConnection dbconn = null;
		SQLCustomStatement sqlC = null;
		ArrayList<TransactionMapping> l = new ArrayList<TransactionMapping>();
				
		try {
			dbconn = new DBConnection();
			sqlC = new SQLCustomStatement (dbconn);
			
			Properties param = new Properties();
			param.put("trxType", AppUtil.normalizeStringWithQuote(trxFrm.getTrxType()));
			boolean bothExist = false;
			if (Util.isNotEmpty(trxFrm.getCoa1()) && Util.isNotEmpty(trxFrm.getCoa2())) {
				param.put("criterias", "(coa.coa_id = "+AppUtil.normalizeStringWithQuote(trxFrm.getCoa1())+" or coa.coa_id = "+AppUtil.normalizeStringWithQuote(trxFrm.getCoa2())+")");
				bothExist = true;
			} else {
				param.put("criterias", "1=1");
			}
			l = sqlC.executeQueryList("sql-transaction", "getMapping", param, TransactionMapping.class);
			
			String lastSide = null;
			for (TransactionMapping m : l) {
				if (bothExist) {
					out.put(m.getAccNo(), m);
				} else {
					if (Util.isNotEmpty(trxFrm.getCoa1())) {
						if (trxFrm.getCoa1().startsWith(m.getAccNo()) && !out.containsKey(m.getAccNo())) {
							out.put(m.getAccNo(), m);		// save this account
							lastSide = m.getDc();			// is debet / credit
						}
					} else if (Util.isNotEmpty(trxFrm.getCoa2())) {
						if (trxFrm.getCoa2().startsWith(m.getAccNo()) && !out.containsKey(m.getAccNo())) {
							out.put(m.getAccNo(), m);		// save this account
							lastSide = m.getDc();			// is debet / credit
						}
					}
					
					if (Util.isNotEmpty(lastSide) && !m.getDc().equalsIgnoreCase(lastSide)) {
						out.put(m.getAccNo(), m);		// get the first opposite side of latest account and save it
						break;
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage());
		} finally {
			if (dbconn != null) dbconn.close();
		}
				
		return out;
	}
	
}
