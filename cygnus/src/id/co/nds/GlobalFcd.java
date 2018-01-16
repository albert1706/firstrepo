package id.co.nds;

import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqaccess.SQLAccess;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;

public class GlobalFcd {

	
	public static boolean isDataExist(String criterias, String tablename) throws Exception {
		DBConnection dbconn = null;
		Boolean exists = false;
		
		try {
			dbconn = new DBConnection();
			exists = isDataExist(criterias, tablename, dbconn);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		return exists;
	}
	
	
	public static boolean isDataExist(String criterias, String tablename, DBConnection dbconn) throws Exception {
		
		Boolean exist = false;
		SQLAccess sql = new SQLAccess(dbconn);
		int count = sql.countRows(tablename, criterias);
		exist = count>0;
		
		return exist;
	}
}
