package net.cygnus.parameter.acc;

import id.co.nds.AppConstants;
import id.co.nds.beans.Activity;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.AppException;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.core.exception.ValidationException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.log.LogFcd;
import id.co.nds.util.Util;
import id.co.nds.webapp.GeneralConstants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.cygnus.account.Account;
import net.cygnus.account.AccountFcd;
import net.cygnus.coa.CoaFcd;
import net.cygnus.parameter.acc.level1.AccLevel1Fcd;
import net.cygnus.parameter.acc.level2.AccLevel2Fcd;
import net.cygnus.parameter.acc.level3.AccLevel3Fcd;

public class AccCommonFcd {

	public enum ACCLEVEL {
		Lv1, 
		Lv2, 
		Lv3, 
		Lv4
	}
	
	public static synchronized Long getID() {
		return System.currentTimeMillis();
	}
	
	public static AccCommon getDetail(AccCommon acc, ACCLEVEL lvl) throws GeneralException
	{
		return getDetail(acc, lvl, GeneralConstants.REC_STATUS_ACTIVE);
	}
	
	public static AccCommon getDetail(AccCommon acc, ACCLEVEL lvl, String recStatus) throws GeneralException
	{
		ArrayList l = getListAcc(acc, lvl);
		if (l != null && l.size() > 0) return (AccCommon) l.get(0);

		return null;
	}
	
