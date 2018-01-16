package net.cygnus.report.journal.web.action;

import id.co.nds.webapp.JFXActionSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.cygnus.report.journal.JournalFcd;
import net.cygnus.report.journal.SearchFilter;

public class JournalInvalAction extends JFXActionSupport {

	private HashMap paramReports;
	private List listItems = new ArrayList();
	private SearchFilter filter;
	
	public String doDownloadReport() throws Exception {

		String[] s = new String[]{};
		filter.setTransaction(s);
		
		paramReports = JournalFcd.getReportParam(filter);
		listItems = JournalFcd.getListData(filter);
		
		return SUCCESS;
		
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		filter = new SearchFilter();
		return filter;
	}	
	public SearchFilter getFilter() {
		return filter;
	}
	public void setFilter(SearchFilter filter) {
		this.filter = filter;
	}
	public HashMap getParamReports() {
		return paramReports;
	}
	public void setParamReports(HashMap paramReports) {
		this.paramReports = paramReports;
	}
	public List getListItems() {
		return listItems;
	}
	public void setListItems(List listItems) {
		this.listItems = listItems;
	}
	
}
