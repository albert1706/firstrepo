package net.cygnus.report.akun.web.action;

import id.co.nds.webapp.JFXActionSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.cygnus.report.journal.JournalFcd;
import net.cygnus.report.journal.SearchFilter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class RptAkunInvalAction extends JFXActionSupport {

	private static final long serialVersionUID = 1L;
	private HashMap paramReports;
	private List listItems = new ArrayList();
	private SearchFilter filter;
	private InputStream is = null;
	
	public String doDownloadReport() throws Exception {

		paramReports = JournalFcd.getReportParam(filter);
		filter = new SearchFilter();
		String[] s = new String[]{};
		filter.setTransaction(s);
//		listItems = JournalFcd.getListData(filter);
//		listItems = RptAkunFcd.getListData();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String thePath = getRequest().getSession().getServletContext().getRealPath("/WEB-INF/classes/net/cygnus/report/akun/report/rpt_akun.jasper");
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new File(thePath));
		JasperPrint print = JasperFillManager.fillReport(jasperReport, paramReports, getDataSource());
		JasperExportManager.exportReportToPdfStream(print, out);
		is = new ByteArrayInputStream(out.toByteArray());
		
		return SUCCESS;
		
	}

	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public JRDataSource getDataSource() {
		return new JREmptyDataSource();
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
