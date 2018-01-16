package net.cygnus.eod;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;

import java.sql.SQLException;
import java.util.ArrayList;

public class EodFcd {

	public static EOD getLatestEod() throws GeneralException {
		
		ArrayList l = new ArrayList();
		DBConnection dbconn = null;
		SQLStandard sql = null;
		EOD out = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			ArrayList orderRules = new ArrayList();
			orderRules.add("posting_date desc");
			l = sql.executeQueryList("ps_eod", null, null, orderRules, EOD.class);
			
			if (l != null && l.size() > 0) {
				out = (EOD) l.get(0);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(),e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return out;
		
	}
	
}
