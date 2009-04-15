package com.freqds.base.editorpart;

import java.sql.SQLException;
import java.util.Date;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;

import com.freqds.base.dialog.BaseCountryDialog;
import com.freqds.dbo.base.BaseCountryDAO;
import com.freqds.mapping.base.BaseCountry;

import cosco.vsagent.base.wizard.BasVslMsgWizard;
import cosco.vsagent.mapping.base.Basvslmsg;
import cosco.vsagent.system.EditorActionGroup;

public class BaseCountryEditorActionGroup extends EditorActionGroup{
	TableViewer tv;
	public BaseCountryEditorActionGroup(TableViewer tv) {
		super(tv);
		// TODO Auto-generated constructor stub
	}
	public void fireAddAction() {
		BaseCountryDialog dialog = new BaseCountryDialog(null);
		BaseCountry country = new BaseCountry();
		dialog.setCountry(country);
		if(dialog.open() == IDialogConstants.OK_ID){
			country = dialog.getCountry();
			Object params[] =new Object[4];
			params[0] = country.getCountry_code();
			params[1] = country.getCountry_cname();
			params[2] = country.getCountry_ename();
			params[3] = country.getInsert_usercode();
			//params[4] = new Date();
			String sql = "insert base_country(country_code,country_cname,country_ename,insert_usercode)values(?,?,?,?)";
			BaseCountryDAO dao = new BaseCountryDAO();
			try {
				dao.update(sql, params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void fireFirstAction(){
		tv = getTv();
		tv.setInput( new BaseCountryDAO().getBaseCountry(getQueryInfo()));
		refreshActionsState();
	}
	
	public void firePrevAction(){
		tv = getTv();
		getQueryInfo().currentPage =1;
		tv.setInput( new BaseCountryDAO().getBaseCountry(getQueryInfo()));
		refreshActionsState();
	}
	
	public void fireModifyAction() {
		IStructuredSelection sel  = (IStructuredSelection) tv.getSelection();
		BaseCountry country = (BaseCountry)sel.getFirstElement();
		
		if(country == null) return;

		BaseCountryDialog dialog = new BaseCountryDialog(null);
		dialog.setCountry(country);
		
		if(dialog.open() == IDialogConstants.OK_ID){
			country = dialog.getCountry();
			BaseCountryDAO dao = new BaseCountryDAO();
			Object params[] =new Object[3];
			try {
				/*
				private String act;
				private String memo;
				private String ;
				private Date ;
				*/
				params[0] = country.getCountry_cname();
				params[1] = country.getCountry_ename();
				params[2] = country.getCountry_code();
				
				dao.update("update base_country set country_cname =?,country_ename=? where country_code=?", params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dao.modifyBaseCountry(dialog.getCountry());
			tv.refresh(country);
		}
	}
}
