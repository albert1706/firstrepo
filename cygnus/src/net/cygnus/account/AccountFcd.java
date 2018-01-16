package net.cygnus.account;

import id.co.nds.AppConstants;
import id.co.nds.GlobalFcd;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.util.Util;
import id.co.nds.webapp.GeneralConstants;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import net.cygnus.coa.Coa;
import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;

import org.apache.commons.lang3.StringUtils;

public class AccountFcd implements Serializable {

	private static final long serialVersionUID = -6482381852041142085L;
	
	public static Account getDetailAcc(Account searchAccount) throws GeneralException {
		ArrayList<Account> listAcc = null;
		Account acc = null;
		
		listAcc = getListAcc(searchAccount);
		if (listAcc != null && listAcc.size() >0 ) {
			acc = listAcc.get(0);
		}
		
		return acc;
	}
	
	
	public static ArrayList<Account> getListAccFromSegment(String segmentList) throws GeneralException {
		ArrayList<Account> listAcc = null;
		DBConnection dbconn = null;
		SQLStandard sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			criterias.put("1=1", " AND acc_no IN (select acc_no from "+Coa.TABLENAME+" where segment_id IN ('"+segmentList+"'))");
			
			listAcc = sql.executeQueryList(Account.TABLENAME, null, criterias, null, Account.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return listAcc;
	}
	
	public static ArrayList<Account> getListAcc(Account searchAccount) throws GeneralException {
		ArrayList<Account> listAcc = null;
		DBConnection dbconn = null;
		SQLStandard sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			criterias = CriteriasDB.buildCriteriaFromObj(searchAccount, null, MODE.EQUAL);
			
			listAcc = sql.executeQueryList(Account.TABLENAME, null, criterias, null, Account.class);
			
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
		
		return listAcc;
	}
	
	public static void unregisterAccount(Long id, DBConnection dbconn, BasicUser user) throws SQLException, GeneralException  {
		SQLStandard sql = new SQLStandard (dbconn);
		
		Account acc = new Account();
		acc.setId(id);
		acc.setRecStatus(GeneralConstants.REC_STATUS_NON);
		
		Properties criterias = new Properties();
		criterias.put("id", "=" + id);
		
		sql.executeUpdate(Account.TABLENAME, acc, criterias);
	}
	
	public static Account registerAccount(Account acc, ACCLEVEL lvl, DBConnection dbconn, BasicUser user) throws Exception {
		
		acc = AccCommonFcd.getAccMatrix(acc, lvl);
		
		Account account = acc;
		
		// prepare object
		account.setLevel(lvl.name());
		account.setAccNo(getAccNo(acc));
		account.setAccName(getAccName(acc));
		account.setAccNoHeading(getHeadingAccNo(acc));
		account.setOwner("ALL");
		account.setCreateBy(user.getUserId());
		account.setCreateDate(new Timestamp(System.currentTimeMillis()));
		account.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);

		SQLStandard sql = new SQLStandard(dbconn);
		Properties cri = CriteriasDB.buildCriteriaFromObj(account, "id", MODE.EQUAL);
		String criteria = "1=1" + String.valueOf(cri.get("1=1"));
		if (GlobalFcd.isDataExist(criteria, Account.TABLENAME, dbconn)) {
			sql.executeUpdate(Account.TABLENAME, account, cri);
		} else {
			sql.executeInsert(Account.TABLENAME, account);			
		}
		
		return account;
		
	}

	public static String getAccName(AccCommon acc) {
		String accName = "";
		
		if (Util.isNotEmpty(acc.getAccLv4Id())) {
			accName = acc.getAccLv4Name();
		} else if (Util.isNotEmpty(acc.getAccLv3Id())) {
			accName = acc.getAccLv3Name();
		} else if (Util.isNotEmpty(acc.getAccLv2Id())) {
			accName = acc.getAccLv2Name();
		}
		
		return accName; 
	}

