package net.cygnus.budget.plan;

import id.co.nds.beans.Activity;
import id.co.nds.beans.BasicUser;
import id.co.nds.beans.JfxGrid;
import id.co.nds.core.exception.GeneralException;
import id.co.nds.dbaccess.connection.DBConnection;
import id.co.nds.dbaccess.sqlcustom.SQLCustomStatement;
import id.co.nds.dbaccess.sqlstandard.SQLStandard;
import id.co.nds.dbaccess.util.CriteriasDB;
import id.co.nds.dbaccess.util.MODE;
import id.co.nds.log.LogFcd;
import id.co.nds.webapp.GeneralConstants;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import net.cygnus.budget.Budget;

public class BudgetPlanFcd {

	public static synchronized Long getID() {
		return System.currentTimeMillis();
	}

	public static BudgetPeriod getBudgetPeriod(BudgetPeriod _bp) throws GeneralException {
		DBConnection dbconn = null;
		SQLStandard sql = null;
		BudgetPeriod bp = new BudgetPeriod();

		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = CriteriasDB.buildCriteriaFromObj(_bp, null, MODE.EQUAL);
			
			ArrayList<BudgetPeriod> ar = sql.executeQueryList("ms_budget_period", null, criterias, null, BudgetPeriod.class);
			if (ar != null && ar.size() > 0) {
				bp = ar.get(0);
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
		} finally {
			if (dbconn != null) dbconn.close();
		}
			
		return bp;
	}
	
	public static void deleteBudgetPeriod(BudgetPeriod _bp, DBConnection dbconn) throws GeneralException {
		SQLStandard sql = null;
		
		try {
			dbconn = new DBConnection();
			sql = new SQLStandard(dbconn);
			
			Properties criterias = CriteriasDB.buildCriteriaFromObj(_bp, null, MODE.EQUAL);
			sql.executeDelete("ms_budget_period", criterias);
			
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
		}
	}
	
	public static void createBudgetPeriod(BudgetPeriod bp, BasicUser actor, DBConnection dbconn) throws GeneralException {
		SQLStandard sql = null;
		
		try {
		
			Long budgetPeriodId = getID();
			Timestamp current = new Timestamp(System.currentTimeMillis());
			bp.setBudgetPeriodId(budgetPeriodId);
//			bp.setStatus(BudgetPeriod.STATUS_WAITING);			
			
			sql = new SQLStandard(dbconn);

			BudgetPeriod existing = new BudgetPeriod();
			existing.setStatus(BudgetPeriod.STATUS_WAITING);
			deleteBudgetPeriod(existing, dbconn);
			
			sql.executeInsert("ms_budget_period", bp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
		
		
	}
	
	public static ArrayList<BudgetPlanFrm> searchCoaForPsBudget(BudgetPlanFrm search, JfxGrid page) throws GeneralException {
		ArrayList<BudgetPlanFrm> listCoa = null;
		DBConnection dbconn = null;
		SQLCustomStatement sql = null;
		Properties criterias = new Properties();
		
		try {
			dbconn = new DBConnection();
			sql = new SQLCustomStatement(dbconn);
			
			criterias.put("criterias", String.valueOf(CriteriasDB.buildCriteriaFromObj(search, null, MODE.LIKE).get("1=1")));
			criterias.put("orderBy", " ORDER BY " + page.getSortIdx() + " " + page.getSortMode());
			listCoa = sql.executeQueryPagingList("sql-budget", "searchCoaForPsBudget", criterias, page, BudgetPlanFrm.class);
			
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
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
		return listCoa;
	}
	
	public static void registerBudget(Budget budget, BasicUser actor) throws GeneralException {
		
		DBConnection dbconn = null;
		
		try {
			dbconn = new DBConnection();
			
			registerBudget(budget, actor, dbconn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} finally {
			if (dbconn != null) dbconn.close();
		}
		
	}
	
	public static void registerBudget(Budget budget, BasicUser actor, DBConnection dbconn) throws GeneralException {
		try {
			SQLStandard sql = new SQLStandard(dbconn);

			Timestamp current = new Timestamp(System.currentTimeMillis());
			budget.setBudgetId(getID());
			budget.setCreateBy(actor.getUserId());
			budget.setCreateDate(current);
//			budget.setRecStatus(GeneralConstants.REC_STATUS_ACTIVE);
			
			Properties del = new Properties();
			del.put("coa_id", "='" + budget.getCoaId() + "'");
			sql.executeDelete(Budget.TABLE_PS_BUDGET, del);
			sql.executeInsert(Budget.TABLE_PS_BUDGET, budget);			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
}
