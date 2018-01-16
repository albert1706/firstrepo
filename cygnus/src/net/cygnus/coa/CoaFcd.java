package net.cygnus.coa;

import id.co.nds.AppConstants;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.util.Util;
import id.co.nds.webapp.GeneralConstants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import net.cygnus.account.Account;
import net.cygnus.branch.Branch;
import net.cygnus.branch.BranchFcd;
import net.cygnus.gl.GL;
import net.cygnus.segment.Segment;
import net.cygnus.segment.SegmentFcd;

public class CoaFcd {

	public static ArrayList<Coa> getListCoaByAccNo(String accNo) throws GeneralException {
		Coa coa = new Coa();
		coa.setAccNo(accNo);
		return getListCoa(coa);
	}
	
	public static ArrayList<Coa> getListCoa(Coa searchCoa) throws GeneralException {
		ArrayList<Coa> listCoa = null;
		DBConnection dbconn = null;
		SQLStandard sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			criterias = CriteriasDB.buildCriteriaFromObj(searchCoa, null, MODE.LIKE);
			
			listCoa = sql.executeQueryList(Coa.TABLENAME, null, criterias, null, Coa.class);
			
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
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return listCoa;
	}
	
	public static void registerCOAFrmAccount(Account account, String branchId, List<String> listSegments, DBConnection dbconn) throws GeneralException {
		
		// TODO : add coa validation, weither it has been used for transaction or not.
		
		
		ArrayList<Branch> listBranches = new ArrayList<Branch>();
		if (Util.isNotEmpty(branchId)) {
			listBranches.add(new Branch(branchId));
		} else {
			listBranches = BranchFcd.getListBranches(null);
		}

		Hashtable<String, Segment> htSelectedSegments = new Hashtable<String, Segment>();
		ArrayList<Segment> listSegment = SegmentFcd.getListSegments(null);
		for (Segment segment : listSegment) {
			if (listSegments.contains(segment.getSegmentId())) {
				htSelectedSegments.put(segment.getSegmentId(), segment);
			}
		}
		
		int i = 0;
		for (Branch branch : listBranches) {
			for (String segmentId : listSegments) {
				
				Coa coa = new Coa();
				coa.setCoaId(buildCoaId(account.getAccNo(), branchId, segmentId));
				coa.setCoaName(account.getAccName() + " - " + htSelectedSegments.get(segmentId).getSegmentName());
				coa.setAccNo(account.getAccNo());
				coa.setBranchId(branch.getBranchId());
				coa.setSegmentId(segmentId);
				coa.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);
				coa.setUpdateBy(account.getUpdateBy());
				coa.setUpdateDate(account.getUpdateDate());
				coa.setCoaType(account.getToa());
				if (coa.getCoaType().equals(Coa.COA_TYPE_PL)) {
					coa.setCoaBudget(Coa.COA_BUDGET_Y);
				} else {
					coa.setCoaBudget(Coa.COA_BUDGET_N);
				}
				
				// delete first before insert 
				if (i == 0) {
					deleteCoa(new Coa(account.getAccNo()), dbconn);
				}
				registerCOA(coa, dbconn);
				i++;
			}			
		}
		
	}
	
	public static void registerCOA(Coa coa, DBConnection dbconn) throws GeneralException {
		
		try {
			SQLStandard sql = new SQLStandard(dbconn);
			
			sql.executeInsert(Coa.TABLENAME, coa);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
		
	}
	
	public static void deleteCoa(Coa coa, DBConnection dbconn) throws GeneralException {
		
		try {
			SQLStandard sql = new SQLStandard(dbconn);
			
			Properties criterias = CriteriasDB.buildCriteriaFromObj(coa, null, MODE.EQUAL);
			
			sql.executeDelete(Coa.TABLENAME, criterias);
			
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
		}
		
	}

	public static String getAccFromCoaId(String coaId) {
		String separator = AppConstants.DEFAULT_ACC_SEPARATOR;
		return coaId.substring(0, coaId.lastIndexOf(separator));
	}
	
	public static String buildCoaId(String accNo, String branchId, String segmentId) {
		
		StringBuilder builder = new StringBuilder();
		String separator = AppConstants.DEFAULT_ACC_SEPARATOR;
		
		builder.append(accNo).append(separator);
//		builder.append(branchId).append(separator);
		builder.append(segmentId);
		
		return builder.toString();
		
	}
	
	public static ArrayList<CoaBalance> getBalanceFromGL(ArrayList<GL> listGl) throws GeneralException {
		
		ArrayList<CoaBalance> balances = new ArrayList<CoaBalance>();
		StringBuilder sb = new StringBuilder();
		
		for (GL gl : listGl) {
			sb.append(gl.getCoa()).append(", ");
		}
		String listcoa = sb.toString().substring(0, sb.length() - 2);

		DBConnection dbconn = null;
		SQLCustomStatement sqlC = null;
		
		try {
			dbconn = new DBConnection();
			sqlC = new SQLCustomStatement(dbconn);
			
			Properties param = new Properties();
			param.put("coa", listcoa);
			
			balances = sqlC.executeQueryList("sql-coa", "getCoaBalance", param, CoaBalance.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return balances;
		
	}

	
	public static ArrayList<CoaBalance> saveBalance(ArrayList<GL> listGl) throws GeneralException {
		
		ArrayList<CoaBalance> balances = new ArrayList<CoaBalance>();
		ArrayList<CoaBalance> curBal = null;
		
		// 1. get current coa balance
		HashMap<String, CoaBalance> curBalMap = new HashMap<String, CoaBalance>();
		curBal = getBalanceFromGL(listGl);
		for (CoaBalance coaBalance : curBal) {
			curBalMap.put(coaBalance.getCoaId(), coaBalance);
		}
		
		// 2. update balance
		for (GL gl : listGl) {
			
			CoaBalance cb = curBalMap.get(gl.getCoa());
			if (cb != null) {
				
				cb.setLastUpdate(gl.getGlDate());
				cb.setPrevBalBase(cb.getCurMonthBalBase());
				cb.setPrevBalCcy(cb.getCurMonthBalCcy());
				
				
			} else {
				
			}
		}
		
		return balances;
	}
	
}