	public static String getHeadingAccNo(AccCommon acc) {
		String accNoHead = null;
		StringBuilder accNoHeadBuilder = new StringBuilder();
		String separator = AppConstants.DEFAULT_ACC_SEPARATOR;
		Integer padding = 5;
		
		if (Util.isNotEmpty(acc.getAccLv4Id()) && !acc.getAccLv4Id().equals(AppConstants.DEFAULT_ACC_HEADING)) {
			accNoHeadBuilder.append(acc.getAccLv1Id()).append(separator)
							.append(acc.getAccLv2Id()).append(separator)
							.append(acc.getAccLv3Id()).append(separator)
							.append(AppConstants.DEFAULT_ACC_HEADING);
		} else if(Util.isNotEmpty(acc.getAccLv4Id()) && acc.getAccLv4Id().equals(AppConstants.DEFAULT_ACC_HEADING)) {
			accNoHeadBuilder.append(acc.getAccLv1Id()).append(separator)
							.append(acc.getAccLv2Id()).append(separator)
							.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
							.append(AppConstants.DEFAULT_ACC_HEADING);
		} else {
			if (Util.isNotEmpty(acc.getAccLv3Id()) && !acc.getAccLv3Id().equals(AppConstants.DEFAULT_ACC_HEADING)) {
				accNoHeadBuilder.append(acc.getAccLv1Id()).append(separator)
								.append(acc.getAccLv2Id()).append(separator)
								.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
								.append(AppConstants.DEFAULT_ACC_HEADING);
			} else if(Util.isNotEmpty(acc.getAccLv3Id()) && acc.getAccLv3Id().equals(AppConstants.DEFAULT_ACC_HEADING)) {
				accNoHeadBuilder.append(acc.getAccLv1Id()).append(separator)
								.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
								.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
								.append(AppConstants.DEFAULT_ACC_HEADING);
			} else {
				if (Util.isNotEmpty(acc.getAccLv2Id()) && !acc.getAccLv2Id().equals(AppConstants.DEFAULT_ACC_HEADING)) {
					accNoHeadBuilder.append(acc.getAccLv1Id()).append(separator)
									.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
									.append(AppConstants.DEFAULT_ACC_HEADING).append(separator)
									.append(AppConstants.DEFAULT_ACC_HEADING);
				} else {
					accNoHeadBuilder.append(" ");
				}
			}
		}
		
		accNoHead = accNoHeadBuilder.toString();
		return accNoHead;
	}
	
	public static String getAccNo(AccCommon acc) {
		String accNo = null;
		StringBuilder accNoBuilder = new StringBuilder();
		String separator = ".";
		Integer padding = 2;
		
		// the structure will be : 
		// L1.L2.L3.L4 => x.xx.xx.xx
		
		if (Util.isNotEmpty(acc.getAccLv1Id())) accNoBuilder.append(acc.getAccLv1Id()).append(separator);
		else accNoBuilder.append("X").append(separator);
		
		if (Util.isNotEmpty(acc.getAccLv2Id())) {
			String lv2Id = StringUtils.leftPad(acc.getAccLv2Id(), padding);
			accNoBuilder.append(lv2Id).append(separator);
		} else accNoBuilder.append(AppConstants.DEFAULT_ACC_HEADING).append(separator);
		
		if (Util.isNotEmpty(acc.getAccLv3Id())) {
			String lv3Id = StringUtils.leftPad(acc.getAccLv3Id(), padding);
			accNoBuilder.append(lv3Id).append(separator);
		} else accNoBuilder.append(AppConstants.DEFAULT_ACC_HEADING).append(separator);
		
		if (Util.isNotEmpty(acc.getAccLv4Id())) {
			String lv4Id = StringUtils.leftPad(acc.getAccLv4Id(), padding);
			accNoBuilder.append(lv4Id);
		} else accNoBuilder.append(AppConstants.DEFAULT_ACC_HEADING);
		
		accNo = accNoBuilder.toString();
		return accNo;
	}
	
}
