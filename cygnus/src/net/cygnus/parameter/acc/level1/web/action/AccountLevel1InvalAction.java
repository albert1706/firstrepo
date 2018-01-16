package net.cygnus.parameter.acc.level1.web.action;

import id.co.nds.standardization.IInvalAction;
import id.co.nds.webapp.JFXActionSupport;
import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;
import net.cygnus.parameter.acc.level1.AccLevel1Fcd;

public class AccountLevel1InvalAction extends JFXActionSupport implements IInvalAction {

	private AccCommon acc = null;
	
	@Override
	public Object getModel() {
		acc = new AccCommon();
		return acc;
	}
	
	@Override
	public String prepareCreate() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String prepareUpdate() throws Exception {
		
		acc = AccLevel1Fcd.getDetail(acc, ACCLEVEL.Lv1);
		
		// Cache for update process
		saveToModuleSession("currentAcc", acc);
		
		return SUCCESS;
	}


	public AccCommon getAcc() {
		return acc;
	}

	public void setAcc(AccCommon acc) {
		this.acc = acc;
	}

	
	
}
