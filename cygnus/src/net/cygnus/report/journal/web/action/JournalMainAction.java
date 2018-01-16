package net.cygnus.report.journal.web.action;

import id.co.nds.parameter.Parameter;
import id.co.nds.parameter.ParameterFcd;
import id.co.nds.webapp.JFXActionSupport;

import java.util.ArrayList;
import java.util.List;

public class JournalMainAction extends JFXActionSupport {

	List<Parameter> listTrx = new ArrayList<Parameter>();
	
	public String prepareSearch() throws Exception {
		listTrx = ParameterFcd.getListParameter(Parameter.KEY_PARAM_TRX_TYPE);
		
		return SUCCESS;
	}
	
	public List<Parameter> getListTrx() {
		return listTrx;
	}
	public void setListTrx(List<Parameter> listTrx) {
		this.listTrx = listTrx;
	}
	
	
}
