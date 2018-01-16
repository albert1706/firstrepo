package net.cygnus.parameter.acc.level4;

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

public class AccLevel4Fcd extends AccCommonFcd {
	
	public static ArrayList<AccCommon> searchAcc(AccCommon searchAcc, JfxGrid page) throws GeneralException
	{
		ArrayList<AccCommon> rsl = null;
		
		DBConnection dbconn = null;
		SQLStandard sql;
		ArrayList fields = new ArrayList();
		String tableName = 	"ms_acc_lv4 four "+
							"inner join ms_acc_lv3 three on four.parent_id = three.id " +
							"inner join ms_acc_lv2 two on three.parent_id = two.id " + 
							"inner join ms_acc_lv1 one on two.parent_id = one.id and four.rec_status = '"+GeneralConstants.REC_STATUS_ACTIVE+"'";
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			if (page != null) {
				// called for table search
				fields.add("four.*");
				fields.add("acc_lv1_name");
				fields.add("acc_lv2_name");
				fields.add("acc_lv3_name");
				criterias = CriteriasDB.buildCriteriaFromObj(searchAcc, null, MODE.LIKE);
				ArrayList order = new ArrayList();
				order.add(page.getSortIdx() + " " + page.getSortMode());
				rsl = sql.executeQueryPaging(tableName, fields, criterias, order, page, AccCommon.class);
			} else {
				// called for combo list
				criterias.put("four.rec_status='", GeneralConstants.REC_STATUS_ACTIVE + "'");
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
	
	public static AccCommon getDetailWithHierarchy(AccCommon searchAcc) throws GeneralException {
		ArrayList<AccCommon> rsl = null;
		AccCommon out = null;
		
		DBConnection dbconn = null;
		SQLStandard sql;
		ArrayList fields = new ArrayList();
		String tableName = 	"ms_acc_lv4 four "+
							"inner join ms_acc_lv3 three on four.acc_lv1_id = three.acc_lv1_id and four.acc_lv2_id = three.acc_lv2_id and four.acc_lv3_id = three.acc_lv3_id " +
							"inner join ms_acc_lv2 two on three.acc_lv1_id = two.acc_lv1_id and three.acc_lv2_id = two.acc_lv2_id " + 
							"inner join ms_acc_lv1 one on three.acc_lv1_id = one.acc_lv1_id and three.rec_status = '"+GeneralConstants.REC_STATUS_ACTIVE+"'";
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = new Properties();
			criterias.put("four.rec_status='", GeneralConstants.REC_STATUS_ACTIVE + "'");
			criterias.put("four.id='", searchAcc.getId());
			rsl = sql.executeQueryList(tableName, null, criterias, null, AccCommon.class);
			if (rsl.size() > 0) {
				out = rsl.get(0);
			}
			
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
		
		return out;
	}
}
