package net.cygnus.report.journal;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.util.CriteriasDB;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class JournalFcd {
	
	public static HashMap getReportParam(SearchFilter searchFilter) {
		
		HashMap map = new HashMap();
		
		map.put("periodStart", searchFilter.getPeriodStart());
		if (searchFilter.getPeriodEnd() != null) {
			map.put("periodEnd", searchFilter.getPeriodEnd());;
		} else {
			map.put("periodEnd", new Timestamp(System.currentTimeMillis()));;
		}
			
				
		return map;
	}
	
	public static ArrayList getListData(SearchFilter searchFilter) throws GeneralException {
		
		ArrayList list = new ArrayList();
		DBConnection dbconn = null;
		SQLCustomStatement sqlC = null;
		String dateRangeCrit = "";
		String[] listTrx = searchFilter.getTransaction();
		StringBuilder bTrx = new StringBuilder();
		String xxx = "";
		
		try {
			dbconn = new DBConnection();
			sqlC = new SQLCustomStatement(dbconn);

			Properties p = new Properties();

			StringBuilder sb = new StringBuilder("1=1");

			// prepare criteria date range
			dateRangeCrit = CriteriasDB.crtSQLDateRangeHandle(searchFilter.getPeriodStart(), searchFilter.getPeriodEnd(), dbconn);
			if (dateRangeCrit.trim().length() > 0) {
				sb
				.append(" AND ")
				.append(" trx_date ")
				.append(dateRangeCrit);	
			}
			
			// prepare criteria list trx
			for (String trx : listTrx) {
				bTrx.append("'" + trx + "',");
			}
			xxx = bTrx.toString();
			if (xxx.endsWith(",")) xxx = xxx.substring(0, xxx.length() - 1);
			if (xxx.length() > 0) {
				sb
				.append(" AND ")
				.append(" trx_type IN (")
				.append(xxx)
				.append(")");
			}
			
			String crit = sb.toString().trim();
			if (crit.endsWith("AND")) crit = crit.substring(0, crit.length() - 4);
			p.setProperty("criterias", crit);
			
			list = sqlC.executeQueryList("sql-rpt", "journal", p, Journal.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return list;
		
	}
	
	
	
}
