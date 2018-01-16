package net.cygnus.parameter.acc.level1.web.action;

import id.co.nds.session.UserSession;
import id.co.nds.standardization.IValAction;
import id.co.nds.webapp.JFXActionSupport;
import id.co.nds.webapp.session.UserSessionManager;
import net.cygnus.account.Account;
import net.cygnus.parameter.acc.AccCommon;
import net.cygnus.parameter.acc.AccCommonFcd;
import net.cygnus.parameter.acc.AccCommonFcd.ACCLEVEL;
import net.cygnus.parameter.acc.level1.AccLevel1Fcd;

public class AccountLevel1ValAction extends JFXActionSupport implements IValAction {

	private static final long serialVersionUID = -790811927392450108L;
	private Account acc = new Account();
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
//		if (HelperUtil.isEmpty(user.getUserId())) addActionError(getText("error.user.id.cannot.empty"));
	}
	
	@Override
	public String doCreate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		AccLevel1Fcd.create(acc, null, null, userSession.getUser(), ACCLEVEL.Lv1);
			
		addActionMessage("Akun baru ["+ acc.getAccLv1Name() +"] dengan ID "+acc.getAccLv1Id() +" telah berhasil di daftarkan");
		
		return SUCCESS;
	}
	
	@Override
	public String doUpdate() throws Exception {
		// TODO Auto-generated method stub
		
		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
		
		// CALL main process
		AccCommon oldAcc = (AccCommon) getFromModuleSession("currentAcc");
		AccCommonFcd.update(oldAcc, acc, null, null, userSession.getUser(), "Pembaharuan Data Akun ID "+acc.getAccLv1Id(), ACCLEVEL.Lv1);
		
		addActionMessage("Akun baru ["+ acc.getAccLv1Name() +"] dengan ID "+acc.getAccLv1Id() +" telah berhasil di perbaharui");
		
		return SUCCESS;
	}

//	@Override
//	public String doUpdate() throws Exception {
//		// TODO Auto-generated method stub
//		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
//		
//		// CALL main process
//		UserFcd.update(user, userSession.getUser(), "Update User");
//		
//		addActionMessage("User with ID "+user.getUserId()+" has been sucessfully UPDATED");
//		
//		return SUCCESS;
//	}
//
//	@Override
//	public String doDelete() throws Exception {
//		// TODO Auto-generated method stub
//		UserSession userSession = UserSessionManager.getFromSession(getRequest().getSession());
//		
//		try{
//		// CALL main process
//		UserFcd.delete(user.getUserId(), userSession.getUser());
//		
//		addActionMessage("User with ID "+user.getUserId()+" has been sucessfully DELETED");
//		
//		} catch (ValidationException e) {
//			addActionError(getText(e.getMessage()));
//			return INPUT;
//		} catch (Exception e) {
//			e.printStackTrace();
//			addActionError(e.getMessage());
//			return INPUT;
//		}
//		return SUCCESS;
//	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return acc;
	}

	@Override
	public String doDelete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
