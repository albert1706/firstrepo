package net.cygnus.parameter.acc.level1;

import id.co.nds.beans.JfxGrid;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.webapp.GeneralConstants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;

public class AccLevel1Fcd extends AccCommonFcd {

	public static ArrayList<AccCommon> searchAcc(AccCommon searchAcc, JfxGrid page) throws GeneralException
	{
		ArrayList<AccCommon> rsl = null;
		
		DBConnection dbconn = null;
		SQLStandard sql;
		String tableName = "ms_acc_lv1";
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			if (page != null) {
				// called for table search
				criterias = CriteriasDB.buildCriteriaFromObj(searchAcc, null, MODE.LIKE);
				ArrayList order = new ArrayList();
				order.add(page.getSortIdx() + " " + page.getSortMode());
				rsl = sql.executeQueryPaging(tableName, null, criterias, order, page, AccCommon.class);
			} else {
				// called for combo list
				criterias.put("rec_status='", GeneralConstants.REC_STATUS_ACTIVE + "'");
				rsl = sql.executeQueryList(tableName, null, criterias, null, AccCommon.class);
			}
			
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
	
	public static ArrayList<AccCommon> searchForCombo(AccCommon searchAcc) throws GeneralException
	{
		ArrayList<AccCommon> rsl = null;
		
		DBConnection dbconn = null;
		SQLStandard sql;
		String tableName = "ms_acc_lv1";
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			// called for combo list
			criterias.put("rec_status='", GeneralConstants.REC_STATUS_ACTIVE + "'");
			rsl = sql.executeQueryList(tableName, null, criterias, null, AccCommon.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return rsl;
	}
	
}
