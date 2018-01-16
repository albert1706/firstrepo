package net.cygnus.report.akun;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.util.CriteriasDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.cygnus.report.journal.Journal;
import net.cygnus.report.journal.SearchFilter;

public class RptAkunFcd {

	public static List getListData() {
		List out = new ArrayList();
		
		DBConnection dbconn = null;
		SQLCustomStatement sqlC = null;
		
		try {
			
			dbconn = new DBConnection();
			sqlC = new SQLCustomStatement(dbconn);
			
			out = sqlC.executeQueryList("sql-rpt", "akun", null, AkunRpt.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (dbconn !=null) dbconn.close();
		}
		
		return out;
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

			ResultSet rs = sqlC.executeQuery("sql-rpt", "akun", null);
			
			while(rs.next()) {
				String sVal4id = rs.getString("acc_lv4_id");
				String sVal3id = rs.getString("acc_lv3_id");
				String sVal2id = rs.getString("acc_lv2_id");
				String sVal1id = rs.getString("acc_lv1_id");
				
				String sVal4name = rs.getString("acc_lv4_name");
				String sVal3name = rs.getString("acc_lv3_name");
				String sVal2name = rs.getString("acc_lv2_name");
				String sVal1name = rs.getString("acc_lv1_name");
				
				
			}
			
		} catch (SQLException e) {
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
	
	class data {
		String key;
		List value;
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public List getValue() {
			return value;
		}
		public void setValue(List value) {
			this.value = value;
		}
		
	}
}
