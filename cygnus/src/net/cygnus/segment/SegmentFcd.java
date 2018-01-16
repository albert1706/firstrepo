package net.cygnus.segment;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import net.cygnus.branch.Branch;

public class SegmentFcd {

	public static ArrayList<Segment> getListSegments(Segment criteria) throws GeneralException {
		
		ArrayList result = null;
		DBConnection dbconn = null;
		SQLStandard sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			Properties criterias = new Properties();
			
			criterias = CriteriasDB.buildCriteriaFromObj(criteria, null, MODE.EQUAL);
			
			result = sql.executeQueryList(Segment.TABLENAME, null, criterias, null, Segment.class);
			
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
		
		return result;
		
	}
	
}
