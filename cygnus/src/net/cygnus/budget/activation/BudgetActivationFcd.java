package net.cygnus.budget.activation;

import id.co.nds.beans.Activity;
import id.co.nds.beans.BasicUser;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqaccess.SQLAccess;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.log.LogFcd;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import net.cygnus.budget.plan.BudgetPeriod;
import net.cygnus.budget.plan.BudgetPlanFcd;
import net.cygnus.coa.Coa;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class BudgetActivationFcd {

	public static void ActivateBudget(BudgetPeriod bp, BasicUser actor) throws GeneralException {

		SQLStandard sql = null;
		DBConnection dbconn = null;
		Timestamp current = new Timestamp(System.currentTimeMillis());
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard (dbconn);
			
			dbconn.beginTransaction();
			
			bp.setActivateBy(actor.getUserId());
			bp.setActivateDate(current);
			
			// TODO 
			// 1. Check if today is after or the same day of start budget period
			LocalDate today = new LocalDate();
			LocalDate startPeriod = new LocalDate(bp.getPeriodStart().getTime());
			String sourcetable = "ps_budget_plan";
			String destTable = null;
			if (today.isAfter(startPeriod) || today.isEqual(startPeriod)) {
			// 2. if today is after or the same day of start budget period, then activate budget directly to master table
				destTable = "ms_budget_plan";
				bp.setStatus(BudgetPeriod.STATUS_ACTIVE);
			} else {
			// 3. if today is before start budget period, then put the budget plan into WAIT table
				destTable = "ps_budget_plan_wait";
				bp.setStatus(BudgetPeriod.STATUS_WAITING);
			}
			BudgetPlanFcd.createBudgetPeriod(bp, actor, dbconn);
			Activate(sourcetable, destTable, dbconn);
			// 4. delete source table content
			Properties criterias = new Properties();
			criterias.put("1", "=1");
			sql.executeDelete(sourcetable, criterias);
			
			// activity log
			Activity log = new Activity();
			log.setObjectId("AKTIFASI BUDGET");
			log.setDescription("Peng-aktifan Budget. periode awal : " + startPeriod);
			log.setLogBy(actor.getUserId());
			log.setLogDate(current);
			log.setModuleId(actor.getOnModule());
			LogFcd.writeActivityLog(log, dbconn);
			
			dbconn.commitTransaction();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(dbconn != null) dbconn.rollback();
			throw new GeneralException(e.getMessage(), e);
		} catch (GeneralException e) {
			if(dbconn != null) dbconn.rollback();
			throw e;
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
	}
	
	public static void Activate(String tableSource, String destTable) throws GeneralException {
		
		DBConnection dbconn = null;
		
		try {
			dbconn = new DBConnection();
			Activate(tableSource, destTable, dbconn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
	}
	
	public static void Activate(String tableSource, String destTable, DBConnection dbconn) throws GeneralException {
		SQLCustomStatement sqlC = null;
		try {
			sqlC = new SQLCustomStatement(dbconn);
			
			Properties param = new Properties();
			param.put("tableSource", tableSource);
			param.put("destTable", destTable);
			
			sqlC.executeNonQuery("sql-budget", "activate", param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
		
	}
	
	public static BudgetActivation prepareActivation() throws GeneralException {
		
		// get budget activation
		BudgetActivation activation  = getRegisteredBudgetPlanSummary();
		
		// get budget period
		BudgetPeriod bp = BudgetPlanFcd.getBudgetPeriod(null);
		
		if (bp.getPeriodEnd() !=null) {
			DateTime dt = new DateTime(bp.getPeriodEnd().getTime());
			Integer year = dt.getYear();
			activation.setYearOfActivation(year);			
		}
		
		return activation;
	}
	
	public static BudgetActivation getRegisteredBudgetPlanSummary() throws GeneralException {
		BudgetActivation out = null;
		DBConnection dbconn = null;
		SQLCustomStatement sqlCustom = null;
		SQLAccess sql = null;
		
		try {
			dbconn = new DBConnection();
			sqlCustom = new SQLCustomStatement(dbconn);
			sql = new SQLAccess(dbconn);

			ArrayList<BudgetActivation> l1 = new ArrayList();
			l1 = sqlCustom.executeQueryList("sql-budget", "getSummaryPsBudgetPlan", null, BudgetActivation.class);
			if (l1 != null && l1.size() > 0) {
				out = l1.get(0);	
			}
			Integer totalCoa = sql.countRows(Coa.TABLENAME, "coa_type = '"+Coa.COA_TYPE_PL+"'");
			out.setTotalCoa(totalCoa);
			out.setTotalCoaNotSet(totalCoa - out.getTotalCoaSet());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(),e);
		} catch (GeneralException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(),e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(),e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return out;
	}
	
}