	public static ArrayList getListAcc(AccCommon searchAcc, ACCLEVEL lvl) throws GeneralException
	{
		ArrayList rsl = null;
		DBConnection dbconn = null;
		SQLStandard sql;

		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = CriteriasDB.buildCriteriaFromObj(searchAcc, null, MODE.EQUAL, AccCommon.class);
			rsl = sql.executeQueryList(getTableName(lvl), null, criterias, null, AccCommon.class);
			
		} catch (SQLException e) {
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
	
	public static void create(Account newAcc, String branchId, List<String> listSegment, BasicUser actor, ACCLEVEL lvl) throws ValidationException, Exception
	{
		DBConnection dbconn = null;
		SQLStandard sql = null;
		try {
			// prepare object
			newAcc = prepareObject(newAcc, lvl);
			
			// Validate iD
			AccCommon existingAcc = getDetail(newAcc, lvl);
			if (existingAcc != null && existingAcc.getRecStatus().equals(GeneralConstants.REC_STATUS_ACTIVE)) throw new ValidationException("error.acc.id.already.exist", new Exception(""));
			
			// BEGIN processing record			
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			dbconn.beginTransaction();
			
			Timestamp current = new Timestamp(System.currentTimeMillis());
			if (existingAcc == null) {
				// Generate ID
				newAcc.setId(getID());
				newAcc.setCreateBy(actor.getUserId());
				newAcc.setCreateDate(current);
				newAcc.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);
				sql.executeInsert(getTableName(lvl), newAcc, AccCommon.class);
			} else {
				newAcc.setId(existingAcc.getId());
				newAcc.setUpdateBy(actor.getUserId());
				newAcc.setUpdateDate(current);
				newAcc.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);
				Properties criterias = new Properties();
				criterias = CriteriasDB.buildCriteriaFromObj(newAcc, null, MODE.EQUAL);
				sql.executeUpdate(getTableName(lvl), newAcc, criterias, AccCommon.class);
			}
			
			// ACCOUNT & COA
			if (Util.isNotEmpty(newAcc.getHeading()) && newAcc.getHeading().equals(AppConstants.HEADING_NO)) {
				// register ACCOUNT
				
				Account account = AccountFcd.registerAccount(newAcc, lvl, dbconn, actor);
				// register COA
				CoaFcd.registerCOAFrmAccount(account, branchId, listSegment, dbconn);
			}
			
			// activity log
			Activity log = new Activity();
			log.setObjectId(newAcc.getAccLv1Id() + newAcc.getAccLv2Id() + newAcc.getAccLv3Id() + newAcc.getAccLv4Id());
			log.setDescription("CREATE new Account [" + lvl.toString() + "]");
			log.setLogBy(actor.getUserId());
			log.setLogDate(current);
			log.setModuleId(actor.getOnModule());
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
		} catch (Exception e) {
			if (dbconn != null) dbconn.rollback();
			throw e;
		} finally {
			if (dbconn != null) dbconn.close();
		}

	}
	
	public static void update(AccCommon oldAcc, Account newAcc, String branchId, List<String> listSegment, BasicUser actor, String logDescription, ACCLEVEL lvl) throws GeneralException
	{
		DBConnection dbconn = null;
		SQLStandard sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			// prepare object
			newAcc = prepareObject(newAcc, lvl);
			
			dbconn.beginTransaction();
			
			// set actor
			Timestamp current = new Timestamp(System.currentTimeMillis());
			newAcc.setUpdateBy(actor.getUserId());
			newAcc.setUpdateDate(current);
			
			criterias = CriteriasDB.buildCriteriaFromObj(oldAcc, "id", MODE.EQUAL);
			
			sql.executeUpdate(getTableName(lvl), newAcc, criterias, AccCommon.class);
			
			// ACCOUNT & COA
			if (Util.isNotEmpty(newAcc.getHeading()) && newAcc.getHeading().equals(AppConstants.HEADING_NO)) {
				// register ACCOUNT
				Account account = AccountFcd.registerAccount(newAcc, lvl, dbconn, actor);
				// register COA
				CoaFcd.registerCOAFrmAccount(account, branchId, listSegment, dbconn);
			}
			
			// activity log
			Activity log = new Activity();
			log.setObjectId(newAcc.getAccLv1Id() + newAcc.getAccLv2Id() + newAcc.getAccLv3Id() + newAcc.getAccLv4Id());
			log.setDescription(logDescription);
			log.setLogBy(actor.getUserId());
			log.setLogDate(current);
			log.setModuleId(actor.getOnModule());
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
			
		} catch (SQLException e) {
			if (dbconn != null) dbconn.rollback();
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			if (dbconn != null) dbconn.rollback();
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
	}

	public static void delete(Account newAcc, BasicUser actor, ACCLEVEL lvl) throws GeneralException, AppException, ValidationException
	{
		newAcc.setRecStatus(GeneralConstants.REC_STATUS_NON);
		DBConnection dbconn = null;
		SQLStandard sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			// prepare object
			newAcc = prepareObject(newAcc, lvl);
			
			dbconn.beginTransaction();
			
			// set actor
			Timestamp current = new Timestamp(System.currentTimeMillis());
			newAcc.setUpdateBy(actor.getUserId());
			newAcc.setUpdateDate(current);
			
			criterias = CriteriasDB.buildCriteriaFromObj(newAcc, "id", MODE.EQUAL);
			
			sql.executeUpdate(getTableName(lvl), newAcc, criterias, AccCommon.class);
			
			// activity log
			Activity log = new Activity();
			log.setObjectId(newAcc.getAccLv1Id() + newAcc.getAccLv2Id() + newAcc.getAccLv3Id() + newAcc.getAccLv4Id());
			log.setDescription("DELETE Akun");
			log.setLogBy(actor.getUserId());
			log.setLogDate(current);
			log.setModuleId(actor.getOnModule());
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
			
		} catch (SQLException e) {
			if (dbconn != null) dbconn.rollback();
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			if (dbconn != null) dbconn.rollback();
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
	}
	
	public static Account getAccMatrix(Account acc, ACCLEVEL lvl) throws GeneralException {
		
		// get parent account matrix
		AccCommon parentAcc = new AccCommon();
		if (lvl.equals(ACCLEVEL.Lv2)) {
			parentAcc.setId(acc.getParentId());
			parentAcc = AccLevel1Fcd.getDetail(parentAcc, ACCLEVEL.Lv1);
			acc.setAccLv1Id(parentAcc.getAccLv1Id());
			acc.setAccLv1Name(parentAcc.getAccLv1Name());
		} else if (lvl.equals(ACCLEVEL.Lv3)) {
			parentAcc.setId(acc.getParentId());
			parentAcc = AccLevel2Fcd.getDetailWithHierarchy(parentAcc);
			acc.setAccLv1Id(parentAcc.getAccLv1Id());
			acc.setAccLv1Name(parentAcc.getAccLv1Name());
			acc.setAccLv2Id(parentAcc.getAccLv2Id());
			acc.setAccLv2Name(parentAcc.getAccLv2Name());
		} else if (lvl.equals(ACCLEVEL.Lv4)) {
			parentAcc.setId(acc.getParentId());
			parentAcc = AccLevel3Fcd.getDetailWithHierarchy(parentAcc);
			acc.setAccLv1Id(parentAcc.getAccLv1Id());
			acc.setAccLv1Name(parentAcc.getAccLv1Name());
			acc.setAccLv2Id(parentAcc.getAccLv2Id());
			acc.setAccLv2Name(parentAcc.getAccLv2Name());
			acc.setAccLv3Id(parentAcc.getAccLv3Id());
			acc.setAccLv3Name(parentAcc.getAccLv3Name());
		}
		
		return acc;
	}
	
	final static Account prepareObject (Account account, ACCLEVEL mode) {
		Account acc = account;
		if (mode.equals(ACCLEVEL.Lv4)) {
			acc.setAccLv1Id(null);
			acc.setAccLv1Name(null);
			acc.setAccLv2Id(null);
			acc.setAccLv2Name(null);
			acc.setAccLv3Id(null);
			acc.setAccLv3Name(null);
//			acc.setParentId(Long.valueOf(acc.getAccLv3Id()));
		} else if (mode.equals(ACCLEVEL.Lv3)) {
			acc.setAccLv1Id(null);
			acc.setAccLv1Name(null);
			acc.setAccLv2Id(null);
			acc.setAccLv2Name(null);
//			acc.setParentId(Long.valueOf(acc.getAccLv2Id()));
		} else if (mode.equals(ACCLEVEL.Lv2)) {
			acc.setAccLv1Id(null);
			acc.setAccLv1Name(null);
//			acc.setParentId(Long.valueOf(acc.getAccLv1Id()));
		} else if (mode.equals(ACCLEVEL.Lv1)) {
			// do nothing
		}
		
		return acc;
	}
	
	final static String getCriteriaField(ACCLEVEL mode) {
		String criteriaField = "";
//		if (mode.equals(ACCLEVEL.Lv4)) {
//			criteriaField  = "accLv4Id,accLv4Name";
//		} else if (mode.equals(ACCLEVEL.Lv3)) {
//			criteriaField  = "accLv3Id,accLv3Name";
//		} else if (mode.equals(ACCLEVEL.Lv2)) {
//			criteriaField  = "accLv2Id,accLv3Name";
//		} else if (mode.equals(ACCLEVEL.Lv1)) {
//			criteriaField = "accLv1Id";
//		}
		criteriaField = "id,recStatus";
		
		return criteriaField;
	}
	
	final static String getTableName(ACCLEVEL Mode) {
		String tableName= "";
		
		if (Mode.equals(ACCLEVEL.Lv4)) {
			tableName = "MS_ACC_LV4";
		} else if (Mode.equals(ACCLEVEL.Lv3)) {
			tableName = "MS_ACC_LV3";
		} else if (Mode.equals(ACCLEVEL.Lv2)) {
			tableName = "MS_ACC_LV2";
		} else if (Mode.equals(ACCLEVEL.Lv1)) {
			tableName = "MS_ACC_LV1";
		}
		
		return tableName;
	}
	
}
