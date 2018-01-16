package net.cygnus.branch;

import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class BranchFcd {

	public static ArrayList<Branch> getListBranches(Branch branchCriterias) throws GeneralException {
		
		ArrayList result = null;
		DBConnection dbconn = null;
		SQLStandard sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			Properties criterias = new Properties();
			
			criterias = CriteriasDB.buildCriteriaFromObj(branchCriterias, null, MODE.EQUAL);
			
			result = sql.executeQueryList(Branch.TABLENAME, null, criterias, null, Branch.class);
			
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
